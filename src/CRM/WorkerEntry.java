package CRM;

import Exceptions.ToLateException;

/*that is class for entry object with specific details for the crm thread for workers job process
 * the entry have comparability by the time from the last worker status change.**/



import Model.Worker;
import Utils.SpecificTime;
import Utils.TimeDistance;

public class WorkerEntry implements Comparable<WorkerEntry>{
	private Worker worker;
	private Boolean isBusy;
	private TimeDistance statusLong;
	
	
	public Worker getWorker() {
		return worker;
	}
	public void setWorker(Worker worker) {
		this.worker = worker;
	}
	public Boolean getIsBussy() {
		return isBusy;
	}
	public void setIsBussy(Boolean isBussy) {
		this.isBusy = isBussy;
	}
	public TimeDistance getStatusLong() {
		return statusLong;
	}
	public void setStatusLong(SpecificTime statusLong) throws  ToLateException {
		this.statusLong = SpecificTime.now().Distance(worker.getLastCheck());
	}
	public WorkerEntry(Worker worker) throws  ToLateException {
		super();
		this.worker = worker;
		this.isBusy = worker.isBusy();
		this.statusLong = SpecificTime.now().Distance(worker.getLastCheck());
	}
	@Override
	public String toString() {
		return worker.getFirstName()+" "+worker.getLastName() + " " + ((isBusy)?"busy":"free") + " " + statusLong;
	}
	@Override
	public int compareTo(WorkerEntry o) {
		if(this.getIsBussy()!=o.getIsBussy()) {
			return (this.getIsBussy())?1:-1;
		}
		int i=statusLong.compareTo(o.getStatusLong());
		return(i==0)?this.getWorker().getId()-o.getWorker().getId():i;
	}
	
	
	
}
