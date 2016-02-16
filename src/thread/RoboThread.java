package thread;

public class RoboThread extends Thread {

	protected boolean interrupted;
	protected long sleepTime = 30;
	
	public RoboThread() {
		super();
		interrupted = false;
	}
	
	protected void AutomatonSleep(long sleepTime) throws RoboThreadInterruptedException {
		if(interrupted)
			throw new RoboThreadInterruptedException();
		
		try{
			Thread.sleep(sleepTime);
		}catch(InterruptedException e){
			throw new RoboThreadInterruptedException();
		}		
	}
	
	public void interrupt(){
		if(isAlive()){
			interrupted = true;
			super.interrupt();
		}
	}
	
	public void run(){
		turnOn();
		try{
			while(true){
				iteration();
				AutomatonSleep(sleepTime);
			}
		}catch(RoboThreadInterruptedException e){
			turnOff();
			interrupted = false;
		}
	}

	protected void turnOn(){}
	protected void iteration() throws RoboThreadInterruptedException {}
	protected void turnOff(){}

}
