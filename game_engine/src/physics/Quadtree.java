package physics;

import game_engine.GameObject;
import game_engine.Vector2;

import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.List;

public class Quadtree {

	// How many objects a node will hold before it splits
	private final int MAX_OBJECTS = 10;
	// Deepest level the node will go
	private final int MAX_LEVELS = 5;
	// The current level node, 0 being the top
	private int level;
	// The list of objects in this quadtree
	private List<GameObject> objects;
	// The retrieved list
	private List<GameObject> retrieveList;
	// The bounds of this quadtree
	private Rectangle bounds;
	// The quadrants or branches of this quadtree
	private Quadtree[] nodes;

	/**
	 * Constructor
	 * 
	 * @param pLevel
	 * @param pBounds
	 */
	public Quadtree(int pLevel, Rectangle pBounds) {
		// sets the layer of this quadtree to the new layer
		level = pLevel;
		// the vector list of objects in this quadtree
		objects = new LinkedList<GameObject>();
		retrieveList = new LinkedList<GameObject>();
		// the rectangle defining this quadtree
		bounds = pBounds;
		nodes = new Quadtree[4];
	}

	public void clear() {
		objects.clear();
		for (int i = 0; i < nodes.length; i++) {
			if ( nodes[i] != null ) {
				nodes[i].clear();
				nodes[i] = null;
			}
		}
	}

	/**
	 * Splits the node into 4 subnodes
	 * 
	 */
	public void split() {
		int subWidth = (int) (bounds.getWidth() / 2);
		int subHeight = (int) (bounds.getHeight() / 2);
		int x = (int) bounds.getX();
		int y = (int) bounds.getY();
		// Creates four new quadtrees in this quadtree
		nodes[0] = new Quadtree(level + 1, new Rectangle(x + subWidth, y, subWidth, subHeight));
		nodes[1] = new Quadtree(level + 1, new Rectangle(x, y, subWidth, subHeight));
		nodes[2] = new Quadtree(level + 1, new Rectangle(x, y + subHeight, subWidth, subHeight));
		nodes[3] = new Quadtree(level + 1, new Rectangle(x + subWidth, y + subHeight, subWidth,
				subHeight));
	}

	/**
	 * Determines which node the object belongs to. -1 means object cannot
	 * completely fit within a child node and is part of the parent node.
	 * 
	 * @param ent
	 * @return
	 */
	private int getIndex(GameObject ent) {
		// TODO eventually use collider.getPositionInWorldSpace() when it is
		// implemented
		Vector2 objLocation = ent.getCollider().positionInWorldSpace();

		int index = -1;
		double verticalMidpoint = bounds.getX() + (bounds.getWidth() / 2);
		double horizontalMidpoint = bounds.getY() + (bounds.getHeight() / 2);
		// Object can completely fit within the top quadrants
		// TODO not sure if this is completely right with the
		// getBounds().height...will only know once we are doing collisions
		boolean topQuadrant = (objLocation.y < horizontalMidpoint && objLocation.y
				+ ent.getCollider().getBoundedArea().getBounds().height < horizontalMidpoint);
		// Object can completely fit within the bottom quadrants
		boolean bottomQuadrant = (objLocation.y > horizontalMidpoint);
		// Object can completely fit within the left quadrants
		// TODO not sure if this is completely right with the
		// getBounds().width...will only know once we are doing collisions
		if ( ent.getCollider().positionInWorldSpace().x < verticalMidpoint
				&& ent.getCollider().positionInWorldSpace().y
						+ ent.getCollider().getBoundedArea().getBounds().width < verticalMidpoint ) {
			if ( topQuadrant ) {
				index = 1;
			}
			else if ( bottomQuadrant ) {
				index = 2;
			}
		}
		// Object can completely fit within the right quadrants
		else if ( objLocation.x > verticalMidpoint ) {
			if ( topQuadrant ) {
				index = 0;
			}
			else if ( bottomQuadrant ) {
				index = 3;
			}
		}
		return index;
	}

	public void insert(GameObject objectToInsert) {
		if ( nodes[0] != null ) {
			int index = getIndex(objectToInsert);

			if ( index != -1 ) {
				nodes[index].insert(objectToInsert);
				return;
			}
		}
		objects.add(objectToInsert);

		if ( objects.size() > MAX_OBJECTS && level < MAX_LEVELS ) {
			if ( nodes[0] == null ) {
				split();
			}
			for (int i = 0; i < objects.size(); i++) {
				int index = getIndex(objects.get(i));
				if ( index != -1 ) {
					nodes[index].insert(objects.remove(i));
				}
			}
		}
	}

	public List<GameObject> retrieve(GameObject objToCheck) {
		retrieveList.clear();
		int index = getIndex(objToCheck);
		if ( index != -1 && nodes[0] != null ) {
			retrieveList = nodes[index].retrieve(objToCheck);
		}
		retrieveList.addAll(objects);
		return retrieveList;
	}
}
