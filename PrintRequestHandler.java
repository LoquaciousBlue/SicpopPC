import java.io.File;
import java.util.ArrayList;
//import java.awt.*;
import java.awt.Frame;
import java.awt.FileDialog;


public class PrintRequestHandler {
    private Frame dialogFrame;
    private FileDialog fileBrowser;
    private ArrayList<File> listOfFiles;


    public PrintRequestHandler(){
        dialogFrame = new Frame();
        fileBrowser = new FileDialog(dialogFrame);
        listOfFiles = new ArrayList<File>();
    }

    public void askForFilesToPrint(){
        fileBrowser.setVisible(true);
        File[] tmpFileList = fileBrowser.getFiles();
        for (File f : tmpFileList) listOfFiles.add(f);
        fileBrowser.setVisible(false);
    }

    public ArrayList<File> getFiles(){
        return listOfFiles;
    } 
}
