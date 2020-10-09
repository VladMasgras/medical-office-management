package Cabinet;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class Audit {
    public static void write(String action, String thread) {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new FileWriter("audit.csv", true));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Date date = new Date();
        writer.println(date + "," + action + "," + thread);

        writer.flush();
        writer.close();
    }
}
