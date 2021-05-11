import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;

public class PrinterParser {
    
    public static boolean validateFile(File f) throws IOException {
        PDDocument pdfDoc;
        PDPage page;
        PDRectangle dimensions;

        if (!f.getName().matches(".+[.]pdf")) {
            reportError("Error: this program only accepts pdf files of appropriate size");
            return false;
        }

        pdfDoc = PDDocument.load(f);
           
        if (pdfDoc.isEncrypted()){
            reportError("Error: Encrypted files can not be printed at this time.");
            return false;
        } 

        if(pdfDoc.getPages().getCount() != 1){
            reportError("Error: Only one page can be printed at a time.");
            return false;
        }

        page = pdfDoc.getPage(0);
        dimensions = page.getMediaBox();
        
        // Math converts from points to in (72 'points' in an inch).
        if (dimensions.getWidth()/72 > 3){
            reportError("Error: Image wider than 3 inches; it won't fit on an index card.");
            return false;
        }

        if (dimensions.getHeight()/72 > 5){
            reportError("Error: Image taller than 5 inches; it won't fit on an index card.");
            return false;
        }

        // File's good to go at this point
        return true;
    }

    private static void reportError(String errorMessage){
        MessageDialog dialog = new MessageDialog(null, errorMessage);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
        dialog.addNotify();
    }

}
