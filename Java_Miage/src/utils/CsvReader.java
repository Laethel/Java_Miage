package utils;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CsvReader {
    private static final String CSV_FILE_PATH = "/Users/quent/Documents/FichiersV1__78__0/elements.csv";

    public static void main(String[] args) throws IOException {
        try (
            Reader reader = Files.newBufferedReader(Paths.get(CSV_FILE_PATH));
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withDelimiter(';'));
        		
        ) {
            for (CSVRecord csvRecord : csvParser) {
                // Accessing Values by Column Index
                String code = csvRecord.get(0);
                String nom = csvRecord.get(1);
                String qte = csvRecord.get(2);
                String unite = csvRecord.get(3);
                String prixAchat = csvRecord.get(4);
                String prixVente = csvRecord.get(5);

                System.out.println("---------------");
                System.out.println("Code : " + code);
                System.out.println("Nom : " + nom);
                System.out.println("Quantite : " + qte);
                System.out.println("Unite : " + unite);
                System.out.println("Prix achat : " + prixAchat);
                System.out.println("Prix vente : " + prixVente);
                System.out.println("---------------\n\n");
            }
        }
    }
}
