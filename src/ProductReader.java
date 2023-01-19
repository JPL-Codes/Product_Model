import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

public class ProductReader {
    public static void main(String[] args)
    {
        JFileChooser chooser = new JFileChooser();
        Scanner inFile;
        String line;
        Path target = new File(System.getProperty("user.dir")).toPath();
        target = target.resolve("src");
        chooser.setCurrentDirectory(target.toFile());

        System.out.println("Please select an existing person data file.");

        try
        {

            if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
            {
                target = chooser.getSelectedFile().toPath();  // object line

                inFile = new Scanner(target);
                System.out.println("ID#        Name          Description        Cost");
                System.out.println("=======================================================");
                while(inFile.hasNextLine())
                {
                    line = inFile.nextLine();
                    System.out.printf("%s\n",line.replace(",","     " ));
                }

                inFile.close();
            }
            else   // User did not pick a file, closed the chooser
            {
                System.out.println("Sorry, you must select a file! Termininating!");
                System.exit(0);
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File Not Found Error");
            e.printStackTrace();
        }
        catch (IOException e)
        {
            System.out.println("IOException Error");
            e.printStackTrace();
        }
    }
}

