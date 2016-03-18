package org.usfirst.frc.team2022.robot.subsystems;

import org.usfirst.frc.team2022.robot.RobotMap;
import org.usfirst.frc.team2022.robot.commands.DriveCommand;

import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveSubsystem extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	TalonSRX leftback, rightback, rightfront, leftfront;
	
	public DriveSubsystem() {
		leftback = new TalonSRX(RobotMap.leftMotorBack);
		leftfront = new TalonSRX(RobotMap.leftMotorFront);
		rightback = new TalonSRX(RobotMap.rightMotorBack);
		rightfront = new TalonSRX(RobotMap.rightMotorFront);
		
		leftback.setInverted(true);
		leftfront.setInverted(true);
		rightback.setInverted(true);
		rightfront.setInverted(true);

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
    
    public void stop(){
    	leftfront.set(0);
    	leftback.set(0);
    	rightback.set(0);
    	rightfront.set(0);
    }
}

