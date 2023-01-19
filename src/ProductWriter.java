
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;

public class ProductWriter {
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);

        ArrayList<String> productCSVData = new ArrayList<>();

        String ID = "";
        String Name = "";
        String Description = "";
        Double Cost = 0.0;
        String productRecord = "";

        String YN ="";
        boolean again = false;

        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\productTestData.txt");






        // for(int i = 0; i < ID.length; i++);

        do{
            ID = SafeInput.getNonZeroLenString(in,"Enter the ID as 6 digits");
            Name = SafeInput.getNonZeroLenString(in,"Enter the name");
            Description = SafeInput.getNonZeroLenString(in,"Enter the description");
            Cost = SafeInput.getRangedDouble(in,"Enter the cost", 1, 9999);

            productRecord = (ID + ","+ Name + "," + Description+ "," +Cost);
            productCSVData.add(productRecord);

            again = SafeInput.getYNConfirm(in,"Are you done entering prompts?");


        }while(!again);

        for(String p:productCSVData)

            System.out.println(p);
        try
        {
            // Typical java pattern of inherited classes
            // we wrap a BufferedWriter around a lower level BufferedOutputStream
            OutputStream out =
                    new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer =
                    new BufferedWriter(new OutputStreamWriter(out));

            for(String rec : productCSVData)
            {
                writer.write(rec, 0, rec.length());
                writer.newLine();  // adds the new line

            }
            writer.close(); // must close the file to seal it and flush buffer
            System.out.println("Data file written!");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
