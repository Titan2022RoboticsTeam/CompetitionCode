package org.usfirst.frc.team2022.robot.commands.autonomous;

import org.usfirst.frc.team2022.robot.Robot;
import org.usfirst.frc.team2022.robot.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TurnGyroAutonomousCommand extends Command {
	
	DriveSubsystem driveSubsystem;
	double angle;
	boolean running = true;

    public TurnGyroAutonomousCommand(double angle) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveSubsystem);
    	driveSubsystem = Robot.driveSubsystem;
    	this.angle = angle;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	driveSubsystem.resetGyro();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(angle > 0){
    		while(angle > driveSubsystem.getGyroAngle()){
    			driveSubsystem.setLeftSpeed(0.4);
    			driveSubsystem.setRightSpeed(-0.4);
    		}
    	}
    	else{
    		while(angle < driveSubsystem.getGyroAngle()){
    			driveSubsystem.setLeftSpeed(-0.4);
    			driveSubsystem.setRightSpeed(0.4);
    		}
    	}
    	driveSubsystem.setRightSpeed(0);
    	driveSubsystem.setRightSpeed(0);
    	running = false;    	
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return !running;
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
