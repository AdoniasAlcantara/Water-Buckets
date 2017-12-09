package org.myopenproject.pathfinding;

/**
 * <p>
 * A node containing a pair of {@linkplain Bucket buckets} (A, B). The node also
 * has a reference to its parent to support algorithms that use backtracking.
 * </p>
 * 
 * <p>
 * The identity of a node is determined by the quantity of gallons within
 * buckets A and B, i.e. two nodes are identical only if their buckets have the
 * same amount of gallons respectively. The total capacity is neglected in the
 * uniqueness of the node. For example, the following statements lead to a true
 * result:
 * </p>
 * 
 * <pre>
 * {@code 
 * Node node1 = new Node(new Bucket(3, 4), new Bucket(4, 2))
 * Node node2 = new Node(new Bucket(7, 4), new Bucket(2, 2))
 * node1.equals(node2) // Here returns true
 * }
 * </pre>
 * 
 * <p>
 * Notice how the buckets have the same gallons but do not have equal
 * capacities.
 * </p>
 * 
 * @see Bucket
 */
public class Node {
	private Bucket a;
	private Bucket b;
	private Node parent;
	private int depth;

	/**
	 * Builds a node with a pair of buckets and a parent node. The parent could be
	 * null.
	 * 
	 * @param a
	 *            a given bucket
	 * @param b
	 *            another bucket
	 * @param parent
	 *            the node's parent
	 */
	public Node(Bucket a, Bucket b, Node parent) {
		this.a = a;
		this.b = b;
		setParent(parent);
	}

	/**
	 * Builds a node with a pair of buckets.
	 * 
	 * @param a
	 *            a given bucket
	 * @param b
	 *            another bucket
	 */
	public Node(Bucket a, Bucket b) {
		this(a, b, null);
	}

	/**
	 * Retrieves the A bucket (1st constructor parameter).
	 * 
	 * @return the A bucket
	 * @see Node#Node(Bucket, Bucket) Node()
	 */
	public Bucket getA() {
		return a;
	}

	/**
	 * Retrieves the B bucket (2nd constructor parameter).
	 * 
	 * @return the B bucket
	 * @see Node#Node(Bucket, Bucket) Node()
	 */
	public Bucket getB() {
		return b;
	}

	/**
	 * Retrieves the parent node.
	 * 
	 * @return the parent node
	 */
	public Node getParent() {
		return parent;
	}

	/**
	 * Retrieves the node depth.
	 * 
	 * @return the node depth
	 */
	public int getDepth() {
		return depth;
	}

	/**
	 * Assigns the parent to this node.
	 * 
	 * @param parent
	 *            the parent node that will be assigned
	 */
	public void setParent(Node parent) {
		if (parent != null)
			depth = parent.depth + 1;
		else
			depth = 0;

		this.parent = parent;
	}

	/**
	 * Generates offspring based on bucket operations. The children generated
	 * contain a reference to the parent node.
	 * 
	 * @return a children array.
	 */
	public Node[] breed() {
		Node children[] = new Node[6];

		// Generating children by parent copy
		for (int i = 0; i < 6; i++) {
			Bucket newA = new Bucket(a.getCapacity(), a.getGallons());
			Bucket newB = new Bucket(b.getCapacity(), b.getGallons());
			children[i] = new Node(newA, newB, this);
		}

		// Performing all bucket operations
		children[0].getA().fill(); // Fills A
		children[1].getB().fill(); // FIlls B
		children[2].getA().empty(); // Empties A
		children[3].getB().empty(); // Empties B
		children[4].getA().pourTo(children[4].getB()); // Pours from A to B
		children[5].getB().pourTo(children[5].getA()); // Pours from B to A

		return children;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "(" + a.getGallons() + ", " + b.getGallons() + ")";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result *= prime + a.getGallons();
		result *= prime + b.getGallons();
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (obj == null)
			return false;

		if (!(obj instanceof Node))
			return false;

		Node other = (Node) obj;

		if (a.getGallons() != other.getA().getGallons())
			return false;

		if (b.getGallons() != other.getB().getGallons())
			return false;

		return true;
	}
}