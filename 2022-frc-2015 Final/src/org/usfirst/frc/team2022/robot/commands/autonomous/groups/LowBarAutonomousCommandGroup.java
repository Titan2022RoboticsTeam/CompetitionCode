package org.usfirst.frc.team2022.robot.commands.autonomous.groups;

import org.usfirst.frc.team2022.robot.commands.autonomous.CamFlipAutonomous;
import org.usfirst.frc.team2022.robot.commands.autonomous.DriveStraightAutonomousCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LowBarAutonomousCommandGroup extends CommandGroup {

    public  LowBarAutonomousCommandGroup() {
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
    	
//    	//Face towards the tower
//    	addSequential(new TurnGyroAutonomousCommand(90));
//    	
//    	//Drive towards shooting range using encoders, while getting shooter in ready position
//    	addSequential(new DriveStraightAutonomousCommand(50));
//    	addParallel(new SetShooterAngleAutonomousCommand(ShooterPositions.shoot));
//    	
//    	//Turn towards tower
//    	addSequential(new TurnGyroAutonomousCommand(30));
//    	
//    	//Drive to shooting range
//    	addSequential(new DriveStraightAutonomousCommand(20));
//    	
//    	//Shoot
//    	addSequential(new ShootAutonomousCommand());	
    	
    }
}
