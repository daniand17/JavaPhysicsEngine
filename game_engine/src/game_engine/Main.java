package game_engine;

public class Main {

	public static void main(String[] args) {

		// Declares and initializes a 2D vector with no values (0,0)
		Vector2D unInitVec = new Vector2D();
		// Initialize the vector with values (1,1)
		Vector2D initVec = new Vector2D(3f, 4f);

		// Use of a static method in an abstract class
		float dist = Vector.distance(unInitVec, initVec);

		// Opens a new debug log with a filename specified in the method--this
		// is useful if you want to route specific error messages to specific
		// files.
		Debug log1 = new Debug();
		Debug log2 = new Debug();
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
		log1.closeLog();
		log2.closeLog();

		// Since the variables (aka fields) are public, we can access them
		// directly
		float meh = initVec.x + initVec.y;

		log3.LogF("" + meh);
		log3.closeLog();

		// Note: don't make things public if you don't want people to be doing
		// this.

	}

}
