package View;


import java.io.IOException;

/*this is login form. it have username field, password field and login and register buttons.
 * the login progress uses "accounts" HashMap in Restaurant class:
 * is the partial account that created from the getting username and password is exist -
 * the program will open new customer form window with the customer value belong to the account key in accounts HashMap.
 * 
 * the register button reference to addCustomer form, deleting JavaEatAdminForm from lastStack stack**/



import Exceptions.CommandWasNotFoundException;
import Exceptions.ReaderConnectionException;
import View.Nodes.IndPasswordField;
import View.Nodes.IndTextField;
import View.Nodes.ToolsBar;
import application.App;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public class Login extends StackPane{

	private Label er;
	BorderPane layout;
	


	
	public Login() {
		ToolsBar.getInstance().getPlace().pushText("Login");
		double w=App.getW();
		double h=App.getH();
		double s=App.getS();
		this.layout=App.getLayout();
		er=new Label();
		IndTextField userField=new IndTextField();
		IndPasswordField passField=new IndPasswordField();
		Button loginButton=new Button("login");
		Button register=new Button("Register now!");
		loginButton.setOnAction(e->{
			try {
				
				userField.isLegal();
				passField.isLegal();
				checkCredential(userField.getText(),passField.getText());
			
			} catch (IOException | CommandWasNotFoundException | ReaderConnectionException e1) {
				PopUp.display(e1.getMessage(), false);
			}
		});
		register.setOnAction(e->{
			
			
			NewMenu nm=new NewMenu();
			nm.newCustomer();
			ToolsBar.getInstance().getLastStacks().pop();
			
			
			
		});
		
		userField.checkStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f; -fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-font-weight:bold;-fx-border-width:2;-fx-border-radius:15px",-325*w,-50*h,300*w,300*w,50*h,50*h,20*s));
		passField.checkStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f; -fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-font-weight:bold;-fx-border-width:2;-fx-border-radius:15px",-325*w,100*h,300*w,300*w,50*h,50*h,20*s));
		loginButton.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f; -fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-font-weight:bold;-fx-background-radius:15px",-400*w,200*h,150*w,150*w,50*h,50*h,20*s));
		register.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f; -fx-min-width:%f;-fx-max-width:%f;-fx-min-height:%f;-fx-max-height:%f;-fx-font-size:%f;-fx-font-weight:bold;-fx-background-radius:15px",-235*w,200*h,150*w,150*w,50*h,50*h,14*s));
		ToolsBar.getInstance().getLastStacks().push(App.getLayout().getCenter());
		this.getChildren().addAll(userField,passField,loginButton,er,register);
		this.setStyle("-fx-background-image: url('file:images/loginForm.png'); -fx-background-position: center center;-fx-background-repeat: stretch;-fx-background-size:"+w*1980+" "+h*1080);
		this.setAlignment(Pos.CENTER);
		}






	public void checkCredential(String username, String password) throws  CommandWasNotFoundException, ReaderConnectionException, IOException {
		
		if(username.equals("manager")&&password.equals("manager")) {
			layout.setCenter(new JavaEatAdminForm());
			return;
			
		}
		
		ToolsBar.getInstance().getOut().println("login/"+username+"/"+password);
		String[] answer=ToolsBar.getInstance().getIn().checkedReadLine().split("/");
		if(!Boolean.parseBoolean(answer[0])) {
			er.setText("Invalid credentials");
			er.setStyle(String.format("-fx-translate-x:%f;-fx-translate-y:%f;-fx-font-size:%f;-fx-font-weight:bold;-fx-text-fill:RED",-325*App.getW(),300*App.getH(),25*App.getS()));

			return;
		}
		else {
			ToolsBar.getInstance().getOut().println("getRealCust/"+answer[1]);
			answer=ToolsBar.getInstance().getIn().checkedReadLine().split("!");
			layout.setCenter(ClientForm.getInstance(answer));
			return;
		}
		
	}


}
