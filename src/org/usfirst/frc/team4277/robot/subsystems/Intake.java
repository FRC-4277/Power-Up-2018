package org.usfirst.frc.team4277.robot.subsystems;

import org.usfirst.frc.team4277.robot.PortMap;
import org.usfirst.frc.team4277.robot.Preferences;
import org.usfirst.frc.team4277.robot.Robot;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 */
public class Intake extends Subsystem implements PortMap {

	Victor intakeMotorLeft;
	Victor intakeMotorRight;
	int channel = 0;
	int moduleNum = 0;
	

	public Intake(int leftMotorPort, int rightMotorPort) {
		intakeMotorLeft = new Victor(leftMotorPort);
		intakeMotorRight = new Victor(rightMotorPort);
		
		//Solenoid piston = new Solenoid(moduleNum, channel);
	}

	public void pullCubeIn() {
		System.out.println("Pull in");
		double intakeSpeed = Robot.prefs.getDouble(Preferences.INTAKE_SPEED, Preferences.INTAKE_DEFAULT_SPEED);
		runIntake(intakeSpeed);
	}
	
	public void pushCubeOut() {
		System.out.println("Push out");
		double intakeSpeed = Robot.prefs.getDouble(Preferences.INTAKE_SPEED, Preferences.INTAKE_DEFAULT_SPEED);
		runIntake(-intakeSpeed);
	}

	public void stop() {
		System.out.println("Intake Stop");
		//intakeMotorRight.set(ControlMode.PercentOutput, 0);
		//intakeMotorLeft.set(ControlMode.PercentOutput, 0);
		intakeMotorRight.set(0);
		intakeMotorLeft.set(0);
	}
	
	public void runIntake(double speed) {
		SmartDashboard.putNumber(Preferences.INTAKE_SPEED, speed);
		//intakeMotorRight.set(ControlMode.PercentOutput, speed);
		//intakeMotorLeft.set(ControlMode.PercentOutput, -speed);
		intakeMotorRight.set(speed);
		intakeMotorLeft.set(-speed);
	}
	
	public void lowerIntake(boolean state) {
		//piston.set(state);
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}
