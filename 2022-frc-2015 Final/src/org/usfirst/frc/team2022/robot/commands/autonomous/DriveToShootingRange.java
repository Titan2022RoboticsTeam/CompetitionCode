package org.usfirst.frc.team2022.robot.commands.autonomous;

import org.usfirst.frc.team2022.robot.ConstantsMap;
import org.usfirst.frc.team2022.robot.Robot;
import org.usfirst.frc.team2022.robot.subsystems.CameraSubsystem;
import org.usfirst.frc.team2022.robot.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveToShootingRange extends Command {

	DriveSubsystem driveSubsystem;
	CameraSubsystem cameraSubsystem;
	boolean running = true;
	boolean shoot;
	public double distanceFromTower;
	public double distanceToMove;
	
    public DriveToShootingRange() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveSubsystem);
    	requires(Robot.cameraSubsystem);
    	driveSubsystem = Robot.driveSubsystem;
    	cameraSubsystem = Robot.cameraSubsystem;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	driveSubsystem.resetEncoders();
    	shoot = cameraSubsystem.getNetworkTableValues();
    	if(shoot){
    		distanceFromTower = cameraSubsystem.getDistance();
        	distanceToMove = distanceFromTower - ConstantsMap.TARGET_DISTANCE_FROM_TOWER;
    	}
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(shoot){
    		driveSubsystem.enableLeftPIDController(distanceToMove);
        	driveSubsystem.enableRightPIDController(distanceToMove);
        	while(driveSubsystem.rightPIDOnTarget() == false && driveSubsystem.leftPIDOnTarget() == false){
        		driveSubsystem.setRightSpeed(driveSubsystem.getRightPIDOutput());
        		driveSubsystem.setLeftSpeed(driveSubsystem.getLeftPIDOuput());
        	}
        	
        	long time = System.currentTimeMillis();
    		driveSubsystem.disablePIDControllers();
    		driveSubsystem.setRightSpeed(0);
    		driveSubsystem.setLeftSpeed(0);
        	while(System.currentTimeMillis() < time + 1000){
        		
        	}
        	driveSubsystem.resetEncoders();
       	}
    	
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
