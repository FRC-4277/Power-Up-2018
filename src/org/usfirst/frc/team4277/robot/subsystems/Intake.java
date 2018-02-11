package org.usfirst.frc.team4277.robot.subsystems;

import org.usfirst.frc.team4277.robot.PortMap;
import org.usfirst.frc.team4277.robot.Preferences;
import org.usfirst.frc.team4277.robot.Robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.ControlMode;

/**
 */
public class Intake extends Subsystem implements PortMap{
	
	VictorSPX intakeMotorOne;
	VictorSPX intakeMotorTwo;
	 
	 public Intake(int portOne, int portTwo) {
		 intakeMotorOne = new VictorSPX(portOne);
		 intakeMotorTwo = new VictorSPX(portTwo);
	 }
	 
	 public void startIntake() {
		 getCube();
	 }
	 
	 public void stopIntake() {
		 stop();
	 }
	 
	 public void getCube() {
		 	System.out.println("Intake Chomp");
			double intakeSpeed = Robot.prefs.getDouble(Preferences.INTAKE_SPEED,Preferences.INTAKE_DEFAULT_SPEED);
			SmartDashboard.putNumber(Preferences.INTAKE_SPEED, intakeSpeed);
			intakeMotorTwo.set (ControlMode.PercentOutput, intakeSpeed);
			intakeMotorOne.set(ControlMode.PercentOutput,-intakeSpeed);
		 }
	 
	 public void stop() {
		 System.out.println("Intake Stop");
		 intakeMotorTwo.set (ControlMode.PercentOutput,0);
		 intakeMotorOne.set(ControlMode.PercentOutput,0);
	 }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

