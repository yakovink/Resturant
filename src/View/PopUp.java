package View;

/*this is the pop-up pane. it have message label, button "ok" and some Voice, depends if the news are good or bad**/

import java.io.File;

import View.Nodes.ToolsBar;
import application.App;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class PopUp extends StackPane{
public static void display(String question, Boolean goodNews) {
		ToolsBar.getInstance().getPlace().pushText(question);
		Label label=new Label(question);
		Button ok=new Button("ok");
		String popVoicePath=(goodNews)?"mp3/SuccesNews.mp3":"mp3/FaildNews.mp3";
		MediaPlayer media=new MediaPlayer(new Media(new File(popVoicePath).toURI().toString()));
		
		StackPane layout=new StackPane();
		layout.getChildren().addAll(label,ok);
		
		ok.setOnAction(e->{
			App.getLayout().setCenter(ToolsBar.getInstance().getLastStacks().pop());
		});
		
		label.setStyle("-fx-translate-x:0;-fx-translate-y:-25;-fx-font-size:25;-fx-text-fill:"+((goodNews)?"GREEN":"RED"));
		ok.setStyle("-fx-translate-x:0;-fx-translate-y:25; -fx-min-width:100;-fx-min-height:50;-fx-font-size:14;-fx-background-radius:15px");
		
		layout.setAlignment(Pos.CENTER);
		ToolsBar.getInstance().getLastStacks().push(App.getLayout().getCenter());
		App.getLayout().setCenter(layout);
		media.play();
	}
}
