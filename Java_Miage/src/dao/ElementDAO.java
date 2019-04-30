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

import modele.Element;
import params.ControleurParams;

/**
 * @author Quentin Beaussart et Damian Riquart
 *
 */
public class ElementDAO extends Dao<Element> {
	
	private final String CSV_FILE_PATH_ELEMENT = ControleurParams.pathElem;
	
	@Override
	public boolean create(Element obj) {
		try {			
			BufferedWriter bw = new BufferedWriter(new FileWriter(CSV_FILE_PATH_ELEMENT, true));
			
			try {				
				bw.write(obj.getCode() +";" + obj.getNom() +";" + obj.getQte() + ";" + obj.getUnite() +";" + obj.getPrixAchat() 
							+";" + obj.getPrixVente() + ";" + obj.getDemande() + "\n");
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

	/* (non-Javadoc)
	 * @see dao.Dao#delete(java.lang.Object)
	 */
	@Override
	public boolean delete(Element obj) {
		try {
			File file = new File(CSV_FILE_PATH_ELEMENT);
			BufferedReader br = new BufferedReader(new FileReader(file));
			File tempFile = new File(CSV_FILE_PATH_ELEMENT + ".tmp");
			BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile, true));
			String line = null;
			String remove = obj.getCode() +";" + obj.getNom() +";" + obj.getQte() + ";" + obj.getUnite() +";" + obj.getPrixAchat() 
			+";" + obj.getPrixVente() + ";" + obj.getDemande();
			
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

	/* (non-Javadoc)
	 * @see dao.Dao#update(java.lang.Object, java.lang.Object)
	 */
	@Override
	public boolean update(Element oldObj, Element newObj) {
		try {
			File file = new File(CSV_FILE_PATH_ELEMENT);
			BufferedReader br = new BufferedReader(new FileReader(file));
			File tempFile = new File(CSV_FILE_PATH_ELEMENT + ".tmp");
			BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile, true));
			String line = null;
			String old = oldObj.getCode() +";" + oldObj.getNom() +";" + oldObj.getQte() + ";" + oldObj.getUnite() +";" + oldObj.getPrixAchat() 
			+";" + oldObj.getPrixVente() + ";" + oldObj.getDemande(); 
			String update = newObj.getCode() +";" + newObj.getNom() +";" + newObj.getQte() + ";" + newObj.getUnite() +";" + newObj.getPrixAchat() 
			+";" + newObj.getPrixVente() + ";" + newObj.getDemande();
						
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

	/* (non-Javadoc)
	 * @see dao.Dao#find(int)
	 */
	@Override
	public Element find(String id) {
		Element elem = null;
		if(CSV_FILE_PATH_ELEMENT != null) {
			try {
				Reader reader = Files.newBufferedReader(Paths.get(CSV_FILE_PATH_ELEMENT));
		        CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader().withDelimiter(';').withNullString("").withIgnoreSurroundingSpaces());
		        				
		        for (CSVRecord csvRecord : csvParser) {
		            String code = csvRecord.get(0);
		            
		            if (code.equals(id)) {
		            	String nom = csvRecord.get(1);
				        double qte = Double.parseDouble(csvRecord.get(2));
				        String unite = csvRecord.get(3);
				        String prixAchat = csvRecord.get(4);
				        String prixVente =csvRecord.get(5);
				        double demande = Double.parseDouble(csvRecord.get(6));
				        elem = new Element(code, nom, qte, unite, prixAchat, prixVente, demande);
		            }
		        }				
				csvParser.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return elem;
	}

	/* (non-Javadoc)
	 * @see dao.Dao#findAll()
	 */
	@Override
	public ArrayList<Element> findAll() {
		ArrayList<Element> elements = new ArrayList<Element>();
		
		if(CSV_FILE_PATH_ELEMENT != null) {
			try {
				Reader reader = Files.newBufferedReader(Paths.get(CSV_FILE_PATH_ELEMENT));
		        CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader().withDelimiter(';').withNullString("").withIgnoreSurroundingSpaces());
		        				
		        for (CSVRecord csvRecord : csvParser) {
		            String code = csvRecord.get(0);
		            String nom = csvRecord.get(1);
		            double qte = Double.parseDouble(csvRecord.get(2));
		            String unite = csvRecord.get(3);
		            String prixAchat = csvRecord.get(4);
		            String prixVente =csvRecord.get(5);
		            double demande = Double.parseDouble(csvRecord.get(6));
		            Element elem = new Element(code, nom, qte, unite, prixAchat, prixVente, demande);
		            elements.add(elem);
		        }
				
				csvParser.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return elements;
	}
}
