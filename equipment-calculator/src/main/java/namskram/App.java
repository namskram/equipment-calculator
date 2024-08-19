package namskram;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

public class App 
{
    public static void main(String[] args) throws IOException, ClassNotFoundException, InstantiationException, 
                                                  IllegalAccessException, NoSuchMethodException, 
                                                  IllegalArgumentException, InvocationTargetException {

        try (Scanner scnr = new Scanner(System.in)) {
            while (true) {
                System.out.print("Enter character name: ");
                String charName = scnr.nextLine();
                if (charName.equals("q")) {
                    break;
                }

                if (charName.equals("b")) {
                    continue;
                }

                System.out.print("Enter filename: ");
                String fileName = scnr.nextLine();
                if (fileName.equals("q")) {
                    break;
                }

                if (fileName.equals("b")) {
                    continue;
                }
                
                String filePath = "C:/Users/Brandon Du/OneDrive/Pictures/" + fileName + ".png";
                System.out.println("Analyzing " + fileName + " for " + charName);
                Extracter.ExtractTextAndNumbers(charName, filePath);
                System.out.println("Enter \"q\" to quit");
            }
        }
    }
}
