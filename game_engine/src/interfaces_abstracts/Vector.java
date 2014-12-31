package interfaces_abstracts;

public interface Vector {

	/**
	 * This function returns a vector scaled by the scalar. This does not change
	 * the vector itself, so in order to actually scale the vector, use the
	 * return value as the new vector.
	 * 
	 * @param scalar
	 *            the amount to scale the vector by
	 * @return the scaled vector
	 */
	public Vector scale(double scalar);
	
}
