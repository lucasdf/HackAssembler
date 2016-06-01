import java.io.PrintWriter;
import java.util.*;

/**
 * Created by Lucas on 5/29/2016.
 */
public class Parser {
    SymbolTable table;
    List program = new ArrayList<String>();
    List translatedProgram = new ArrayList<String>();
    String current;
    int nextAddress = 16;

    public Parser(List program) {
        this.program = program;
        table = new SymbolTable();
        buildTable();
        parse ();
    }

    public void writeBinary(String filePath) {
        filePath = filePath + ".hack";
        try {
            PrintWriter writer = new PrintWriter(filePath, "UTF-8");
            Iterator<String> iter = translatedProgram.iterator();

            while (iter.hasNext()) {
                String command = iter.next();
                writer.println(command);
            }
            System.out.println(filePath + " file created.");
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void buildTable () {
        Iterator<String> iter = program.iterator();

        int running = 0;
        while (iter.hasNext()) {
            current = iter.next();
            String type = commandType();

            if ((current.startsWith("("))) {
                String cleanSymbol = current.substring(1, current.length()-1);
                table.addEntry(cleanSymbol, running);
            } else {
                running++;
            }
        }
    }

    public void parse () {
        Iterator<String> iter = program.iterator();

        while (iter.hasNext()) {
            current = iter.next();
            System.out.println("Translating " + current);
            String type = commandType();

            if (type.equals("A_COMMAND") ){
                String currentWithoutA = current.substring(1);

                if (!currentWithoutA.matches("^\\d+$")) {
                    if (table.contains(currentWithoutA)) {
                        int value = table.getAddress(currentWithoutA);
                        current = Integer.toString(value);

                        String translated = symbol();
                        translatedProgram.add(translated);
                        System.out.println(" A_COMMAND_CONTAINS  " + translated);

                    } else {
                        table.addEntry(currentWithoutA, nextAddress);
                        current = Integer.toString(nextAddress);

                        String translated = symbol();
                        translatedProgram.add(translated);
                        System.out.println(" A_COMMAND_NOTCONTAINS " + translated);
                        nextAddress++;
                    }

                } else if (type.equals("A_COMMAND")) {
                    int value = Integer.parseInt(currentWithoutA);
                    current = Integer.toString(value);

                    String translated = symbol();
                    translatedProgram.add(translated);
                    System.out.println(" A_COMMAND " + translated);
                }

            } else if (type.equals("L_COMMAND")) {
                System.out.println(" L_COMMAND ");
            } else if (type.equals("C_COMMAND")) {
                int position = current.indexOf(";");

                String destination = dest();
                String jump = jump();
                String comp = comp();

                String translated = "111" + comp + destination + jump;

                translatedProgram.add(translated);
                System.out.println(" C_COMMAND " + translated);

            }
        }
    }

    public boolean hasMoreCommands () {
        return true;
    }

    public void advance () {}

    public String commandType () {
        if (current.startsWith("@")) {
            return "A_COMMAND";
        } else if (current.startsWith("(")) {
            return "L_COMMAND";
        } else {
            return "C_COMMAND";
        }
    }

    public String symbol() {
        String first = "0";

        int currentInt = Integer.parseInt(current);
        String result = Integer.toBinaryString(currentInt);

        if (result.length() < 15) {
            int quantity = 15 - result.length();
            for(int i = 0; i < quantity; i++) {
                result = "0" + result;
            }
        }
        result = first + result;
        return result;
    }

    public String dest() {
        String destination = "";
        if (current.contains(";")) {
            int position = current.indexOf(";");
            destination = current.substring(0, position);
            return "000";
        } else {
            int position = current.indexOf("=");
            destination = current.substring(0, position);
        }

        return Code.dest(destination);
    }

    public String comp () {
        String computation = "";
        if (current.contains(";")) {
            int position = current.indexOf(";");
            computation = current.substring(0, position);
        } else {
            int position = current.indexOf("=");
            computation = current.substring(position + 1);
        }

        return Code.comp(computation);

    }

    public String jump() {
        String jump = "";
        if (current.contains(";")) {
            int position = current.indexOf(";");
            jump = current.substring(position + 1);
            jump = Code.jump(jump).toString();;
        } else {
            jump = "000";
        }

        return jump;
    }

}
