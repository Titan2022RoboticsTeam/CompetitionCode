package org.usfirst.frc.team2022.robot;

public class ConstantsMap {
	
	//Encoder Constants For Drive Train
	public static final double DRIVE_WHEEL_RADIUS = 4;
	public static final int DRIVE_PULSE_PER_ROTATION = 360;
	public static final double DRIVE_GEAR_RATIO = 50/24;
	public static final double DRIVE_ENCODER_PULSE_PER_ROT = DRIVE_PULSE_PER_ROTATION * DRIVE_GEAR_RATIO;
	public static final double DRIVE_ENCODER_DIST_PER_TICK = (Math.PI * DRIVE_WHEEL_RADIUS * 2) / DRIVE_ENCODER_PULSE_PER_ROT;
	public static final double DRIVE_ENCODER_MAX_PERIOD = .1;
	public static final double DRIVE_ENCODER_MIN_RATE = 10;
//	digi-key
	
	//PID Constants For Drive
	public static double pDrive = 0.4;
	public static double iDrive = 0.02;
	public static double dDrive = 0.6;
	public static double fDrive = 0.2;
	
	//Encoder Constants For Shooter
	public static final double SHOOTER_WHEEL_RADIUS = 2.25;
	public static final int SHOOTER_PULSE_PER_ROTATION = 1024;
	public static final double SHOOTER_GEAR_RATIO = 1/1;
	public static final double SHOOTER_ENCODER_PULSE_PER_ROT = SHOOTER_PULSE_PER_ROTATION * SHOOTER_GEAR_RATIO;
	public static final double SHOOTER_ENCODER_DIST_PER_TICK = (Math.PI * DRIVE_WHEEL_RADIUS * 2) / DRIVE_ENCODER_PULSE_PER_ROT;
	public static final double SHOOTER_ENCODER_MAX_PERIOD = .1;
	public static final double SHOOTER_ENCODER_MIN_RATE = 10;
//	Shooter wheel is ctr mag
//	Potentiometer is andymark
	
	//PID Constants For Shooter
	public static double pShooter = 0.4;
	public static double iShooter = 0.02;
	public static double dShooter = 0.2;
	public static double fShooter = 0.5;
	
	//Robot Dimensions
	public static final double ROBOT_WIDTH = 22;
	public static final double CIRCUMFERENCE = ROBOT_WIDTH*Math.PI;
	
	//Speeds
	public static final double HINGE_SPEED = 0.5;
	public static final double SHOOTER_SPEED = 284.64; //inches per second
	
	//Shooter Positions
	public static final double DOWN_POSITION_ANGLE = -10.5;
	public static final double FLAT_POSITION_ANGLE = 0;
	public static final double READY_POSITION_ANGLE = 65;
	public static final double UP_POSITION_ANGLE = 74.25;
	
	//Camera Constants
	public static final double CAMERA_WIDTH_PIXEL = 640;
	//The height of image in pixels
	public static final double CAMERA_HEIGHT_PIXEL = 480;
	//The field of view of camera
	public static final double CAMERA_FOV = 54;
	//The vertical distance of tower
	public static final double TOWER_HEIGHT = 45-14;
	// Width of the tape in inches
	public static final double ACTUAL_WIDTH = 20;
	//Height of goal in incheas
	public static final double ACTUAL_HEIGHT = 12;
	// Focal length of the camera
	public static final double FOCAL_LENGTH = 750.75;
	public static final double TARGET_DISTANCE_FROM_TOWER = 55.61;
	public static final double CAMERA_OFFSET_INCHES = 10.444;
	
	
}
