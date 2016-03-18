package org.usfirst.frc.team2022.robot.subsystems;
import org.usfirst.frc.team2022.robot.Robot;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

public class PIDGyroSource implements PIDSource {

	DriveSubsystem driveSubsystem = Robot.driveSubsystem;
	@Override
	public void setPIDSourceType(PIDSourceType pidSource) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PIDSourceType getPIDSourceType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double pidGet() {
		// TODO Auto-generated method stub
		return driveSubsystem.getGyroAngle();
	}

}
