
package org.usfirst.frc.team4528.robot;


import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends SampleRobot {
    Victor left, right;
    Joystick stick;
    //Solenoid push, retract;
    DoubleSolenoid fire;
    DoubleSolenoid lift;
    
    public Robot() {
        stick = new Joystick(0);
        left = new Victor(0);
        right = new Victor(1);
        //push = new Solenoid(0);
        //retract = new Solenoid(1);
        fire = new DoubleSolenoid(0, 0, 1);
        lift = new DoubleSolenoid(0, 2, 3);
    }
    
    public void robotInit() {
        SmartDashboard.putString("TestTxt", "Testing...");
        System.out.println("Testing...");
    }

    public void autonomous() {}

    public void operatorControl() {
    	while (isOperatorControl() && isEnabled()) {
    		boolean buttonA = stick.getRawButton(1);
    		boolean buttonB = stick.getRawButton(2);
    		boolean trigger = stick.getRawAxis(3) > 0.2;
    		boolean buttonC = stick.getRawButton(3);
    		boolean buttonD = stick.getRawButton(4);
    		
    		
            if(buttonA & !buttonB){
            	left.set(1.0);
            	right.set(-1.0);
            }else if(!buttonA & buttonB){
            	left.set(-0.4);
            	right.set(0.4);
            }else if(!buttonA & !buttonB){
            	left.set(0);
            	right.set(0);
            }
            
            if(!trigger){
            	fire.set(DoubleSolenoid.Value.kForward);
            }else{
            	fire.set(DoubleSolenoid.Value.kReverse);
            }
            
            if(buttonC & !buttonD){
            	lift.set(DoubleSolenoid.Value.kForward);
            }else if(!buttonC & buttonD){
            	lift.set(DoubleSolenoid.Value.kReverse);
            }
            
            //push.set(trigger);
            //retract.set(!trigger);
        	System.out.println(stick.getRawAxis(3));
        }
    }
    
    public void test() {}
}
