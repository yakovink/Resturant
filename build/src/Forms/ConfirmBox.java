package Forms;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class ConfirmBox extends Stage{

	
	private static boolean confirm=false;
	
	
	public static boolean display(String question) {
		Stage cb=new Stage();
		cb.setTitle("confirm box");
		Label label=new Label(question);
		Button ok=new Button("ok");
		Button cancel=new Button("cancel");
		
		StackPane layout=new StackPane();
		layout.getChildren().addAll(label,ok,cancel);
		
		ok.setOnAction(e->{
			confirm=true;
			cb.close();
		});
		cancel.setOnAction(e->{
			confirm=false;
			cb.close();
		});
		
		label.setStyle("-fx-translate-x:0;-fx-translate-y:-25");
		ok.setStyle("-fx-translate-x:45;-fx-translate-y:25; -fx-min-width:75");
		cancel.setStyle("-fx-translate-x:-45;-fx-translate-y:25; -fx-min-width:75");
		
		layout.setAlignment(Pos.CENTER);
		
		Scene scene=new Scene(layout,200,100);
		cb.setScene(scene);
		cb.showAndWait();
		return confirm;
	}
	
}
