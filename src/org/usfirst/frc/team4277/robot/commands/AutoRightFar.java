package org.usfirst.frc.team4277.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoRightFar extends CommandGroup {

    public AutoRightFar() {
    	addSequential(new AutoDrive(2.2));
		addSequential(new AutoDriveSide(0,7.0));
		addSequential(new AutoSpinRight());
		addSequential(new AutoDriveBack(1.75));
		addSequential(new AutoSpinLeft(50));
		addSequential(new AutoDriveBack(1.5));
		addSequential(new AutoStop());
		addSequential(new IntakeCubeOutCommand());
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
