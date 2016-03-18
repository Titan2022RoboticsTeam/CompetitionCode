package org.usfirst.frc.team2022.robot.commands.autonomous;

import org.usfirst.frc.team2022.robot.Robot;
import org.usfirst.frc.team2022.robot.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveStraightAutonomousCommand extends Command {
	
	// names DriveSubsystem object
	DriveSubsystem driveSubsystem;
	// names a boolean variable that will be used to determine
	// whether the robot should be in autonomous mode.
	boolean running = true;
	private double distance;

    public DriveStraightAutonomousCommand(double distance) {
    	// Initializes driveSubsystem
    	driveSubsystem = Robot.driveSubsystem;
        // Use requires() here to declare subsystem dependencies
        requires(driveSubsystem); 
        this.distance = distance;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    	// Makes sure the encoders are set to 0 before autonomous starts.
    	driveSubsystem.resetEncoders();
    	
    }

 // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	// Enables PIDs so that the robot only drives as far as the
    	// specified distance 'distance'.
    	driveSubsystem.enableGyroPIDController();
    	
    	// Sets the speed of the robot based on the PIDs
		while(driveSubsystem.getRightEncoderDistance() < distance){
			
			driveSubsystem.setRightSpeed(.5);
			driveSubsystem.setLeftSpeed(driveSubsystem.getGyroPIDOutput());
			
    	}

    	
    	// Stops the robot
    	long time = System.currentTimeMillis();
		driveSubsystem.disablePIDControllers();
	    driveSubsystem.setRightSpeed(0);
	    driveSubsystem.setLeftSpeed(0);
	    
	    // Pauses the robot to ensure it is completely stopped.
    	while(System.currentTimeMillis() < time + 1000){
    		
    	}
    	
    	// Resets encoders so they are ready for the next Command
	    driveSubsystem.resetEncoders();
	    
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
