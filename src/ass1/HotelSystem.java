package ass1;

import java.time.LocalDate;
import java.util.ArrayList;

public class HotelSystem {
	private ArrayList <HotelManager> hotelmanagers;
	private ArrayList <Client> clients;
	private int year=2018;
	
	public HotelSystem() {
		this.hotelmanagers = new ArrayList <HotelManager>() ;
		this.clients = new ArrayList <Client>(); 
	}
	
	/**
	 * Is the receive the input and then change it into the format we want to process
	 * @param command
	 */
	public void manage_input(String command) {
		String[] commands= command.split(" ");
		//System.out.println(command);
		if(commands[0].equals("Change")||commands[0].equals("Cancel")||commands[0].equals("Booking")) {
			System.out.print(commands[0]);
		}
		int i=0;
		int change=0;
		Client cancelClient = new Client(commands[1]);
		switch (commands[0]) {
			case "Hotel":
				i=0;
				for(HotelManager exist : hotelmanagers) {
					if(exist.getHotel_name().equals(commands[1])) {break;}
					i++;
				}
				if(i<hotelmanagers.size()) { //hotel exist
					//System.out.println("hotel exist");
					Room new_room = new Room(Integer.parseInt(commands[2]),Integer.parseInt(commands[3]));
					hotelmanagers.get(i).addRoom(new_room);
					//hotelmanagers.get(i).printRooms();
				}
				else { //hotel doesn't exist
					HotelManager new_hotel= new HotelManager(commands[1]); 
					Room new_room = new Room(Integer.parseInt(commands[2]),Integer.parseInt(commands[3]));
					new_hotel.addRoom(new_room);
					hotelmanagers.add(new_hotel);
					//new_hotel.printRooms();
				}
				break;
			case "Change":
				change++; // change= cancel then booking
			case "Cancel":
				i=0;
				for(Client fclient: clients) {
					if(fclient.getName().equals(commands[1])) {break;}
					i++;
				}
				if(i>=clients.size()) {
					System.out.println(" rejected");
					break;
				}
				else {
					cancelClient = clients.get(i);
					// remove the client
					clients.remove(i);
					// start to remove the booking in hotelmanager
					int j=0;
					for (HotelManager hotelx: hotelmanagers) {
						if(hotelx.getHotel_name().equals(cancelClient.getBooking().getHotel().getHotel_name())) {break;}
						j++;
					}
					if(j<hotelmanagers.size()) {
						hotelmanagers.get(j).delete_book(cancelClient.getBooking());
					}
					//hotelmanagers.get(j)
					//hotelmanagers.get(arg0)(cancelClient.getBooking());
				}
				if(change==0) {//case : cancel
					System.out.printf(" %s\n",commands[1]);
					break;
				}
			case "Booking":
				
				Client new_client = new Client(commands[1]);
				Date_to_local dtl = new Date_to_local();
				LocalDate start_date= dtl.date_to_local_function(year,commands[2],commands[3]);
				LocalDate end_date=start_date.plusDays(Integer.parseInt(commands[4])-1);
				
				// put date inside
				Booking new_booking = new Booking(commands[2],start_date,end_date,Integer.parseInt(commands[4]));
				for(i=5;i<commands.length;i+=2) {
					new_booking.add_request(commands[i], commands[i+1]);
				}
				//check if date available
				i=0;
				Hotel accept_hotel=null;
				for (HotelManager hotelx : hotelmanagers) {
					accept_hotel=hotelx.check_request(new_booking);
					if(accept_hotel!=null) {
						break;
					}
					//if(request.check_request(hotelx)) {break;}
					i++;
				}
				if(i>=hotelmanagers.size()) {
					System.out.println(" rejected");
					if(change==1) {//means this in change condition.
						clients.add(cancelClient);
						int j=0;
						for (HotelManager hotelx: hotelmanagers) {
							if(hotelx.getHotel_name().equals(cancelClient.getBooking().getHotel().getHotel_name())) {break;}
							j++;
						}
						if(j<hotelmanagers.size()) {
							hotelmanagers.get(j).add_bd(cancelClient.getBooking());
						}
					}
				}
				else {
					hotelmanagers.get(i).add_bd(new_booking);
					new_booking.setHotel(accept_hotel);
					new_client.setBooking(new_booking);
					
					clients.add(new_client);
					new_client.print_Client();
					//System.out.println("Booking success");
				}
				
				break;
			case "Print":
				for(HotelManager hotelx: hotelmanagers) {
					if(hotelx.getHotel_name().equals(commands[1])) {
						hotelx.print();
						break;
					}
				}
				break;
			default:
				System.out.println("Invalid Input\n");
		}
	}
}
