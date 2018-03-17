package org.usfirst.frc.team4277.robot.commands;

import org.usfirst.frc.team4277.robot.OI;
import org.usfirst.frc.team4277.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoDriveBack extends Command {

	public AutoDriveBack() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveTrain.mechDirectionalDriveGyro(90, 0.5,2.0,OI.getGyroA());
    	//Robot.driveTrain.mechDirectionalDrive(180, 0.5, 5);
    	//Robot.driveTrain.stop();
    	//System.out.println("command");
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
