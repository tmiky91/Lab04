package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {

	public List<Studente> getTuttiGliStudenti() {
		
		//final String sql1 = "SELECT nome, cognome FROM studente where matricola=?";
		final String sql1 = "SELECT * FROM studente";
		
		List<Studente> studenti = new LinkedList<Studente>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql1);
			//st.setString(1, matricola);
			ResultSet rs = st.executeQuery();
			
			while (rs.next()) {
				
				int matricola= rs.getInt("matricola");
				String nome = rs.getString("nome");
				String cognome = rs.getString("cognome");
				String cds = rs.getString("cds");

				Studente s = new Studente(matricola, nome, cognome, cds);
				studenti.add(s);
				
			}
			return studenti;
			
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
		
	}
	
	public boolean iscrittoCorso(Studente studente, Corso corso) {
		
		final String sql = "SELECT * FROM iscrizione where codins=? and matricola=?";
		boolean iscritto= false;
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, corso.getCodins());
			st.setInt(2, studente.getMatricola());
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				iscritto=true;
			}
			return iscritto;
			
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}

}
