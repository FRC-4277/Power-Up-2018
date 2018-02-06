package org.usfirst.frc.team4277.robot.subsystems;

import org.usfirst.frc.team4277.robot.PortMap;
import org.usfirst.frc.team4277.robot.Robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Shooter extends Subsystem implements PortMap {
	 TalonSRX rightMotor;
	 TalonSRX leftMotor;
	 Encoder rightEncoder;
	 Encoder leftEncoder;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	 
	 public Shooter(int leftPort, int rightPort) {
		 rightMotor = new TalonSRX(rightPort);
		 leftMotor = new TalonSRX(leftPort);
		 leftEncoder = new Encoder(SHOOTER_IO_CHANNEL, SHOOTER_IO_POWER, false, Encoder.EncodingType.k4X);
	 }
	 
	 public void takeShot() {
		 startSpinning();
	 }

	 public void startSpinning() {
		double shooterSpeed = Robot.prefs.getDouble("ShooterSpeed",0.25);
		System.out.println("shooter speed: " + shooterSpeed);
		//System.out.println("encoder right speed: " + rightEncoder.get());
		System.out.println("encoder left: " + leftEncoder.getDistance());
		
		SmartDashboard.setDefaultNumber("Shooter speed", shooterSpeed);
		//SmartDashboard.setDefaultNumber("Right encoder speed", rightEncoder.get());
		SmartDashboard.setDefaultNumber("Left encoder speed", leftEncoder.get());
		 leftMotor.set (ControlMode.PercentOutput,shooterSpeed);
		 rightMotor.set(ControlMode.PercentOutput,-shooterSpeed);
	 }
	 
	 public void stopSpinning() {
		 leftMotor.set (ControlMode.PercentOutput,0);
		 rightMotor.set(ControlMode.PercentOutput,0);
		// rightEncoder.reset();
		 leftEncoder.reset();
	 }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

