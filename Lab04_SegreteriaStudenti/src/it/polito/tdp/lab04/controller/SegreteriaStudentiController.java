package it.polito.tdp.lab04.controller;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class SegreteriaStudentiController {
	
	Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Corso> btnComboBox;

    @FXML
    private Button btnCercaIscrittiCorso;

    @FXML
    private TextArea txtMatricola;

    @FXML
    private ImageView imgPlay;

    @FXML
    private TextArea txtNome;

    @FXML
    private TextArea txtCognome;

    @FXML
    private Button btnCercaCorsi;

    @FXML
    private Button btnIscrivi;

    @FXML
    private TextArea txtResult;

    @FXML
    private Button btnReset;

    @FXML
    void doIscrivi(ActionEvent event) {

    }

    @FXML
    void doPlay(MouseEvent event) {
    	try {
    	txtNome.setText(model.getNomeFromMatricola(Integer.parseInt(txtMatricola.getText())));
    	txtCognome.setText(model.getCognomeFromMatricola(Integer.parseInt(txtMatricola.getText())));
    	}
    	catch(NumberFormatException e){
    		throw e;
    	}
    	catch(RuntimeException e) {
    		throw e;
    	}

    }

    @FXML
    void doReset(ActionEvent event) {
    	txtMatricola.clear();
		txtResult.clear();
		txtNome.clear();
		txtCognome.clear();
    }

    @FXML
    void doSearchCourse(ActionEvent event) {
    	txtResult.clear();
    	try {
    		if(btnComboBox.getValue()!=null) {
    			Studente s=new Studente(Integer.parseInt(txtMatricola.getText()));
    	    	if(!model.getStudenti().contains(s)) {
    	    		txtResult.setText("Errore: Studente non presente");
    	    		return;
    	    	}
    	    	if(model.isIscritto(s, btnComboBox.getValue())) {
    	    		txtResult.setText("Studente iscritto al corso in questione");
    	    		return;
    	    	}
    	    	else {
    	    		txtResult.setText("Studente non iscritto al corso in questione");
    	    		return;
    	    	}
    		}
    		Studente s=new Studente(Integer.parseInt(txtMatricola.getText()));
    		if(!model.getStudenti().contains(s)) {
    			txtResult.setText("Errore: Studente non presente");
    			return;
    		}
    		String risultato="";
    		for(Corso c: model.corsiStudenti(s)) {
    			risultato+=c.getCodins()+" "+c.getCrediti()+" "+c.getNome()+" "+c.getPd()+"\n";
    		}
    		txtResult.setText(risultato);
		}
    	catch(NumberFormatException e){
    		throw e;
    	}
    	catch(RuntimeException e) {
    		throw e;
    	}
    	

    }

    @FXML
    void doSearchStudent(ActionEvent event) {
    	txtResult.clear();
    	try {
    	Corso c = btnComboBox.getValue();
    	if(c==null) {
    		txtResult.setText("Seleziona corso");
    		return;
    		}
    	String risultato="";
    	for(Studente s: model.studentiCorsi(c)) {
    		risultato+=s.getMatricola()+" "+s.getNome()+" "+s.getCognome()+" "+s.getCds()+"\n";
    		}
    	txtResult.setText(risultato);
    	}
    	catch(NumberFormatException e){
    		throw e;
    	}
    	catch(RuntimeException e) {
    		throw e;
    	}

    }
    
    void setModel() {
    	model = new Model();
    	btnComboBox.getItems().addAll(model.getCorsi());
    }

    @FXML
    void initialize() {
        assert btnComboBox != null : "fx:id=\"btnComboBox\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnCercaIscrittiCorso != null : "fx:id=\"btnCercaIscrittiCorso\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert imgPlay != null : "fx:id=\"imgPlay\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnCercaCorsi != null : "fx:id=\"btnCercaCorsi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnIscrivi != null : "fx:id=\"btnIscrivi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        setModel();

    }
}
