package CRM;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/*that is the crm server. accept sockets and open dealWith client. nothing special.**/

public class CrmListener extends Thread{
	
	
	private static ServerSocket srv;
	
	private static CrmListener listener;
	
	public static CrmListener getInstance() {
		if(listener==null) {
			listener=new CrmListener();
		}
		return listener;
	}
	
	private static ArrayList<DealWithCrmClient> processes;
	
	public static ServerSocket getSrv() {
		return srv;
	}

	public static void setSrv(ServerSocket srv) {
		CrmListener.srv = srv;
	}

	public static ArrayList<DealWithCrmClient> getProcesses() {
		return processes;
	}

	public static void setProcesses(ArrayList<DealWithCrmClient> processes) {
		CrmListener.processes = processes;
	}

	private CrmListener() {
		this.setName("CrmSrv");
	}
	
	public void run() {
		try {
			srv=new ServerSocket(5657);
			processes=new ArrayList<>();
			while(true) {
				Socket cl=srv.accept();
				DealWithCrmClient dwcc=new DealWithCrmClient(cl);
				processes.add(dwcc);
				dwcc.start();
				
			}
			
			
		} catch (IOException e) {	}
		
	}
}
