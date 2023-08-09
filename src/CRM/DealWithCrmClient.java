package CRM;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.LinkedList;

public class DealWithCrmClient extends Thread{
	
	private Socket cl;
	private PrintWriter out;
	private BufferedReader in;
	private String inLine,output;
	
	public DealWithCrmClient(Socket cl) throws IOException {
		this.setName("DealWithCrm_"+cl.getInetAddress().getHostAddress());
		this.cl=cl;
		out=new PrintWriter(cl.getOutputStream(),true);
		in=new BufferedReader(new InputStreamReader(cl.getInputStream()));
	}
	
	@SuppressWarnings("deprecation")
	public void run() {
		while(true) {
			try {
				inLine=in.readLine();
				output="";
				if(inLine.equals("sync")) {
					LinkedList<String> tmp=new LinkedList<>();
					CrmThread.getInstance().getCooks().forEach(e->tmp.add(e.toString()));
					output+=String.join(",", tmp)+"-";
					tmp.clear();
					CrmThread.getInstance().getDeliveryPersons().forEach(e->tmp.add(e.toString()));
					output+=String.join(",", tmp)+"-";
					tmp.clear();
					CrmThread.getInstance().getOrders().forEach(e->tmp.add(e.toString()));
					output+=String.join(",", tmp);
					if(CrmThread.getInstance().getOrders().isEmpty()) {
						output+="empty";
					}
				}
				else if(inLine.equals("close")){
					out.println("closing");
					out.close();
					in.close();
					cl.close();
					this.stop();
				}
				out.println(output);
			} catch (IOException e1) {	}

		}
	}

	public Socket getCl() {
		return cl;
	}

	public void setCl(Socket cl) {
		this.cl = cl;
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
	
	

}
