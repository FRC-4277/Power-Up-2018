package org.usfirst.frc.team4277.robot.subsystems;

import org.usfirst.frc.team4277.robot.Robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

/**
 *
 */
public class Shooter extends Subsystem {
	 TalonSRX rightMotor;
	 TalonSRX leftMotor;
	 Encoder encoder;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	 
	 public Shooter(int leftPort, int rightPort) {
		 rightMotor = new TalonSRX(rightPort);
		 leftMotor = new TalonSRX(leftPort);
	 }
	 
	 public void takeShot() {
		 startSpinning();
	 }

	 public void startSpinning() {
		double shooterSpeed = Robot.prefs.getDouble("ShooterSpeed",0.25);
		System.out.println("shooter speed: " + shooterSpeed);
		 leftMotor.set (ControlMode.PercentOutput,shooterSpeed);
		 rightMotor.set(ControlMode.PercentOutput,-shooterSpeed);
	 }
	 
	 public void stopSpinning() {
		 leftMotor.set (ControlMode.PercentOutput,0);
		 rightMotor.set(ControlMode.PercentOutput,0);
	 }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

