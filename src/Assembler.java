import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;

/**
 * Created by Lucas on 5/29/2016.
 */
public class Assembler {
    List program = new ArrayList<String>();
    String filePath;
    String fileName;


    public Assembler (String inFile) {
        filePath = inFile;
        fileName = filePath.substring(0, filePath.lastIndexOf('.'));
        readFile();
        Parser parser = new Parser(program);
        parser.writeBinary(fileName);
    }

    private void readFile() {
        Path path = Paths.get(filePath);
        try (InputStream in = Files.newInputStream(path);
             BufferedReader reader =
                     new BufferedReader(new InputStreamReader(in))) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                String noWhiteSpace = line.replaceAll("(?m)^[ \t]*\r?\n", "").trim();
                if (!noWhiteSpace.startsWith("//") && !noWhiteSpace.isEmpty()) {
                    int pos = noWhiteSpace.indexOf("//");
                    if (pos > 0) {
                        noWhiteSpace = noWhiteSpace.substring(0, pos);
                        noWhiteSpace = noWhiteSpace.trim();
                    }

                    program.add(noWhiteSpace);
                }
            }

            reader.close();
        } catch (IOException x) {
            System.err.println(x);
            System.out.println("Error reading file...");
            System.out.println("Closing program.");
            System.exit(0);
        }
    }

    public static void main(String [] args)
    {
        if(args.length!=0) {
            for (String file : args) {
                System.out.println("Translating " + file);
                Assembler program = new Assembler(file);
            }
            System.exit(0);
        } else {
            Assembler program = new Assembler("C:\\Users\\Lucas\\workspace\\HackAssembler\\out\\artifacts\\HackAssembler_jar\\problem1.asm");

            System.out.println("No argument given...");
            System.out.println("Closing program.");
            System.exit(0);
        }
    }

}
