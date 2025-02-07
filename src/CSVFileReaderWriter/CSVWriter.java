package CSVFileReaderWriter;

import java.io.*;
import java.util.List;

public class CSVWriter {

    public static <T extends CSVSerializable<T>> void writeEntity(String filePath, List<T> entities) throws Exception {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));

        if (entities.isEmpty()) {
            writer.write(entities.get(0).toCSVString().split("\n")[0]);
            writer.newLine();

            for (T entity : entities) {
                writer.write(entity.toCSVString());
                writer.newLine();
            }
        }
        writer.close();
    }

}
