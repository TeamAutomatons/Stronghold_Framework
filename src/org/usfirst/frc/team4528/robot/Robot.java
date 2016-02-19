
package org.usfirst.frc.team4528.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import modules.DriveModule;
import thread.RoboThreadManager;

public class Robot extends IterativeRobot {

	RoboThreadManager rtm;
	
    public void robotInit() {
    	rtm = RoboThreadManager.getInstance();
    	
    	rtm.createGroup("auto");
		rtm.createGroup("man");
		
		/***********************************************************
		 * Create and add modules to 'auto' or 'man' thread groups
		 ***********************************************************/
		//For Example,
		rtm.addThread("man", new DriveModule());
		
    }
    
    public void disabledInit() {
    }

    public void autonomousInit() {
      System.out.println("Default IterativeRobot.autonomousInit() method... Overload me!");
    }

    public void teleopInit() {
      System.out.println("Default IterativeRobot.teleopInit() method... Overload me!");
    }
    
    public void disabledPeriodic() {
    }
    
    public void autonomousPeriodic() {
    }

    public void teleopPeriodic() {
    }
}
