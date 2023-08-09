package View.Nodes;

import javafx.scene.Node;
import javafx.scene.control.Label;

/*that label is like normal label, but it have "pushText" method that bind it to LastStacksNames stack at the tools bar**/

public class PlaceLabel extends Label{

	public PlaceLabel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PlaceLabel(String text, Node graphic) {
		super(text, graphic);
		// TODO Auto-generated constructor stub
	}

	public PlaceLabel(String text) {
		super(text);
		// TODO Auto-generated constructor stub
	}
	
	public void pushText(String value) {
		ToolsBar.getInstance().getLastStacksNames().push(getText());
		setText("    "+value);
	}

}
