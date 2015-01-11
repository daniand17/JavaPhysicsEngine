package game_engine;

import graphics.GraphicsThread;

import java.awt.Rectangle;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import physics.Collider;
import physics.Quadtree;
import physics.Rigidbody2D;

public class ObjectManager {
	private static List<GameObject> startObjects = new CopyOnWriteArrayList<GameObject>();
	private static List<GameObject> allObjects = new CopyOnWriteArrayList<GameObject>();
	private static List<Rigidbody2D> rigidbodies = new CopyOnWriteArrayList<Rigidbody2D>();
	private static List<Collider> colliders = new CopyOnWriteArrayList<Collider>();

	private static Quadtree quadtree = new Quadtree(0, new Rectangle(GraphicsThread.SIZE.width,
			GraphicsThread.SIZE.height));

	public static synchronized List<GameObject> getAllObjects() {
		return allObjects;
	}

	public static synchronized List<Rigidbody2D> getPhysicsObjects() {
		return rigidbodies;
	}

	public static synchronized List<Collider> getColliderObjects() {
		return colliders;
	}

	/**
	 * Gets from the quadtree the objects nearby to the object specified in the
	 * parameter
	 * 
	 * @param objToCheck
	 *            the object to check collisions against
	 * @return the list of objects that might collide with the object specified
	 */
	public static List<Collider> getNearbyObjects(Collider coll) {

		return quadtree.retrieve(coll);
	}

	/**
	 * Instantiates the object provided in the first argument at the 2D location
	 * specified in the second argument
	 * 
	 * @param newObj
	 *            the entity to instantiate
	 * @param location
	 *            the Vector2 location to instantate the object at
	 * @return the instantiated object
	 */
	public static synchronized GameObject instantiate(GameObject newObj, Vector2 location) {
		if ( newObj != null ) {
			newObj.getTransform().setPosition(location);
			startObjects.add(newObj);
		}
		return newObj;
	}

	public static synchronized void sortObjectsByComponents() {

		for (int i = 0; i < ObjectManager.startObjects.size(); i++) {
			GameObject obj = ObjectManager.startObjects.remove(i);

			// Call the start function of the object
			obj.start();

			allObjects.add(obj);

			// Subject to physics updates if object has a rigidbody
			if ( obj.getRigidbody() != null )
				rigidbodies.add(obj.getRigidbody());

			// Add to the list of collidable objects if object has a
			// collider
			if ( obj.getCollider() != null )
				colliders.add(obj.getCollider());

		}

		// Insert all the colliders into the quadtree
		for (Collider col : colliders)
			quadtree.insert(col);

	}

	public static synchronized void clearQuadtreeAndResetColliders() {
		quadtree.clear();
		for (Collider obj : colliders)
			obj.setCollisionsResolved(false);

	}
}
