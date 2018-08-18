package ass1;

public class Room {
	private int room_number;
	private int capacity;
	
	/**
	 * this is the initialize of Room
	 * @param room_num
	 * @param cap
	 */
	public Room(int room_num, int cap) {
		this.room_number=room_num;
		this.capacity=cap;
	}

	/**
	 * @return the room_number
	 */
	public int getRoom_number() {
		return room_number;
	}

	/**
	 * @return the capacity
	 */
	public int getCapacity() {
		return capacity;
	}
	

	
}
