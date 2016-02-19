
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
		rtm.addThread("auto", new DriveModule());
		rtm.addThread("man", new DriveModule());
		
    }
    
    public void disabledInit() {
    	rtm.interruptThreads("auto");
    	rtm.interruptThreads("man");
    }

    public void autonomousInit() {
    	rtm.startThreads("auto");
    }

    public void teleopInit() {
    	rtm.startThreads("man");
    }
    
    public void disabledPeriodic() {
    	// Meh
    }
    
    public void autonomousPeriodic() {
    	// Nothing there really to do...
    }

    public void teleopPeriodic() {
    	// Damn these thread taking all our jobs...
    }
}
