package thread;

import java.util.Hashtable;
import java.util.Vector;

public class RoboThreadManager {
	private final static RoboThreadManager instance = new RoboThreadManager();
	private final Hashtable<String, AutomatonThreadGroup> threadGroups = new Hashtable<String, AutomatonThreadGroup>(); // <String, Vector>
	
	private RoboThreadManager(){}
	
	public static RoboThreadManager getInstance(){
		return instance;
	}
	
	public boolean createGroup(String groupID){
		if(threadGroups.containsKey(groupID)){
			return false;
		}else{
			threadGroups.put(groupID, new AutomatonThreadGroup());
			return true;
		}
	}
	
	public void removeGroup(String groupID){
		if(threadGroups.containsKey(groupID)){
			interruptThreads(groupID);
			threadGroups.remove(groupID);
		}
	}
	
	public boolean addThread(String groupID, RoboThread thread){
		System.out.printf("Adding %s to %s\n", thread.getName(), groupID);
		
		if(!threadGroups.containsKey(groupID)){
			this.createGroup(groupID);
			System.out.printf("Creating threadGroup %s...\n", groupID);
		}
		
		if(threadGroups.get(groupID).contains(thread))
			return false;
		
		threadGroups.get(groupID).addElement(thread);
		if(threadGroups.get(groupID).isAlive() && !thread.isAlive()){
			thread.start();
		}
		
		return true;
	}
	
	public void removeThread(String groupID, RoboThread thread){
		if(threadGroups.containsKey(groupID))
			if(threadGroups.get(groupID).contains(thread))
				threadGroups.get(groupID).removeElement(thread);
	}
	
	public boolean startThreads(String groupID){
		if(!threadGroups.containsKey(groupID)){
			System.err.printf("Thread group %s not found!\n", groupID);
			return false;
		}
		
		System.out.printf("Starting all threads in group %s...\n", groupID);
		return threadGroups.get(groupID).start();
	}
	
	public boolean interruptThreads(String groupID){
		
		if(threadGroups.containsKey(groupID)){
			System.out.printf("Interrupting all threads in group: %s\n", groupID);
			return threadGroups.get(groupID).interrupt();
		}else{
			return false;
		}
	}

	private class AutomatonThreadGroup extends Vector<RoboThread> {
		private boolean alive = false;
		
		public boolean isAlive(){
			return alive;
		}
		
		public boolean start(){
			for(int i = 0; i < this.size(); i++){
				this.get(i).start();
			}
			return true;
		}
		
		public boolean interrupt(){
			for(int i = 0; i < this.size(); i++){
				this.get(i).interrupt();
			}
			return true;
		}
	}
}