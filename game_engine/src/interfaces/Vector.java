package interfaces;

/** This is the interface used for all Vector-type classes. 
 * Only methods which are general enough for N-dimensional vectors are specified through 
 * this interface. Methods which are only applicable to lower-dimensional "geometric" vectors
 * (i.e. the cross product) are not specified in this interface.

 * @author Joe S.
 *
 * @param <T>
 * 			The implementing class, i.e. Vector2 or Vector3, etc.
 */

public interface Vector<T> {
	/**
	 * Returns the string representation of this vector for debugging purposes
	 */
	public String toString();
	
	/**
	 * Addition method. This operation is commutative.
	 * 
	 * @param otherVector
	 * 			Add this vector to the existing vector instance
	 * 
	 * @return  new Vector.
	 */

	public T add(T otherVector);

	/**
	 * Subtraction method. This operation is anti-commutative.
	 * 
	 * @param otherVector
	 * 			Subtract this vector FROM the existing vector instance
	 * 
	 * @return  new Vector
	 */
	
	public T sub(T otherVector);
	
	/**
	 * Dot product method. This operation is commutative. The dot product is defined as
	 * 
	 * A (dot) B = norm(A) * norm(B) * cos(theta) 
	 * 
	 * Where theta is the minimal angle between the two vectors. It is computed by summing the
	 * the products of each vector component. The dot product of two perpendicular vectors
	 * is 0.
	 * 
	 * @param otherVector
	 * 			Take the dot product of the existing vector instance WITH this vector.
	 * 
	 * @return  double scalar.
	 */

	public double dot(T otherVector);
	
	
	/**
	 * The norm or magnitude of a vector. Uses the standard Euclidean definition.
	 * 
	 * @param NONE
	 * 
	 * @return double scalar 
	 */
	
	public double norm();

	/**
	 * This function returns a vector scaled by the scalar. This does not change
	 * the vector itself, so in order to actually scale the vector, use the
	 * return value as the new vector.
	 * 
	 * @param scalar
	 *            the amount to scale the vector by
	 * @return the scaled vector
	 */
	public T scale(double scalar);
	
	/**
	 * Determine the minimal angle between the existing vector and a supplied vector using
	 * the dot product. 
	 * 
	 * @param otherVector
	 * 
	 * @return double float the minimal angle in RADIANS
	 */
	public double angle(T otherVector);
	
	
	/**
	 * Determine the tangential unit vector.
	 * 
	 * @param NONE
	 * 
	 * @return new Vector the unit vector tangent to the existing vector.
	 */
	public T tangent();
	
	
}
