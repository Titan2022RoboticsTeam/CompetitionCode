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


	OI oi = Robot.oi;
	DriveSubsystem driveSubsystem;
	
	public DriveCommand() {
		requires(Robot.driveSubsystem);
		driveSubsystem = Robot.driveSubsystem;
	}

	@Override
	protected void initialize() {
//		SmartDashboard.putString("TankDrive", "COMMAND_INIT");
	}

	@Override
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
		
		
		
		if (oi.xbox.GetRightBumperValue()) {
			driveSubsystem.toggleInversion();
		}
		
//		if(tankSubsystem.isInverted()){
//			speedModifier = .3;
//		}
		
		if (oi.xbox.GetLeftTriggers() > 0.1) { // turtle
			speedModifier = .5; 
		} else if (oi.xbox.GetRightTriggers() > 0.1) { // turbo
			speedModifier = 1; 
		}
		
		if(driveSubsystem.isInverted()){
			driveSubsystem.setLeftSpeed(-right * speedModifier);
			driveSubsystem.setRightSpeed(-left * speedModifier);
		}else{
			driveSubsystem.setLeftSpeed(left * speedModifier);
			driveSubsystem.setRightSpeed(right * speedModifier);
		}
		
		SmartDashboard.putNumber("Distance of right side", driveSubsystem.getRightEncoderDistance());
		SmartDashboard.putNumber("Distance of left side", driveSubsystem.getLeftEncoderDistance());
		SmartDashboard.putNumber("Gyro Angle", driveSubsystem.getGyroAngle());
		
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		Robot.driveSubsystem.stop();
	}

	@Override
	protected void interrupted() {
		end();
	}
}
