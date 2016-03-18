package org.usfirst.frc.team2022.robot.commands.autonomous;

import org.usfirst.frc.team2022.robot.Robot;
import org.usfirst.frc.team2022.robot.subsystems.ShooterSubsystem;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CamFlipAutonomous extends Command {
	
	ShooterSubsystem shooterSubsystem;
	boolean finished = false;

    public CamFlipAutonomous() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	shooterSubsystem = Robot.shooterSubsystem;
    	requires(shooterSubsystem);
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	while (shooterSubsystem.getHingeAngle() > -95) {
    		
    		shooterSubsystem.hingeSpeed(-1);
    	}
    	
    	Timer.delay(0.5);
    	
    	while (shooterSubsystem.getHingeAngle() < 0){
    		shooterSubsystem.hingeSpeed(1);
    	}
    	
    	finished = true;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return finished;
    }

    // Called once after isFinished returns true
    protected void end() {
    	shooterSubsystem.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
