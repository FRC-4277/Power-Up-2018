package org.usfirst.frc.team4277.robot.commands.auto.groups;

import org.usfirst.frc.team4277.robot.commands.Shoot;
import org.usfirst.frc.team4277.robot.commands.auto.AutoDrive;
import org.usfirst.frc.team4277.robot.commands.auto.AutoDriveBack;
import org.usfirst.frc.team4277.robot.commands.auto.AutoSpinLeftR;
import org.usfirst.frc.team4277.robot.commands.auto.AutoStop;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoLeftClose extends CommandGroup {

    public AutoLeftClose() {
    	//OI.gyro.reset();
    //	if(Robot.isSwitchLeft) {
    		addSequential(new AutoDrive(1.8));
    		//addSequential(new AutoDriveSide(180,2.0));
    		addSequential(new AutoSpinLeftR(50));//make left
    		addSequential(new AutoDriveBack(1.5));
    		addSequential(new AutoStop());
    		addSequential(new Shoot());
    //	}
    	
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
