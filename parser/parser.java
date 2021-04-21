import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class parser {

    /**
     * This method parses all the files in the to Parse directory
     */
    public static void main(String[] args) {
        try {
            File path = new File("parser\\to-parse\\");
            File[] files = path.listFiles();
            for (int i = 0; i < files.length; i++) {
                System.out.println("Parsing File: " + (i + 1));
                Parse(files[i]);
                System.out.println();
            }

        } catch (FileNotFoundException e) {
            System.out.print("Error loading one of the files");;
        }
    }

    /**
     * Parses a given flight release
     * @param file the file release txt file to parse
     * @throws FileNotFoundException thrown if the file is not found
     */
    static void Parse(File file) throws FileNotFoundException {
        Scanner parser = new Scanner(file);
        String flight_number = "";
        while (parser.hasNextLine()) {
            String line = parser.nextLine();
            if (line.matches("FLIGHT \\d+\\s+AIRCRAFT ID \\d+")) {
                flight_number = line.split("\\s+")[1];
            }
            if (line.equals("---- --- ---- ---- ---- ---- ---- ----- ----- ----- ----- -----")) {
                System.out.println("Flight Number " + flight_number);
                ParseFlightSequence(parser.nextLine());
            }
        }
    }

    /**
     * parses the flight sequence of a given flight
     * @param line the line of important data
     */
    static void ParseFlightSequence(String line) {
        String[] output = {"Origin: ",
                "Destination: ",
                "Alternate 1: ",
                "Alternate 2: "};
        String[] splitArray = line.split("\\s+");
        int i = 0;
        while (!splitArray[i].matches("-?\\d+")) {
            System.out.println("\u2022 " + output[i] + splitArray[i]);
            i++;
        }
        System.out.println();

    }
}
