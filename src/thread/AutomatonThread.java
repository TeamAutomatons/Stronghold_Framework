package thread;

public class AutomatonThread extends Thread {

	protected boolean interrupted;
	protected long sleepTime = 30;
	
	public AutomatonThread() {
		super();
		interrupted = false;
	}
	
	protected void AutomatonSleep(long sleepTime) throws AutomatonThreadInterruptedException {
		if(interrupted)
			throw new AutomatonThreadInterruptedException();
		
		try{
			Thread.sleep(sleepTime);
		}catch(InterruptedException e){
			throw new AutomatonThreadInterruptedException();
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
		}catch(AutomatonThreadInterruptedException e){
			turnOff();
			interrupted = false;
		}
	}

	protected void turnOn(){}
	protected void iteration() throws AutomatonThreadInterruptedException {}
	protected void turnOff(){}

}
