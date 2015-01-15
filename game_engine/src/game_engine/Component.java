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

	protected String name = "Component";
	private GameObject gameObject;
	protected Transform transform;

	void initializeComponentReferences(GameObject gameObj, Transform trans) {
		this.gameObject = gameObj;
		this.transform = trans;
	}

	/**
	 * @return the gameObject
	 */
	public GameObject getGameObject() {
		return gameObject;
	}

	/**
	 * @return the transform
	 */
	public Transform getTransform() {
		return transform;
	}

	/**
	 * This is a protected-level method used to set the transform of a component
	 * to the attached game object. This method should not be used to
	 * arbitrarily change the transform of a component. Used only at
	 * initialization of the component when attached to a game object for the
	 * first time.
	 * 
	 * @param transform
	 */
	protected void setTransform(Transform transform) {
		this.transform = transform;
	}

	/**
	 * Set the GameObject that this component is attached to
	 * 
	 * @param gameObject
	 *            the GameObject to attach
	 */
	protected void setGameObject(GameObject gameObject) {
		this.gameObject = gameObject;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
}
