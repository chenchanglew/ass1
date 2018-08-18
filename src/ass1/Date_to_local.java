package ass1;

import java.time.LocalDate;

public class Date_to_local {
	
	public LocalDate date_to_local_function(int year,String m, String d) {
		LocalDate Date = LocalDate.of(year,month_to_int(m),Integer.parseInt(d));
		return Date;
	}
	
	public int month_to_int(String m) {
		switch(m) {
			case "Dec":
				return 12;
			case "Nov":
				return 11;
			case "Oct":
				return 10;
			case "Sep":
				return 9;
			case "Aug":
				return 8;
			case "Jul":
				return 7;
			case "Jun":
				return 6;
			case "May":
				return 5;
			case "Apr":
				return 4;
			case "Mar":
				return 3;
			case "Feb":
				return 2;
			case "Jan":
				return 1;
			default:
				System.out.println("Invalid Month Input!");
				return 0;
		}
	}

}
