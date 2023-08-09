package application;

import java.io.IOException;


import CRM.CrmListener;
import CRM.DealWithCrmClient;

public class ConnectionCheckThread extends Thread{
	
	public static ConnectionCheckThread getInstance() {
		if(check==null) {
			check=new ConnectionCheckThread();
		}
		return check;
	}

	public static ConnectionCheckThread check;
	
	private ConnectionCheckThread() {
		this.setName("ConChkThrd");
	}
	
	@SuppressWarnings("deprecation")
	public void run() {
		
		while(true) {
			for(int i=0;i<ServerThread.getProcesses().size();i++) {
				DealWithClient dwc=ServerThread.getProcesses().get(i);
				try {
					if(dwc.getThreadGroup()==null) {
						dwc.getOut().close();
						dwc.getIn().close();
						dwc.getCl().close();
						dwc.stop();
						ServerThread.getProcesses().remove(i);
					}
				} catch (IOException e) {
					dwc.stop();
					ServerThread.getProcesses().remove(i);
				}
			}
			for(int i=0;i<CrmListener.getProcesses().size();i++) {
				DealWithCrmClient dwc=CrmListener.getProcesses().get(i);
				try {
					if(dwc.getThreadGroup()==null) {
						dwc.getOut().close();
						dwc.getIn().close();
						dwc.getCl().close();
						dwc.stop();
						CrmListener.getProcesses().remove(i);
					}
				} catch (IOException e) {
					dwc.stop();
					CrmListener.getProcesses().remove(i);
				}
			}
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			this.stop();
		}
		}
		
		
	}
}
