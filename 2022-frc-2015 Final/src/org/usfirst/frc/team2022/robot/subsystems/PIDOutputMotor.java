package org.usfirst.frc.team2022.robot.subsystems;

import org.usfirst.frc.team2022.robot.Robot;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PIDOutputMotor implements PIDOutput{
	double output;
	
	
	@Override
	public void pidWrite(double output) {
		this.output = output;
	}
	
	public double getOutput(){
		return output;
	}
}
