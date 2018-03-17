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
import org.usfirst.frc.team4277.robot.commands.WinchUpCommand;
import org.usfirst.frc.team4277.robot.commands.TipperUpCommand;
import org.usfirst.frc.team4277.robot.commands.WinchDownCommand;
import org.usfirst.frc.team4277.robot.commands.TipperDownCommand;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.interfaces.Gyro;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI implements ClonePortMap{
	
	public static Joystick driveStick = new Joystick(JOYSTICK);
	public static JoystickButton dSTrigger = new JoystickButton(driveStick,1);
	public static Joystick xboxController = new Joystick(2);
	public static ADXRS450_Gyro gyro = new ADXRS450_Gyro();

	public OI() {
		//Pilot Controls
		//JoystickButton shooterTrigger = new JoystickButton(driveStick, 0);
		//shooterTrigger.whileHeld(new Shoot());
		
		JoystickButton intakeButton = new JoystickButton (driveStick, 6);
		intakeButton.whileHeld(new IntakeCubeInCommand());
		
		JoystickButton climbUpButton = new JoystickButton (driveStick, 5);
		climbUpButton.whileHeld(new ClimberLaunchCommand()); 
		
		JoystickButton outtakeButton = new JoystickButton (driveStick, 4);
		outtakeButton.whileHeld(new IntakeCubeOutCommand());
		
		JoystickButton climbDownButton = new JoystickButton (driveStick, 3);
		climbDownButton.whileHeld(new ClimberRetractCommand());
		
		JoystickButton winchUpButton = new JoystickButton(driveStick, 9);
		winchUpButton.whileHeld(new WinchUpCommand());
		
		JoystickButton tipperEngageButton = new JoystickButton(driveStick, 10);
		tipperEngageButton.whenPressed(new TipperUpCommand());
		
		JoystickButton tipperDisengageButton = new JoystickButton(driveStick, 12);
		tipperDisengageButton.whenPressed(new TipperDownCommand());
		
		JoystickButton winchDownButton = new JoystickButton(driveStick, 11);
		winchDownButton.whileHeld(new WinchDownCommand());
		
		//XboxButtonControllers
		JoystickButton intakeXboxButton = new JoystickButton (xboxController, XBOX_BUTTON_X);
		intakeXboxButton.whileHeld(new IntakeCubeInCommand());
		
		JoystickButton outtakeXboxButton = new JoystickButton (xboxController, XBOX_BUTTON_B);
		outtakeXboxButton.whileHeld(new IntakeCubeOutCommand());
		
		JoystickButton climbUpXboxButton = new JoystickButton (xboxController, XBOX_BUTTON_Y);
		climbUpXboxButton.whileHeld(new ClimberLaunchCommand());
		
		JoystickButton climbDownXboxButton = new JoystickButton (xboxController, XBOX_BUTTON_A);
		climbDownXboxButton.whileHeld(new ClimberRetractCommand());
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
	public static Gyro getGyroA() {
		return gyro;
	}
}