package org.myopenproject.pathfinding;

/**
 * The Algorithm interface provides the
 * {@linkplain Algorithm#execute(Node, Node) execute()} method used to
 * encapsulate several different implementations of pathfinding algorithms.
 */
public interface Algorithm {
	/**
	 * Performs a path search and returns the corresponding path.
	 * 
	 * @param root
	 *            the starting node
	 * @param target
	 *            the target node
	 * @return either an array representing the path or null if the target was not
	 *         reached
	 */
	public Node[] execute(Node root, Node target);
}