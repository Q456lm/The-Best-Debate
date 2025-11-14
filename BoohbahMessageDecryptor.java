import java.io.*;

/**
 * Boohbah Message Decryptor
 * 
 * STORY: The Boohbah have intercepted a secret message from an unknown enemy.
 * Intelligence suggests it's encrypted with a Caesar cipher (shift unknown).
 * Your mission: Read the encrypted message, decrypt it, and write the 
 * decrypted message to a file. Then determine who is attacking!
 */
public class BoohbahMessageDecryptor {
    
    /**
     * Decrypts a Caesar cipher encrypted message by shifting each letter back by the given shift amount.
     * Non-letter characters remain unchanged.
     * 
     * @param encrypted the encrypted message
     * @param shift the number of positions to shift back
     * @return the decrypted message
     */
    public static String decrypt(String encrypted, int shift) {
        StringBuilder decrypted = new StringBuilder();
        
        for (char c : encrypted.toCharArray()) {
            if (Character.isLetter(c)) {
                if (Character.isUpperCase(c)) {
                    // Shift uppercase letters back by the given amount
                    char shifted = (char) (c - shift);
                    if (shifted < 'A') {
                        shifted = (char) (shifted + 26); // Wrap around
                    }
                    decrypted.append(shifted);
                } else {
                    // Shift lowercase letters back by the given amount
                    char shifted = (char) (c - shift);
                    if (shifted < 'a') {
                        shifted = (char) (shifted + 26); // Wrap around
                    }
                    decrypted.append(shifted);
                }
            } else {
                // Keep non-letter characters as-is
                decrypted.append(c);
            }
        }
        
        return decrypted.toString();
    }
    
    public static void main(String[] args) {
        //Declares variables for file paths
        String inputFile = "encrypted_message.txt";
        String outputFile = "decrypted_message.txt";
        
        //Try Block for error checking + defines file
        try (BufferedReader file = new BufferedReader (new FileReader(inputFile))) {
        
            //Reads the encrypted message from the file (one line)
            String encryptedMessage = file.readLine();
            
            //Goes through values 0-25, and runs encrypted message with that value, then prints the result.
            for (int i = 0; i <= 25; i++){
                System.out.println(decrypt(encryptedMessage,i));
            }
            
            //Identifies the correct shift value, and uses it to decrypt the message
            int correctShift = 3;
            String decryptedMessage = decrypt(encryptedMessage, correctShift);
            
            FileWriter writer = new FileWriter(outputFile);
            writer.write(decryptedMessage);
            writer.close();
            
            //Displays results to console
            System.out.println("\n\n\n");
            System.out.println("=== BOOHBAH INTELLIGENCE REPORT ===");
            System.out.println("Encrypted message: "+encryptedMessage);
            System.out.println("Decrypted message: "+decryptedMessage);
            System.out.println("Decrypted message saved to: "+outputFile);
            System.out.println("STATUS: THREAT IDENTIFIED!");

            
        }catch (FileNotFoundException e){
            System.out.println("Unable to find file: "+inputFile);
        } catch (IOException e) {
            System.out.println("Something is wrong with the files");
        }
    }
}
