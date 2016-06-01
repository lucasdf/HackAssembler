import java.util.BitSet;

/**
 * Created by Lucas on 5/29/2016.
 */
public class Code {
    public static String dest(String mne) {
        String dest = "";
        switch (mne) {
            case "null":
                dest = "000";
                break;
            case "M":
                dest = "001";
                break;
            case "D":
                dest = "010";
                break;
            case "MD":
                dest = "011";
                break;
            case "A":
                dest = "100";
                break;
            case "AM":
                dest = "101";
                break;
            case "AD":
                dest = "110";
                break;
            case "AMD":
                dest = "111";
                break;
            default:
                System.out.println(mne);
                throw new IllegalArgumentException();
        }
        return dest;
    }

    public static String comp (String mne) {
        String comp = "";
        switch (mne) {
            case "0":
                comp = "0101010";
                break;
            case "1":
                comp = "0111111";
                break;
            case "-1":
                comp = "0111010";
                break;
            case "D":
                comp = "0001100";
                break;
            case "A":
                comp = "0110000";
                break;
            case "!D":
                comp = "0001101";
                break;
            case "!A":
                comp = "0110001";
                break;
            case "-D":
                comp = "0001111";
                break;
            case "-A":
                comp = "0110011";
                break;
            case "D+1":
                comp = "0011111";
                break;
            case "A+1":
                comp = "0110111";
                break;
            case "D-1":
                comp = "0001110";
                break;
            case "A-1":
                comp = "0110010";
                break;
            case "D+A":
                comp = "0000010";
                break;
            case "D-A":
                comp = "0010011";
                break;
            case "A-D":
                comp = "0000111";
                break;
            case "D&A":
                comp = "0000000";
                break;
            case "D|A":
                comp = "0010101";
                break;

            case "M":
                comp = "1110000";
                break;
            case "!M":
                comp = "1110001";
                break;
            case "-M":
                comp = "1110011";
                break;
            case "M+1":
                comp = "1110111";
                break;
            case "M-1":
                comp = "1110010";
                break;
            case "D+M":
                comp = "1000010";
                break;
            case "D-M":
                comp = "1100011";
                break;
            case "M-D":
                comp = "1000111";
                break;
            case "D&M":
                comp = "1000000";
                break;
            case "D|M":
                comp = "1010101";
                break;
            default:
                throw new IllegalArgumentException();
        }
        return comp;
    }

    public static String jump (String mne) {
        String jump = "";
        switch (mne) {
            case "null":
                jump = "000";
                break;
            case "JGT":
                jump = "001";
                break;
            case "JEQ":
                jump = "010";
                break;
            case "JGE":
                jump = "011";
                break;
            case "JLT":
                jump = "100";
                break;
            case "JNE":
                jump = "101";
                break;
            case "JLE":
                jump = "110";
                break;
            case "JMP":
                jump = "111";
                break;
            default:
                throw new IllegalArgumentException();
        }
        return jump;
    }
}
