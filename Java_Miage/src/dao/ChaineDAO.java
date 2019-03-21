package dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
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
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(CSV_FILE_PATH_CHAINE, true));
			try {				
				bw.write(obj.getCode() +";" + obj.getNom() +";" + obj.getSEntree() + ";" + obj.getSSortie() +";" + obj.getNivAct() 
							+";" + obj.getResultat() + "\n");
				bw.close();
				return true;
			} catch (IOException e) {
				e.printStackTrace();
				bw.close();
				return false;
			}
		} catch (IOException e1) {
			return false;
		}
	}

	public boolean delete(Chaine obj) {
		try {
			File file = new File(CSV_FILE_PATH_CHAINE);
			BufferedReader br = new BufferedReader(new FileReader(file));
			File tempFile = new File(CSV_FILE_PATH_CHAINE + ".tmp");
			BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile, true));
			String line = null;
			String remove = (obj.getCode() +";" + obj.getNom() +";" + obj.getSEntree() + ";" + obj.getSSortie() +";" + obj.getNivAct() 
			+";" + obj.getResultat());
			
			try {
				while((line = br.readLine()) != null) {
					if (!line.equals(remove)) {
						bw.write(line + "\n");
					}
				}
				
				bw.close();
				br.close();
				
				file.delete();
				tempFile.renameTo(file);
				
				return true;
			} catch (IOException e) {
				e.printStackTrace();
				bw.close();
				return false;
			}
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean update(Chaine oldObj, Chaine newObj) {
		try {
			File file = new File(CSV_FILE_PATH_CHAINE);
			BufferedReader br = new BufferedReader(new FileReader(file));
			File tempFile = new File(CSV_FILE_PATH_CHAINE + ".tmp");
			BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile, true));
			String line = null;
			String old = (oldObj.getCode() +";" + oldObj.getNom() +";" + oldObj.getSEntree() + ";" + oldObj.getSSortie() +";" + oldObj.getNivAct() 
			+";" + oldObj.getResultat()); 
			String update = (newObj.getCode() +";" + newObj.getNom() +";" + newObj.getSEntree() + ";" + newObj.getSSortie() +";" + newObj.getNivAct() 
			+";" + newObj.getResultat());
			
			try {
				while((line = br.readLine()) != null) {
					if (line.equals(old)) {
						bw.write(update + "\n");
					} else {
						bw.write(line + "\n");
					}
				}
				
				bw.close();
				br.close();
				
				file.delete();
				tempFile.renameTo(file);
				
				return true;
			} catch (IOException e) {
				e.printStackTrace();
				bw.close();
				return false;
			}
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
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
		            String resultat;
		            
		            //Si le niveau d'activité n'est pas précisé, le définit à "1" par défaut
		            if (csvRecord.isSet("NivActivite")) {
		            	nivAct = Integer.parseInt(csvRecord.get(4));
		            } else {
		            	nivAct = 1;
		            }
		            
		            //Si le niveau d'activité n'est pas précisé, le définit à "1" par défaut
		            if (csvRecord.isSet("Resultat")) {
		            	resultat = csvRecord.get(5);
		            } else {
		            	resultat = "Indéfini";
		            }
		            		            
		            Chaine chaine = new Chaine(code, nom, entree, sortie, nivAct, resultat);
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
