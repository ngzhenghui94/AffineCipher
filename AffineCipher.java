import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class AffineCipher {

    public static int gcd(int a, int b){
        int temp;
        while(b != 0){
            temp = a;
            a = b;
            b = temp%b;
        }
        return a;
    }

    private static String encrypt(char[] plainText){
        String cipherText = "";
        int ptLength = plainText.length;
        for (int i = 0; i < ptLength; i++){
            if (plainText[i] != ' ' && Character.isUpperCase(plainText[i]) == true && Character.isLetter(plainText[i]) == true) {
                cipherText = cipherText + (char)((((keyA * (plainText[i] - 'A')) + keyB) % 26) + 'A');
            }else if(plainText[i] != ' ' && Character.isUpperCase(plainText[i]) == false && Character.isLetter(plainText[i]) == true) {
                cipherText = cipherText + (char) ((((keyA * (plainText[i] - 'a')) + keyB) % 26) + 'a');
            }else{
                cipherText += plainText[i];
            }
        }
        return cipherText;
    }

    // Affine Cipher Decryption
    // Modulo inverse
    // keyA pow inverse 1 (cipherText - keyB) = plainText
    private static String decrypt(char[] cipherText){
        String plainText = "";
        int ctLength = cipherText.length;
        int x = 0;
        int inverse =0;
//        while(true){
//            inverse = keyA * x % 26;
//            if(inverse == 1)
//                break;
//            x++;
//        }
        for(int i = 0; i < 26; i++){
            x = (keyA * i) % 26;
            if (x == 1){
                inverse = i;
            }
        }
        for(int i = 0; i < ctLength; i++){
            if (cipherText[i] != ' ' && Character.isUpperCase(cipherText[i]) == true && Character.isLetter(cipherText[i]) == true){
                plainText = plainText + (char)(((inverse * ((cipherText[i] + 'A' - keyB)) % 26)) + 'A');
            }else if (cipherText[i] != ' ' && Character.isUpperCase(cipherText[i]) == false && Character.isLetter(cipherText[i]) == true){
                plainText = plainText + Character.toLowerCase((char)(((inverse * ((Character.toUpperCase(cipherText[i]) + 'A' - keyB)) % 26)) + 'A'));
            }else {
                plainText += cipherText[i];
            }
        }
        return plainText;
    }

    private static String readFile(String fileName) throws IOException {
        String data = "";
        data = new String(Files.readAllBytes(Paths.get(fileName)));
        return data;
    }

    private static void writeFile(String filename, String string) throws IOException {
        File file = new File(filename);
        if (file.createNewFile()){
            //System.out.println("File is created!");
        } else {
            //System.out.println("File already exists.");
        }
        FileWriter writer = new FileWriter(file);
        writer.write(string);
        writer.close();
    }

    private static int keyA;
    private static int keyB;
    public static void main(String[] args) throws IOException {
        keyA = Integer.parseInt(args[0]);
        keyB = Integer.parseInt(args[1]);
        String mode = args[2];
        if(gcd(keyA,26)!=1){
            throw new ArithmeticException("Key A is invalid");
        }
        if(!(keyA >= 1 && keyA <= 25)){
            throw new ArithmeticException("Key A is invalid");
        }
        if(!(keyB >= 0 && keyB <= 25)){
            throw new ArithmeticException("Key B is invalid");
        }
        if (mode.equals("-e") || mode.equals("-encrypt")){
            String inputFileName = args[3];
            String outputFileName = args[4];
            String data = readFile(inputFileName);
            System.out.println("PlainText: " + data);
            String cipherText = encrypt(data.toCharArray());
            System.out.println("Encrypted Msg: " + cipherText);
            writeFile(outputFileName, cipherText);
            System.out.println("Output'd Encrypted Msg to: " + outputFileName);
        }else if (mode.equals("-d") || mode.equals("-decrypt")){
            String inputFileName = args[3];
            String outputFileName = args[4];
            String data = readFile(inputFileName);
            System.out.println("CipherText: " + data);
            String plainText = decrypt(data.toCharArray());
            System.out.println("Decrypted Msg: " + plainText);
            writeFile(outputFileName, plainText);
            System.out.println("Output'd Decrypted Msg to: " + outputFileName);
        }else{
            System.out.println("No valid mode selected. Please enter the correct mode: -e, -encrypt, -d or -decrypt");
            System.exit(0);
        }
    }
}
