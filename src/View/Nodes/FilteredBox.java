package View.Nodes;

/*this is an helper object for long choise option.
 *its like a ComboBox, but it have another text field to filter it.
 *the filter, with complexability of O(n^2), filtered by text only.
 *the add, remove and clear method used to define not just the showed list, but also the internal one.
 *that filter is also indictive. **/


import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;

import Exceptions.BlankFieldException;
import application.App;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class FilteredBox<T> extends VBox{
	
	private ComboBox<T> box;
	private TextField search;
	private LinkedList<T> internalList;
	
	
	public FilteredBox(Collection<T> collection) {
		
		box=new ComboBox<T>();
		internalList=new LinkedList<>(collection);
		box.getItems().addAll(internalList);
		search=new TextField();
		search.setPromptText("search item");
		search.textProperty().addListener(e->{
			box.getItems().clear();
			for(T t:internalList) {
				if(t.toString().toUpperCase().contains(search.getText().toUpperCase())) {
					box.getItems().add(t);
					box.show();
				}
			}
		});

		box.valueProperty().addListener(e1->{
			try {
				this.isLegal();
				App.setBorderColor(box, "GREEN");
				
			} catch (BlankFieldException e21) {
				App.setBorderColor(box, "RED");
			}
			});

		this.getChildren().addAll(search,box);
	}
	
	public FilteredBox(T[] array) {
		internalList=new LinkedList<>(Arrays.asList(array));
		box=new ComboBox<T>();
		box.getItems().addAll(internalList);
		search=new TextField();
		search.setPromptText("search item");
		search.textProperty().addListener(e->{
			box.getItems().clear();
			for(T t:internalList) {
				if(t.toString().toUpperCase().contains(search.getText().toUpperCase())) {
					box.getItems().add(t);
					box.show();
				}
			}
		});

		box.valueProperty().addListener(e1->{
				try {
					this.isLegal();
					App.setBorderColor(box, "GREEN");
					
					
				} catch (BlankFieldException e2) {
					App.setBorderColor(box, "RED");
				}
			});


		this.getChildren().addAll(search,box);
	}

	public void isLegal() throws BlankFieldException {
		if (box.getSelectionModel().isEmpty()) {
			throw new BlankFieldException();
		}
	}
	
	public void checkStyle(String s) {
		this.setStyle(s);
		try {
			isLegal();
			App.setBorderColor(this.getBox(), "GREEN");

		} catch (BlankFieldException e1) {
			App.setBorderColor(this.getBox(), "RED");
		}
	}
	
	
	public ComboBox<T> getBox() {
		return box;
	}


	public TextField getSearch() {
		return search;
	}
	
	public boolean add(T t) {
		boolean b=box.getItems().add(t)&&internalList.add(t);
		return b;
	}
	
	public boolean remove(T t) {
		return box.getItems().remove(t)&&internalList.remove(t);
	}
	
	public boolean addAll(Collection<T> col) {
		return box.getItems().addAll(col)&&internalList.addAll(col);
	}
	

	
	public boolean removeAll(Collection<T> col) {
		return box.getItems().removeAll(col)&&internalList.removeAll(col);
	}
	
	public boolean addAll(T[] arr) {
		return addAll(Arrays.asList(arr));
	}
	
	public boolean removeAll(T[] arr) {
		return removeAll(Arrays.asList(arr));
	}
	
	public void clear() {
		box.getItems().clear();
		internalList.clear();;
	}
}
