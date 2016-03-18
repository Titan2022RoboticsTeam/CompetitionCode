package org.usfirst.frc.team2022.robot.subsystems;

import org.usfirst.frc.team2022.robot.ConstantsMap;
import org.usfirst.frc.team2022.robot.RobotMap;
import org.usfirst.frc.team2022.robot.commands.ShooterCommand;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ShooterSubsystem extends Subsystem {
    
    TalonSRX intakeLeft, intakeRight, hinge;
    
    double pos;
    
    AnalogPotentiometer pot;
    
  //names for wheel encoders
	Encoder rightEncoder, leftEncoder, hingeEncoder;
	
//	names for PID Controllers
	PIDController rightController;
	PIDController leftController;
	PIDController hingeController;
//	
//	names for PID Output
	PIDOutputMotor pidOutputRight;
	PIDOutputMotor pidOutputLeft;
	PIDOutputMotor pidOutputHinge;
	
	Servo kicker;
	
    
    public ShooterSubsystem(){
    	intakeLeft = new TalonSRX(RobotMap.leftIntakeMotor);
		intakeRight = new TalonSRX(RobotMap.rightIntakeMotor);
		hinge = new TalonSRX(RobotMap.hingePort);
		kicker = new Servo(RobotMap.kickerPort);
		intakeLeft.setSafetyEnabled(true);
		
		intakeLeft.setInverted(false);
		intakeRight.setInverted(true);
		
		pot = new AnalogPotentiometer(RobotMap.potentiometerPort, 360*10, 0);
		
		rightEncoder = new Encoder(RobotMap.rightShooterEncoderPortA, RobotMap.rightShooterEncoderPortB, false);
		leftEncoder = new Encoder(RobotMap.leftShooterEncoderPortA, RobotMap.leftShooterEncoderPortB, false);
		
//		//Set distance per pulse for encoders
		rightEncoder.setDistancePerPulse(ConstantsMap.SHOOTER_ENCODER_DIST_PER_TICK);
		leftEncoder.setDistancePerPulse(ConstantsMap.SHOOTER_ENCODER_DIST_PER_TICK);
		
//		// sets that the PID controllers are going to get the speeds of the motors
		rightEncoder.setPIDSourceType(PIDSourceType.kRate);
		leftEncoder.setPIDSourceType(PIDSourceType.kRate);
		
		//Instantiate PID controllers and output objects
		pidOutputRight = new PIDOutputMotor();
		rightController = new PIDController(ConstantsMap.pShooter, ConstantsMap.iShooter, ConstantsMap.dShooter, ConstantsMap.fShooter, rightEncoder, pidOutputRight);
//
		pidOutputLeft = new PIDOutputMotor();
		leftController = new PIDController(ConstantsMap.pShooter, ConstantsMap.iShooter, ConstantsMap.dShooter, ConstantsMap.fShooter, leftEncoder, pidOutputLeft);
		
		pidOutputHinge = new PIDOutputMotor();
		hingeController = new PIDController(ConstantsMap.pShooter, ConstantsMap.iShooter, ConstantsMap.dShooter, ConstantsMap.fShooter, pot, pidOutputHinge);
		
		//Set Ouput Range for pid outputs
		rightController.setOutputRange(-1, 1);
		leftController.setOutputRange(-1,1);
		hingeController.setOutputRange(-0.5, 0.5);
		
		//Set the tolerance for on target method
		rightController.setAbsoluteTolerance(5);
		leftController.setAbsoluteTolerance(5);
		hingeController.setAbsoluteTolerance(0.5);
		
		intakeLeft.setSafetyEnabled(true);
		intakeRight.setSafetyEnabled(true);
		
		intakeLeft.setExpiration(0.1);
		intakeRight.setExpiration(0.1);
		
    }

	public void hingeSpeed(double speed){
		hinge.set(speed);
	}
	
	public void setShooterSpeed(double speed){
		intakeLeft.set(speed);
		intakeRight.set(speed);
	}
	
	
	public void setIntake (Intakes i) {
		
		switch(i) {
			case in :
				intakeLeft.set(-0.5);
				intakeRight.set(-0.5);
				break;
			case neutral :
				intakeLeft.set(0);
				intakeRight.set(0);
			case out :
				resetEncoders();
				enableRightPIDController(ConstantsMap.SHOOTER_SPEED);
				enableLeftPIDController(ConstantsMap.SHOOTER_SPEED);
				while(rightController.onTarget() == false && leftController.onTarget() == false){
					intakeLeft.set(pidOutputLeft.getOutput());
					intakeRight.set(pidOutputRight.getOutput());
				}
				setShooterAngle(ShooterPositions.shoot);
				kicker.set(1);
				Timer.delay(1);
				kicker.set(0);
				//Then write code for kicker
				disablePIDControllers();
				intakeLeft.set(0);
				intakeRight.set(0);
				
		}
		
	}

	public void setShooterAngle(ShooterPositions p) {
		
		switch (p) {
			case down:
				pos = ConstantsMap.DOWN_POSITION_ANGLE;
			case flat:
				pos = ConstantsMap.FLAT_POSITION_ANGLE;
				break;
			case shoot:
				pos = ConstantsMap.UP_POSITION_ANGLE;
				break;
		}
		
		if (pot.get() < pos) {
			
			while (pot.get() < pos) {
				
				hinge.set(.8);
				
			}
			
		} else if (pot.get() > pos) {
			
			while (pot.get() > pos) {
					
				hinge.set(-.8);
					
			}
				
		}
		
		hinge.set(0);
			
	}
	
	
	public double getHingeAngle(){
		return pot.get();
	}
	
	//Get Encoder Distances
	public double getRightEncoderDistance(){
		return rightEncoder.getDistance();
	}
	
	public double getLeftEncoderDistance(){
		return leftEncoder.getDistance();
	}
	//Get raw encoder counts
	public int getLeftEncoderRawValue(){
		return leftEncoder.get();
	}
	
	public int getRightEncoderRawValue(){
		return rightEncoder.get();
	}
	//Get Encoder Rates
	public double getRightEncoderRate(){
		return rightEncoder.getRate();
	}
	
	public double getLeftEncoderRate(){
		return leftEncoder.getRate();
	}
	
	//reset encoders
	public void resetEncoders(){
		rightEncoder.reset();
		leftEncoder.reset();
	}
	
	//PID Methods
	public void enableRightPIDController(double speed){
		rightController.setSetpoint(speed);
		rightController.enable();
	}
	public void disablePIDControllers(){
		rightController.disable();
		leftController.disable();
	}
	
	public void enableLeftPIDController(double speed){
		leftController.setSetpoint(speed);
		leftController.enable();
	}
	
	public double getRightPIDOutput(){
		return pidOutputRight.getOutput();
	}
	
	public double getLeftPIDOuput(){
		return pidOutputLeft.getOutput();
	}
	
	//Is the robot within the absolute tolerance of the target distance
	public boolean rightPIDOnTarget(){
		return rightController.onTarget();
	}
	
	public boolean leftPIDOnTarget(){
		return leftController.onTarget();
	}
	
	public void stop(){
		intakeLeft.set(0);
		intakeRight.set(0);
		hinge.set(0);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new ShooterCommand());
    }
}

