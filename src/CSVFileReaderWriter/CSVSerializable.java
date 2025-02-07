package CSVFileReaderWriter;

public interface CSVSerializable <T > {

    /**
     * @return the line of the CSV file that represent this object
     */
    public String toCSVString();

    /**
     * @param line the line of the CSV file that represent this object
     *             return the object
     */
    T fromCSVString(String line);
}
