package org.usfirst.frc.team2022.robot.subsystems;

import org.usfirst.frc.team2022.robot.ConstantsMap;
import org.usfirst.frc.team2022.robot.RobotMap;
import org.usfirst.frc.team2022.robot.commands.DriveCommand;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Gyro;

/**
 *
 */
public class DriveSubsystem extends Subsystem {
	
	private TalonSRX frontLeft, frontRight, rearLeft, rearRight;
	private double leftSpeed, rightSpeed;
	private boolean inverted;
	private long lastTime;
	
    
	//Encoders
	public static Encoder rightEncoder;
	public static Encoder leftEncoder;
	
	public PIDController rightController;
	public PIDOutputMotor pidOutputRight;
	
	public PIDController leftController;
	public PIDOutputMotor pidOutputLeft;
	
	public PIDController gyroController;
	public PIDGyroSource gyroSource;
	
	public Gyro gyro;
		
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public DriveSubsystem(){
		frontLeft = new TalonSRX(RobotMap.leftMotorFront);
		frontRight = new TalonSRX(RobotMap.rightMotorFront);
		rearLeft = new TalonSRX(RobotMap.leftMotorBack);
		rearRight = new TalonSRX(RobotMap.rightMotorBack);
		
		//Reverse motors
		frontLeft.setInverted(true);
		rearLeft.setInverted(true);
		frontRight.setInverted(true);
		rearRight.setInverted(true);
		
		//Initialize Encoders
		rightEncoder = new Encoder(RobotMap.rightEncoderPortA, RobotMap.rightEncoderPortB, false);
		leftEncoder = new Encoder(RobotMap.leftEncoderPortA, RobotMap.leftEncoderPortB, false);
		//Set Encoder distance per pulse
		rightEncoder.setDistancePerPulse(ConstantsMap.DRIVE_ENCODER_DIST_PER_TICK);
		leftEncoder.setDistancePerPulse(ConstantsMap.DRIVE_ENCODER_DIST_PER_TICK);	
		
		rightEncoder.setPIDSourceType(PIDSourceType.kDisplacement);
		leftEncoder.setPIDSourceType(PIDSourceType.kDisplacement);
		
		pidOutputRight = new PIDOutputMotor();
		rightController = new PIDController(ConstantsMap.pDrive, ConstantsMap.iDrive, ConstantsMap.dDrive, ConstantsMap.fDrive, rightEncoder, pidOutputRight);

		pidOutputLeft = new PIDOutputMotor();
		leftController = new PIDController(ConstantsMap.pDrive, ConstantsMap.iDrive, ConstantsMap.dDrive, ConstantsMap.fDrive, leftEncoder, pidOutputLeft);
		
		gyro = new AnalogGyro(RobotMap.gyroPort);
		gyroSource = new PIDGyroSource();
		gyroController = new PIDController(ConstantsMap.pDrive, ConstantsMap.iDrive, ConstantsMap.dDrive, ConstantsMap.fDrive, gyroSource, pidOutputLeft);
				
		rightController.setOutputRange(-0.6, 0.6);
		leftController.setOutputRange(-0.6, 0.6);
		gyroController.setOutputRange(-1, 1);
		
		rightController.setAbsoluteTolerance(1);
		leftController.setAbsoluteTolerance(1);
		gyroController.setAbsoluteTolerance(1);
		
		inverted = false;
		lastTime = System.currentTimeMillis();
    
	}
	
	public void resetGyro(){
		gyro.reset();
	}
	
	public double getGyroAngle(){
		return gyro.getAngle();
	}

    public void initDefaultCommand() {
    	setDefaultCommand(new DriveCommand());

    }	
    
 // Speed Manipulation Methods-these are more fine grained
 	public double getLeftSpeed() {
 		return leftSpeed;
 	}

 	public double getRightSpeed() {
 		return rightSpeed;
 	}

 	public void setLeftSpeed(double ls) {
 		leftSpeed = ls;
 		frontLeft.set(ls);
 		rearLeft.set(ls);
 	}

 	public void setRightSpeed(double rs) {
 		rightSpeed = rs;
 		frontRight.set(rs);
 		rearRight.set(rs);
 	}

 	// Inversion
 	public boolean isInverted() {
 		return inverted;
 	}

 	public void toggleInversion() {
 		if (System.currentTimeMillis() > lastTime + 250) {
 			lastTime = System.currentTimeMillis();
 			inverted = !inverted;
 			leftSpeed *= -1;
 			rightSpeed *= -1;
 		}
 	}

	//Get Encoder Distances
	public double getRightEncoderDistance(){
//		System.out.println(rightEncoder.getDistance());
		return rightEncoder.getDistance();
	}
	
	public double getLeftEncoderDistance(){
//		System.out.println(leftEncoder.getDistance());
		return leftEncoder.getDistance();
	}
	
	public double getLeftEncoderRawValue(){
//		System.out.println(leftEncoder.get());
		return leftEncoder.get();
	}
	
	public double getRightEncoderRawValue(){
//		System.out.println(rightEncoder.get());
		return rightEncoder.get();
	}
	//Get Encoder Rates
	public double getRightEncoderRate(){
		return rightEncoder.getRate();
	}
	
	public double getLeftEncoderRate(){
		return leftEncoder.getRate();
	}
	
	public void resetEncoders(){
		rightEncoder.reset();
		leftEncoder.reset();
	}
	
	//PID Methods
	public void enableGyroPIDController() {
		gyro.reset();
		gyroController.setSetpoint(0);
		gyroController.enable();
	}
	
	public void enableRightPIDController(double distance){
		rightController.setSetpoint(distance);
		rightController.enable();
	}
	public void disablePIDControllers(){
		rightController.disable();
		leftController.disable();
	}
	
	public void enableLeftPIDController(double distance){
		leftController.setSetpoint(distance);
		leftController.enable();
	}
	
	public double getGyroPIDOutput() {
		return pidOutputLeft.getOutput();
	}
	
	public double getRightPIDOutput(){
		return pidOutputRight.getOutput();
	}
	
	public double getLeftPIDOuput(){
		return pidOutputLeft.getOutput();
	}

	//Is the robot within the absolute tolerance of the target distance
	public 	boolean rightPIDOnTarget(){
		return rightController.onTarget();
	}
	
	public boolean leftPIDOnTarget(){
		return leftController.onTarget();
	}

	// Forwards and Reverse Control for each side.
	public void stop() {
		frontRight.set(0);
		frontLeft.set(0);
		rearRight.set(0);
		rearLeft.set(0);
		rightSpeed = 0;
		leftSpeed = 0;
	}

}

