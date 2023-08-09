package Utils;


public class TimeDistance implements Comparable<TimeDistance>{
	
	private Integer Years,Months,Days,Hours,Minutes,Seconds;



	public TimeDistance(int years, int months, int days, int hours, int minutes, int seconds) {
		super();
		Years = years;
		Months = months;
		Days = days;
		Hours = hours;
		Minutes = minutes;
		Seconds = seconds;
	}



	@Override
	public String toString() {
		return  Years + "/" + Months + "/" + Days + "  " + Hours+ ":" + Minutes + ":" + Seconds;
	}

	public int byHours() {
		return Hours+Days*24+Months*24*30+Years*24*365;
	}
	
	public int ByMinutes() {
		return Minutes+this.byHours()*60;
	}
	public int BySeconds() {
		return Seconds+this.ByMinutes()*60;
	}

	@Override
	public int compareTo(TimeDistance o) {
		if(Years!=0) {
			return Years;
		}
		if(Months!=0) {
			return Months;
		}
		if(Days!=0) {
			return Days;
		}
		if(Hours!=0) {
			return Hours;
		}
		if(Minutes!=0) {
			return Minutes;
		}
		return Seconds;
	}
}
