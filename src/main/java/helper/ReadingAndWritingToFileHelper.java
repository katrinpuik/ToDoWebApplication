package helper;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReadingAndWritingToFileHelper {

    private static final String FILE_NAME = "/home/valiit/IdeaProjects/toDoApplication/src/data.txt";

    public static List<String> initStringsFromFile() throws FileNotFoundException {
        List<String> rowsFromFile = new ArrayList<>();
        File file = new File(FILE_NAME);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            rowsFromFile.add(scanner.nextLine());
        }
        scanner.close();
        return rowsFromFile;
    }

    public static void writeRowsToFile(List<String> rowsFromFile) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME));
        for (String row : rowsFromFile) {
            writer.write(row);
            writer.newLine();
        }
        writer.close();
    }

}
