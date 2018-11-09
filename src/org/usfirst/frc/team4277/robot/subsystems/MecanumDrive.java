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
import edu.wpi.first.wpilibj.RobotDrive;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class MecanumDrive extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	static WPI_TalonSRX m_frontRight;
	static WPI_TalonSRX m_frontLeft;
	static WPI_TalonSRX m_backRight;
	static WPI_TalonSRX m_backLeft;
	static RobotDrive m_robotDrive;
	
	double frontRightSpeed,backRightSpeed,frontLeftSpeed,backLeftSpeed;
	
	@SuppressWarnings("deprecation")
	public MecanumDrive(int frPort, int flPort, int brPort, int blPort){
		System.out.println("MecanumDrive create");
			//motor controller assignments
			m_frontRight = new WPI_TalonSRX(frPort);
			m_frontLeft = new WPI_TalonSRX(flPort);
			m_backRight = new WPI_TalonSRX(brPort);
			m_backLeft = new WPI_TalonSRX(blPort);
			
			m_robotDrive = new RobotDrive(m_frontLeft,m_backLeft,m_frontRight,m_backRight);
			
			m_backLeft.setInverted(true);
			m_frontLeft.setInverted(true);
			
			frontRightSpeed = m_frontRight.getMotorOutputPercent();
			backRightSpeed = m_backRight.getMotorOutputPercent();
			backLeftSpeed = m_backLeft.getMotorOutputPercent();
			frontLeftSpeed = m_frontLeft.getMotorOutputPercent();
		}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new Drive());
    }
	
	@SuppressWarnings("deprecation")
	public static void mechJoystickDrive(Joystick stick, Double gyro){
		
		//double sensitivity = Robot.prefs.getDouble("Sensitivity", 5);
		//SmartDashboard.putNumber(Preferences.SENSITIVITY, sensitivity);
		
		double twist = Math.pow(stick.getZ(), 3);
		
		double yVal = Math.pow(stick.getY(), 5);//experiment with running this through equations
		double xVal = Math.pow(stick.getX(),5);
		
		if (Math.abs(xVal) < 0.05) xVal = 0;
		if (Math.abs(yVal) < 0.05) yVal = 0;
		if (Math.abs(twist) < 0.05) twist = 0;
		
		
		
		/*double fLeft = (yVal-xVal-twist) ;
		double fRight = (yVal+xVal+twist) ;
		double bLeft = (yVal-xVal+twist) ;
		double bRight = (yVal+xVal-twist) ;*/
		
		
		
		/*frontLeftTalon.set (ControlMode.PercentOutput,fLeft);
		frontRightTalon.set (ControlMode.PercentOutput, fRight);
		backLeftTalon.set (ControlMode.PercentOutput, bLeft);
		backRightTalon.set (ControlMode.PercentOutput, bRight);*/
		m_robotDrive.mecanumDrive_Cartesian(xVal, yVal, twist, gyro);
	}
	
	/*public static void mechJoystickGyroDrive(Joystick stick, Double gyro) {//hopefully we can get a navX gyro for precise data(IM)
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
	}*/
	
	public void stop() {
		mechDirectionalDrive(0,0,0.5);
	}
	
	public void autoStop() {
		m_frontLeft.set( ControlMode.PercentOutput, 0);
		m_frontRight.set( ControlMode.PercentOutput, 0);
		m_backLeft.set(ControlMode.PercentOutput, 0);
		m_backRight.set(ControlMode.PercentOutput, 0);

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
			m_frontRight.set(ControlMode.PercentOutput,(yVal - xVal)/1.45);
			m_backLeft.set(ControlMode.PercentOutput, (yVal - xVal)/1.45);
			m_frontLeft.set(ControlMode.PercentOutput, (yVal + xVal)/1.45);
			m_backRight.set(ControlMode.PercentOutput, (yVal + xVal)/1.45);
			System.out.println("init"+initTime);
			System.out.println("Time" + RobotController.getFPGATime());
			System.out.println(" time to run" + millisecondsToRun);
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
			m_frontRight.set(ControlMode.PercentOutput,(yVal - xVal)/1.45);
			m_backLeft.set(ControlMode.PercentOutput, (yVal - xVal)/1.45);
			m_frontLeft.set(ControlMode.PercentOutput, (yVal + xVal)/1.45);
			m_backRight.set(ControlMode.PercentOutput, (yVal + xVal)/1.45);
			//System.out.println("init"+initTime);
			System.out.println(RobotController.getFPGATime());
		}
		
	}
	public void mechSpinRight (Double speed){
		m_frontRight.set(ControlMode.PercentOutput, speed);
		m_backLeft.set(ControlMode.PercentOutput, -speed);
		m_frontLeft.set(ControlMode.PercentOutput, -speed);
		m_backRight.set(ControlMode.PercentOutput, speed);
	}
	public void mechSpinLeft (Double speed){
		m_frontRight.set(ControlMode.PercentOutput,-speed);
		m_backLeft.set(ControlMode.PercentOutput, speed);
		m_frontLeft.set(ControlMode.PercentOutput, speed);
		m_backRight.set(ControlMode.PercentOutput, -speed);
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
