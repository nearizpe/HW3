package planet.detail;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class LoadObject {
	private FileChooser fileChooser = new FileChooser();
	Stage fileChooserStage = new Stage();
	private File planetFile;

	public Planet getPlanet(){
		fileChooser.setTitle("Choose A Planet");
		fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("TXT Files", "*.txt"));
		planetFile = fileChooser.showOpenDialog(fileChooserStage);
		
		if (planetFile == null) {
			return null;	
		}

		try {
			ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(planetFile));
			Planet planet = (Planet) inputStream.readObject();
			inputStream.close();
			return planet;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
}
