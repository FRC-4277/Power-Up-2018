/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4277.robot.subsystems;

import org.usfirst.frc.team4277.robot.commands.*;
import edu.wpi.first.wpilibj.Joystick;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class MecanumDrive extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	static TalonSRX frontRightTalon;
	static TalonSRX frontLeftTalon;
	static TalonSRX backRightTalon;
	static TalonSRX backLeftTalon;
	
	double frontRightSpeed,backRightSpeed,frontLeftSpeed,backLeftSpeed;
	
	public MecanumDrive(int frPort, int flPort, int brPort, int blPort){
		System.out.println("MecanumDrive create");
			//motor controller assignments
			frontRightTalon = new TalonSRX(frPort);
			frontLeftTalon = new TalonSRX(flPort);
			backRightTalon = new TalonSRX(brPort);
			backLeftTalon = new TalonSRX(blPort);
			
			backLeftTalon.setInverted(true);
			frontLeftTalon.setInverted(true);
			
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
		
		double xVal = stick.getX();
		double yVal = stick.getY();//experiment with running this through equations
		double twist = stick.getTwist();
		
		if (Math.abs(xVal) < 0.05) xVal = 0;
		if (Math.abs(yVal) < 0.05) yVal = 0;
		if (Math.abs(twist) < 0.05) twist = 0;
		
		double adjustor = ((2*Math.sqrt(2))/2) + Math.abs(twist);
		
		double speed = Math.sqrt((Math.pow(xVal, 2)) + (Math.pow(yVal, 2)));
		double angle = Math.asin(yVal/speed);
		
		double fLeft = ((Math.sin(angle)*speed)-(Math.cos(angle)*speed)-twist) / adjustor;
		double fRight = ((Math.sin(angle)*speed)+(Math.cos(angle)*speed)+twist) / adjustor;
		double bLeft = ((Math.sin(angle)*speed)-(Math.cos(angle)*speed)+twist) / adjustor;
		double bRight = ((Math.sin(angle)*speed)+(Math.cos(angle)*speed)-twist) / adjustor;
		
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
		double xVal = stick.getX();
		double yVal = stick.getY();//experiment with running this through equations
		double twist = stick.getTwist();
		double orientation = Math.toRadians(gyro);
		
		if (Math.abs(xVal) < 0.05) xVal = 0;
		if (Math.abs(yVal) < 0.05) yVal = 0;
		if (Math.abs(twist) < 0.05) twist = 0;
		
		double adjustor = ((2*Math.sqrt(2))/2) + Math.abs(twist);
		
		double speed = Math.sqrt((Math.pow(xVal, 2)) + (Math.pow(yVal, 2)));
		double angle = Math.asin(yVal/speed) + orientation;
		
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
	}
	
	public void mechDirectionalDrive (Double angle, Double speed, long durration) {
		//Sets motor timeouts
		/*FrontRight.setExpiration(durration);
		FrontLeft.setExpiration(durration);
		BackRight.setExpiration(durration);
		BackLeft.setExpiration(durration);*/
		
		long millisecondsToRun = durration; // Timeout
		long initTime = RobotController.getFPGATime();
		
		//Conversion from polar to coordinate system
		Double rad = Math.toRadians(angle);
		Double xVal = Math.cos(rad) * speed;
		Double yVal = Math.sin(rad) * speed;
		
		//Drives the motors
		while (RobotController.getFPGATime() - initTime <= millisecondsToRun){
			frontRightTalon.set(ControlMode.PercentOutput,(yVal - xVal)/1.45);
			backLeftTalon.set(ControlMode.PercentOutput, (yVal - xVal)/1.45);
			frontLeftTalon.set(ControlMode.PercentOutput, (yVal + xVal)/1.45);
			backRightTalon.set(ControlMode.PercentOutput, (yVal + xVal)/1.45);
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
