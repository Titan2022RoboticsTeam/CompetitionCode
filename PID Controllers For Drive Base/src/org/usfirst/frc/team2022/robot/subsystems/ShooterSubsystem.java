package org.usfirst.frc.team2022.robot.subsystems;

import org.usfirst.frc.team2022.robot.RobotMap;
import org.usfirst.frc.team2022.robot.commands.ShooterCommand;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ShooterSubsystem extends Subsystem {
	
	TalonSRX leftIntake, rightIntake;
	TalonSRX hinge;
	Servo kicker;
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new ShooterCommand());
    }
    
    public ShooterSubsystem(){
    	leftIntake = new TalonSRX(RobotMap.leftIntakeMotor);
    	rightIntake = new TalonSRX(RobotMap.rightIntakeMotor);
    	rightIntake.setInverted(true);
    	hinge = new TalonSRX(RobotMap.hingePort);
    	kicker = new Servo(RobotMap.kickerPort);
    }
    
    public void setSpeed(double speed){
    	leftIntake.set(speed);
    	rightIntake.set(speed);
    }
    
    public void setHingeSpeed(double speed){
    	hinge.set(speed);
    }
    
    public void setServo(double speed){
    	if(speed == 1){
    		kicker.set(1);
    	}
    	if(speed == 0){
    		kicker.set(0);
    	}
    }
    
    public void stop(){
    	leftIntake.set(0);
    	rightIntake.set(0);
    	hinge.set(0);
    }
    
}

