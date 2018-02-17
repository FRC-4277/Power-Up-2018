package org.usfirst.frc.team4277.robot.subsystems;

import org.usfirst.frc.team4277.robot.Preferences;
import org.usfirst.frc.team4277.robot.Robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Launcher {
	
	public TalonSRX climberLauncherMotor;
	
	public Launcher(int launchMotor) {
		climberLauncherMotor = new TalonSRX(launchMotor);

	}
	
	public void launch () {
		double launcherSpeed = Robot.prefs.getDouble("LaucherSpeed", 0.6);
		double launcherWait = Robot.prefs.getDouble("LaucherWait", 2);
		SmartDashboard.putNumber(Preferences.LAUNCHER_SPEED, launcherSpeed);
		SmartDashboard.putNumber(Preferences.LAUNCHER_WAIT, launcherWait);
		climberLauncherMotor.set(ControlMode.PercentOutput, launcherSpeed);
		Timer.delay(launcherWait);
		stop();
	}
	
	public void stop() {
		System.out.println("Launcher Stop");
		climberLauncherMotor.set(ControlMode.PercentOutput, 0.0);
	}


}
