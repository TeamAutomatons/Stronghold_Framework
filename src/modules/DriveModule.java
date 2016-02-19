package modules;

import org.usfirst.frc.team4528.robot.Hardware;

import edu.wpi.first.wpilibj.RobotDrive;
import thread.RoboThreadInterruptedException;

public class DriveModule extends ModuleBase {

	RobotDrive drive;
	
	public DriveModule(){
		super("Drive");
		drive = new RobotDrive(Hardware.vicFL, Hardware.vicBL, Hardware.vicFR, Hardware.vicBR);
	}

	@Override
	protected void activateAuto() {
		// Autonomous Routine
		try {
			drive.drive(0.4, 4); // Spin right at 0.4
			Thread.sleep(1000); // Wait 1.0 sec
			drive.drive(0.4, -4); // Spin left at 0.4
			Thread.sleep(1000); // Wait 1.0 sec
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void activateMan() {}

	@Override
	protected void loopAuto() throws RoboThreadInterruptedException {
		try {
			drive.drive(0, 4); // Forward at 0.4
			Thread.sleep(1000); // Wait 1.0 sec
			drive.drive(0, -4); // Forward at 0.4
			Thread.sleep(1000); // Wait 1.0 sec
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
//		throw new RoboThreadInterruptedException(); //HOLDUP DONT INTERRUPT!!
	}

	@Override
	protected void loopMan() {
		// Teleop Code
		drive.arcadeDrive(Hardware.controller);
	}

	@Override
	protected void deactivateAuto() {
		// Cleanup
		drive.stopMotor();
	}

	@Override
	protected void deactivateMan() {
		// Cleanup
		drive.stopMotor();
	}

}
