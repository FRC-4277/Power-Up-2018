package org.usfirst.frc.team4277.robot.commands;

import org.usfirst.frc.team4277.robot.OI;
import org.usfirst.frc.team4277.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoSpinRight extends Command {

	private boolean end = false;
    public AutoSpinRight() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Math.abs(OI.getGyroA().getAngle()) >= 0.5) {
    		Robot.driveTrain.mechSpinLeft(0.5);
    	}
    	else {
    		Robot.driveTrain.stop();
    		end = true;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return end;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}