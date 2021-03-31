package it.polito.tdp.spellchecker;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
//import java.util.Dictionary;
import java.util.ResourceBundle;
import it.polito.tdp.spellchecker.model.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class FXMLController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> boxLanguage;
    
    private ObservableList<String> lingue = FXCollections.observableArrayList("Italian", "English"); 

    @FXML
    private TextArea txtInput;

    @FXML
    private Button btnSpellCheck;

    @FXML
    private TextArea txtWrongWords;

    @FXML
    private Label txtErrors;

    @FXML
    private Button btnClearText;

    @FXML
    private Label txtTime;

    @FXML
    void doClearText(ActionEvent event) {
    	
    	txtInput.clear();
    	txtWrongWords.clear();

    }

    @FXML
    void doSpellCheck(ActionEvent event) {
    	
    	 Dictionary d = new Dictionary();
         d.loadDictionary(boxLanguage.getValue());
         d.setLanguage(boxLanguage.getValue());
         List<String> testoInput = getInput();
         d.spellCheckTextDichotomic(testoInput);
         txtWrongWords.setText(d.getWrongWords());
         txtErrors.setText("Ci sono " + d.getErrors() + " errori");

    }
    
    private List<String> getInput () {
    	List<String> stemp = new LinkedList<String>();
    	for (String s : txtInput.getText().split(" ")) {
    		stemp.add(s);
    	}
    	return stemp;
    }

    @FXML
    void initialize() {
        assert boxLanguage != null : "fx:id=\"boxLanguage\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtInput != null : "fx:id=\"txtInput\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnSpellCheck != null : "fx:id=\"btnSpellCheck\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtWrongWords != null : "fx:id=\"txtWrongWords\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtErrors != null : "fx:id=\"txtErrors\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnClearText != null : "fx:id=\"btnClearText\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtTime != null : "fx:id=\"txtTime\" was not injected: check your FXML file 'Scene.fxml'.";
        boxLanguage.setItems(lingue);
        
       
    }
}
