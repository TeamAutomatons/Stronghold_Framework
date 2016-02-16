package org.usfirst.frc.team4528.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Victor;

public class Hardware {
	public static final Victor vicFL = new Victor(0);
	public static final Victor vicFR = new Victor(1);
	public static final Victor vicBL = new Victor(2);
	public static final Victor vicBR = new Victor(3);

//	public static final Victor extra1 = new Victor(4);
//	public static final Victor extra2 = new Victor(5);
	
    public static DoubleSolenoid shooter = new DoubleSolenoid(0, 0, 1);
    
    public static Joystick controller = new Joystick(0);

    
}