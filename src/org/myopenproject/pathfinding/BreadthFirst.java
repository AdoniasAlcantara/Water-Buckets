package org.myopenproject.pathfinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Breadth First Search (BFS) is a fairly simple search algorithm commonly used
 * in tree graphs. The BreadthFirst class provides an implementation for said
 * algorithm. See more at
 * <a href="https://en.wikipedia.org/wiki/Breadth-first_search">Wikipedia</a>.
 */
public class BreadthFirst implements Algorithm {
	private List<Node> discovered;
	private int index;

	/**
	 * Constructs a BreadthFirst object. The constructed object can be reused as
	 * many times as necessary.
	 */
	public BreadthFirst() {
		// The list where discovered nodes are stored
		discovered = new ArrayList<>();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see project.ai.pathfinding.Algorithm#execute(project.ai.pathfinding.Node,
	 * project.ai.pathfinding.Node)
	 */
	@Override
	public Node[] execute(Node root, Node target) {
		// Initializes the discovered list with root node
		index = 0;
		discovered.clear();
		discovered.add(root);

		/*
		 * The index slides through the discovered list until the target is found or
		 * reaches the end of the list.
		 */
		while (index < discovered.size()) {
			Node current = discovered.get(index++);

			// if target was found
			if (current.equals(target))
				return buildPath(current);
			
			Node[] children = current.breed();

			// Add new nodes to discovered list without repeating (cycles)
			for (Node child : children) {
				if (!discovered.contains(child))
					discovered.add(child);
			}
		}

		return null;
	}

	// Builds a path based on the last node found
	private Node[] buildPath(Node node) {
		ArrayList<Node> path = new ArrayList<>();

		// Backtracking
		do {
			path.add(node);
			node = node.getParent();
		} while (node != null);

		Collections.reverse(path);
		return path.toArray(new Node[0]);
	}
}