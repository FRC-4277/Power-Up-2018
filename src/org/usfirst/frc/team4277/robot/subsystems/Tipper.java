package org.usfirst.frc.team4277.robot.subsystems;

import org.usfirst.frc.team4277.robot.PortMap;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Solenoid;

/**
 *
 */
public class Tipper extends Subsystem implements PortMap {
	
	public Solenoid tipperSolenoid;

	public Tipper (int modNumber, int channel) {
		tipperSolenoid = new Solenoid(modNumber, channel);
	}	

    public void initDefaultCommand() {
    	up();
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void down() {
    	tipperSolenoid.set(true);
    }
    public void up() {
    	tipperSolenoid.set(false);
    }
}

