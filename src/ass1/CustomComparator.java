package ass1;

import java.util.Comparator;

public class CustomComparator implements Comparator<Booking> {
    @Override
    public int compare(Booking o1, Booking o2) {
        return o1.getStart_date().compareTo(o2.getStart_date());
    }
}