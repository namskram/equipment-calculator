package namskram;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

import namskram.classes.Amplifier;
import namskram.classes.CharacterType;
import namskram.classes.DamageDealer;
import namskram.classes.Specialist;
import namskram.classes.Sustain;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class Extracter {

    public static void ExtractTextAndNumbers(String filePath) throws IOException {
        File imageFile = new File(filePath);

        int newWidth = 600;
        int newHeight = 500;
        
        // Resize the image so Tesseract can read it more accurately
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
            
            String[] keywords = {"ATK", "DEF", "HP", "CRIT DMG", "CRIT Rate", "Break Effect", 
                                "Effect Hit Rate", "Effect RES", "SPD"};
            String filteredText = includeStringsWithPercentage(text, keywords);

            System.out.println("Overall Stats:");
            System.out.println(filteredText);
            System.out.println();

            Map<String, Double> vals = filteredTextToDictionary(filteredText);
            // Ignore main stats, assuming BIS already
            Map<String, Double> newVals = removeFirstPair(vals);
            System.out.println("test: " + newVals);

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

    public static String classChecker(String charName) {
        CharacterType tempDD = new DamageDealer(charName);
        if (tempDD.getCharacters().contains(charName)) {
            return "DamageDealer";
        }
        
        CharacterType tempSpec = new Specialist(charName);
        if (tempSpec.getCharacters().contains(charName)) {
            return "Specialist";
        }

        CharacterType tempAmp = new Amplifier(charName);
        if (tempAmp.getCharacters().contains(charName)) {
            return "Amplifier";
        }

        CharacterType tempSus = new Sustain(charName);
        if (tempSus.getCharacters().contains(charName)) {
            return "Sustain";
        }
        return "No such character";
    }

    // Method to convert filtered text to a dictionary
    public static Map<String, Double> filteredTextToDictionary(String filteredText) {
        Map<String, Double> resultMap = new LinkedHashMap<>(); // Using LinkedHashMap to maintain order
        String[] lines = filteredText.split("\\n");
        
        for (String line : lines) {
            System.out.println("Processing line: " + line); // Debug print
            int lastSpaceIndex = line.lastIndexOf(' ');
            if (lastSpaceIndex == -1) {
                System.err.println("No space found in line: " + line); // Debug print
                continue;
            }
            String key = line.substring(0, lastSpaceIndex).trim();
            String valueStr = line.substring(lastSpaceIndex + 1).trim();
            boolean isPercentage = valueStr.endsWith("%");
            if (isPercentage) {
                valueStr = valueStr.substring(0, valueStr.length() - 1); // Remove percentage sign
                key += "%"; // Append percentage sign to key
            }
            try {
                double value = Double.parseDouble(valueStr);
                resultMap.put(key, value);
            } catch (NumberFormatException e) {
                System.err.println("Invalid number format for: " + valueStr);
            }
        }
        
        return resultMap;
    }

    // Method to create a new dictionary without the first pair
    public static Map<String, Double> removeFirstPair(Map<String, Double> originalMap) {
        Map<String, Double> newMap = new LinkedHashMap<>(); // Using LinkedHashMap to maintain order
        boolean firstPair = true;
        for (Map.Entry<String, Double> entry : originalMap.entrySet()) {
            if (firstPair) {
                firstPair = false; // Skip the first entry
                continue;
            }
            newMap.put(entry.getKey(), entry.getValue());
        }
        return newMap;
    }

}