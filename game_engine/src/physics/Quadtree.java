package physics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.List;

public class Quadtree {

	// How many objects a node will hold before it splits
	private final int MAX_OBJECTS = 8;
	// Deepest level the node will go
	private final int MAX_LEVELS = 5;
	// The current level node, 0 being the top
	private int level;
	// The list of objects in this quadtree
	private List<Collider> objects;
	// The retrieved list
	private List<Collider> retrieveList;
	// The bounds of this quadtree
	private Rectangle bounds;
	// The quadrants or branches of this quadtree
	private Quadtree[] nodes = null;

	public void renderNode(Graphics2D g2d) {
		g2d.setColor(Color.red);
		g2d.draw(this.bounds);

		g2d.setFont(new Font("Serif", Font.PLAIN, 24));

		g2d.setColor(Color.white);
		g2d.drawString("" + objects.size(), (int) bounds.getCenterX(), (int) bounds.getCenterY());

		if ( nodes != null )
			for (Quadtree node : nodes)
				if ( node != null )
					node.renderNode(g2d);
	}

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
		objects = new LinkedList<Collider>();
		retrieveList = new LinkedList<Collider>();
		// the rectangle defining this quadtree
		bounds = pBounds;
		nodes = new Quadtree[4];
	}

	public void clear() {
		objects.clear();
		if ( nodes != null )
			for (int i = 0; i < nodes.length; i++) {
				if ( nodes[i] != null )
					nodes[i].clear();
				nodes[i] = null;
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
	 * @param col
	 * @return
	 */
	private int getIndex(Collider col) {
		// The midpoints of the bounding box of this node
		double horizontalMidpoint = bounds.getCenterX();
		double verticalMidpoint = bounds.getCenterY();

		Rectangle colBounds = col.getBoundedArea().getBounds();

		// Object can completely fit within the top 2 quadrants
		boolean topHalf = colBounds.y + colBounds.height < verticalMidpoint;
		// Object can completely fit within the bottom quadrants
		boolean bottomHalf = colBounds.y > verticalMidpoint;
		// Object can completely fit within the left 2 quadrants
		boolean leftHalf = colBounds.x + colBounds.width < horizontalMidpoint;
		// Object can completely fit within the right 2 quadrants
		boolean rightHalf = colBounds.x > horizontalMidpoint;

		// The assigned quadrant
		int index = -1;

		if ( topHalf ) {
			if ( rightHalf )
				index = 0;
			else if ( leftHalf )
				index = 1;
		}
		else if ( bottomHalf ) {
			if ( leftHalf )
				index = 2;
			else if ( rightHalf )
				index = 3;
		}
		return index;
	}

	public void insert(Collider objectToInsert) {

		int index = getIndex(objectToInsert);
		// If this isn't the last child
		if ( nodes[0] != null ) {
			// If this object can fit completely into one of the quadrants
			if ( index != -1 ) {
				nodes[index].insert(objectToInsert);
				return;
			}
			// Add to this node since it doesn't fit in the children nodes
		}
		objects.add(objectToInsert);
		// If this is the last child
		// Add to this quadrant if there is room
		if ( objects.size() > MAX_OBJECTS && level < MAX_LEVELS ) {
			if ( nodes[0] == null )
				split();

			for (int i = 0; i < objects.size(); i++) {
				index = getIndex(objects.get(i));
				if ( index != -1 ) {
					nodes[index].insert(objects.remove(i));
					i--;
				}
			}
		}
	}

	public List<Collider> retrieve(Collider objToCheck) {
		retrieveList.clear();

		if ( nodes == null )
			return objects;

		int index = getIndex(objToCheck);
		if ( index != -1 && nodes[index] != null )
			retrieveList = nodes[index].retrieve(objToCheck);

		retrieveList.addAll(objects);

		return retrieveList;
	}
}
