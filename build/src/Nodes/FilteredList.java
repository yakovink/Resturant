package Nodes;


import java.util.Arrays;
import java.util.Collection;


import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;


public class FilteredList<T> extends VBox{
	
	private ListView<T> list;
	private TextField search;
	
	
	public FilteredList(Collection<T> collection) {
		
		list=new ListView<T>();
		list.getItems().addAll(collection);
		search=new TextField();
		search.setPromptText("search item");
		search.textProperty().addListener(e->{
			list.getItems().clear();
			for(T t:collection) {
				if(t.toString().toUpperCase().contains(search.getText().toUpperCase())) {
					list.getItems().add(t);
				}
			}
		});
		this.getChildren().addAll(search,list);
	}
	
	public FilteredList(T[] array) {
		Collection<T> collection=Arrays.asList(array);
		list=new ListView<T>();
		list.getItems().addAll(collection);
		search=new TextField();
		search.setPromptText("search item");
		search.textProperty().addListener(e->{
			list.getItems().clear();
			for(T t:collection) {
				if(t.toString().toUpperCase().contains(search.getText().toUpperCase())) {
					list.getItems().add(t);
				}
			}
		});
		this.getChildren().addAll(search,list);
	}


	public ListView<T> getList() {
		return list;
	}


	public TextField getSearch() {
		return search;
	}
	

	
	
}
