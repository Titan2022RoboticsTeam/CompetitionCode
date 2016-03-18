package org.usfirst.frc.team2022.robot.commands;

import org.usfirst.frc.team2022.robot.OI;
import org.usfirst.frc.team2022.robot.Robot;
import org.usfirst.frc.team2022.robot.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveCommand extends Command {
	DriveSubsystem driveSubsystem;
	OI oi;

    public DriveCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	oi = Robot.oi;
    	driveSubsystem = Robot.driveSubsystem;
    	requires(Robot.driveSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	
    	double speedModifier = 1;
		double right;
		double left;
		
		if(oi.xbox.GetRightY() > 0.1 || oi.xbox.GetRightY() < -0.1){
			right = oi.xbox.GetRightY();
		}
		else{
			right = 0;
		}
		if(oi.xbox.GetLeftY() > 0.1 || oi.xbox.GetLeftY() < -0.1){
			left = oi.xbox.GetLeftY();
		}
		else{
			left = 0;
		}
		
		if (oi.xbox.GetLeftTriggers() > 0.1) { // turtle
			speedModifier = .5; 
		} else if (oi.xbox.GetRightTriggers() > 0.1) { // turbo
			speedModifier = 1; 
		}
		
    	driveSubsystem.setRightSpeed(right*speedModifier);
    	driveSubsystem.setLeftSpeed(left*speedModifier);
    	
    	SmartDashboard.putNumber("Distance of right side", driveSubsystem.getRightEncoderDistance());
		SmartDashboard.putNumber("Distance of left side", driveSubsystem.getLeftEncoderDistance());
		SmartDashboard.putNumber("Gyro Angle", driveSubsystem.getGyroAngle());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	driveSubsystem.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
