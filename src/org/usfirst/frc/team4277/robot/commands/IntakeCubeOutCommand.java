package org.usfirst.frc.team4277.robot.commands;

import org.usfirst.frc.team4277.robot.OI;
import org.usfirst.frc.team4277.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeCubeOutCommand extends Command {

    public IntakeCubeOutCommand() {
        // Use requires() here to declare subsystem dependencies
        // 
    	requires(Robot.intake);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.intake.stop();

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.intake.pushCubeOut();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return !OI.getPhotoElectric();
    }

    // Called once after isFinished returns true
    protected void end() { 
    	Robot.intake.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.intake.stop();
    }
}
