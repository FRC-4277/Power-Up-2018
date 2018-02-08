package org.usfirst.frc.team4277.robot.subsystems;

import org.usfirst.frc.team4277.robot.PortMap;
import org.usfirst.frc.team4277.robot.Robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;

/**
 *
 */
public class Intake extends Subsystem implements PortMap{
	
	 TalonSRX intakeMotorOne;
	 TalonSRX intakeMotorTwo;
	 Encoder intakeEncoderOne;
	 Encoder intakeEncoderTwo;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	 
	 public Intake(int leftPort, int rightPort) {
		 intakeMotorOne = new TalonSRX(rightPort);
		 intakeMotorTwo = new TalonSRX(leftPort);
		 intakeEncoderTwo = new Encoder(INTAKE_IO_CHANNEL, INTAKE_IO_POWER, false, Encoder.EncodingType.k4X);
	 }
	 
	 public void startIntake() {
		 chomp();
	 }
	 
	 public void stopIntake() {
		 stop();
	 }
	 
	 public void chomp() {
			double intakeSpeed = Robot.prefs.getDouble("ShooterSpeed",0.25);
			System.out.println("shooter speed: " + intakeSpeed);
			//System.out.println("encoder right speed: " + rightEncoder.get());
			System.out.println("encoder left: " + intakeEncoderTwo.getDistance());
			
			SmartDashboard.setDefaultNumber("Shooter speed", intakeSpeed);
			//SmartDashboard.setDefaultNumber("Right encoder speed", rightEncoder.get());
			SmartDashboard.setDefaultNumber("Left encoder speed", intakeEncoderTwo.get());
			intakeMotorTwo.set (ControlMode.PercentOutput, intakeSpeed);
			intakeMotorOne.set(ControlMode.PercentOutput,-intakeSpeed);
		 }
	 
	 public void stop() {
		 intakeMotorTwo.set (ControlMode.PercentOutput,0);
		 intakeMotorOne.set(ControlMode.PercentOutput,0);
		// rightEncoder.reset();
		 intakeEncoderTwo.reset();
	 }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

