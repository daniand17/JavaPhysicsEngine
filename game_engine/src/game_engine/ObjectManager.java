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
			newObj.transform.position = location;
			startObjects.add(newObj);
		}
		return newObj;
	}

	public static synchronized void sortObjectsByComponents() {

		for (int i = 0; i < ObjectManager.startObjects.size(); i++) {
			GameObject obj = ObjectManager.startObjects.remove(i);
			// Initialize the references for the game object so transform,
			// gameobject etc can get referenced from any component

			obj.initializeComponentReferences();
			// Call the start function of the object
			obj.start();

			allObjects.add((GameObject) obj);

			// Do physics based parsing
			if ( obj.rigidbody != null )
				physicsObjects.add(obj);

			// Do collision based parsing
			if ( obj.collider != null ) {
				colliderObjects.add(obj);

				// TODO Insert object into quadtree (not used yet)
				quadtree.insert(obj);
			}
		}
	}
}
