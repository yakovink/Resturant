package Utils;


/*because the time 0:0:0 is ilegal for localtime and local date i build special object which can save that value.
 * it used to calculate the times in crm thread and the age in the age restriction**/

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



	public Integer getYears() {
		return Years;
	}



	public void setYears(Integer years) {
		Years = years;
	}



	public Integer getMonths() {
		return Months;
	}



	public void setMonths(Integer months) {
		Months = months;
	}



	public Integer getDays() {
		return Days;
	}



	public void setDays(Integer days) {
		Days = days;
	}



	public Integer getHours() {
		return Hours;
	}



	public void setHours(Integer hours) {
		Hours = hours;
	}



	public Integer getMinutes() {
		return Minutes;
	}



	public void setMinutes(Integer minutes) {
		Minutes = minutes;
	}



	public Integer getSeconds() {
		return Seconds;
	}



	public void setSeconds(Integer seconds) {
		Seconds = seconds;
	}
}
