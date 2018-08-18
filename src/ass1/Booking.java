package ass1;

import java.time.LocalDate;
/**
 * 
 * @author z5231006
 * booking means each independent booking
 * which inside contains hotel and booking periods
 * the hotel inside contains ONLY the room booked by the certain book.
 */
public class Booking {
	private Hotel hotel;
	private String start_month;
	private LocalDate start_date;
	private LocalDate end_date;
	private int period;
	private int[] request; //the number of needed for each type of room
	
	public Hotel getHotel() {
		return hotel;
	}

	public String getStart_month() {
		return start_month;
	}
	
	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public LocalDate getStart_date() {
		return start_date;
	}

	public LocalDate getEnd_date() {
		return end_date;
	}
	
	public int getPeriod() {
		return period;
	}

	public int[] getRequest() {
		return this.request;
	}

	public Booking(String stm,LocalDate st,LocalDate ed,int period) {
		this.request = new int[3];
		this.start_month=stm;
		this.start_date=st;
		this.end_date=ed;
		this.period=period;
	}
	
	public void add_request(String room_type, String value ) {
		this.request[transform(room_type)]=Integer.parseInt(value);
		//System.out.printf("add_request suscess, %d with demand %d\n",transform(room_type),Integer.parseInt(value));
	}
	
	
	
	public boolean check_available(int roomnum, LocalDate st, LocalDate en) {
		//System.out.printf("need room %d, check if my hotel contains that room number with conflict date\n",roomnum);
		for(Room room_booked: this.hotel.rooms) {
			if(room_booked.getRoom_number()==roomnum) {// this room has other booking
				
				/*System.out.printf("check if the date available for room%d, start_date=",roomnum);
				System.out.print(this.getStart_date());
				System.out.printf(" end date=");
				System.out.println(this.getEnd_date());
				
				System.out.print("the new booking, start_date=");
				System.out.print(st);
				System.out.printf(" end date=");
				System.out.println(en);
				*/
				
				if     (this.getStart_date().isEqual(st) || this.getStart_date().isEqual(en)) {return false;}
				else if(this.getEnd_date().isEqual(st)   || this.getEnd_date().isEqual(en)) {return false;}
				else if(this.getStart_date().isBefore(st)&& this.getEnd_date().isAfter(st)) {return false;}
				else if(this.getStart_date().isBefore(en)&& this.getEnd_date().isAfter(en)) {return false;}
				else if(this.getStart_date().isAfter(st) && this.getEnd_date().isBefore(en)) {return false;}
			}
		}
		return true;
	}
	
	public int transform(String rt) {
		if(rt.equals("single")) {return 0;}
		else if (rt.equals("double")) {return 1;}
		else {return 2;}
	}	
}
