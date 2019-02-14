package dao;

import java.io.BufferedWriter;
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

public class ElementDAO extends Dao<Element> {
	
	private final String CSV_FILE_PATH_ELEMENT = ControleurParams.pathElem;
	BufferedWriter bw;
	
	@Override
	public boolean create(Element obj) {
		try {
			bw = new BufferedWriter(new FileWriter(CSV_FILE_PATH_ELEMENT, true));
			try {				
				bw.write(obj.getCode() +";" + obj.getNom() +";" + obj.getQte() + ";" + obj.getUnite() +";" + obj.getPrixAchat() 
							+";" + obj.getPrixVente() + "\n");
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

	@Override
	public boolean delete(Element obj) {
		return false;
	}

	@Override
	public boolean update(Element obj) {
		return false;
	}

	@Override
	public Element find(int id) {
		return null;
	}

	@Override
	public ArrayList<Element> findAll() {
		ArrayList<Element> elements = new ArrayList<Element>();
		
		if(CSV_FILE_PATH_ELEMENT != null) {
			try {
				Reader reader = Files.newBufferedReader(Paths.get(CSV_FILE_PATH_ELEMENT));
		        CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withDelimiter(';'));
		        				
		        for (CSVRecord csvRecord : csvParser) {
		            String code = csvRecord.get(0);
		            String nom = csvRecord.get(1);
		            double qte = Double.parseDouble(csvRecord.get(2));
		            String unite = csvRecord.get(3);
		            String prixAchat = csvRecord.get(4);
		            String prixVente =csvRecord.get(5);
		            Element elem = new Element(code, nom, qte, unite, prixAchat, prixVente);
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