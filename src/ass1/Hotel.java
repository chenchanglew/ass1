package ass1;

import java.util.ArrayList;
/**
 * 
 * @author z5231006
 * Hotel represented
 */
public class Hotel {
	public ArrayList <Room> rooms;
	private String hotel_name;
	
	public Hotel(String name) {
		this.hotel_name=name;
		this.rooms= new ArrayList<Room>();
	}
	/**
	 * addRoom function is used to add new room into hotel.
	 * @param new_room
	 */
	public void addRoom(Room new_room) {
		this.rooms.add(new_room);
	}
	
	/**
	 * @return the hotel_name
	 */
	public String getHotel_name() {
		return hotel_name;
	}
	
	/**
	 * debug used
	 */
	public void printRooms() {
		System.out.printf("Hotel names=%s\n Rooms are:\n",hotel_name);
		for(int i=0;i<rooms.size();i++) {
			System.out.printf("rooms num= %d, rooms cap= %d\n",rooms.get(i).getRoom_number(),rooms.get(i).getCapacity());
		}
	}
}
