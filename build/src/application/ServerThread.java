package application;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import CRM.CrmThread;
import Model.Restaurant;

public class ServerThread extends Thread{
	
	private static ServerThread srv;
	
	public static ServerThread getInstance() {
		if(srv==null) {
			srv=new ServerThread();
		}
		return srv;
	}
	
	@SuppressWarnings("deprecation")
	public void run() {
		@SuppressWarnings("unused")
		Restaurant rest=Restaurant.getInstance();
		CrmThread crm=CrmThread.getInstance();

		crm.start();
		

		
		try {
			ServerSocket listener=new ServerSocket(5656);
			while(true) {
				Socket cl=listener.accept();
				DealWithClient dwc=new DealWithClient(cl);
				dwc.start();
				if(!Main.isServer()) {
					crm.stop();

					dwc.stop();
					cl.close();
					listener.close();
					break;
				}
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.exit(0);
		}
		
	}
	


	public ServerThread() {	}
}


