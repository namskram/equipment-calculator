package namskram;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

import static namskram.Extracter.classChecker;


public class App 
{
    @SuppressWarnings("unchecked")
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
                String classType = classChecker(charName);
                String className = "namskram.classes." + classType;
                Class cl = Class.forName(className);
                Constructor con = cl.getConstructor(String.class);
                Object xyz = con.newInstance(charName);

                System.out.print("Enter filename: ");
                String fileName = scnr.nextLine();
                if (fileName.equals("q")) {
                    break;
                }
                
                String filePath = "C:/Users/Brandon Du/OneDrive/Pictures/" + fileName + ".png";
                System.out.println("Analyzing " + fileName + " for " + charName);
                Extracter.ExtractTextAndNumbers(filePath);
                System.out.println("Enter \"q\" to quit");
            }
        }
    }
}
