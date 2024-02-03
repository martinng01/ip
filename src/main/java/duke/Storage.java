package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Storage {
    private static final String FILE_PATH = "data/duke.txt";
    private static File file;

    /**
     * Creates a new file if it does not exist.
     */
    public static void init() {
        try {
            file = new File(FILE_PATH);
            file.getParentFile().mkdirs();
            file.createNewFile();
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    /**
     * Writes the given string to the file.
     *
     * @param str The string to be written to the file.
     */
    public static void write(String str) {
        try {
            FileWriter fileWriter = new FileWriter(FILE_PATH);
            fileWriter.write(str);
            fileWriter.close();
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    /**
     * Reads the file and returns its contents as a string.
     *
     * @return The contents of the file as a string.
     */
    public static String read() {
        try {
            Scanner scanner = new Scanner(file);
            StringBuilder str = new StringBuilder();
            while (scanner.hasNext()) {
                str.append(scanner.nextLine());
                if (scanner.hasNext()) {
                    str.append("\n");
                }
            }
            return str.toString();
        } catch (FileNotFoundException e) {
            System.out.println(e.toString());
            return "";
        }
    }
}
