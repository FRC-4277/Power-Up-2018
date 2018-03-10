package org.usfirst.frc.team4277.robot.subsystems;

import org.usfirst.frc.team4277.robot.PortMap;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Tipper extends Subsystem implements PortMap {
	
	public Solenoid tipperSolenoid;

	public Tipper (int modNumber, int channel) {
	//	tipperSolenoid = new Solenoid(modNumber, channel);
	}	

    public void initDefaultCommand() {
    	up();
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void down() {
    //	tipperSolenoid.set(true);
    }
    public void up() {
    //	tipperSolenoid.set(false);
    }
}

