package org.usfirst.frc.team4277.robot.commands.auto.groups;

import org.usfirst.frc.team4277.robot.commands.OuttakeCubeCommand;
import org.usfirst.frc.team4277.robot.commands.Shoot;
import org.usfirst.frc.team4277.robot.commands.auto.AutoDrive;
import org.usfirst.frc.team4277.robot.commands.auto.AutoDriveBack;
import org.usfirst.frc.team4277.robot.commands.auto.AutoDriveSide;
import org.usfirst.frc.team4277.robot.commands.auto.AutoDriveSideGyro;
import org.usfirst.frc.team4277.robot.commands.auto.AutoSpinLeftR;
import org.usfirst.frc.team4277.robot.commands.auto.AutoSpinRightR;
import org.usfirst.frc.team4277.robot.commands.auto.AutoStop;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoRightFar extends CommandGroup {

    public AutoRightFar() {
    	addSequential(new AutoDrive(2.4));
    	addSequential(new AutoSpinLeftR(62.5));	
		addSequential(new AutoDriveSideGyro(0,3.0));
		addSequential(new AutoDriveSide(0,2.85));
		addSequential(new AutoSpinRightR(90));
		addSequential(new AutoDriveBack(1.5));
		addSequential(new AutoStop());
		addSequential(new Shoot());
		

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
