package game_engine;

import java.awt.Rectangle;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ObjectManager {
	public static List<GameObject> startObjects = new CopyOnWriteArrayList<GameObject>();
	private static List<GameObject> allObjects = new CopyOnWriteArrayList<GameObject>();
	private static List<GameObject> physicsObjects = new CopyOnWriteArrayList<GameObject>();
	private static List<GameObject> colliderObjects = new CopyOnWriteArrayList<GameObject>();

	private static Quadtree quadtree = new Quadtree(0, new Rectangle(Display.WIDTH, Display.HEIGHT));

	public static synchronized List<GameObject> getAllObjects() {
		return allObjects;
	}

	public static synchronized List<GameObject> getPhysicsObjects() {
		return physicsObjects;
	}

	public static synchronized List<GameObject> getColliderObjects() {
		return colliderObjects;
	}

	/**
	 * Gets from the quadtree the objects nearby to the object specified in the
	 * parameter
	 * 
	 * @param objToCheck
	 *            the object to check collisions against
	 * @return the list of objects that might collide with the object specified
	 */
	public static List<GameObject> getNearbyObjects(GameObject objToCheck) {
		return quadtree.retrieve(objToCheck);
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
			newObj.getTransform().position = location;
			startObjects.add(newObj);
		}
		return newObj;
	}

	public static synchronized void sortObjectsByComponents() {

		for (int i = 0; i < ObjectManager.startObjects.size(); i++) {
			GameObject obj = ObjectManager.startObjects.remove(i);

			// Call the start function of the object
			obj.start();

			// Initialize the references for the game object so transform,
			// gameobject etc can get referenced from any component
			obj.initializeComponentReferences();

			allObjects.add((GameObject) obj);

			// Subject to physics updates if object has a rigidbody
			if ( obj.getRigidbody() != null )
				physicsObjects.add(obj);

			// Add to the list of collidable objects if object has a collider
			if ( obj.getCollider() != null )
				colliderObjects.add(obj);

		}

		for (GameObject obj : colliderObjects)
			quadtree.insert(obj);

	}

	public static synchronized void clearQuadtreeAndResetColliders() {
		quadtree.clear();
		for (GameObject obj : colliderObjects)
			obj.getCollider().collisionsResolvedThisFrame = false;

	}
}
