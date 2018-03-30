package org.usfirst.frc.team4277.robot.subsystems;

import org.usfirst.frc.team4277.robot.Preferences;
import org.usfirst.frc.team4277.robot.Robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *  This system is responsible for running the winch to climb
 */
public class Climber extends Subsystem {
	
	public TalonSRX winchLeftMotor;
	public TalonSRX winchRightMotor;
	
	public Climber (int leftWinch, int rightWinch) {
		winchLeftMotor = new TalonSRX(leftWinch);
		winchRightMotor = new TalonSRX(rightWinch);
	}		
	

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public void slowClimb() {
		//both motors go backwards
		double slowClimbSpeed = Robot.prefs.getDouble(Preferences.CLIMBER_SPEED_SLOW, Preferences.CLIMBER_SLOW_SPEED); //TODO add this to preferences
		climbAtSpeed(slowClimbSpeed);
	}
	
	public void fastClimb() {
		//double fastClimbSpeed = Robot.prefs.getDouble(Preferences.CLIMBER_SPEED_FAST, Preferences.CLIMBER_DEFAULT_SPEED); //TODO add this to preferences
		climbAtSpeed(1);
	}		
	public void reversefastClimb() {
		//double fastClimbSpeed = Robot.prefs.getDouble(Preferences.CLIMBER_SPEED_FAST, Preferences.CLIMBER_DEFAULT_SPEED); //TODO add this to preferences
		climbAtSpeed(-1);
	}
	
	public void climbAtSpeed(double speed) {
		winchLeftMotor.set(ControlMode.PercentOutput, -1.0);
		winchLeftMotor.set(ControlMode.PercentOutput, -1.0);
	}
	
	public void stop() {
		climbAtSpeed(0);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	
    }
}

