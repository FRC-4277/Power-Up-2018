package org.usfirst.frc.team4277.robot.subsystems;

import org.usfirst.frc.team4277.robot.PortMap;
import org.usfirst.frc.team4277.robot.Preferences;
import org.usfirst.frc.team4277.robot.Robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;
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
		leftEncoder = new Encoder(SHOOTER_CHANNEL_A, SHOOTER_CHANNEL_B, false, Encoder.EncodingType.k4X);
	}

	public void takeShot() {
		leftEncoder.reset();
		startSpinning();
	}

	public void startSpinning() {
		double shooterSpeed = Robot.prefs.getDouble(Preferences.SHOOTER_SPEED, Preferences.SHOOTER_DEFAULT_SPEED);
		SmartDashboard.putNumber(Preferences.SHOOTER_SPEED, shooterSpeed);
		leftMotor.set(ControlMode.PercentOutput, -shooterSpeed);
		rightMotor.set(ControlMode.PercentOutput, shooterSpeed);

		SmartDashboard.putNumber("Shooter:  Left speed", leftMotor.getMotorOutputPercent());
		SmartDashboard.putNumber("Shooter:  Right speed", rightMotor.getMotorOutputPercent());
		SmartDashboard.putNumber("Shooter:  Left encoder", leftEncoder.getRate());
		SmartDashboard.putNumber("Shooter:  Right encoder", rightEncoder.get());

	}

	public void stopSpinning() {
		leftMotor.set(ControlMode.PercentOutput, 0);
		rightMotor.set(ControlMode.PercentOutput, 0);
		rightEncoder.reset();
		leftEncoder.reset();
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}
