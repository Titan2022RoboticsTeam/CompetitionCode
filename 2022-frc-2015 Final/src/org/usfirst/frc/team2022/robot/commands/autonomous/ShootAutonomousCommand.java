package org.usfirst.frc.team2022.robot.commands.autonomous;

import org.usfirst.frc.team2022.robot.Robot;
import org.usfirst.frc.team2022.robot.subsystems.Intakes;
import org.usfirst.frc.team2022.robot.subsystems.ShooterPositions;
import org.usfirst.frc.team2022.robot.subsystems.ShooterSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */

public class ShootAutonomousCommand extends Command {
	
	ShooterSubsystem shooterSubsystem;
	boolean finished = false;

    public ShootAutonomousCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.shooterSubsystem);
    	shooterSubsystem = Robot.shooterSubsystem;
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	shooterSubsystem.setShooterAngle(ShooterPositions.shoot);
    	shooterSubsystem.setIntake(Intakes.out);
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
