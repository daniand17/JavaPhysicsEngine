package components;

import game_engine.Quaternion;
import game_engine.Vector2;
import interfaces_abstracts.GameEntity;

import java.util.LinkedList;
import java.util.List;

public class Transform {

	public Quaternion rotation;

	public GameEntity gameEntity;
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
		// Returns the child in case we want to use it for other things after instantiation
		return newChild;
	}

	public int getChildCount() {
		return children.size();
	}
}
