package org.usfirst.frc.team4277.robot.subsystems;

import org.usfirst.frc.team4277.robot.Preferences;
import org.usfirst.frc.team4277.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Crane extends Subsystem {
	
	Victor climberLauncherMotor;
	
	public Crane(int launchMotor) {
		climberLauncherMotor = new Victor(launchMotor);
	}
	
	public void launch () {
		double craneSpeed = Robot.prefs.getDouble("CraneUpSpeed", 0.6);
		double craneWait = Robot.prefs.getDouble("CraneWait", 2);
		SmartDashboard.putNumber(Preferences.CRANE_SPEED, craneSpeed);
		SmartDashboard.putNumber(Preferences.CRANE_WAIT, craneWait);
		System.out.println("Crane Launch");
		//climberLauncherMotor.set(ControlMode.PercentOutput, craneSpeed);
		climberLauncherMotor.set(craneSpeed);
		Timer.delay(craneWait);
		stop();
	}

	public void Retract () { //temp name for test
		double craneSpeed = Robot.prefs.getDouble("CraneRetractSpeed", -0.25);
		double craneWait = Robot.prefs.getDouble("CraneWait", 2);
		SmartDashboard.putNumber(Preferences.CRANE_SPEED, craneSpeed);
		SmartDashboard.putNumber(Preferences.CRANE_WAIT, craneWait);
		System.out.println("Crane Launch");
		//climberLauncherMotor.set(ControlMode.PercentOutput, craneSpeed);
		climberLauncherMotor.set(craneSpeed);
		Timer.delay(craneWait);
		stop();
	}
	public void stop() {
		System.out.println("Crane Stop");
		//climberLauncherMotor.set(ControlMode.PercentOutput, 0.0);
		climberLauncherMotor.set(0.0);
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
}
