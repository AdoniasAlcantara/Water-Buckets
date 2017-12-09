package org.myopenproject.pathfinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Utilities class for pathfinding algorithms.
 */
public class AlgorithmUtil {
	/**
	 * Compares two arrays of Nodes and removes the buckets from {@code nodes} that are present on {@code visitedNodes}.
	 * 
	 * @param nodes
	 *            the array that non-unique nodes will be removed from
	 * @param visitedNodes
	 *            the elements that will be avoided in the output array
	 * @return a array without cycles
	 * 
	 * @see Node
	 */
	public static Node[] removeCycles(Node nodes[], Node visitedNodes[]) {
		ArrayList<Node> included = new ArrayList<>();
		List<Node> except = Arrays.asList(visitedNodes);

		for (Node node : nodes)
			if (!except.contains(node))
				included.add(node);

		return included.toArray(new Node[] {});
	}

	/**
	 * Generates a new array with the {@code newNode} appended to the end.
	 * 
	 * @param path
	 *            the source path
	 * @param newNode
	 *            the new node to be appended
	 * @return {@linkplain Node} array representing a path with the new node
	 *         appended
	 * @see Node
	 */
	public static Node[] append(Node path[], Node newNode) {
		Node newPath[] = Arrays.copyOf(path, path.length + 1);
		newPath[path.length] = newNode;
		return newPath;
	}

	/**
	 * Finds the shortest path from a path list. The shortest path is the one with
	 * the lowest length.
	 * 
	 * @param paths
	 *            the path list to be searched
	 * @return the shortest path
	 */
	public static Node[] findShortest(List<Node[]> paths) {
		Node shortest[] = paths.get(0);
		int i = 1;

		while (i < paths.size()) {
			Node node[];

			if ((node = paths.get(i++)).length < shortest.length)
				shortest = node;
		}

		return shortest;
	}

	/**
	 * Prints an array of nodes as a list of tuples
	 * @param path the path to print
	 */
	public static void printPath(Node[] path) {
		if (path == null) {
			System.out.println("The target has not been reached");
			return;
		}
		
		System.out.println("Path lenght: " + path.length);

		for (Node node : path)
			System.out.println(node);

		System.out.print('\n');
	}
}