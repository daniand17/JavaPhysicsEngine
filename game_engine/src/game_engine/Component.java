package game_engine;

/**
 * This class serves as a base class for all components that a GameObject could
 * have attached to it. This is used to be able to reference any component that
 * a GameObject could have attached, from any component on the object itself.
 * 
 * @author andrew
 *
 */
public abstract class Component {

	public GameObject gameObject;
	public Transform transform;
	
	void initializeComponentReferences(GameObject gameObj, Transform trans) {
		gameObject = gameObj;
		transform = trans;
	}

}
