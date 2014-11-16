package game_engine;

public class Main {

	public static void main(String[] args) {

		// Declares and initializes a 2D vector with no values (0,0)
		Vector2D unInitVec = new Vector2D();
		// Initialize the vector with values (1,1)
		Vector2D initVec = new Vector2D(3f, 4f);

		// Use of a static method in an abstract class
		float dist = Vector.distance(unInitVec, initVec);

		System.out.println("Distance: " + dist);

		// Use of a static method in an abstract class
		float mag = Vector.magnitude(initVec);
		System.out.println("Magnitude before scale: " + mag);

		// Use of a method in an inheriting class that was promised to use by the abstract class
		// that requires it. Each scale method must be implemented in the Vector2D, Vector3D, etc
		// class. Interfaces follow a similar scheme in that they make a "contract" with the
		// implementing class.
		initVec.scale(5f);

		mag = Vector.magnitude(initVec);
		// It works!
		System.out.println("Magnitude before scale: " + mag);

		// Since the variables (aka fields) are public, we can access them directly
		float meh = initVec.x + initVec.y;

		// Note: don't make things public if you don't want people to be doing this.

	}

}
