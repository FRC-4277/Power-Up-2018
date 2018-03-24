/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4277.robot.subsystems;

import org.usfirst.frc.team4277.robot.commands.Drive;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Gyro;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class MecanumDrive extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	static WPI_TalonSRX frontRightTalon;
	static WPI_TalonSRX frontLeftTalon;
	static WPI_TalonSRX backRightTalon;
	static WPI_TalonSRX backLeftTalon;
	edu.wpi.first.wpilibj.drive.MecanumDrive mDriveWPI;
	
	double frontRightSpeed,backRightSpeed,frontLeftSpeed,backLeftSpeed;
	
	public MecanumDrive(int frPort, int flPort, int brPort, int blPort){
		System.out.println("MecanumDrive create");
			//motor controller assignments
			frontRightTalon = new WPI_TalonSRX(frPort);
			frontLeftTalon = new WPI_TalonSRX(flPort);
			backRightTalon = new WPI_TalonSRX(brPort);
			backLeftTalon = new WPI_TalonSRX(blPort);
			
			mDriveWPI = new edu.wpi.first.wpilibj.drive.MecanumDrive(frontLeftTalon,backRightTalon,frontRightTalon,backRightTalon);
			
			backRightTalon.setInverted(true);
			frontRightTalon.setInverted(true);
			
			frontRightSpeed = frontRightTalon.getMotorOutputPercent();
			backRightSpeed = backRightTalon.getMotorOutputPercent();
			backLeftSpeed = backLeftTalon.getMotorOutputPercent();
			frontLeftSpeed = frontLeftTalon.getMotorOutputPercent();
		}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new Drive());
    }
	
	public static void mechJoystickDrive(Joystick stick){
		
		//double sensitivity = Robot.prefs.getDouble("Sensitivity", 5);
		//SmartDashboard.putNumber(Preferences.SENSITIVITY, sensitivity);
		
		double twist = Math.pow(stick.getX(), 5);
		
		double yVal = Math.pow(stick.getY(), 3);//experiment with running this through equations
		double xVal = stick.getTwist();
		
		if (Math.abs(xVal) < 0.05) xVal = 0;
		if (Math.abs(yVal) < 0.05) yVal = 0;
		if (Math.abs(twist) < 0.05) twist = 0;
		
		double adjustor = ((2*Math.sqrt(2))/2) + Math.abs(twist);
		
		//double speed = Math.sqrt((Math.pow(xVal, 2)) + (Math.pow(yVal, 2)));
		//double angle = Math.asin(yVal/speed);
		
		//double fLeft = ((Math.sin(angle)*speed)-(Math.cos(angle)*speed)-twist) / adjustor;
		//double fRight = ((Math.sin(angle)*speed)+(Math.cos(angle)*speed)+twist) / adjustor;
		//double bLeft = ((Math.sin(angle)*speed)-(Math.cos(angle)*speed)+twist) / adjustor;
		//double bRight = ((Math.sin(angle)*speed)+(Math.cos(angle)*speed)-twist) / adjustor;
		
		double fLeft = (yVal-xVal-twist) / adjustor;
		double fRight = (yVal+xVal+twist) / adjustor;
		double bLeft = (yVal-xVal+twist) / adjustor;
		double bRight = (yVal+xVal-twist) / adjustor;
		
		/*if (Math.abs(fLeft) < 0.05) fLeft = 0;
		if (Math.abs(fRight) < 0.05) fRight = 0;
		if (Math.abs(bLeft) < 0.05) bLeft = 0;
		if (Math.abs(bRight) < 0.05) bRight = 0;*/
		
		frontLeftTalon.set (ControlMode.PercentOutput,fLeft);
		frontRightTalon.set (ControlMode.PercentOutput, fRight);
		backLeftTalon.set (ControlMode.PercentOutput, bLeft);
		backRightTalon.set (ControlMode.PercentOutput, bRight);
	}
	
	public static void mechJoystickGyroDrive(Joystick stick, Double gyro) {//hopefully we can get a navX gyro for precise data(IM)
		double xVal = stick.getTwist();
		double yVal = stick.getY();//experiment with running this through equations
		double twist = stick.getX();
		double orientation = Math.toRadians(gyro);
		
		if (Math.abs(xVal) < 0.05) xVal = 0;
		if (Math.abs(yVal) < 0.05) yVal = 0;
		if (Math.abs(twist) < 0.05) twist = 0;
		
		double adjustor = ((2*Math.sqrt(2))/2) + Math.abs(twist);
		
		double speed = Math.sqrt((Math.pow(xVal, 2)) + (Math.pow(yVal, 2)));
		double angle = Math.atan2(yVal, xVal) + orientation;
		
		double fLeft = ((Math.sin(angle)*speed)-(Math.cos(angle)*speed)-twist) / adjustor;
		double fRight = ((Math.sin(angle)*speed)+(Math.cos(angle)*speed)+twist) / adjustor;
		double bLeft = ((Math.sin(angle)*speed)-(Math.cos(angle)*speed)+twist) / adjustor;
		double bRight = ((Math.sin(angle)*speed)+(Math.cos(angle)*speed)-twist) / adjustor;
		
		if (Math.abs(fLeft) < 0.05) fLeft = 0;
		if (Math.abs(fRight) < 0.05) fRight = 0;
		if (Math.abs(bLeft) < 0.05) bLeft = 0;
		if (Math.abs(bRight) < 0.05) bRight = 0;
		
		frontLeftTalon.set( ControlMode.PercentOutput, fLeft);
		frontRightTalon.set( ControlMode.PercentOutput, fRight);
		backLeftTalon.set(ControlMode.PercentOutput, bLeft);
		backRightTalon.set(ControlMode.PercentOutput, bRight);
		//System.out.println(gyro);
	}
	
	public void stop() {
		mechDirectionalDrive(0,0,0.5);
	}
	
	public void autoStop() {
		frontLeftTalon.set( ControlMode.PercentOutput, 0);
		frontRightTalon.set( ControlMode.PercentOutput, 0);
		backLeftTalon.set(ControlMode.PercentOutput, 0);
		backRightTalon.set(ControlMode.PercentOutput, 0);

	}
	public void mechDirectionalDrive (double angle, double speed, double durration) {
		
		
		double millisecondsToRun = durration*1000000; // Timeout
		double initTime = RobotController.getFPGATime();
		
		//Conversion from polar to coordinate system
		double rad = Math.toRadians(angle);
		double xVal = Math.cos(rad) * speed;
		double yVal = Math.sin(rad) * speed;
		//Drives the motors
		while (RobotController.getFPGATime() - initTime <= millisecondsToRun){
			frontRightTalon.set(ControlMode.PercentOutput,(yVal - xVal)/1.45);
			backLeftTalon.set(ControlMode.PercentOutput, (yVal - xVal)/1.45);
			frontLeftTalon.set(ControlMode.PercentOutput, (yVal + xVal)/1.45);
			backRightTalon.set(ControlMode.PercentOutput, (yVal + xVal)/1.45);
			//System.out.println("init"+initTime);
			//System.out.println(RobotController.getFPGATime());
		}
		
	}
	public void mechDirectionalDriveGyro (double angle, double speed, double durration,Gyro gyro) {
		
		//gyro.reset();
		double millisecondsToRun = durration*1000000; // Timeout
		double initTime = RobotController.getFPGATime();
		
		//Conversion from polar to coordinate system
		
		//Drives the motors
		while (RobotController.getFPGATime() - initTime <= millisecondsToRun){
			//System.out.println(gyro.getAngle());
			double rad = Math.toRadians(angle+gyro.getAngle());
			double xVal = Math.cos(rad) * speed;
			double yVal = Math.sin(rad) * speed;
			
			//mDriveWPI.driveCartesian(yVal, xVal, 0, gyro.getAngle());
			frontRightTalon.set(ControlMode.PercentOutput,(yVal - xVal)/1.45);
			backLeftTalon.set(ControlMode.PercentOutput, (yVal - xVal)/1.45);
			frontLeftTalon.set(ControlMode.PercentOutput, (yVal + xVal)/1.45);
			backRightTalon.set(ControlMode.PercentOutput, (yVal + xVal)/1.45);
			//System.out.println("init"+initTime);
			//System.out.println(RobotController.getFPGATime());
		}
		
	}
	public void mechSpinRight (Double speed){
		frontRightTalon.set(ControlMode.PercentOutput, speed);
		backLeftTalon.set(ControlMode.PercentOutput, -speed);
		frontLeftTalon.set(ControlMode.PercentOutput, -speed);
		backRightTalon.set(ControlMode.PercentOutput, speed);
	}
	public void mechSpinLeft (Double speed){
		frontRightTalon.set(ControlMode.PercentOutput,-speed);
		backLeftTalon.set(ControlMode.PercentOutput, speed);
		frontLeftTalon.set(ControlMode.PercentOutput, speed);
		backRightTalon.set(ControlMode.PercentOutput, -speed);
		//System.out.println(OI.getGyroA().getAngle());
	}
	
	public double getFRValue(){
		return frontRightSpeed;
		
	}
	public double getBRValue(){
		return backRightSpeed;
		
	}
	public double getBLValue(){
		return backLeftSpeed;
		
	}
	public double getFLValue(){
		return frontLeftSpeed;
		
	}
}
