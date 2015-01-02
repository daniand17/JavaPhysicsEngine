package game_engine;

import java.util.LinkedList;
import java.util.List;

public class Transform {

	public double rotation;

	public GameObject gameObject;
	public Vector2 position;
	private List<Transform> children;

	public Transform() {
		this(new Vector2(0f, 0f));
	}

	public Transform(Vector2 pos) {
		position = pos;
		children = new LinkedList<Transform>();
	}

	public Transform addChild(Transform newChild) {
		// Adds the child to the list of children
		children.add(newChild);
		// Returns the child in case we want to use it for other things after
		// instantiation
		return newChild;
	}

	public int getChildCount() {
		return children.size();
	}
}
