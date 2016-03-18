package org.usfirst.frc.team2022.robot;
import org.usfirst.frc.team2022.robot.commands.DriveCommand;
import org.usfirst.frc.team2022.robot.commands.LowBarAutonomousCommandGroup;
import org.usfirst.frc.team2022.robot.commands.ShooterCommand;
import org.usfirst.frc.team2022.robot.subsystems.DriveSubsystem;
import org.usfirst.frc.team2022.robot.subsystems.ShooterSubsystem;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 * 
	 */
	
	
	public static final ShooterSubsystem shooterSubsystem = new ShooterSubsystem();
	public static final DriveSubsystem driveSubsystem = new DriveSubsystem();
	public static final OI oi = new OI();
	ShooterCommand shooterCommand;
	DriveCommand driveCommand;
	LowBarAutonomousCommandGroup autonomousCommand;
	
	@Override
	public void robotInit() {
		shooterCommand = new ShooterCommand();
    	driveCommand = new DriveCommand();
    	autonomousCommand = new LowBarAutonomousCommandGroup();
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void autonomousInit() {
//		 schedule the autonomous command (example)
//		
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
		shooterCommand.start();
		driveCommand.start();
	}

	/**
	 * This function is called when the disabled button is hit. You can use it
	 * to reset subsystems before shutting down.
	 */
	@Override
	public void disabledInit() {
		shooterCommand.cancel();
		driveCommand.cancel();
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

