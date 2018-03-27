package org.usfirst.frc.team4277.robot.commands.auto.groups;

import org.usfirst.frc.team4277.robot.commands.OuttakeCubeCommand;
import org.usfirst.frc.team4277.robot.commands.auto.AutoDrive;
import org.usfirst.frc.team4277.robot.commands.auto.AutoDriveBack;
import org.usfirst.frc.team4277.robot.commands.auto.AutoDriveSide;
import org.usfirst.frc.team4277.robot.commands.auto.AutoDriveSideGyro;
import org.usfirst.frc.team4277.robot.commands.auto.AutoSpinLeftL;
import org.usfirst.frc.team4277.robot.commands.auto.AutoSpinRightL;
import org.usfirst.frc.team4277.robot.commands.auto.AutoStop;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoLeftFar extends CommandGroup {

    public AutoLeftFar() {
    	addSequential(new AutoDrive(2.4));
    	addSequential(new AutoSpinRightL(55));
    	addSequential(new AutoDriveSideGyro(180,2.7));
    	//addSequential(new AutoDrive(3.1));
    	addSequential(new AutoDriveSide(200,2.00));
    	//OI.gyro.reset();
    	addSequential(new AutoSpinLeftL(95));
    	addSequential(new AutoDriveBack(1.5));
    	addSequential(new AutoStop());
    	addSequential(new OuttakeCubeCommand());
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
