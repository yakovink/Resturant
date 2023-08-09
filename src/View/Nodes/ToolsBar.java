
/*this is little complicated helper object.
 *it have the main client of every program, if you choose client-only or hybrid mode.
 *the ToolsBar has also 2 queues, implements as linked-lists, that save last panes ond their names.
 *that functionality being able to come back and load previous panes.
 *for last, ToolsBar has exit, minimize and save buttons from the full screen program.
 *its necessary to use that X for clean shutdown of the server, if it run on background.
 *the save functionality is able to everyone, even regular customers.
 *the program also saved data when exit from there.
 *that Class has one singelton show.*/

package View.Nodes;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Stack;

import Exceptions.InternalErrorException;
import Model.Restaurant;
import Utils.ClientReader;
import View.PopUp;
import application.App;
import application.ServerThread;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;


public class ToolsBar extends HBox{
	
	private Button back;
	private Button exit;
	private Button pass;
	private Button save;
	private PlaceLabel place;
	
	private PrintWriter out;
	private ClientReader in;
	private Socket cl;
	
	private static ToolsBar tb;
	private Stack<Node> lastStacks;
	private Stack<String> lastStacksNames;
	
	
	public static ToolsBar getInstance() {
		if(tb==null) {
			tb=new ToolsBar();
		}
		return tb;
	}
	
	private ToolsBar() {
		
		place=new PlaceLabel();
		double w=App.getW();
		double h=App.getH();
		double s=App.getS();
		try {
			cl=new Socket(App.getServIP(),5656);
			out=new PrintWriter(cl.getOutputStream(),true);
			in=new ClientReader(new InputStreamReader(cl.getInputStream()));
			
		} catch (IOException e1) {
			PopUp.display("connection false", false);
			System.exit(0);
		}
		if(App.isClient()) {
		lastStacks=new Stack<>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 5263873262536185730L;

			public synchronized Node pop() {
		        Node       obj;
		        int     len = size();
		        obj = peek();
		        removeElementAt(len - 1);
		        place.setText(lastStacksNames.pop());
		        if(lastStacks.size()==0) {
		        	back.setDisable(true);
		        }
		        return obj;
		    }
			
		    public Node push(Node item) {
		        addElement(item);
		        back.setDisable(false);
		        return item;
		    }
		};
		
		lastStacksNames=new Stack<>();
		back=new Button("Back");
		back.setOnAction(e->{
			if(!lastStacks.isEmpty()) {
				
				App.getLayout().setCenter(lastStacks.peek());
				lastStacks.pop();
			}
		});
		exit=new Button("X");
		exit.setOnAction(e->{
			try {
				if(App.isServer()) {
					out.println("shutdown");
				out.close();
				in.close();
				cl.close();
				System.exit(0);
			}} catch (IOException e1) {
				System.err.println("close client faild");
				System.exit(0);
			}
		});
		pass=new Button("_");
		pass.setOnAction(e->{
			App.getWindow().setIconified(true);
		});
		save=new Button("save");
		save.setOnAction(e->{
			try {
				out.println("save");
				if(!Boolean.parseBoolean(in.checkedReadLine())) {
					throw new InternalErrorException("Faild to save the data!");
				}
				PopUp.display("Data saved!", true);
			} catch (IOException e1) {
				PopUp.display(e1.getMessage(), false);
			}
		});
		
		
		back.setStyle(String.format("-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;",75*w,75*w,75*h,75*h,12*s));
		exit.setStyle(String.format("-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;",75*w,75*w,75*h,75*h,20*s));
		pass.setStyle(String.format("-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;",75*w,75*w,75*h,75*h,20*s));
		save.setStyle(String.format("-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;",75*w,75*w,75*h,75*h,12*s));
		place.setStyle(String.format("-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;",800*w,800*w,75*h,75*h,20*s));
		this.setPadding(new Insets(10,25,10,25));
		this.getChildren().addAll(exit,pass,back,save,place);
		}
		else {
			exit=new Button("X");
			exit.setOnAction(e->{
				try {
					ServerThread.getInstance().shutdown();
				} catch (IOException e1) {
					PopUp.display("close client faild!", true);
				}
			});
			pass=new Button("-");
			pass.setOnAction(e->{
				App.getWindow().setIconified(true);
			});
			save=new Button("save");
			save.setOnAction(e->{
				try {
					Restaurant.getInstance().writeObject();
					PopUp.display("Data saved!", true);
				} catch (IOException e1) {
					PopUp.display("Faild to save the data!", false);
				}
			});
			save.setStyle(String.format("-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;",75*w,75*w,75*h,75*h,12*s));
			pass.setStyle(String.format("-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;",75*w,75*w,75*h,75*h,12*s));
			exit.setStyle(String.format("-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;",75*w,75*w,75*h,75*h,20*s));
			this.setPadding(new Insets(10,25,10,25));
			this.getChildren().addAll(exit,pass,save);
		}

	}





	public Stack<String> getLastStacksNames() {
		return lastStacksNames;
	}

	public void setLastStacksNames(Stack<String> lastStacksNames) {
		this.lastStacksNames = lastStacksNames;
	}

	public PlaceLabel getPlace() {
		return place;
	}

	public void setPlace(PlaceLabel place) {
		this.place = place;
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

	public ClientReader getIn() {
		return in;
	}

	public void setIn(ClientReader in) {
		this.in = in;
	}

	public Socket getCl() {
		return cl;
	}

	public void setCl(Socket cl) {
		this.cl = cl;
	}


	
}
