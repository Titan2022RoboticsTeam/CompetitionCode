package org.usfirst.frc.team2022.robot.commands.autonomous.groups;

import org.usfirst.frc.team2022.robot.commands.autonomous.CamFlipAutonomous;
import org.usfirst.frc.team2022.robot.commands.autonomous.DriveStraightAutonomousCommand;
import org.usfirst.frc.team2022.robot.commands.autonomous.DriveToShootingRange;
import org.usfirst.frc.team2022.robot.commands.autonomous.SetShooterAngleAutonomousCommand;
import org.usfirst.frc.team2022.robot.commands.autonomous.ShootAutonomousCommand;
import org.usfirst.frc.team2022.robot.commands.autonomous.TurnCameraAutonomousCommand;
import org.usfirst.frc.team2022.robot.commands.autonomous.TurnGyroAutonomousCommand;
import org.usfirst.frc.team2022.robot.subsystems.ShooterPositions;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LowBarHighGoalCameraAutonomousGroup extends CommandGroup {
    
    public  LowBarHighGoalCameraAutonomousGroup() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	
//    	addSequential(new CamFlipAutonomous());
    	//Drive past low bar
    	addSequential(new DriveStraightAutonomousCommand(260.83));
    	
    	//Face towards the tower
    	addSequential(new TurnGyroAutonomousCommand(30));
    	
    	//Drive towards shooting range using encoders, while getting shooter in ready position
    	addSequential(new DriveStraightAutonomousCommand(60));
    	addParallel(new SetShooterAngleAutonomousCommand(ShooterPositions.shoot));
    	    	
    	//Align with camera
    	addSequential(new TurnCameraAutonomousCommand());
    	
    	//Drive to shooting range
    	addSequential(new DriveToShootingRange());
    	
    	//Shoot
    	addSequential(new ShootAutonomousCommand());
    }
}
