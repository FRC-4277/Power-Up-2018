package org.usfirst.frc.team4277.robot.commands.auto.groups;

import org.usfirst.frc.team4277.robot.commands.auto.AutoDrive;
import org.usfirst.frc.team4277.robot.commands.auto.AutoStop;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoDriveStraight extends CommandGroup {

    public AutoDriveStraight() {
    	addSequential(new AutoDrive(2.5));
    	addSequential(new AutoStop());
    	//addSequential(new AutoSpinRightL(5000));
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
    }
}
