package game_engine;

public interface rotation {
/*This is an interface describing the methods required for a rotation class. 
 * I don't know if we actually need an interface for a rotation class.
 */
	vector3D rotateX(vector3D vector, float theta);
	
	vector3D rotateY(vector3D vector, float theta);
	
	vector3D rotateZ(vector3D vector, float theta);
	
	vector3D complexRotate(vector3D vector, float thetaX, float thetaY, float thetaZ);
}
