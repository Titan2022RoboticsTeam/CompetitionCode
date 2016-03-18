package org.usfirst.frc.team2022.robot.commands;

import org.usfirst.frc.team2022.robot.OI;
import org.usfirst.frc.team2022.robot.Robot;
import org.usfirst.frc.team2022.robot.commands.autonomous.DriveToShootingRange;
import org.usfirst.frc.team2022.robot.commands.autonomous.ShootAutonomousCommand;
import org.usfirst.frc.team2022.robot.commands.autonomous.TurnCameraAutonomousCommand;
import org.usfirst.frc.team2022.robot.subsystems.Intakes;
import org.usfirst.frc.team2022.robot.subsystems.ShooterPositions;
import org.usfirst.frc.team2022.robot.subsystems.ShooterSubsystem;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ShooterCommand extends Command {
	
	//Create reference to shooter subsystem
	ShooterSubsystem shooterSubsystem;
	//Create reference to OI
	OI oi;
	long lastShot = 0;
	TurnCameraAutonomousCommand turnCameraAutonomousCommand= new TurnCameraAutonomousCommand();
	DriveToShootingRange driveToShootingRange = new DriveToShootingRange();
	ShootAutonomousCommand shootAutonomousCommand = new ShootAutonomousCommand();
	
    public ShooterCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.shooterSubsystem);
    	shooterSubsystem = Robot.shooterSubsystem;
    	
    	oi = Robot.oi;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//Intake Buttons
    	//Intake Buttons
    	if(oi.attack3.getButton(2)){
//    		shooterSubsystem.setIntake(Intakes.in);
    	}
    	
    	else if(oi.attack3.getTrigger()){
//    		if(lastShot < System.currentTimeMillis() - 1000){
//    			boolean finished = false;
//    			while(oi.attack3.getButton(2) == false || finished == false){
//    				//Automatically Shoot
//        			turnCameraAutonomousCommand.start();
//        			while(turnCameraAutonomousCommand.isRunning()){
//        				
//        			}
//        			driveToShootingRange.start();
//        			while(driveToShootingRange.isRunning()){
//        				
//        			}
//        			shootAutonomousCommand.start();
//        			while(shootAutonomousCommand.isRunning()){
//        				
//        			}
//        			lastShot = System.currentTimeMillis();
//        			finished = true;
//    			}
//    			turnCameraAutonomousCommand.cancel();
//    			driveToShootingRange.cancel();
//    			shootAutonomousCommand.cancel();
//    		}
//    		shootAutonomousCommand.start();
    		
    	}
//    	else if(oi.attack3.getButton(3)){
//    		new ShootAutonomousCommand();
//    	}
    	else{
    		shooterSubsystem.setIntake(Intakes.neutral);
    	}
    	
    	
    	//Set Shooter Angle Buttons
    	if(oi.attack3.getButton(5)){
    		shooterSubsystem.setShooterAngle(ShooterPositions.down);
    	}
    	else if(oi.attack3.getButton(3)){
    		shooterSubsystem.setShooterAngle(ShooterPositions.flat);
    	}
    	else if (oi.attack3.getButton(4)) {
    		shooterSubsystem.setShooterAngle(ShooterPositions.shoot);
    	}
    	double speed = 0;
    	if(oi.attack3.getY() > 0.05 || oi.attack3.getY() < -0.05){
    		speed = oi.attack3.getY();
    	}
    	
    	shooterSubsystem.hingeSpeed(speed);
    	
    	SmartDashboard.putNumber("Hinge angle", shooterSubsystem.getHingeAngle());
    	SmartDashboard.putNumber("Shooter Speed", shooterSubsystem.getLeftEncoderRate());
    	while(true){
        	shooterSubsystem.setShooterSpeed(0);
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
