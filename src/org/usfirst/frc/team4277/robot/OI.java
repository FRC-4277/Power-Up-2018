/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4277.robot;

import org.usfirst.frc.team4277.robot.commands.ClimberLaunchCommand;
import org.usfirst.frc.team4277.robot.commands.ClimberRetractCommand;
import org.usfirst.frc.team4277.robot.commands.IntakeCommandGroup;
import org.usfirst.frc.team4277.robot.commands.IntakeCubeCommand;
import org.usfirst.frc.team4277.robot.commands.IntakeShooterAdjustGroup;
import org.usfirst.frc.team4277.robot.commands.OuttakeCommandGroup;
import org.usfirst.frc.team4277.robot.commands.OuttakeCubeCommand;
import org.usfirst.frc.team4277.robot.commands.Shoot;
import org.usfirst.frc.team4277.robot.commands.ShootBack;
import org.usfirst.frc.team4277.robot.commands.TipperDownCommand;
import org.usfirst.frc.team4277.robot.commands.TipperUpCommand;
import org.usfirst.frc.team4277.robot.commands.WinchDownCommand;
import org.usfirst.frc.team4277.robot.commands.WinchUpCommand;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.interfaces.Gyro;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI implements PortMap{
	
	public static Joystick driveStick = new Joystick(JOYSTICK);
	//public static JoystickButton dSTrigger = new JoystickButton(driveStick,1);
	public static Joystick xboxController = new Joystick(2);
	public static ADXRS450_Gyro gyro = new ADXRS450_Gyro();
	public static DigitalInput photoElectric = new DigitalInput(1);

	public OI() {
		//Pilot Controls
		JoystickButton shooterTrigger = new JoystickButton(driveStick, 1);
		shooterTrigger.whileHeld(new Shoot());
		
		JoystickButton intakeButton = new JoystickButton (driveStick, 6);
		intakeButton.whileHeld(new IntakeShooterAdjustGroup());
		
		JoystickButton outtakeButton = new JoystickButton (driveStick, 4);
		outtakeButton.whenPressed(new IntakeCommandGroup());		
		
		JoystickButton climbUpButton = new JoystickButton (driveStick, 5);
		climbUpButton.whileHeld(new ClimberLaunchCommand()); 

		JoystickButton climbDownButton = new JoystickButton (driveStick, 3);
		climbDownButton.whileHeld(new ClimberRetractCommand());
		
		JoystickButton winchUpButton = new JoystickButton(driveStick, 9);
		winchUpButton.whileHeld(new WinchUpCommand());

		JoystickButton winchDownButton = new JoystickButton(driveStick, 11);
		winchDownButton.whileHeld(new WinchDownCommand());
		
		JoystickButton tipperEngageButton = new JoystickButton(driveStick, 10);
		tipperEngageButton.whenPressed(new TipperDownCommand());
		
		JoystickButton tipperDisengageButton = new JoystickButton(driveStick, 12);
		tipperDisengageButton.whenPressed(new TipperUpCommand());
		
		JoystickButton vaultOuttake = new JoystickButton(driveStick, 2);
		vaultOuttake.whileHeld(new OuttakeCommandGroup());
		
		JoystickButton shootOut = new JoystickButton(driveStick, 7);
		shootOut.whileHeld(new ShootBack());
		
		
		//XboxButtonControllers
		JoystickButton intakeXboxButton = new JoystickButton (xboxController, XBOX_BUTTON_X);
		intakeXboxButton.whileHeld(new OuttakeCubeCommand());
		
		JoystickButton outtakeXboxButton = new JoystickButton (xboxController, XBOX_BUTTON_B);
		outtakeXboxButton.whileHeld(new IntakeCubeCommand());
		
		JoystickButton climbUpXboxButton = new JoystickButton (xboxController, XBOX_BUTTON_Y);
		climbUpXboxButton.whileHeld(new TipperDownCommand());
		
		JoystickButton climbDownXboxButton = new JoystickButton (xboxController, XBOX_BUTTON_A);
		climbDownXboxButton.whileHeld(new TipperUpCommand());
		
//		JoystickButton winchUpXboxButton = new JoystickButton (xboxController, XBOX_JOY_LEFT_BUTTON);
//		winchUpXboxButton.whileHeld(new WinchUpCommand());
		
//		JoystickButton winchDownXboxButton = new JoystickButton (xboxController, XBOX_JOY_RIGHT_BUTTON);
//		winchDownXboxButton.whileHeld(new WinchDownCommand());
		
		
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
	//public static boolean getdSTrigger() {
	//	return dSTrigger.get();
	//}
	public static double getGyro() {
		return gyro.getAngle();
	}
	public static Gyro getGyroA() {
		return gyro;
	}
	public static boolean getPhotoElectric() {
		return photoElectric.get();
	}
}