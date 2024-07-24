package namskram;


import java.io.IOException;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import static namskram.Extracter.ExtractTextAndNumbers;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void testTestImage() throws IOException {
        String filename = "C:/Users/Brandon Du/OneDrive/Pictures/test-image.png";
        ExtractTextAndNumbers(filename);
    }
}
