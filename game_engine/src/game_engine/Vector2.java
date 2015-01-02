package game_engine;

import interfaces.Vector;


public class Vector2 implements Vector<Vector2> {

	// Fields
	public double x;
	public double y;

	/**
	 * Initializes the vector with a value of 0 for x and y.
	 */
	public Vector2() {
		x = 0;
		y = 0;
	}

	/**
	 * Used to initialize the vector with initial values.
	 * 
	 * @param d
	 * @param e
	 */
	public Vector2(double d, double e) {
		this.x = d;
		this.y = e;
	}

	//////// METHODS SPECIFIC TO VECTOR2 ////////
	/** Obtain the cross product of the calling object with another vector. Since this
	 * is for a two dimensional vector, the cross product is simply the out-of-plane length
	 * of the resultant vector. This number corresponds to the area of the parallelogram formed
	 * by the two vectors in the cross product operation. The cross product can also be
	 * defined as
	 * 
	 * A (cross) B = norm(A) * norm(B) * sin(theta)
	 * 
	 * Where theta is the minimal angle between the two vectors. The cross product of two 
	 * parallel vectors is 0.
	 * 
	 * @param Vector2 otherVector
	 * 			The operation is defined as "this" (cross) "otherVector"
	 * @return double 
	 * 			The length of the resultant out-of-plane vector
	 */
	public double cross(Vector2 otherVector) {
		return (this.x*otherVector.y - this.y*otherVector.x);
	}
	
	/**
	 * Rotation method. Rotates the vector about the in-plane axis in a COUNTERCLOCKWISE
	 * direction. 
	 * 
	 * @param double theta 
	 * 			the rotation angle in RADIANS
	 * 
	 * @return new Vector
	 */
	public Vector2 rotate(double theta) {
		return new Vector2((Math.cos(theta)*x + Math.sin(theta)*this.y),
				-Math.sin(theta)*x + Math.cos(theta)*y);
	}

	///////* IMPLEMENTED METHODS OF THE VECTOR INTERFACE *///////

	@Override
	public String toString() {
		return "Vector2: (" + Utility.roundToThousandth(x) + ", " + Utility.roundToThousandth(y) + ")";
	}
	
	@Override
	public Vector2 scale(double scalar) {
		return new Vector2(scalar*x, scalar*y);
	}

	@Override
	public Vector2 add(Vector2 otherVector) {
		return new Vector2(this.x + otherVector.x, this.y + otherVector.y);
	}
	
	@Override
	public Vector2 sub(Vector2 otherVector) {
		return new Vector2(this.x - otherVector.x, this.y - otherVector.y);
	}
	
	@Override
	public double dot(Vector2 otherVector) {
		return this.x*otherVector.x + this.y*otherVector.y;
	}

	@Override
	public double norm() {
		return Math.sqrt(dot(this));
	}

	@Override
	public double angle(Vector2 otherVector) {
		double cosTheta = dot(otherVector) / (this.norm()*otherVector.norm());
		return Math.acos(cosTheta);
	}

	@Override
	public Vector2 tangent() {
		double norm = this.norm();
		return new Vector2(this.x/norm, this.y/norm);
	}
	
	public static void test() {
		Vector2 zeroVec = new Vector2();
		Vector2 testVec = zeroVec.add(new Vector2(1/Math.sqrt(2), 1/Math.sqrt(2)));
		System.out.println("Initial test Vector (should be [.707, .707]: " + testVec);
		System.out.println("Subtraction test (should be [0, 0]): " + (testVec.sub(testVec)));
		System.out.println("Scaling test (should be [1, 1]): " + testVec.scale(Math.sqrt(2)));
		System.out.println("Dot Product Test (should be 1): " + testVec.dot(testVec));
		System.out.println("Norm test (should be 1): " + testVec.norm());
		System.out.println("Angle test (Should be 0.785): " + testVec.angle(new Vector2(1, 0)));
		System.out.println("Rotate test (should be [1, 0] " + testVec.rotate(Math.PI/4d));
	}
}
