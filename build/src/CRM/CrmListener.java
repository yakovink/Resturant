package CRM;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class CrmListener extends Thread{
	
	
	private static ServerSocket srv;
	
	public CrmListener() {	}
	
	public void run() {
		try {
			srv=new ServerSocket(5657);
			while(true) {
				Socket cl=srv.accept();
				DealWithCrmClient dwcc=new DealWithCrmClient(cl);
				dwcc.start();
				
			}
			
			
		} catch (IOException e) {	}
		
	}
}
