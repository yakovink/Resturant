package View;

/*this is the quota menu. it contains the 7 quotas from exresise 2 in one layout form.**/

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.TreeSet;

import Exceptions.BlankFieldException;
import Exceptions.CommandWasNotFoundException;
import Exceptions.EmptyBufferException;
import Exceptions.FaildToAddException;
import Exceptions.IllegalCharacterException;
import Exceptions.LieException;
import Exceptions.NotNumberException;
import Exceptions.ReaderConnectionException;
import Utils.ClientReader;
import Utils.Expertise;
import View.Nodes.FilteredBox;
import View.Nodes.FilteredList;
import View.Nodes.IndNumField;
import View.Nodes.ToolsBar;
import application.App;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public class QuotaMenu extends StackPane{

	private PrintWriter out;
	private ClientReader in;
	
	private double w;
	private double h;
	private double s;
	

	
	public QuotaMenu() {
		ToolsBar.getInstance().getPlace().pushText("Quotas menu");
		w=App.getW();
		h=App.getH();
		s=App.getS();
		
		this.setAlignment(Pos.CENTER);
		out=ToolsBar.getInstance().getOut();
		in=ToolsBar.getInstance().getIn();
		
		
		Label pr=new Label("profit relation: ");
		Label pc=new Label("popular componenets: ");
		Label dbp=new Label("deliveries by persons: ");
		Label rdl=new Label("relevant dish list: ");
		Label cbe=new Label("cooks by expertise: ");
		Label ai=new Label("AI machine: ");
		

		Label revFromExp=revenueFromExpress();
		Label delPerType=numOfDeliveriesPerType();
		StackPane delByPer=deliveriesByPerson();
		StackPane AI=AImachine();
		StackPane relDishList=relevantDishList();
		StackPane cookByExp=cooksByExpertise();
		FilteredList<String> profRel=profitRelation();
		FilteredList<String> popComp=popularTypes();
		StackPane blackList=blackList();
		
		
		pr.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;",-600*w,-250*h,200*w,200*w,50*h,50*h,14*s));
		pc.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;",-600*w,-25*h,200*w,200*w,50*h,50*h,14*s));
		dbp.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;",-225*w,-300*h,200*w,200*w,50*h,50*h,14*s));
		rdl.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;",-225*w,0*h,200*w,200*w,50*h,50*h,14*s));
		cbe.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;",-225*w,200*h,200*w,200*w,50*h,50*h,14*s));
		ai.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;",200*w,-275*h,200*w,200*w,50*h,50*h,14*s));

		revFromExp.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;",-400*w,-400*h,200*w,200*w,50*h,50*h,14*s));
		delPerType.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;",-400*w,-350*h,200*w,200*w,75*h,75*h,14*s));
		profRel.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;",-425*w,-200*h,150*w,150*w,200*h,50*h,14*s));
		popComp.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;",-425*w,-25*h,150*w,150*w,200*h,200*h,14*s));
		delByPer.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;",-50*w,-250*h,150*w,150*w,250*h,250*h,14*s));
		relDishList.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;",-50*w,0*h,150*w,150*w,250*h,250*h,14*s));
		cookByExp.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;",-50*w,250*h,150*w,150*w,250*h,250*h,14*s));
		AI.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;",350*w,-25*h,150*w,150*w,400*h,400*h,14*s));
		blackList.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;",650*w,-250*h,200*w,200*w,500*h,500*h,14*s));
		
		this.getChildren().addAll(revFromExp,delPerType,profRel,popComp,delByPer,cookByExp,relDishList,AI,pr,pc,dbp,rdl,cbe,ai,blackList);
		
		ToolsBar.getInstance().getLastStacks().push(App.getLayout().getCenter());
		App.getLayout().setCenter(this);
	}

	
	private StackPane blackList() {
		StackPane stack=new StackPane();
		stack.setAlignment(Pos.CENTER);
		out.println("getBL");
		String[] list;
		try {
			list = in.checkedReadLine().split(",");
		} catch (EmptyBufferException | CommandWasNotFoundException | ReaderConnectionException e) {
			PopUp.display(e.getMessage(), false);
			list=new String[0];
		}
		FilteredList<String> bannedIds=new FilteredList<>(list);
		
		IndNumField newID=new IndNumField();
		newID.setPromptText("enter id to ban");
		Button add=new Button("add");
		Label title =new Label("black list: ");
		
		add.setOnAction(e->{
			String[] answer= {"internal error","false"};
			try {
				newID.isLegal();

				
				out.println("addBl/"+newID.getText());
				answer=in.checkedReadLine().split("/");
				if(!Boolean.parseBoolean(answer[0])) {
					throw new FaildToAddException(answer[1]);
				}
				bannedIds.add(newID.getText());
				newID.clear();
				PopUp.display("ID added to banned customers!", true);
				
			}
			catch(IllegalCharacterException | EmptyBufferException | CommandWasNotFoundException | ReaderConnectionException | NumberFormatException | LieException | BlankFieldException | FaildToAddException | NotNumberException e1) {
				PopUp.display(e1.getMessage(), false);
			}
			
		});
		
		
		title.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",0*w,-200*h,100*w,100*w,50*h,50*h,14*s));
		bannedIds.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",0*w,-50*h,150*w,150*w,250*h,250*h,14*s));
		newID.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",0*w,100*h,150*w,150*w,50*h,50*h,14*s));
		add.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px;-fx-background-radius:15px",0*w,150*h,100*w,100*w,50*h,50*h,14*s));
		
		stack.getChildren().addAll(bannedIds,newID,add,title);
		return stack;
	}


	private Label revenueFromExpress() {
		out.println("RevFromDel");
		Label label=new Label("");
		String answer;
		try {
			answer = in.checkedReadLine();
			label.setText("Your revenue from express\ndeliveries is "+answer);
		} catch ( EmptyBufferException | CommandWasNotFoundException | ReaderConnectionException e) {
			PopUp.display(e.getMessage(), false);
		}
		return label;

	}
	private Label numOfDeliveriesPerType() {
		out.println("DelPerType");
		Label label=new Label("");
		try {
			String[] answer=in.checkedReadLine().split(",");
			label=new Label(answer[0]+"\n"+answer[1]);
			
		} catch (EmptyBufferException | CommandWasNotFoundException | ReaderConnectionException e) {
			PopUp.display(e.getMessage(), false);
		}
		
		return label;
	}
	
	private StackPane deliveriesByPerson() {
		StackPane stack=new StackPane();
		stack.setAlignment(Pos.CENTER);
		out.println("getDeps");
		
		FilteredBox<String> searchBox=new FilteredBox<>(new HashSet<>());
		try {
			searchBox.addAll(in.checkedReadLine().split(","));
		} catch (EmptyBufferException | CommandWasNotFoundException | ReaderConnectionException e2) {
			PopUp.display(e2.getMessage(), false);
		}
		FilteredBox<Integer> monthBox=new FilteredBox<>(Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12));
		FilteredList<String> relevant=new FilteredList<>(new HashSet<>());
		searchBox.getBox().getSelectionModel().selectedItemProperty().addListener(e->{
			
			try {
				if(monthBox.getBox().getSelectionModel().getSelectedItem()==null) {
					return;
				}
				relevant.clear();
				out.println("DelByPer/"+searchBox.getBox().getSelectionModel().getSelectedItem().split(" ")[0]+"/"+monthBox.getBox().getSelectionModel().getSelectedItem());
				String[] dels = in.checkedReadLine().split(",");
				relevant.addAll(dels);
			} catch (EmptyBufferException | CommandWasNotFoundException | ReaderConnectionException e1) {
				PopUp.display(e1.getMessage(), false);
			}
			
		});
		searchBox.getSearch().setPromptText("delivery person");
		monthBox.getBox().getSelectionModel().selectedItemProperty().addListener(e->{
			try {
				if(searchBox.getBox().getSelectionModel().getSelectedItem()==null) {
					return;
				}
				relevant.clear();
				out.println("DelByPer/"+searchBox.getBox().getSelectionModel().getSelectedItem().split(" ")[0]+"/"+monthBox.getBox().getSelectionModel().getSelectedItem());
				String[] dels = in.checkedReadLine().split(",");
				relevant.addAll(dels);
			} catch (EmptyBufferException | CommandWasNotFoundException | ReaderConnectionException e1) {
				PopUp.display(e1.getMessage(), false);
			}
		});
		monthBox.getSearch().setPromptText("month");
		
		searchBox.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",0*w,-150*h,200*w,200*w,100*h,100*h,14*s));
		monthBox.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",0*w,-75*h,200*w,200*w,100*h,100*h,14*s));
		relevant.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",0*w,25*h,200*w,200*w,150*h,150*h,14*s));
		stack.getChildren().addAll(searchBox,monthBox,relevant);
		return stack;
	}
	
	private StackPane AImachine() {
		StackPane stack=new StackPane();
		out.println("getAreas");
		FilteredBox<String> searchBox=new FilteredBox<>(new HashSet<>());
		try {
			searchBox.addAll(in.checkedReadLine().split(","));
		} catch (EmptyBufferException | CommandWasNotFoundException | ReaderConnectionException e3) {
			PopUp.display(e3.getMessage(), false);
		}
		FilteredBox<String> dps=new FilteredBox<>(new HashSet<>());
		FilteredList<String> orders=new FilteredList<>(new HashSet<>());
		FilteredList<String> deliveries=new FilteredList<>(new HashSet<>());
		Button decide=new Button("decide");;
		
		searchBox.getSearch().setPromptText("Area");
		dps.getSearch().setPromptText("Delivery Person");
		orders.getSearch().setPromptText("Orders");
		deliveries.getSearch().setPromptText("Deliveries");
		
		searchBox.getBox().getSelectionModel().selectedItemProperty().addListener(e->{
			
			try {
				orders.clear();
				out.println("forDecide/"+searchBox.getBox().getSelectionModel().getSelectedItem().split(" ")[0]);
				orders.addAll(in.checkedReadLine().split(","));
				dps.getBox().getItems().clear();
				out.println("getRealArea/"+searchBox.getBox().getSelectionModel().getSelectedItem().split(" ")[0]);
				String[] deps=in.checkedReadLine().split("/")[2].split(",");
				for(String dp:deps) {
					out.println("getRealDep/"+dp.split(" ")[0]);
					String busy=in.checkedReadLine().split("/")[9];
					if(!Boolean.parseBoolean(busy)) {
						dps.add(dp);
					}
				}
			} catch (EmptyBufferException | CommandWasNotFoundException | ReaderConnectionException e1) {
				PopUp.display(e1.getMessage(), false);
			}
			
		});
		decide.setOnAction(e->{
			try {
				Collection<String> ords=new HashSet<>();
				orders.getList().getItems().forEach(e2->ords.add(e2.split(" ")[0]));
				out.println("AI/"+dps.getBox().getSelectionModel().getSelectedItem().split(" ")[0]+"/"+String.join(",", ords));
				deliveries.addAll(in.checkedReadLine().split(","));
				orders.clear();
				PopUp.display("AI: machine success",true);
			} catch (EmptyBufferException | CommandWasNotFoundException | ReaderConnectionException e1) {
				PopUp.display(e1.getMessage(), false);
			}
			
		});
		searchBox.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",0*w,-385*h,200*w,200*w,100*h,100*h,14*s));
		dps.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",0*w,-300*h,200*w,200*w,100*h,100*h,14*s));
		orders.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",0*w,-185*h,200*w,200*w,150*h,150*h,14*s));
		decide.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",0*w,-115*h,200*w,200*w,50*h,50*h,14*s));
		deliveries.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",0*w,0*h,200*w,200*w,150*h,150*h,14*s));
		
		stack.getChildren().addAll(searchBox,dps,orders,deliveries,decide);
		return stack;
	}
	
	private FilteredList<String> profitRelation() {
		out.println("ProfRel");
		FilteredList<String> message=new FilteredList<>(new TreeSet<>());
		try {
			message.addAll(in.checkedReadLine().split(","));
		} catch (EmptyBufferException | CommandWasNotFoundException | ReaderConnectionException e) {
			PopUp.display(e.getMessage(), false);
		}
		return message;
	}
	private FilteredList<String> popularTypes() {
		out.println("PopComp");
		FilteredList<String> message=new FilteredList<>(new TreeSet<>());
		try {
			message.addAll(in.checkedReadLine().split(","));
		} catch (EmptyBufferException | CommandWasNotFoundException | ReaderConnectionException e) {
			PopUp.display(e.getMessage(), false);
		}
		return message;
	}

	private StackPane relevantDishList() {
		StackPane stack=new StackPane();
		stack.setAlignment(Pos.CENTER);
		
		FilteredBox<String> searchBox=new FilteredBox<>(new HashSet<>());
		FilteredList<String> relevant=new FilteredList<>(new HashSet<>());
		searchBox.getSearch().setPromptText("search customer");
		try {
			out.println("getCusts");
			searchBox.addAll(in.checkedReadLine().split(","));
			out.println("getDishes");
			relevant.addAll(in.checkedReadLine().split(","));
		} catch (EmptyBufferException | CommandWasNotFoundException | ReaderConnectionException e2) {
			PopUp.display(e2.getMessage(), false);
		}
		
		searchBox.getBox().getSelectionModel().selectedItemProperty().addListener(e->{
			
			try {
				relevant.clear();
				out.println("RelDishList/"+searchBox.getBox().getSelectionModel().getSelectedItem().split(" ")[0]);
				relevant.addAll(in.checkedReadLine().split(","));
			} catch (EmptyBufferException | CommandWasNotFoundException | ReaderConnectionException e1) {
				PopUp.display(e1.getMessage(), false);
			}
		});
		searchBox.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",0*w,-50*h,200*w,200*w,100*h,100*h,14*s));
		relevant.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",0*w,50*h,200*w,200*w,150*h,150*h,14*s));
		stack.getChildren().addAll(searchBox,relevant);
		return stack;
	}

	private StackPane cooksByExpertise() {
		StackPane stack=new StackPane();
		stack.setAlignment(Pos.CENTER);
		FilteredBox<Expertise> searchBox=new FilteredBox<>(Arrays.asList(Expertise.values()));
		searchBox.getSearch().setPromptText("search expertise");
		out.println("getCooks");
		FilteredList<String> relevant=new FilteredList<>(new HashSet<>());
		try {
			relevant.addAll(in.checkedReadLine().split(","));
		} catch (EmptyBufferException | CommandWasNotFoundException | ReaderConnectionException e2) {
			PopUp.display(e2.getMessage(), false);
		}
		searchBox.getBox().getSelectionModel().selectedItemProperty().addListener(e->{
			try {
				relevant.clear();
				out.println("CookByExp/"+searchBox.getBox().getSelectionModel().getSelectedItem().name());
				relevant.addAll(in.checkedReadLine().split(","));
			} catch (EmptyBufferException | CommandWasNotFoundException | ReaderConnectionException e1) {
				PopUp.display(e1.getMessage(), false);
			}
		});
		searchBox.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",0*w,-50*h,200*w,200*w,100*h,100*h,14*s));
		relevant.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-background-radius:15px",0*w,-50*h,200*w,200*w,100*h,100*h,14*s));
		stack.getChildren().addAll(searchBox,relevant);
		return stack;
	}
}
