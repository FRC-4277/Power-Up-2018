package org.usfirst.frc.team4277.robot.commands.auto;

import org.usfirst.frc.team4277.robot.Robot;
import org.usfirst.frc.team4277.robot.commands.auto.groups.AutoDriveStraight;
import org.usfirst.frc.team4277.robot.commands.auto.groups.AutoLeftClose;
import org.usfirst.frc.team4277.robot.commands.auto.groups.AutoLeftFar;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoLeft extends Command {

    public AutoLeft() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    
    protected void execute() {
    	if (Robot.randomizerSorter.isCloseSwitchLeft() == null) {
    		new AutoDriveStraight().start();
    	}
    	else if(Robot.randomizerSorter.isCloseSwitchLeft().booleanValue()) {
    		System.out.println("AutoLeftClose"+ Robot.isSwitchLeft);
    		new AutoLeftClose().start();;
    	}
    	else if (!Robot.randomizerSorter.isCloseSwitchLeft().booleanValue()) {
    		System.out.println("AutoLeftFar"+Robot.isSwitchLeft);
    		new AutoLeftFar().start();
    	}
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
