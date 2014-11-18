package game_engine;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ObjectManager {

	private static List<IEntity> objects = new CopyOnWriteArrayList<IEntity>();
	private static int numObjects = 0;

	public static synchronized List<IEntity> getObjects() {
		return objects;
	}

	/**
	 * Instantiates the object provided in the first argument at the 2D location specified in the
	 * second argument
	 * 
	 * @param newObj
	 *            the entity to instantiate
	 * @param location
	 *            the Vector2 location to instantate the object at
	 * @return the instantiated object
	 */
	public static synchronized IEntity instantiate(GameEntity newObj, Vector3 location) {
		if ( newObj != null ) {
			newObj.transform.position = location;
			objects.add(newObj);
			numObjects++;
		}
		return newObj;
	}

	public static int getObjectAmount() {
		return numObjects;
	}
}
