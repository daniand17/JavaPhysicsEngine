package game_engine;

public class Main {

	public static void main(String[] args) {

		// Declares and initializes a 2D vector with no values (0,0)
		Vector2 unInitVec = new Vector2();
		// Initialize the vector with values (1,1)
		Vector2 initVec = new Vector2(3f, 4f);

		// Use of a static method in an abstract class
		float dist = Vector.distance(unInitVec, initVec);

		// Generic debug logs
		Debug log1 = new Debug();
		Debug log2 = new Debug();
		// A debug log specified with a file name.
		Debug log3 = new Debug("out");

		log1.LogF("Distance: " + dist);

		// Use of a static method in an abstract class
		float mag = Vector.magnitude(initVec);
		log2.LogF("Magnitude before scale: " + mag);

		// Use of a method in an inheriting class that was promised to use by
		// the abstract class that requires it. Each scale method must be
		// implemented in the Vector2D, Vector3D, etc class. Interfaces follow a
		// similar scheme in that they make a "contract" with the implementing
		// class.
		initVec.scale(5f);

		mag = Vector.magnitude(initVec);
		// It works!
		log1.LogF("Magnitude before scale: " + mag);

		// Close the debug log once you're done with it
		log1.close();
		log2.close();

		// Since the variables (aka fields) are public, we can access them
		// directly
		float meh = initVec.x + initVec.y;

		log3.LogF("" + meh);
		log3.close();
	}
}
