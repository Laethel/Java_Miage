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
	
	private String CSV_FILE_PATH_CHAINE = ControleurParams.pathCh;

	public String getCSV_FILE_PATH_CHAINE() {
		return CSV_FILE_PATH_CHAINE;
	}

	public void setCSV_FILE_PATH_CHAINE(String cSV_FILE_PATH_CHAINE) {
		CSV_FILE_PATH_CHAINE = cSV_FILE_PATH_CHAINE;
	}

	public boolean create(Chaine obj) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(CSV_FILE_PATH_CHAINE, true));
			try {				
				bw.write(obj.getCode() +";" + obj.getNom() +";" + obj.getSEntree() + ";" + obj.getSSortie() +";" + obj.getNivAct()  + "\n");
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
			String remove = (obj.getCode() +";" + obj.getNom() +";" + obj.getSEntree() + ";" + obj.getSSortie() +";" + obj.getNivAct());
						
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
			String old = (oldObj.getCode() +";" + oldObj.getNom() +";" + oldObj.getSEntree() + ";" + oldObj.getSSortie() +";" + oldObj.getNivAct()); 
			String update = (newObj.getCode() +";" + newObj.getNom() +";" + newObj.getSEntree() + ";" + newObj.getSSortie() +";" + newObj.getNivAct());
			
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

	public Chaine find(String id) {
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
		            
		            int nivAct = Integer.parseInt(csvRecord.get(4));
		            		            
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
	
	//WIP Override
	/*public ArrayList<Chaine> findAll(String cheminSemaine) {

		ArrayList<Chaine> chaines = new ArrayList<Chaine>();
		
		if(cheminSemaine != null) {
			try {
				Reader reader = Files.newBufferedReader(Paths.get(cheminSemaine));
		        CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader().withDelimiter(';').withNullString("").withIgnoreSurroundingSpaces());
		        				
		        for (CSVRecord csvRecord : csvParser) {
		            String code = csvRecord.get(0);
		            String nom = csvRecord.get(1);
		            String entree = csvRecord.get(2);
		            String sortie = csvRecord.get(3);
		            
		            int nivAct = Integer.parseInt(csvRecord.get(4));
		            		            
		            Chaine chaine = new Chaine(code, nom, entree, sortie, nivAct);
		            chaines.add(chaine);
		        }
				csvParser.close();
			
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return chaines;
	}*/


}
