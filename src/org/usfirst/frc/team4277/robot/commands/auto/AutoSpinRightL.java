package org.usfirst.frc.team4277.robot.commands.auto;

import org.usfirst.frc.team4277.robot.OI;
import org.usfirst.frc.team4277.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoSpinRightL extends Command {
	double angle;
	private boolean end = false;
    public AutoSpinRightL() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    	angle = 7.0;
    }
    public AutoSpinRightL(double angle) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    	this.angle = angle;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(angle == 7.0) {
    	if(Math.abs(OI.getGyroA().getAngle()) >= angle) {
    		Robot.driveTrain.mechSpinRight(0.5);
    	}
    	else {
    		Robot.driveTrain.stop();
    		end = true;
    	}
    	}
    	else {
    		if(Math.abs(OI.getGyroA().getAngle()) < angle) {
        		Robot.driveTrain.mechSpinRight(0.5);
        	}
        	else {
        		Robot.driveTrain.stop();
        		end = true;
        	}
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
