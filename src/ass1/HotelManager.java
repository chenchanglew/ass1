package ass1;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 
 * @author z5231006
 *
 * HotelManager represent the manager of each hotel.
 * Thus, each hotel has its own manager
 * Manager knows all the booking details which ONLY contains the CURRENT HOTEL
 */
public class HotelManager extends Hotel {
	
	private ArrayList<Booking> booking_details;
	
	public ArrayList<Booking> getBooking_details() {
		return booking_details;
	}

	public HotelManager(String name) {
		super(name);
		this.booking_details = new ArrayList<Booking>();
		// TODO Auto-generated constructor stub
	}
	
	public void add_bd(Booking new_booking) {
		this.booking_details.add(new_booking);
	}
	
	public Hotel check_request(Booking new_booking) {
		int [] request=new_booking.getRequest();
		Hotel tmp_hotel= new Hotel(this.getHotel_name());
		for(int i=0;i<3;i++) {
			//System.out.printf("current i=%d , needroom = %d, room size = %d\n",i,request[i],this.rooms.size());
			//int need= this.request[i];
			int need=request[i]; 
			int cur_room_index=0;
			
			while(need>0) {
				
				while(cur_room_index<this.rooms.size()) {
					//System.out.printf("need size %d, current room size %d\n",i+1,this.rooms.get(cur_room_index).getCapacity());
					//1.check the capacity of room,ignore if can step 2, else continue to next rooms
					if(this.rooms.get(cur_room_index).getCapacity()==i+1) { //capacity equal
						//2. now check for old_booked of that cur_room
						//System.out.printf("this room match capacity of %d\n",i+1);
						boolean available_book=true;
						for(Booking old_book: booking_details) {
							if(!old_book.check_available(this.rooms.get(cur_room_index).getRoom_number(),
									new_booking.getStart_date(),new_booking.getEnd_date())) {
								//System.out.printf("this room is not available to booked.\n");
								available_book=false;
								break;
							}
						}
						if(available_book) {
							//3. add room to the tmp_hotel, then look for next room
							//System.out.println("available boook");
							tmp_hotel.addRoom(this.rooms.get(cur_room_index));
							cur_room_index++;need--;
							break;
						}
					}
					cur_room_index++;
				}
				if(need==0) {break;}
				if(cur_room_index>=this.rooms.size()) {
					//4.need haven't fulfilled, this hotel fails
					//System.out.printf("cur_room_index>this room size, failed,%d\n",need);
					return null;
				}
				
			}
			if(need>0) {System.out.println("need>0 failed");return null;}
			//if success then go to the next type of room, then wait if it fails or not
		}
		// if we reach here, congraz we are success. start to register.
		// reorder the output to satisfy the system output.
		Hotel tmp_sort_hotel = new Hotel(this.getHotel_name());
		for (Room sort_room : this.rooms) {
			for (Room unsort_room : tmp_hotel.rooms) {
				if(sort_room.getRoom_number()==unsort_room.getRoom_number()) {
					tmp_sort_hotel.addRoom(sort_room);break;
				}
			}
		}
		return tmp_sort_hotel;
	}
	
	public void print() {
		Collections.sort(this.booking_details,new CustomComparator());
		for(Room room :this.rooms) {
			System.out.printf("%s %d",this.getHotel_name(),room.getRoom_number());
			for(Booking booking:this.booking_details) {
				for(Room room_details : booking.getHotel().rooms) {
					if(room_details.getRoom_number()==room.getRoom_number()) {
						System.out.printf(" %s %d %d",booking.getStart_month(),booking.getStart_date().getDayOfMonth(),booking.getPeriod());
						break;
					}
				}
			}
			System.out.println();
		}
	}
	
	public void delete_book(Booking rem_book) {
		int i=0;
		for(Booking booked : this.booking_details) {
			if(booked.equals(rem_book)) {break;}
			i++;
		}
		this.booking_details.remove(i);
	}
	
}
