package it.polito.tdp.lab04.model;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

import java.util.*;

public class Model {
	
	private CorsoDAO corsoDAO=null;
	private StudenteDAO studenteDAO=null;
	
	public Model() {
		corsoDAO = new CorsoDAO();
		studenteDAO = new StudenteDAO();
	}
	
	public List<Corso> getCorsi() {
		List<Corso> risultatoCorsi = corsoDAO.getTuttiICorsi();
		return risultatoCorsi;
	}
	
	public List<Studente> getStudenti(){
		List<Studente> risultatoStudenti = studenteDAO.getTuttiGliStudenti();
		return risultatoStudenti;
	}
	
	
	public String getNomeFromMatricola(int matricola) {
		String nome="";
		for(Studente s: studenteDAO.getTuttiGliStudenti()) {
			if(s.getMatricola()==matricola) {
				nome+=s.getNome();
			}
		}
		return nome;
	}
	
	public String getCognomeFromMatricola(int matricola) {
		String cognome="";
		for(Studente s: studenteDAO.getTuttiGliStudenti()) {
			if(s.getMatricola()==matricola) {
				cognome+=s.getCognome();
			}
		}
		return cognome;
	}
	
	public List<Studente> studentiCorsi(Corso corso){
		List<Studente> matricoleStudentiCorsi = corsoDAO.getStudentiIscrittiAlCorso(corso);
		List<Studente> studentiIscrittiCorsi = new LinkedList<Studente>();
		for(Studente s: studenteDAO.getTuttiGliStudenti()) {
			for(Studente s1 : matricoleStudentiCorsi) {
				if(s.getMatricola()==s1.getMatricola()) {
					Studente s2= new Studente(s1.getMatricola(), s.getNome() , s.getCognome(), s.getCds());
					studentiIscrittiCorsi.add(s2);
				}
			}	
		}
		return studentiIscrittiCorsi;
	}
	
	public List<Corso> corsiStudenti(Studente studente){
		List<Corso> codinsCorsi = corsoDAO.getCorsiFromMatricola(studente);
		List<Corso> corsiFrequentatiDaStudenti = new LinkedList<Corso>();
		for(Corso c: corsoDAO.getTuttiICorsi()) {
			for(Corso c1: codinsCorsi) {
				if(c.getCodins().compareTo(c1.getCodins())==0) {
					Corso c2= new Corso(c1.getCodins(), c.getCrediti(), c.getNome(), c.getPd());
					corsiFrequentatiDaStudenti.add(c2);
				}
			}
		}
		return corsiFrequentatiDaStudenti;
	}
	
	public boolean isIscritto(Studente studente, Corso corso) {
		return studenteDAO.iscrittoCorso(studente, corso);
	}

}
		

