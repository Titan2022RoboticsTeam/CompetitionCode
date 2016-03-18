package org.usfirst.frc.team2022.robot.subsystems;

import org.usfirst.frc.team2022.robot.ConstantsMap;
import org.usfirst.frc.team2022.robot.RobotMap;
import org.usfirst.frc.team2022.robot.commands.DriveCommand;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Gyro;

/**
 *
 */
public class DriveSubsystem extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	TalonSRX leftback, rightback, rightfront, leftfront;
	Encoder leftEncoder, rightEncoder;
	Gyro gyro;
	
	public DriveSubsystem() {
		leftback = new TalonSRX(RobotMap.leftMotorBack);
		leftfront = new TalonSRX(RobotMap.leftMotorFront);
		rightback = new TalonSRX(RobotMap.rightMotorBack);
		rightfront = new TalonSRX(RobotMap.rightMotorFront);
		
		leftback.setInverted(true);
		leftfront.setInverted(true);
		rightback.setInverted(true);
		rightfront.setInverted(true);
		
		leftEncoder = new Encoder(RobotMap.leftEncoderPortA, RobotMap.leftEncoderPortB, false);
		rightEncoder = new Encoder(RobotMap.rightEncoderPortA, RobotMap.rightEncoderPortB, false);
		
		rightEncoder.setDistancePerPulse(ConstantsMap.DRIVE_ENCODER_DIST_PER_TICK);
		leftEncoder.setDistancePerPulse(ConstantsMap.DRIVE_ENCODER_DIST_PER_TICK);	
		
		gyro = new AnalogGyro(RobotMap.gyroPort);
		
	}
	
	public void resetGyro(){
		gyro.reset();
	}
	
	public double getGyroAngle(){
		return gyro.getAngle();
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new DriveCommand());
    }
    
    public void setRightSpeed(double speed){
    	rightback.set(speed);
    	rightfront.set(speed);
    }
    
    public void setLeftSpeed(double speed){
    	leftback.set(speed);
    	leftfront.set(speed);
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
    
    public void stop(){
    	leftfront.set(0);
    	leftback.set(0);
    	rightback.set(0);
    	rightfront.set(0);
    }
}

