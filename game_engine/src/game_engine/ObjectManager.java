package game_engine;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ObjectManager {
	public static List<GameObject> startObjects = new CopyOnWriteArrayList<GameObject>();
	private static List<GameObject> allObjects = new CopyOnWriteArrayList<GameObject>();
	private static List<GameObject> physicsObjects = new CopyOnWriteArrayList<GameObject>();
	private static List<GameObject> colliderObjects = new CopyOnWriteArrayList<GameObject>();

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

	public static synchronized void initializeStartObjects() {

		for (int i = 0; i < ObjectManager.startObjects.size(); i++) {
			GameObject obj = ObjectManager.startObjects.remove(i);
			// Call the start function of the object
			obj.start();
			if ( obj instanceof GameObject ) {
				allObjects.add((GameObject) obj);

				if ( ((GameObject) obj).rigidbody != null )
					physicsObjects.add(obj);
			}
		}
	}
}
