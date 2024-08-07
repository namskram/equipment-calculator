package namskram;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.imageio.ImageIO;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.opencv.opencv_java;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class ImagePreprocessing {
    // static { System.loadLibrary(Core.NATIVE_LIBRARY_NAME); }

    public static String modifyImage(String filePath) throws IOException, ClassNotFoundException, InstantiationException, 
                                                  IllegalAccessException, NoSuchMethodException, 
                                                  IllegalArgumentException, InvocationTargetException {
        
        /*
        File imageFile = new File(filePath);

        int newWidth = 600;
        int newHeight = 500;
        
        // Resize the image so Tesseract can read it more accurately
        BufferedImage originalImage = ImageIO.read(imageFile);

        // Create a new BufferedImage for resized image
        //BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
        //Graphics2D g2d = resizedImage.createGraphics();


        // Create a black-and-white image of the same size.
        BufferedImage im = new BufferedImage(originalImage.getWidth(), originalImage.getHeight(), BufferedImage.TYPE_BYTE_BINARY);
        // Get the graphics context for the black-and-white image.
        Graphics2D g2d = im.createGraphics();
        // Render the input image on it.
        g2d.drawImage(originalImage, 0, 0, null);
        // Store the resulting image using the PNG format.
        //ImageIO.write(im, "PNG", new File("C:/Users/Brandon Du/OneDrive/Pictures/bw.png"));


        // Draw the original image onto the resized image
        //g2d.drawImage(originalImage, 0, 0, newWidth, newHeight, null);
        g2d.dispose();

        int pos = filePath.length() - 4;
        String newFile = filePath.substring(0, pos) + "2" 
                         + filePath.substring(pos);

        // Write the resized image to a file
        //File newImage = new File(newFile);
        File newImage = new File("C:/Users/Brandon Du/OneDrive/Pictures/bw.png");
        //ImageIO.write(resizedImage, "png", newImage);
        ImageIO.write(im, "png", newImage);
        return newFile;
        */

        Loader.load(opencv_java.class);
        int pos = filePath.length() - 4;
        String newFile = filePath.substring(0, pos) + "BW" 
                         + filePath.substring(pos);

        // Load the image
        Mat image = Imgcodecs.imread(filePath, Imgcodecs.IMREAD_GRAYSCALE);
        
        if (image.empty()) {
            System.out.println("Could not open or find the image!");
            return filePath;
        }

        // Apply a median blur to remove small dots
        Mat denoisedImage = new Mat();
        Imgproc.medianBlur(image, denoisedImage, 3);

        // Save the processed image
        Imgcodecs.imwrite(newFile, denoisedImage);

        System.out.println("Image processing completed. Output saved at: " + newFile);


        return newFile;
        
    }

    private BufferedImage changeColor(BufferedImage image, int srcColor, int replaceColor) {
        BufferedImage destImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = destImage.createGraphics();
        g.drawImage(image, null, 0, 0);
        g.dispose();
    
    
        for (int width = 0; width < image.getWidth(); width++)
        {
            for (int height = 0; height < image.getHeight(); height++)
            {
    
            if (destImage.getRGB(width, height) == srcColor)
                {
                destImage.setRGB(width, height, replaceColor);
                }
    
            }
        }
    
        return destImage;
    }
}
