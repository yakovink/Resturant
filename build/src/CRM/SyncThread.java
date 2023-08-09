package CRM;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import Exceptions.DefaultException;
import Nodes.CrmLayout;
import application.Main;

public class SyncThread extends Thread{

	private CrmLayout crm;
	private Socket cl;
	private PrintWriter out;
	private BufferedReader in;
	
	private static SyncThread sync;
	
	public static SyncThread getInstance(CrmLayout crm) throws IOException {
		if(sync==null) {
			sync=new SyncThread(crm);
		}
		return sync;
	}
	
	public SyncThread(CrmLayout crm) throws IOException {
		this.crm=crm;
		cl=new Socket(Main.getServIP(),5657);
		out=new PrintWriter(cl.getOutputStream(),true);
		in=new BufferedReader(new InputStreamReader(cl.getInputStream()));
	}
	
	public PrintWriter getOut() {
		return out;
	}

	public void setOut(PrintWriter out) {
		this.out = out;
	}

	public BufferedReader getIn() {
		return in;
	}

	public void setIn(BufferedReader in) {
		this.in = in;
	}

	public Socket getCl() {
		return cl;
	}

	public void setCl(Socket cl) {
		this.cl = cl;
	}

	public void run() {
		while(true) {
			try {
			
			Thread.sleep(1000);
			Thread.setDefaultUncaughtExceptionHandler(new DefaultException());
			crm.getCooks().getItems().clear();
			crm.getDeliveryPersons().getItems().clear();
			crm.getOrders().getItems().clear();
			
			out.println("sync");
			String[] lists=in.readLine().split("-");
			crm.getCooks().getItems().addAll(lists[0].split(","));
			crm.getDeliveryPersons().getItems().addAll(lists[1].split(","));
			crm.getOrders().getItems().addAll(lists[2].split(","));

				
			}
			 catch (Exception e) {}
		}
	}
}



