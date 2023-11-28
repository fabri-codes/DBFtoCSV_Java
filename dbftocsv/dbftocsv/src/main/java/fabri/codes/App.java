package fabri.codes;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.jamel.dbf.DbfReader;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class App {
    public static void main(String[] args) {
        String filePath = "path/to/.dbf";
        File dbfFilePath = new File(filePath);
        String csvFilePath = "path/to/.csv";

        try (DbfReader reader = new DbfReader(dbfFilePath, StandardCharsets.UTF_8);
             CSVPrinter csvPrinter = new CSVPrinter(new FileWriter(csvFilePath), CSVFormat.DEFAULT)) {

            // Write CSV header
            //csvPrinter.printRecord(((Object) reader).getFieldNames());

            // Write CSV data
            Object[] row;
            while ((row = reader.nextRecord()) != null) {
                csvPrinter.printRecord(row);
            }

            System.out.println("DBF file converted to CSV successfully!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
