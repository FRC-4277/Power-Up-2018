/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4277.robot;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Compressor;
import org.usfirst.frc.team4277.robot.commands.Drive;
import org.usfirst.frc.team4277.robot.commands.DriveTimed;
import org.usfirst.frc.team4277.robot.commands.IntakeCubeInCommand;
import org.usfirst.frc.team4277.robot.commands.IntakeCubeOutCommand;
import org.usfirst.frc.team4277.robot.commands.ClimberLaunchCommand;
import org.usfirst.frc.team4277.robot.commands.Shoot;
import org.usfirst.frc.team4277.robot.commands.TipperDownCommand;
import org.usfirst.frc.team4277.robot.commands.TipperUpCommand;
import org.usfirst.frc.team4277.robot.commands.WinchUpCommand;
import org.usfirst.frc.team4277.robot.subsystems.Climber;
import org.usfirst.frc.team4277.robot.subsystems.Intake;
import org.usfirst.frc.team4277.robot.subsystems.Crane;
import org.usfirst.frc.team4277.robot.subsystems.MecanumDrive;
import org.usfirst.frc.team4277.robot.subsystems.Shooter;
import org.usfirst.frc.team4277.robot.subsystems.Tipper;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 * This is a test
 */
public class Robot extends TimedRobot implements PortMap {
	
	
	public static Preferences prefs;
	public static OI m_oi;
	public static final MecanumDrive driveTrain= new MecanumDrive(DRIVE_FRONT_RIGHT, DRIVE_FRONT_LEFT, DRIVE_BACK_RIGHT, DRIVE_BACK_LEFT);
	public static final Shooter shooter = new Shooter(SHOOTER_LEFT, SHOOTER_RIGHT);
	public static final Intake intake = new Intake(INTAKE_LEFT, INTAKE_RIGHT);
	public static final Climber climber = new Climber(CLIMBER_BACK_WINCH, CLIMBER_FRONT_WINCH);
	public static final Crane launcher = new Crane(CLIMBER_LAUNCHER_MOTOR);
	public static final Tipper tipper = new Tipper(PNUEMATIC_CONTROL_MODULE_CAN_ID,0);
	
	Command autoCommand;
	SendableChooser<Command> sendableChooser = new SendableChooser<>();
	public UsbCamera cameraOne;
	public UsbCamera cameraTwo;
	public Compressor comp = new Compressor(PNUEMATIC_CONTROL_MODULE_CAN_ID);
	


	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		System.out.println("Robot int");
		// Initialize
		m_oi = new OI();
	
		
		// initalize preferences
		System.out.println("Preferences created");
		prefs = Preferences.getInstance();
		
		// Add Autonomous Chooser
		
		// chooser.addObject("My Auto", new MyAutoCommand());

		SmartDashboard.putData("Auto mode", sendableChooser);
		sendableChooser.addDefault("Drive Foreward Auto", new DriveTimed());
		

		
		// Add commands
		
		SmartDashboard.putData("Drive command", new Drive());
		SmartDashboard.putData("Cube in command", new IntakeCubeInCommand());
		SmartDashboard.putData("Cube out command", new IntakeCubeOutCommand());
		SmartDashboard.putData("Launch command", new ClimberLaunchCommand());
		SmartDashboard.putData("Shoot command", new Shoot());
		SmartDashboard.putData("Winch up Command", new WinchUpCommand());
		SmartDashboard.putData("Tip up command", new TipperUpCommand());
		SmartDashboard.putData("Tip down command", new TipperDownCommand());

		
		LiveWindow.add(new Drive());
		LiveWindow.add(new IntakeCubeInCommand());
		LiveWindow.add(new IntakeCubeOutCommand());
		LiveWindow.add(new ClimberLaunchCommand());
		LiveWindow.add(new Shoot());
		LiveWindow.add(new TipperDownCommand());
		LiveWindow.add(new TipperUpCommand());
		
		

		cameraOne = CameraServer.getInstance().startAutomaticCapture(0);
		cameraTwo = CameraServer.getInstance().startAutomaticCapture(1);

	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		autoCommand = sendableChooser.getSelected();

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		if (autoCommand != null) {
			autoCommand.start();
		}
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autoCommand != null) {
			autoCommand.cancel();
		}
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		comp.setClosedLoopControl(true);
		//tipper.set(true);
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
}
