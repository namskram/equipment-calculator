package namskram;

import java.io.IOException;
import java.util.Scanner;

/*
import java.io.File;

import namskram.ExtractTextAndNumbers;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
*/

public class App 
{
    public static void main(String[] args) throws IOException {
        try (Scanner scnr = new Scanner(System.in)) {
            while (true) {
                System.out.print("Enter filename: ");
                String fileName = scnr.nextLine();
                if (fileName.equals("q")) {
                    break;
                }
                
                String filePath = "C:/Users/Brandon Du/OneDrive/Pictures/" + fileName + ".png";
                System.out.println("Analyzing " + fileName);
                Extracter.ExtractTextAndNumbers(filePath);
                System.out.println("Enter \"q\" to quit");
            }
        }
        /*
        ITesseract image = new Tesseract();
        
        try {
            String str = image.doOCR(new File("C:\\Users\\Brandon Du\\Downloads\\test-image.png"));
            System.out.println("Data from Image is " + str);
        } catch (TesseractException e) {
            System.out.println("Exception " + e.getMessage());
        }
        */
    }
}
