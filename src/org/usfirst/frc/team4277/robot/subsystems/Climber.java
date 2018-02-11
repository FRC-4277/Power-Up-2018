package org.usfirst.frc.team4277.robot.subsystems;

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
		double slowClimbSpeed = Robot.prefs.getDouble("Slow CLimb Speed", 0.6); //TODO add this to preferences

		winchLeftMotor.set(ControlMode.PercentOutput, -slowClimbSpeed);
		winchLeftMotor.set(ControlMode.PercentOutput, 0.0);
	}
	
	public void fastClimb() {
		
	}
	
	public void stopClimb() {
		
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	
    }
}

