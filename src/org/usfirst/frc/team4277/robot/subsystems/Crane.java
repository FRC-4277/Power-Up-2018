package org.usfirst.frc.team4277.robot.subsystems;

import org.usfirst.frc.team4277.robot.Preferences;
import org.usfirst.frc.team4277.robot.Robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Crane extends Subsystem {
	
	public TalonSRX climberLauncherMotor;
	
	public Crane(int launchMotor) {
		climberLauncherMotor = new TalonSRX(launchMotor);
	}
	
	public void launch () {
		double craneSpeed = Robot.prefs.getDouble("CraneUpSpeed", 0.75);
		//double craneWait = Robot.prefs.getDouble("CraneWait", 2);
		SmartDashboard.putNumber(Preferences.CRANE_SPEED, craneSpeed);
		//SmartDashboard.putNumber(Preferences.CRANE_WAIT, craneWait);
		System.out.println("Crane Launch");
		climberLauncherMotor.set(ControlMode.PercentOutput, craneSpeed);
		//Timer.delay(craneWait);
		stop();
	}

	public void Retract () { //temp name for test
		double craneSpeed = Robot.prefs.getDouble("CraneRetractSpeed", -0.1);
		//double craneWait = Robot.prefs.getDouble("CraneWait", 2);
		SmartDashboard.putNumber(Preferences.CRANE_SPEED, craneSpeed);
		//SmartDashboard.putNumber(Preferences.CRANE_WAIT, craneWait);
		System.out.println("Crane Launch");
//		climberLauncherMotor.set(ControlMode.PercentOutput, -0.5);
		climberLauncherMotor.set(ControlMode.PercentOutput, -craneSpeed);

		//Timer.delay(craneWait);
		//stop();
	}
	public void limitExtend(){
		climberLauncherMotor.set(ControlMode.PercentOutput,0.85);
		Timer.delay(1.35);
		climberLauncherMotor.set(ControlMode.PercentOutput, 0.0);
		
	}
	public void stop() {
		System.out.println("Crane Stop");
		climberLauncherMotor.set(ControlMode.PercentOutput, 0.0);
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
}
