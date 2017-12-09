package org.myopenproject.pathfinding;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * A* (A-Star) is a sophisticated algorithm for graphs search. In some cases it
 * is more advantageous than the Breadth First Search algorithm. See more at
 * <a href="https://en.wikipedia.org/wiki/A*_search_algorithm">Wikipedia</a>.
 * 
 * @see BreadthFirst
 */
public class AStar implements Algorithm {
	// The path list where paths are stored
	private LinkedList<Node[]> paths;

	/**
	 * Constructs a AStar object. The constructed object can be reused as many times
	 * as necessary.
	 */
	public AStar() {
		paths = new LinkedList<>();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see project.ai.pathfinding.Algorithm#execute(project.ai.pathfinding.Node,
	 * project.ai.pathfinding.Node)
	 */
	@Override
	public Node[] execute(Node root, Node target) {
		// Initializes the path list with the root node
		paths.clear();
		paths.add(new Node[] { root });

		while (!paths.isEmpty()) {
			//Retrieves the shortest path on Paths and removes it from Paths
			Node currentPath[] = paths.poll();
			Node lastNode = currentPath[currentPath.length - 1];
			
			// if target was found
			if (target.equals(lastNode))
				return currentPath;

			// Generates lastNode's children and removes their repeated cycles
			Node children[] = AlgorithmUtil.removeCycles(lastNode.breed(), currentPath);
			heuristic(children, currentPath);
		}

		return null;
	}

	// Applies heuristic to each child found
	private void heuristic(Node children[], Node currentPath[]) {
		for (Node child : children) {
			ArrayList<Node[]> otherPaths = new ArrayList<>();
			// Creates a temporary path with child in tail
			Node newPath[] = AlgorithmUtil.append(currentPath, child);

			// Scans all paths ending with child and put it into otherPaths
			for (Node path[] : paths)
				if (path[path.length - 1].equals(child))
					otherPaths.add(path);

			// If there is no path ending with child then add newPath to the path list
			if (otherPaths.isEmpty()) {
				paths.add(newPath);
			} else {
				// Removes all paths ending with child from path list
				for (Node path[] : otherPaths)
					paths.remove(path);

				// Get the shortest path ending with child from otherPaths
				Node other[] = AlgorithmUtil.findShortest(otherPaths);

				// Add to path list either newPath or other. The shortest one is chosen
				if (newPath.length < other.length)
					paths.add(newPath);
				else
					paths.add(other);
			}
		}
	}
}
