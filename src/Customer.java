
public class Customer {
	//Data
	private int ID;
	private int arrivalTime;
	private int waitingTime;
		
	Customer(int ID, String time){
		this.ID=ID;
				
		String[] times = time.split(":");
						
		int hour = Integer.parseInt(times[0]);
		//convert to 24h
		if(hour < 7) {
			hour += 12;
		} 
		this.arrivalTime = hour*3600 + Integer.parseInt(times[1])*60 + Integer.parseInt(times[2]);

		
		waitingTime = 0;
		
		//if arrival time is earlier than 9 AM
		if(this.arrivalTime < 9*3600) {
			waitingTime += (9*3600 - this.arrivalTime);
			//this.arrivalTime = 9*3600;
		}  
		
	}

	public int getID() {
		return ID;
	}
	
	public int getArrivalTime() {
		return arrivalTime;
	}
	
	public int getWaitingTime() {
		return waitingTime;
	}
	
	public void setWaitingTime(int WaitingTime) {
		this.waitingTime = WaitingTime;
	}
	
	
	
	
}

