package CSVFileReaderWriter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {

    public static <T extends CSVSerializable<T>> List<T> readEntity(String filePath, Class<T> entityClass)
            throws Exception {
        List<T> entites = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String ligne;

        reader.readLine(); // skip header
        while ((ligne = reader.readLine()) != null) {
            T entity = entityClass.getDeclaredConstructor().newInstance();
            entites.add(entity.fromCSVString(ligne));
        }
        reader.close();
        return entites;
    }

}
