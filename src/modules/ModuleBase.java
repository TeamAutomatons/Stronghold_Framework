package modules;

import org.usfirst.frc.team4528.robot.Hardware;

import thread.RoboThread;
import thread.RoboThreadInterruptedException;
import thread.RoboThreadManager;

public abstract class ModuleBase extends RoboThread {
	
	protected void turnOn(){
		//Do some things
		System.out.printf("Module %s: ACTIVE!\n", this.getName());
		
		if(Hardware.ds.isAutonomous())
			activateAuto();
		else
			activateMan();
	}
	
	protected void iteration() throws RoboThreadInterruptedException {
	
		if(Hardware.ds.isAutonomous())
			loopAuto();
		else
			loopMan();
	}
	
	protected void turnOff(){
		System.out.printf("Module %s: INACTIVE!\n", this.getName());

		if(Hardware.ds.isAutonomous())
			deactivateAuto();
		else
			deactivateMan();
	}
	
	public ModuleBase(String name){
		super(name);
	}
	
	public ModuleBase(String name, String groupID){
		this(name);
		RoboThreadManager.getInstance().addThread(groupID, this);
	}
	
	public ModuleBase(String name, String[] groupIDs){
		this(name);
		for(int i = 0; i < groupIDs.length; i++){
			RoboThreadManager.getInstance().addThread(groupIDs[i], this);
		}
	}

	protected abstract void activateAuto();
	protected abstract void activateMan();
	protected abstract void loopAuto() throws RoboThreadInterruptedException;
	protected abstract void loopMan();
	protected abstract void deactivateAuto();
	protected abstract void deactivateMan();
}
