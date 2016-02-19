package thread;

import java.util.Hashtable;
import java.util.Vector;

public class RoboThreadManager {
	private final static RoboThreadManager instance = new RoboThreadManager();
	private final Hashtable<String, RoboThreadGroup> threadGroups = new Hashtable<String, RoboThreadGroup>();
	
	private RoboThreadManager(){}
	
	public static RoboThreadManager getInstance(){
		return instance;
	}
	
	public boolean createGroup(String groupID){
		System.out.printf("Creating %s thread group...", groupID);
		
		if(threadGroups.containsKey(groupID)){
			System.err.println("failed!");
			return false;
		}else{
			threadGroups.put(groupID, new RoboThreadGroup());
			System.out.println("Success!");
			return true;
		}
	}
	
	public void removeGroup(String groupID){
		System.out.printf("Removing thread group %s...\n", groupID);
		if(threadGroups.containsKey(groupID)){
			interruptThreads(groupID);
			threadGroups.remove(groupID);
		}
	}
	
	public boolean addThread(String groupID, RoboThread thread){
		System.out.printf("Adding %s to %s...\n", thread.getName(), groupID);
		
		if(!threadGroups.containsKey(groupID)){
			this.createGroup(groupID);
		}
		
		if(threadGroups.get(groupID).contains(thread)){
			System.out.printf("Thread %s already exists in %s!\n", thread.getName(), groupID);
			return false;
		}
		
		threadGroups.get(groupID).addElement(thread);
		
		if(threadGroups.get(groupID).isAlive() && !thread.isAlive()){
			thread.start();
		}
		
		return true;
	}
	
	public void removeThread(String groupID, RoboThread thread){
		System.out.printf("Removing %s from %s...\n", thread.getName(), groupID);
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
		
		if(!threadGroups.containsKey(groupID)){
			System.err.printf("Thread group %s not found!\n", groupID);
			return false;
		}
		
		System.out.printf("Interrupting all threads in group %s...\n", groupID);
		return threadGroups.get(groupID).interrupt();
	}

	private class RoboThreadGroup extends Vector<RoboThread> {
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