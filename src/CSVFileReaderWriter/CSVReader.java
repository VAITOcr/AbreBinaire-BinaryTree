package CSVFileReaderWriter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVReader<T extends CSVSerializable<T>> {

    private Class<T> entityClass;

    public CSVReader(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public List<T> readEntity(String filepath) throws Exception {

        List<T> entities = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            String nextLine;
            boolean firstLine = true;

            while ((nextLine = reader.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue;
                }
                T entity = entityClass.getDeclaredConstructor(String[].class).newInstance((Object) nextLine.split(","));
                entities.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return entities;
    }

}
