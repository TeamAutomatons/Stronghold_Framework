package thread;

public class RoboThread extends Thread {

	protected boolean interrupted;
	protected long sleepTime = 30;
	
	public RoboThread() {
		super();
		interrupted = false;
	}
	
	public RoboThread(String name){
		super(name);
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
//			System.out.printf("Thread %s interrupted!\n", this.getName());
			super.interrupt();
		}
	}
	
	public void run(){
//		System.out.printf("Thread %s started!\n", this.getName());
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
