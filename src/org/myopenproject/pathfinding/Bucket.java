package org.myopenproject.pathfinding;

/**
 * It represents a single bucket that contains a determined quantity of gallons
 * and maximum capacity. A pair of buckets could be used together with the
 * {@linkplain Node} class.
 * 
 * @see Node
 */
public class Bucket {
	private int capacity;
	private int gallons;

	/**
	 * Creates a bucket with a given capacity and gallons.
	 * 
	 * @param capacity
	 *            the total bucket capacity
	 * @param gallons
	 *            the gallons into the bucket
	 * @throws IllegalArgumentException
	 *             if capacity or gallons exceeded the limits
	 */
	public Bucket(int capacity, int gallons) {
		if (capacity <= 0)
			throw new IllegalArgumentException("Capacity should be greater than zero");

		if (gallons < 0 || gallons > capacity)
			throw new IllegalArgumentException("Gallons exceeded the limits");

		this.capacity = capacity;
		this.gallons = gallons;
	}

	/**
	 * Creates a empty bucket with a given capacity.
	 * 
	 * @param capacity
	 *            the total bucket capacity
	 */
	public Bucket(int capacity) {
		this(capacity, 0);
	}

	/**
	 * Retrieves the current gallons in the bucket.
	 * 
	 * @return the current gallons
	 */
	public int getGallons() {
		return gallons;
	}

	/**
	 * Retrieves total capacity of the bucket.
	 * 
	 * @return the bucket capacity
	 */
	public int getCapacity() {
		return capacity;
	}

	/**
	 * Fills the bucket to its capacity.
	 * 
	 * @see Bucket#empty() empty()
	 * @see Bucket#pourTo(Bucket) pourTo()
	 */
	public void fill() {
		gallons = capacity;
	}

	/**
	 * Empties the bucket.
	 * 
	 * @see Bucket#fill() fill()
	 * @see Bucket#pourTo(Bucket) pourTo()
	 */
	public void empty() {
		gallons = 0;
	}

	/**
	 * Pour the contents of this bucket to another
	 * 
	 * @param other
	 *            the other bucket
	 * @see Bucket#fill() fill()
	 * @see Bucket#empty() empty()
	 */
	public void pourTo(Bucket other) {
		int transfer = other.capacity - other.gallons;

		if (gallons >= transfer) {
			other.gallons = other.capacity;
			gallons -= transfer;
		} else {
			other.gallons += gallons;
			gallons = 0;
		}
	}
}