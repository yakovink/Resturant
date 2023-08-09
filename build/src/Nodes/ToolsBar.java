package Nodes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Stack;

import Forms.ConfirmBox;
import application.Main;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;


public class ToolsBar extends HBox{
	
	private Button back;
	private Button refresh;
	private Button exit;
	private Button pass;
	
	private PrintWriter out;
	private BufferedReader in;
	private Socket cl;
	
	private static ToolsBar tb;
	private Stack<Node> lastStacks;
	
	
	public static ToolsBar getInstance() {
		if(tb==null) {
			tb=new ToolsBar();
		}
		return tb;
	}
	
	public ToolsBar() {
		try {
			cl=new Socket(Main.getServIP(),5656);
			out=new PrintWriter(cl.getOutputStream(),true);
			in=new BufferedReader(new InputStreamReader(cl.getInputStream()));
		} catch (IOException e1) {
			cl=new Socket();
			System.err.println("connection faild");
			System.exit(0);
		}
		lastStacks=new Stack<>();
		back=new Button("Back");
		back.setOnAction(e->{
			if(!lastStacks.isEmpty()) {
				Main.getLayout().setCenter(lastStacks.pop());
			}
		});
		refresh=new Button("refresh");
		exit=new Button("X");
		exit.setOnAction(e->{
			boolean close=ConfirmBox.display("Do you sure you want to exit?");
			if(close) {
				try {
					
					out.close();
					in.close();
					cl.close();
					System.exit(0);
				} catch (IOException e1) {
					System.err.println("close client faild");
					System.exit(0);
				}
			}
		});
		pass=new Button("-");
		pass.setOnAction(e->{
			Main.getWindow().setIconified(true);
		});
		
		back.setStyle("-fx-min-width:75;-fx-max-width:75;-fx-min-height:75;-fx-max-height:75;-fx-font-size:12;");
		refresh.setStyle("-fx-min-width:75;-fx-max-width:75;-fx-min-height:75;-fx-max-height:75;-fx-font-size:12;");
		exit.setStyle("-fx-min-width:75;-fx-max-width:75;-fx-min-height:75;-fx-max-height:75;-fx-font-size:20;");
		pass.setStyle("-fx-min-width:75;-fx-max-width:75;-fx-min-height:75;-fx-max-height:75;-fx-font-size:20;");
		this.getChildren().addAll(exit,pass,refresh,back);
	}

	public Button getRefresh() {
		return refresh;
	}

	public void setRefresh(Button refresh) {
		this.refresh = refresh;
	}

	public Button getPass() {
		return pass;
	}

	public void setPass(Button pass) {
		this.pass = pass;
	}

	public PrintWriter getOut() {
		return out;
	}

	public void setOut(PrintWriter out) {
		this.out = out;
	}

	public static ToolsBar getTb() {
		return tb;
	}

	public static void setTb(ToolsBar tb) {
		ToolsBar.tb = tb;
	}

	public Stack<Node> getLastStacks() {
		return lastStacks;
	}

	public void setLastStacks(Stack<Node> lastStacks) {
		this.lastStacks = lastStacks;
	}

	public Button getBack() {
		return back;
	}

	public void setBack(Button back) {
		this.back = back;
	}

	public Button getExit() {
		return exit;
	}

	public void setExit(Button exit) {
		this.exit = exit;
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


	
}
