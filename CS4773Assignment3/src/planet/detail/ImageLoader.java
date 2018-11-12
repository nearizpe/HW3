package planet.detail;


import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class ImageLoader {
	private FileChooser fileChooser = new FileChooser();
	Stage fileChooserStage = new Stage();
	private Image planetImage;
	private File imageFile;
	
    public Image getImage(String path){
    	FileInputStream inputstream;
    	try {
			inputstream = new FileInputStream(path);
			planetImage = new Image(inputstream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    	
        return planetImage;
    }

    public String getImagePath(){
    	fileChooser.setTitle("Choose an Image");
    	//File recordsDir = new File(System.getProperty("user.home"), ".CS4773Assignment3/images");
    	fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
    	//fileChooser.setInitialDirectory(recordsDir);
    	fileChooser.getExtensionFilters().addAll(new ExtensionFilter("PNG Files", "*.png"));
    	imageFile = fileChooser.showOpenDialog(fileChooserStage);
    	if (imageFile != null) {
    		return imageFile.getAbsolutePath();
    	}
        return "images/no_image.png";
    }
    
}
