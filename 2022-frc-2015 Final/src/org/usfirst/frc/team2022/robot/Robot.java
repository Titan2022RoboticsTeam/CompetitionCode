package org.usfirst.frc.team2022.robot;
import org.usfirst.frc.team2022.robot.commands.DriveCommand;
import org.usfirst.frc.team2022.robot.commands.ShooterCommand;
import org.usfirst.frc.team2022.robot.commands.autonomous.groups.DefaultAutonomousCommandGroup;
import org.usfirst.frc.team2022.robot.commands.autonomous.groups.LowBarAutonomousCommandGroup;
import org.usfirst.frc.team2022.robot.commands.autonomous.groups.LowBarHighGoalCameraAutonomousGroup;
import org.usfirst.frc.team2022.robot.subsystems.CameraSubsystem;
import org.usfirst.frc.team2022.robot.subsystems.DriveSubsystem;
import org.usfirst.frc.team2022.robot.subsystems.ShooterSubsystem;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static final DriveSubsystem driveSubsystem = new DriveSubsystem();
	public static final ShooterSubsystem shooterSubsystem = new ShooterSubsystem();
	public static final CameraSubsystem cameraSubsystem = new CameraSubsystem();
	public static OI oi;

	DriveCommand tankCommand;
	ShooterCommand shooterCommand;

	SendableChooser autoChooser;
	SendableChooser defenseChooser;

	CommandGroup autonomousCommand = new DefaultAutonomousCommandGroup();
	int autonomousSelection = 0;
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		oi = new OI();
//
		tankCommand = new DriveCommand(); 
		shooterCommand = new ShooterCommand();
		autoChooser = new SendableChooser();
		autoChooser.addDefault("Nothing", 0);
    	autoChooser.addObject("Drive Straight Command", 1);
    	autoChooser.addObject("Low Bar Shoot With Camera Command", 2);
    	SmartDashboard.putData("Autonomous Mode Chooser", autoChooser);
    	
//    	defenseChooser = new SendableChooser();
//    	defenseChooser.addDefault("Low Bar", 0);
//    	defenseChooser.addDefault("1", 1);
//    	defenseChooser.addDefault("2", 2);
//    	defenseChooser.addDefault("3", 3);
//    	defenseChooser.addDefault("4", 4);
//    	SmartDashboard.putData("Which defense are we in front of?", defenseChooser);
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void autonomousInit() {
//		 schedule the autonomous command (example)
		autonomousSelection = (int) autoChooser.getSelected();
		switch(autonomousSelection){
			case 0:
				autonomousCommand = new DefaultAutonomousCommandGroup();
				break;
			case 1:
				autonomousCommand = new LowBarAutonomousCommandGroup();
				break;
			case 2:
				autonomousCommand = new LowBarHighGoalCameraAutonomousGroup();
				break;
		}
		
    	autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		autonomousCommand.cancel();
		tankCommand.start();
		shooterCommand.start();
		driveSubsystem.resetGyro();
	}

	/**
	 * This function is called when the disabled button is hit. You can use it
	 * to reset subsystems before shutting down.
	 */
	@Override
	public void disabledInit() {
		tankCommand.cancel();
		shooterCommand.cancel();
		//
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}

