package Nodes;



import java.io.IOException;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public class Login extends StackPane{

	int loginStatus=0;
	private Label er;
	BorderPane layout;

	
	public Login(BorderPane layout) {
		this.layout=layout;
		er=new Label();
		TextField userField=new TextField();
		PasswordField passField=new PasswordField();
		Button loginButton=new Button("login");
		Button register=new Button("Register now!");
		
		loginButton.setOnAction(e->{
			try {
				checkCredential(userField.getText(),passField.getText());
			} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException
					| BadPaddingException | IOException | InvalidAlgorithmParameterException e1) {
				// TODO Auto-generated catch block
				er.setText(e1.getMessage());
				er.setStyle("-fx-translate-x:-325;-fx-translate-y:300;-fx-font-size:25;-fx-font-weight:bold;-fx-text-fill:RED");
			}
		});
		register.setOnAction(e->{
			
			NewMenu nm=new NewMenu(layout);
			nm.newCustomer(layout);
			
			
		});
		
		userField.setStyle("-fx-translate-x:-325;-fx-translate-y:-75; -fx-min-width:300;-fx-max-width:300;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;-fx-font-weight:bold;-fx-border-width:2;-fx-border-color:#1279BE;-fx-border-radius:15px");
		passField.setStyle("-fx-translate-x:-325;-fx-translate-y:100; -fx-min-width:300;-fx-max-width:300;-fx-min-height:50;-fx-max-height:50;-fx-font-size:20;-fx-font-weight:bold;-fx-border-width:2;-fx-border-color:#1279BE;-fx-border-radius:15px");
		loginButton.setStyle("-fx-translate-x:-400;-fx-translate-y:200;-fx-min-width:150;-fx-max-width:150;-fx-min-height:50;-fx-max-height:50;-fx-font-size:25;-fx-font-weight:bold;-fx-background-radius:15px");
		register.setStyle("-fx-translate-x:-235;-fx-translate-y:200;-fx-min-width:115;-fx-max-width:150;-fx-min-height:50;-fx-max-height:50;-fx-font-size:18;-fx-font-weight:bold;-fx-background-radius:15px");
		
		this.getChildren().addAll(userField,passField,loginButton,er,register);
		this.setStyle("-fx-background-image: url('file:images/loginForm.png'); -fx-background-position: center center;-fx-background-repeat: stretch;");
		this.setAlignment(Pos.CENTER);
		}






	public void checkCredential(String username, String password) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException, IOException {
		
		if(username.equals("manager")&&password.equals("manager")) {
			layout.setCenter(JavaEatAdminForm.getInstance());
			return;
			
		}
		
		ToolsBar.getInstance().getOut().println("login/"+username+"/"+password);
		String[] answer=ToolsBar.getInstance().getIn().readLine().split("/");
		if(!Boolean.parseBoolean(answer[0])) {
			er.setText("Invalid credentials");
			er.setStyle("-fx-translate-x:-325;-fx-translate-y:300;-fx-font-size:25;-fx-font-weight:bold;-fx-text-fill:RED");

			return;
		}
		else {
			ToolsBar.getInstance().getOut().println("getRealCust/"+answer[1]);
			answer=ToolsBar.getInstance().getIn().readLine().split("!");
			layout.setCenter(ClientForm.getInstance(layout, answer));
			return;
		}
		
	}

	public int getLoginStatus() {
		return loginStatus;
	}

	public void setLoginStatus(int loginStatus) {
		this.loginStatus = loginStatus;
	}

}
