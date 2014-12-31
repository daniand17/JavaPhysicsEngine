package game_engine;

import interfaces_abstracts.CanUpdate;
import interfaces_abstracts.GameEntity;
import interfaces_abstracts.PhysicsEntity;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ObjectManager {

	private static List<GameEntity> objects = new CopyOnWriteArrayList<GameEntity>();
	private static List<PhysicsEntity> physicsObjects = new CopyOnWriteArrayList<PhysicsEntity>();

	public static List<CanUpdate> startObjects = new CopyOnWriteArrayList<CanUpdate>();

	public static synchronized List<GameEntity> getObjects() {
		return objects;
	}

	public static synchronized List<PhysicsEntity> getPhysicsObjects() {
		return physicsObjects;
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
	public static synchronized GameEntity instantiate(GameEntity newObj, Vector2 location) {
		if ( newObj != null ) {
			newObj.transform.position = location;
			startObjects.add(newObj);
		}
		return newObj;
	}

	public static synchronized void initializeStartObjects() {

		for (int i = 0 ; i < ObjectManager.startObjects.size() ; i++) {
			CanUpdate obj = ObjectManager.startObjects.remove(i);
			// Call the start function of the object
			obj.start();
			if ( obj instanceof GameEntity ) {
				objects.add((GameEntity) obj);

				if ( ((GameEntity) obj).rigidbody != null )
					physicsObjects.add((PhysicsEntity) obj);
			}
		}
	}
}
