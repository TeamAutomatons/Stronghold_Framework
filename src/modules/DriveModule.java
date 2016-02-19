package modules;

import thread.RoboThreadInterruptedException;

public class DriveModule extends ModuleBase {

	public DriveModule(){
		super("Drive");
	}

	@Override
	protected void activateAuto() {
		// Autonomous Routine
	}

	@Override
	protected void activateMan() {}

	@Override
	protected void loopAuto() throws RoboThreadInterruptedException {
		throw new RoboThreadInterruptedException();
	}

	@Override
	protected void loopMan() {
		// Teleop Code
	}

	@Override
	protected void deactivateAuto() {
		// Cleanup
	}

	@Override
	protected void deactivateMan() {
		// Cleanup
	}

}
