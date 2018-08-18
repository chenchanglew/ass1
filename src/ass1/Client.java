package ass1;
/**
 * 
 * @author z5231006
 * Each Client has his/her own booking and name.
 */
public class Client {
	private String name;
	private Booking booked;
	
	public Client(String name) {
		this.name=name;
	}
	
	public String getName() {
		return this.name;
	}

	public Booking getBooking() {
		return this.booked;
	}
	public void setBooking(Booking booking) {
		this.booked = booking;
	}
	
	public void print_Client() {
		Hotel booked_hotel = this.booked.getHotel();
		System.out.printf(" %s %s",this.name,booked_hotel.getHotel_name());
		for (Room booked_room : booked_hotel.rooms) {
			System.out.printf(" %d",booked_room.getRoom_number());
		}
		System.out.println("");
	}
	
}
