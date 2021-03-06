package dao;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import modele.Personnel;
import params.ControleurParams;

public class PersonnelDAO extends Dao<Personnel>{
	
	/**
	 * Variable qui stock le chemin du fichier du personnel
	 */
	private final String CSV_FILE_PATH_PERSONNEL = ControleurParams.pathPers;
	
	/**
	 * ObersableList qui stock sous forme d'objet le personne
	 */
	private ObservableList<Personnel> personnel;
	
	/* (non-Javadoc)
	 * @see dao.Dao#findAll()
	 */
	@Override
	public ArrayList<Personnel> findAll() {
		ArrayList<Personnel> personnel = new ArrayList<Personnel>();
		
		if(CSV_FILE_PATH_PERSONNEL != null) {
			try {
				Reader reader = Files.newBufferedReader(Paths.get(CSV_FILE_PATH_PERSONNEL));
		        CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader().withDelimiter(';').withNullString("").withIgnoreSurroundingSpaces());
		        				
		        for (CSVRecord csvRecord : csvParser) {
		            String prenom = csvRecord.get(0);
		            String nom = csvRecord.get(1);
		            String qualif = csvRecord.get(2);
		            double heuresSemaine = Double.parseDouble(csvRecord.get(3));
		            Personnel pers = new Personnel(prenom, nom, qualif, heuresSemaine);
		            personnel.add(pers);
		        }
				
				csvParser.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return personnel;
	}

	/* (non-Javadoc)
	 * @see dao.Dao#create(java.lang.Object)
	 */
	@Override
	public boolean create(Personnel obj) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see dao.Dao#delete(java.lang.Object)
	 */
	@Override
	public boolean delete(Personnel obj) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see dao.Dao#update(java.lang.Object)
	 */
	@Override
	public boolean update(Personnel oldObj, Personnel newObj) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see dao.Dao#find(java.lang.Object)
	 */
	@Override
	public Personnel find(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Méthode qui permet de calculer le total des heures disponibles du personnel qualifié
	 * @return Retourne le total des heures disponibles du personnel qualifié
	 */
	public double heuresQualifDispo() {
		this.personnel = FXCollections.observableArrayList(findAll());
		double heuresQualif = 0;
		for(Personnel p : personnel) {
			if(p.getQualif().equals("Oui")) {
				heuresQualif += p.getHeuresSemaine();
			}
		}
		return heuresQualif;		
	}
	
	/**
	 * Méthode qui permet de calculer le total des heures disponibles du personnel non qualifié
	 * @return Retourne le total des heures disponibles du personnel non qualifié
	 */
	public double heuresNonQualifDispo() {
		this.personnel = FXCollections.observableArrayList(findAll());
		double heuresNonQualif = 0;
		for(Personnel p : personnel) {
			if(p.getQualif().equals("Non")) {
				heuresNonQualif += p.getHeuresSemaine();
			}
		}
		return heuresNonQualif;
	}
}
