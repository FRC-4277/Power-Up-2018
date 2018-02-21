package org.usfirst.frc.team4277.robot.commands;

import org.usfirst.frc.team4277.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveTimed extends Command {

	private double driveForewardSpeed;
	private double time;
	
	 public DriveTimed() {
		 DriveForewardTimed(2.0,0.2);     
	    }
	 
	public void DriveForewardTimed(double time, double speed) {
        requires(Robot.driveTrain);
        this.driveForewardSpeed = speed;
        this.time = time;
       
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	setTimeout(time);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	System.out.println ("Executing; Time since intialized: " + Double.toString(timeSinceInitialized()));
    	Robot.driveTrain.mechDirectionalDrive(0.0, driveForewardSpeed, time);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	System.out.println ("Finished; Time since intialized: " + Double.toString(timeSinceInitialized()));
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.driveTrain.stop();
    }
}
