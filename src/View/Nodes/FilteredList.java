package View.Nodes;


/*this is an helper object for long choise options.
 *its like a ListView, but it have another text field to filter it.
 *the filter, with complexability of O(n^2), filtered by text only.
 *the add, remove and clear method used to define not just the showed list, but also the internal one. **/

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;

import application.App;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;


public class FilteredList<T> extends VBox{
	
	private ListView<T> list;
	private TextField search;
	private LinkedList<T> internalList;

	
	
	public FilteredList(Collection<T> collection) {
		internalList=new LinkedList<>(collection);
		list=new ListView<T>();
		list.getItems().addAll(internalList);
		search=new TextField();
		search.setPromptText("search item");
		search.textProperty().addListener(e->{
			list.getItems().clear();
			for(T t:internalList) {
				if(t.toString().toUpperCase().contains(search.getText().toUpperCase())) {
					list.getItems().add(t);
				}
			}
			if(list.getItems().isEmpty()) {
				App.setBorderColor(list, "RED");
			}
		});
		this.getChildren().addAll(search,list);
	}
	
	public FilteredList(T[] array) {
		internalList=new LinkedList<>(Arrays.asList(array));
		list=new ListView<T>();
		list.getItems().addAll(internalList);
		search=new TextField();
		search.setPromptText("search item");
		search.textProperty().addListener(e->{
			list.getItems().clear();
			for(T t:internalList) {
				if(t.toString().toUpperCase().contains(search.getText().toUpperCase())) {
					list.getItems().add(t);
					App.setBorderColor(list, "GREEN");
				}
			}
			if(list.getItems().isEmpty()) {
				App.setBorderColor(list, "RED");
			}
		});
		if(list.getItems().isEmpty()) {
			App.setBorderColor(list, "RED");
		}
		this.getChildren().addAll(search,list);
	}

	
	public ListView<T> getList() {
		return list;
	}


	public TextField getSearch() {
		return search;
	}
	
	public boolean add(T t) {
		boolean b=list.getItems().add(t)&&internalList.add(t);
		return b;
	}
	
	public boolean remove(T t) {
		return list.getItems().remove(t)&&internalList.remove(t);
	}
	
	public boolean addAll(Collection<T> col) {
		return list.getItems().addAll(col)&&internalList.addAll(col);
	}
	

	
	public boolean removeAll(Collection<T> col) {
		return list.getItems().removeAll(col)&&internalList.removeAll(col);
	}
	
	public boolean addAll(T[] arr) {
		return addAll(Arrays.asList(arr));
	}
	
	public boolean removeAll(T[] arr) {
		return removeAll(Arrays.asList(arr));
	}
	
	public void clear() {
		list.getItems().clear();
		internalList.clear();;
	}
	
	
}
