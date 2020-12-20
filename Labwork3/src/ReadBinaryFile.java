import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadBinaryFile {
    public static List<Integer> readBinaryFile(String pathToFile) throws IOException {
        List<Integer> listValues = new ArrayList<>();
        FileInputStream fin = null;
        try {
            fin = new FileInputStream(pathToFile);
        } catch (FileNotFoundException e) {
            System.out.println("File isn't found");
        }
        int value = fin.read();
        while (value != -1) {
            value = fin.read();
            listValues.add(value);
        }
        return listValues;
    }
}
