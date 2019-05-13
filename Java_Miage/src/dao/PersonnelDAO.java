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
	
	private final String CSV_FILE_PATH_PERSONNEL = ControleurParams.pathPers;
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

	@Override
	public boolean create(Personnel obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Personnel obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Personnel oldObj, Personnel newObj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Personnel find(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	
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
