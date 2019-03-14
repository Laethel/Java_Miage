package dao;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import modele.Chaine;
import params.ControleurParams;

public class ChaineDAO extends Dao<Chaine> {
	
	private final String CSV_FILE_PATH_CHAINE = ControleurParams.pathCh;

	public boolean create(Chaine obj) {
		return false;
	}

	public boolean delete(Chaine obj) {
		return false;
	}

	public boolean update(Chaine oldObj, Chaine newObj) {
		return false;
	}

	public Chaine find(int id) {
		return null;
	}

	public ArrayList<Chaine> findAll() {

		ArrayList<Chaine> chaines = new ArrayList<Chaine>();
		
		if(CSV_FILE_PATH_CHAINE != null) {
			try {
				Reader reader = Files.newBufferedReader(Paths.get(CSV_FILE_PATH_CHAINE));
		        CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader().withDelimiter(';').withNullString("").withIgnoreSurroundingSpaces());
		        				
		        for (CSVRecord csvRecord : csvParser) {
		            String code = csvRecord.get(0);
		            String nom = csvRecord.get(1);
		            String entree = csvRecord.get(2);
		            String sortie = csvRecord.get(3);
		            int nivAct;
		            
		            //Si le niveau d'activité n'est pas précisé, le définit à "1" par défaut
		            if (csvRecord.isSet("NivActivite")) {
		            	nivAct = Integer.parseInt(csvRecord.get(4));
		            } else {
		            	nivAct = 1;
		            }
		            
		            Chaine chaine = new Chaine(code, nom, entree, sortie, nivAct);
		            chaines.add(chaine);
		        }
				csvParser.close();
			
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return chaines;
	}


}
