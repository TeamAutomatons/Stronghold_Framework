
package org.usfirst.frc.team4528.robot;


import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends SampleRobot {
    Victor driveLeft, driveRight;
    Talon shooterLeft, shooterRight, shooterAim1, shooterAim2;
	RobotDrive Skylar;
    Joystick stick;
    DoubleSolenoid fire, lift, climber;
    DigitalInput limitSwitch1, limitSwitch2;

    public Robot() {
        stick = new Joystick(0);
        shooterLeft = new Talon(0);
        shooterRight = new Talon(1);
        fire = new DoubleSolenoid(0, 0, 1);
        lift = new DoubleSolenoid(0, 2, 3);
        climber = new DoubleSolenoid(0, 6, 7);
        shooterAim1 = new Talon(2);
        shooterAim2 = new Talon(3);
        driveLeft = new Victor(5);
        driveRight = new Victor(4);
        limitSwitch1= new DigitalInput(1); //Implementing these two when limitSwitches are added
        limitSwitch2 = new DigitalInput(1);//
        Skylar = new RobotDrive(driveLeft, driveRight);
        Skylar.setExpiration(0.5);
    }
    
    public void robotInit() {
        SmartDashboard.putString("TestTxt", "Testing...");
        System.out.println("Testing...");
    }

    public void autonomous() {}
    
    public void operatorControl() {
        Skylar.setSafetyEnabled(true);
        int climbCounter = 0;
        while (isOperatorControl() && isEnabled()) {
        	boolean buttonA = stick.getRawButton(1);
    		boolean buttonB = stick.getRawButton(2);
    		boolean rightTrigger = stick.getRawAxis(3) > 0.2;
    		boolean leftTrigger = stick.getRawAxis(2) > 0.2;
    		boolean buttonC = stick.getRawButton(3);
    		boolean buttonD = stick.getRawButton(4);
    		boolean buttonE = stick.getRawButton(5);
    		boolean buttonF = stick.getRawButton(6);
    		boolean retrieve = buttonA;
    		boolean throttleUp = buttonB;
    		boolean shoot = rightTrigger;
    		boolean liftUp = buttonC;
    		boolean liftDown = buttonD;
    		boolean shooterAimUp = buttonE;
    		boolean shooterAimDown = leftTrigger;
    		boolean climb = buttonF;
    		double leftDrive, rightDrive;
    		leftDrive = -stick.getRawAxis(1);
    		rightDrive = -stick.getRawAxis(5);
    		
    		Skylar.tankDrive(leftDrive, rightDrive);    
    		Timer.delay(.005);
    		    		
    		if(retrieve & !throttleUp){
            	shooterLeft.set(0.3);
            	shooterRight.set(-0.3);
            }else if(!retrieve & throttleUp){
            	shooterLeft.set(-1.0);
            	shooterRight.set(1.0);
            }else if(!retrieve & !throttleUp){
            	shooterLeft.set(0);
            	shooterRight.set(0);
            }
            
           if(shooterAimUp & !shooterAimDown){
               	shooterAim1.set(0.25);
            	shooterAim2.set(-0.25);
           }else if(!shooterAimUp & shooterAimDown){
            	shooterAim1.set(-0.25);
                shooterAim2.set(0.25);
            }else if(!shooterAimUp & !shooterAimDown){
            	shooterAim1.set(0);
            	shooterAim2.set(0);
            }
            
            if(!shoot){
            	fire.set(DoubleSolenoid.Value.kForward);
            }else{
            	fire.set(DoubleSolenoid.Value.kReverse);
            }
            
            if(liftUp & !liftDown){
            	lift.set(DoubleSolenoid.Value.kForward);
            }else if(!liftUp & liftDown){
            	lift.set(DoubleSolenoid.Value.kReverse);
            }
            
            if(climb & climbCounter == 0){
            	climber.set(DoubleSolenoid.Value.kForward);
            	climbCounter = 1;
            }else if(climb & climbCounter == 1){
            	climber.set(DoubleSolenoid.Value.kReverse);
            	climbCounter = 0;
            }
            //push.set(trigger);
            //retract.set(!trigger);
        	System.out.println(stick.getRawAxis(3));
        }
    }

    /**
     * Runs during test mode
     */
    public void test() {
    }
}
