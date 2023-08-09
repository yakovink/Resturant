package application;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import CRM.CrmListener;
import CRM.CrmThread;
import CRM.DealWithCrmClient;
import Model.Restaurant;

/*server thread class is the main server thread.
 * it has a singelton show and one server socket.
 * the constractor will start the Restaurant database and command the load of the data forom Rest.ser.
 * when run function is running, the crm thread start to run too,
 * and the while true loop for listening to port and open dealWith threads for clients begun.**/

public class ServerThread extends Thread{
	
	private ServerSocket listener;
	
	private static ServerThread srv;
	
	public static ArrayList<DealWithClient> getProcesses() {
		return processes;
	}


	public static void setProcesses(ArrayList<DealWithClient> processes) {
		ServerThread.processes = processes;
	}


	private static ArrayList<DealWithClient> processes;
	
	public static ServerThread getInstance() {
		if(srv==null) {
			srv=new ServerThread();
		}
		return srv;
	}
	


	public void run() {
		Restaurant.getInstance();
		CrmThread crm=CrmThread.getInstance();
		processes=new ArrayList<>();
		
		crm.start();
		ConnectionCheckThread.getInstance().start();
		try {
			listener=new ServerSocket(5656);
			while(true) {
				Socket cl=listener.accept();
				DealWithClient dwc=new DealWithClient(cl);
				processes.add(dwc);
				dwc.start();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.exit(0);
		}
		
	}
	


	private ServerThread() {
		this.setName("SrvThrd");
	}


	@SuppressWarnings("deprecation")
	public void shutdown() throws FileNotFoundException{
		try {
			Restaurant.getInstance().writeObject(new ObjectOutputStream(new FileOutputStream("Rest.ser")));
			ConnectionCheckThread.getInstance().stop();
			for(DealWithCrmClient dwcc:CrmListener.getProcesses()) {
				dwcc.getOut().close();
				dwcc.getIn().close();
				dwcc.getCl().close();
				dwcc.stop();
			}
			CrmListener.getSrv().close();
			CrmThread.getInstance().stop();
			for(DealWithClient dwc:processes) {
				dwc.getOut().close();
				dwc.getIn().close();
				dwc.getCl().close();
				dwc.stop();
			}
			System.exit(1);
			
		} catch (IOException e) {		}
		
		System.exit(1);
		
	}
	
}


