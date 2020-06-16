import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;


public class ServingCustomers {
	
	static int served;
	static int totalIdleTime;		//total break time
	static int maxBreak = 0;			//maximum break time
	static int maxQueue = 0;
	static ArrayList<Customer> customersArray;
	static int SERVICETIME;


	public static void main(String args[]){
		
		int currentTime = 9*3600;
		
		customersArray = new ArrayList<Customer>();
		
		LinkedQueue<Customer> customers = readCustomers("customersfile.txt");
		
		LinkedQueue<Customer> inQueue = new LinkedQueue<Customer>();

		while (currentTime < 17*3600) {

			ArrayList<Customer> toBeRemoved = new ArrayList<Customer>();
			
			//add people who arrive before or at the currentTime to the queue
			for (int i = 0; i < customersArray.size(); i++) {
				if (customersArray.get(i).getArrivalTime() <= currentTime) {
					inQueue.enqueue(customersArray.get(i));
					toBeRemoved.add(customersArray.get(i));
				}
			}
			
			//remove people in queue from the customers array
			for (int x = 0; x < toBeRemoved.size(); x++) {
				for (int y = 0; y < customersArray.size(); y++) {
					if (toBeRemoved.get(x) == customersArray.get(y)) {
						customersArray.remove(y);
					}
				}
			}
			
			//if there is someone in line
			if (!inQueue.isEmpty()) {

				
				//update max length of the line
				if(inQueue.size() > maxQueue) {
				 maxQueue = inQueue.size();
				}
				
				Customer current = inQueue.dequeue();
				served++;			//increment number served
				
				//set waiting time for those arriving after 9
				if (current.getArrivalTime() > 9*3600) {
					current.setWaitingTime(currentTime - current.getArrivalTime());
					currentTime += SERVICETIME;			//increment serving time
				}
				
			}
			
			//if there is nobody in line
			else if (!customersArray.isEmpty() && inQueue.isEmpty()) {
				int breakTime = customersArray.get(0).getArrivalTime() - currentTime;
				totalIdleTime += breakTime;
				if(breakTime > maxBreak) {
					maxBreak = breakTime;
				}
				currentTime = customersArray.get(0).getArrivalTime();
			}
			
			//if there is no other customer until the end of the day
			else {
				System.out.println("nobody in line EVER");
				int breakTime = 17*3600 - currentTime;
				totalIdleTime += breakTime;
				if (breakTime > maxBreak) {
					maxBreak = breakTime;
				}
				currentTime = 17*3600;
			}
		}
			
		try {
			String input;
			BufferedReader br = new BufferedReader(new FileReader("queriesfile.txt"));
			while( (input = br.readLine()) != null){
				switch(input){
				case "NUMBER-OF-CUSTOMERS-SERVED":{
					System.out.println("Number of Customers Served: " + served);
					break;
				}
				case "LONGEST-BREAK-LENGTH":{
					System.out.println("Longest Break Length: " + maxBreak);
					break;
				}
				case "TOTAL-IDLE-TIME":{
					System.out.println("Total Idle Time: " + totalIdleTime);
					break;
				}
				case "MAXIMUM-NUMBER-OF-PEOPLE-IN-QUEUE-AT-ANY-TIME":{
					System.out.println("Maximum Number of People in Queue at Any Time: " + maxQueue);
					break;
				}
				default: {
					if(input.matches("WAITING-TIME-OF \\d+")){
						String[] splitted = input.split("\\s+");
						int id = Integer.parseInt(splitted[1]);
						Customer cust;
						for(int j = 0; j < customers.size(); j++) {
							cust = customers.dequeue();
							if(cust.getID() == id) {
								System.out.println("Waiting Time of " + id + ": " + cust.getWaitingTime());
							}
							customers.enqueue(cust);
						}
						
					}
					break;
				}
				}
			}
			br.close();

		} catch(Exception ex){
			System.out.println( "---QUERIES FILE NOT FOUND---");	
		} 
				
	}

	private static LinkedQueue<Customer> readCustomers(String arg){
		LinkedQueue<Customer> customers = new LinkedQueue<Customer>();
		try {
			String input;
			BufferedReader br = new BufferedReader(new FileReader(arg));
			input = br.readLine();
			SERVICETIME = Integer.parseInt(input);
			while((input=br.readLine()) !=null){
				int id;
				String clock;
				input = br.readLine();
				id = Integer.parseInt(input.split("  ")[1]);
				input = br.readLine();
				clock = input.split(" ")[1];
				//place the customer into the queue and array
				Customer temp = new Customer(id, clock);
				customers.enqueue(temp);
				customersArray.add(temp);
			}
			br.close();
		}
		catch(Exception ex){
			System.out.println( "---CUSTOMERS FILE NOT FOUND---");	
			ex.printStackTrace();
		}
		return customers;
	}
	
	public static int extractID(String query){
		int ID = 0;
		char[] chars = query.toCharArray();
			for(char c : chars){
				if(Character.isDigit(c)){
		            ID = c;
		        }
		     }
			return ID;
	}
	
}
