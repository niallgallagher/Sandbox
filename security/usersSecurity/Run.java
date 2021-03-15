package usersSecurity;
``````````
public class Run {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Example();
	}
	
	public static void Example() {
		Services services = new Services();
		int userID = 1234;
		int fackID = 4321;
		String msg = "I have encrypted this text";
		
		System.out.println("My msg is: "+ msg);
		System.out.println("-------------------------------------");
		//encryption
		String msgE = services.encryption(userID, msg);
		System.out.println("My encrypted msg is: "+ msgE);
		System.out.println("-------------------------------------");
		
		//decryption with fack ID
		String msgD = services.decryption(fackID, msgE);
		System.out.println("My decrypted msg with ID: "+ userID + ", reads the following: " + msgD);
		System.out.println("-------------------------------------");
		
		//decryption with correct user ID
		msgD = services.decryption(userID, msgE);
		System.out.println("My decrypted msg with ID: "+ userID + ", reads the following: " + msgD);
	}

}
