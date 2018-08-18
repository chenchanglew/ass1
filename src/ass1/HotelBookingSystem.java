package ass1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class HotelBookingSystem {

	/**
	 * Main function
	 * @param args
	 */
	public static void main(String[] args) {
		  HotelSystem hotelsystem1 = new HotelSystem();
		  Scanner sc = null;
		  try{
	          sc = new Scanner(new File(args[0]));
	          while(sc.hasNextLine()) {
	        	  String s= sc.nextLine();
	        	  hotelsystem1.manage_input(s);
	          }
	      }
	      catch (FileNotFoundException e){
	          System.out.println(e.getMessage());
	      }
	      finally{
	          if (sc != null) sc.close();
	      }
	
	}
}
