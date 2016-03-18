package org.usfirst.frc.team2022.robot.subsystems;

import java.util.HashMap;

import org.usfirst.frc.team2022.robot.ConstantsMap;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

/**
 *
 */
public class CameraSubsystem extends Subsystem {
    
    NetworkTable table;
	//Hashmap with network table values from camera
	private HashMap<String, Double> networkTableValues= new HashMap();
	//Hashnmap of coordinates of rectangle from Roborealm
	private HashMap<String, Double> coordinates= new HashMap();	
	//Shoot?
	private boolean shoot = false;
	
	public double distanceFromTower = 0;
	
	/*p2 = top left corner of box
	 * p1 = top right
	 * p3 = bottom left
	 * p4 = bottom right
	 * origin is bottom left
	 */
	
	public CameraSubsystem() {
		// TODO Auto-generated constructor stub		
		networkTableValues.put("area", (double) 0);
		networkTableValues.put("centerX", (double) 0);
		networkTableValues.put("centerY", (double) 0);
		networkTableValues.put("width", (double) 0);
		networkTableValues.put("height", (double) 0);
		
		coordinates.put("p1x", (double) 0);
		coordinates.put("p1y", (double) 0);
		coordinates.put("p2x", (double) 0);
		coordinates.put("p2y", (double) 0);
		coordinates.put("p3x", (double) 0);
		coordinates.put("p3y", (double) 0);
		coordinates.put("p4x", (double) 0);
		coordinates.put("p4y", (double) 0);	
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public boolean getNetworkTableValues(){
    	
    	shoot = false;
    	
    	table = NetworkTable.getTable("SmartDashboard");
    	
    	
    	double[] coordinatesArray = table.getNumberArray("BFR_COORDINATES", new double[0]);
    	
    	if(coordinatesArray.length != 0){
    		coordinates.replace("p1x", coordinatesArray[0]);
    		coordinates.replace("p1y", coordinatesArray[1]);
    		coordinates.replace("p2x", coordinatesArray[2]);
    		coordinates.replace("p2y", coordinatesArray[3]);
    		coordinates.replace("p3x", coordinatesArray[4]);
    		coordinates.replace("p3y", coordinatesArray[5]);
    		coordinates.replace("p4x", coordinatesArray[6]);
    		coordinates.replace("p4y", coordinatesArray[7]);
    		shoot = true;
    	}
    	
    	/*p2 = top left corner of box
		 * p1 = top right
		 * p3 = bottom left
		 * p4 = bottom right
		 * origin is bottom left
		 */
    	
    	if(shoot){
    		//Get average width of rectangle
    		double widthOne = Math.sqrt(Math.pow(coordinates.get("p2x")-coordinates.get("p1x"), 2)+(Math.pow(coordinates.get("p2y")-coordinates.get("p1y"), 2)));
    	
    		double widthTwo = Math.sqrt(Math.pow(coordinates.get("p3x")-coordinates.get("p4x"), 2)+(Math.pow(coordinates.get("p3y")-coordinates.get("p4y"), 2)));

    		double averageWidth = (widthOne + widthTwo)/2;
    			
    		networkTableValues.replace("width", averageWidth);
    		
    		//Get average height of rectangle
    		
    		double heightOne = Math.sqrt(Math.pow(coordinates.get("p1x")-coordinates.get("p4x"), 2)+(Math.pow(coordinates.get("p1y")-coordinates.get("p4y"), 2)));

    		double heightTwo = Math.sqrt(Math.pow(coordinates.get("p2x")-coordinates.get("p3x"), 2)+(Math.pow(coordinates.get("p2y")-coordinates.get("p3y"), 2)));

    		double averageHeight = (heightOne + heightTwo)/2;
    		
    		networkTableValues.replace("height", averageHeight);
    	}
		
    	return shoot;
    	
    }
    
    public double getDistance(){

		getNetworkTableValues();
		
		//Get the percent of the frame the rectangle takes up in pixels Horizontally
		
		double percentWidth = networkTableValues.get("width")/ConstantsMap.CAMERA_WIDTH_PIXEL;
		
		//Get the width of the frame in inches
		
		double cameraWidthInches = ConstantsMap.ACTUAL_HEIGHT/percentWidth;		
		
		double diagonalDistance = (ConstantsMap.ACTUAL_WIDTH * ConstantsMap.FOCAL_LENGTH)/networkTableValues.get("width");
		
		
		double horizontalDistance = Math.sqrt((Math.pow(diagonalDistance, 2)-(Math.pow(ConstantsMap.TOWER_HEIGHT, 2))));
		
		return horizontalDistance;
    }
    
    public double getOffset(){
    	
    	getNetworkTableValues();
		
    	//Get the percent of the frame the rectangle takes up in pixels Horizontally
		
		double percentWidth = networkTableValues.get("width")/ConstantsMap.CAMERA_WIDTH_PIXEL;
		
		//Get the width of the frame in inches
		
		double cameraWidthInches = ConstantsMap.ACTUAL_HEIGHT/percentWidth;
	
		double diagonalDistance = (ConstantsMap.ACTUAL_WIDTH * ConstantsMap.FOCAL_LENGTH)/networkTableValues.get("width");
		
		double cameraOffset = Math.atan(Math.toRadians(ConstantsMap.CAMERA_OFFSET_INCHES/diagonalDistance));
		// Gets midpoint coordinates of the rectangle
		
		double midPointX = (coordinates.get("p2x")+coordinates.get("p4x")/2);
		double midPointY = (coordinates.get("p2y")+coordinates.get("p4y")/2);
		
		
		double horizontalDistance = Math.sqrt((Math.pow(diagonalDistance, 2)-(Math.pow(ConstantsMap.TOWER_HEIGHT, 2))));

		double distanceFromCenterInches = midPointX / ConstantsMap.CAMERA_WIDTH_PIXEL*cameraWidthInches;
		
		double offset = Math.atan((distanceFromCenterInches/diagonalDistance)*(180/Math.PI));
		
		offset = offset - cameraOffset;
		
		return offset;
	}
    
    
    
}

