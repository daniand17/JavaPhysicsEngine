package game_engine;

import interfaces.Vector;


public class VectorN implements Vector<VectorN> {

	// Fields
	public int size;
	public double[] eles;

	/**
	 * Initializes the vector with a value of 0 for all elements
	 */
	public VectorN(int N, double value) {
		size = N;
		// Default values of 0.0d for double arrays
		eles = new double[size];
		for (int i = 0; i < size; i++) {
			eles[i] = value;
		}
	}

	/**
	 * Used to initialize the vector with initial values.
	 * 
	 * @param d
	 * @param e
	 */
	public VectorN(int N, double[] inputArray) {
		size = N;
		eles = new double[size];
		for (int i = 0; i < size; i++) {
			eles[i] = inputArray[i];
		}
	}

	// /////* IMPLEMENTED METHODS OF THE VECTOR INTERFACE *///////

	@Override
	public String toString() {
		return "VectorN: (" + Utility.roundToThousandth(eles[0]) + ", ..., "
				+ Utility.roundToThousandth(eles[size - 1]) + ")";
	}

	@Override
	public VectorN scale(double scalar) {
		VectorN scaledVector = new VectorN(size, 0);
		for (int i = 0; i < size; i++) {
			scaledVector.eles[i] = scalar * this.eles[i];
		}
		return scaledVector;
	}

	@Override
	public VectorN add(VectorN otherVector) {
		if (this.size != otherVector.size) {
			System.out.println("Inconsistent VectorN dimensions");
			throw new InternalError();
		}
		VectorN newVector = new VectorN(size, 0);
		for (int i = 0; i < size; i++) {
			newVector.eles[i] = this.eles[i] + otherVector.eles[i];
		}
		return newVector;
	}

	@Override
	public VectorN sub(VectorN otherVector) {
		if (this.size != otherVector.size) {
			System.out.println("Inconsistent VectorN dimensions");
			throw new InternalError();
		}
		VectorN newVector = new VectorN(size, 0);
		for (int i = 0; i < size; i++) {
			newVector.eles[i] = this.eles[i] - otherVector.eles[i];
		}
		return newVector;
	}

	@Override
	public double dot(VectorN otherVector) {
		if (this.size != otherVector.size) {
			System.out.println("Inconsistent VectorN dimensions");
			throw new InternalError();
		}
		double dotProduct = 0;
		for (int i = 0; i < size; i++) {
			dotProduct += this.eles[i] * otherVector.eles[i];
		}
		return dotProduct;
	}

	@Override
	public double norm() {
		double magnitude = 0;
		for (int i = 0; i < size; i++) {
			magnitude += eles[i] * eles[i];
		}
		return Math.sqrt(magnitude);
	}

	@Override
	public double angle(VectorN otherVector) {
		double cosTheta = dot(otherVector) / (this.norm() * otherVector.norm());
		return Math.acos(cosTheta);
	}

	@Override
	public VectorN tangent() {
		double norm = this.norm();
		return this.scale(1 / norm);
	}
}
