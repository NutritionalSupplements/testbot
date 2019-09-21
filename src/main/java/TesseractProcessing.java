import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.awt.image.BufferedImage;
import java.io.File;

public class TesseractProcessing {
    static Tesseract tesseract;
   static {

         tesseract = new Tesseract();
        tesseract.setDatapath("C:/Users/Saschen'ka/Desktop/testsTesseract/tessdata");
        tesseract.setLanguage("rus");

    }

    protected String doOcr (BufferedImage image){
        String text = null;
        try {
            text = tesseract.doOCR(image);
        } catch (TesseractException e) {
            e.printStackTrace();
        }
        return text;
    }
}
