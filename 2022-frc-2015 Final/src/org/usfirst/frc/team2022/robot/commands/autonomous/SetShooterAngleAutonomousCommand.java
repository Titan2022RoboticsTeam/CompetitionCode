package org.usfirst.frc.team2022.robot.commands.autonomous;

import org.usfirst.frc.team2022.robot.Robot;
import org.usfirst.frc.team2022.robot.subsystems.ShooterPositions;
import org.usfirst.frc.team2022.robot.subsystems.ShooterSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetShooterAngleAutonomousCommand extends Command {

	ShooterSubsystem shooterSubsystem;
	ShooterPositions pos;
	boolean running = true;
	
    public SetShooterAngleAutonomousCommand(ShooterPositions pos) {
    	
        requires(Robot.shooterSubsystem);
        shooterSubsystem = Robot.shooterSubsystem;
        this.pos = pos;
        
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	shooterSubsystem.setShooterAngle(pos);
    	running = false;
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return !running;
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
