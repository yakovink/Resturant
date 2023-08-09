package Nodes;

import java.util.Arrays;
import java.util.Collection;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class FilteredBox<T> extends VBox{
	
	private ComboBox<T> box;
	private TextField search;
	
	
	public FilteredBox(Collection<T> collection) {
		
		box=new ComboBox<T>();
		box.getItems().addAll(collection);
		search=new TextField();
		search.setPromptText("search item");
		search.textProperty().addListener(e->{
			box.getItems().clear();
			for(T t:collection) {
				if(t.toString().toUpperCase().contains(search.getText().toUpperCase())) {
					box.getItems().add(t);
					box.show();
				}
			}
		});
		this.getChildren().addAll(search,box);
	}
	
	public FilteredBox(T[] array) {
		Collection<T> collection=Arrays.asList(array);
		box=new ComboBox<T>();
		box.getItems().addAll(collection);
		search=new TextField();
		search.setPromptText("search item");
		search.textProperty().addListener(e->{
			box.getItems().clear();
			for(T t:collection) {
				if(t.toString().toUpperCase().contains(search.getText().toUpperCase())) {
					box.getItems().add(t);
					box.show();
				}
			}
		});
		this.getChildren().addAll(search,box);
	}


	public ComboBox<T> getBox() {
		return box;
	}


	public TextField getSearch() {
		return search;
	}
}
