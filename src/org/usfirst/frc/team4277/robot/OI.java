/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4277.robot;

import org.usfirst.frc.team4277.robot.commands.ClimberLaunchCommand;
import org.usfirst.frc.team4277.robot.commands.ClimberRetractCommand;
import org.usfirst.frc.team4277.robot.commands.IntakeCubeInCommand;
import org.usfirst.frc.team4277.robot.commands.IntakeCubeOutCommand;
import org.usfirst.frc.team4277.robot.commands.Shoot;
import org.usfirst.frc.team4277.robot.commands.WinchUpCommand;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI implements PortMap{
	
	public static Joystick driveStick = new Joystick(JOYSTICK);
	public static JoystickButton dSTrigger = new JoystickButton(driveStick,8);
	public static ADXRS450_Gyro gyro = new ADXRS450_Gyro();

	public OI() {
		//Pilot Controls
		JoystickButton shooterTrigger = new JoystickButton(driveStick, 7);
		shooterTrigger.whileHeld(new Shoot());
		
		JoystickButton intakeTrigger = new JoystickButton (driveStick, 6);
		intakeTrigger.whileHeld(new IntakeCubeInCommand());
		
		JoystickButton climbUpTrigger = new JoystickButton (driveStick, 5);
		climbUpTrigger.whileHeld(new ClimberLaunchCommand()); 
		
		JoystickButton outtakeTrigger = new JoystickButton (driveStick, 4);
		outtakeTrigger.whileHeld(new IntakeCubeOutCommand());
		
		JoystickButton climbDownTrigger = new JoystickButton (driveStick, 3);
		climbDownTrigger.whileHeld(new ClimberRetractCommand());
		
		JoystickButton winchUpTrigger = new JoystickButton(driveStick, 9);
		winchUpTrigger.whileHeld(new WinchUpCommand());
		
	}
	
	
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
	public static Joystick getdriveStick(){
		return driveStick;
	}
	public static boolean getdSTrigger() {
		return dSTrigger.get();
	}
	public static double getGyro() {
		return gyro.getAngle();
	}
}
