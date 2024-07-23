package namskram;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class Extracter {

    public static void ExtractTextAndNumbers(String filePath) throws IOException {
        File imageFile = new File(filePath);

        int newWidth = 600;
        int newHeight = 500;
        
        BufferedImage originalImage = ImageIO.read(imageFile);

        // Create a new BufferedImage for resized image
        BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = resizedImage.createGraphics();

        // Draw the original image onto the resized image
        g2d.drawImage(originalImage, 0, 0, newWidth, newHeight, null);
        g2d.dispose();

        int pos = filePath.length() - 4;
        String newFile = filePath.substring(0, pos) + "2" 
                         + filePath.substring(pos);

        // Write the resized image to a file
        File newImage = new File(newFile);
        ImageIO.write(resizedImage, "png", newImage);

        ITesseract tesseract = new Tesseract();
        try {
            String text = tesseract.doOCR(newImage);

            // FOR DEBUGGING
            // System.out.println("Original:");
            // System.out.println(text);
            
            String[] keywords = {"ATK", "DEF", "CRIT DMG", "Break Effect", "CRIT Rate"};
            String filteredText = includeStringsWithPercentage(text, keywords);

            System.out.println("Overall Stats:");
            System.out.println(filteredText);
            System.out.println();

        } catch (TesseractException e) {
            System.err.println("Error during OCR: " + e.getMessage());
        }
    }

    public static String includeStringsWithPercentage(String text, String[] includeStrings) {
        StringBuilder filteredText = new StringBuilder();
        // Normalize line endings and remove extra spaces/newlines
        text = text.replaceAll("\\r\\n|\\r|\\n", " ").replaceAll("\\s{2,}", " ").trim();
        
        for (String includeString : includeStrings) {
            // Escape special characters in the keyword for regex
            String escapedIncludeString = Pattern.quote(includeString);
            // Regex pattern to match the keyword, followed by optional non-word characters, then the number (with or without percentage)
            Pattern pattern = Pattern.compile(escapedIncludeString + "\\s*[^\\d]*\\d+\\.?\\d*%?");
            Matcher matcher = pattern.matcher(text);
            while (matcher.find()) {
                String match = matcher.group();
                // Extract the percentage or number part
                Pattern numberPattern = Pattern.compile("(\\d+\\.?\\d*%?)");
                Matcher numberMatcher = numberPattern.matcher(match);
                if (numberMatcher.find()) {
                    String cleanMatch = numberMatcher.group();
                    filteredText.append(includeString).append(" ").append(cleanMatch).append("\n");
                }
            }
        }
        return filteredText.toString().trim();
    }

}