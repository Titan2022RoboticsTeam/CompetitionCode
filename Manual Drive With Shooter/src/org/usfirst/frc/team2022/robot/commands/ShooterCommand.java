package org.usfirst.frc.team2022.robot.commands;

import org.usfirst.frc.team2022.robot.OI;
import org.usfirst.frc.team2022.robot.Robot;
import org.usfirst.frc.team2022.robot.subsystems.ShooterSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ShooterCommand extends Command {
	ShooterSubsystem shooterSubsystem;
	OI oi;
    public ShooterCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	shooterSubsystem = Robot.shooterSubsystem;
    	oi = Robot.oi;
    	requires(Robot.shooterSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	shooterSubsystem.setSpeed(oi.attack3.getY());
    	if(oi.attack3.getButton(3)){
    		shooterSubsystem.setSpeed(1);;
    	}
    	else if(oi.attack3.getButton(2)){
    		shooterSubsystem.setSpeed(-0.2);;
    	}
    	else{
    		shooterSubsystem.setSpeed(0);
    	}
    	
    	shooterSubsystem.setHingeSpeed(oi.attack3.getY());
    	
    	if(oi.attack3.getButton(4)){
    		shooterSubsystem.setServo(1);
    	}
    	else{
    		shooterSubsystem.setServo(0);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
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
