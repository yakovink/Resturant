package Utils;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class SpecificTime implements Comparable<SpecificTime>{
	LocalDate date;
	LocalTime time;
	public SpecificTime(LocalDate date, LocalTime time) {
		this.date = date;
		this.time = time;
	}
	
	public TimeDistance Distance(SpecificTime st) throws IOException {
		if(st==null) {
			st=SpecificTime.now();
		}
		int Seconds=time.getSecond()-st.getTime().getSecond();
		int Minutes=time.getMinute()-st.getTime().getMinute();
		int Hours=time.getHour()-st.getTime().getHour();
		int Days=date.getDayOfMonth()-st.getDate().getDayOfMonth();
		int Months=date.getMonthValue()-st.getDate().getMonthValue();
		int Years=date.getYear()-st.getDate().getYear();
		if(Seconds<0) {
			Minutes-=1;
			Seconds+=60;
		}
		if(Minutes<0) {
			Hours-=1;
			Minutes+=60;
		}
		if(Hours<0) {
			Days-=1;
			Hours+=24;
		}
		if(Days<0) {
			Months-=1;
			Days+=30;
		}
		if(Months<0) {
			Years-=1;
			Months+=12;
		}
		if(Years<0) {
			throw new IOException("the older time first");
		}
		return new TimeDistance(Years, Months, Days,Hours, Minutes, Seconds);
	}
	
	public static SpecificTime now() {
		return new SpecificTime(LocalDate.now(),LocalTime.now());
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return  date.format(DateTimeFormatter.ISO_LOCAL_DATE)+ " " + time.format(DateTimeFormatter.ISO_LOCAL_TIME);
	}

	@Override
	public int compareTo(SpecificTime o) {
		int i=this.date.compareTo(o.getDate());
		return (i==0)?this.time.compareTo(o.time):i;
	}
	
	
}
