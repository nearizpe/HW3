package planet.detail;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.text.DecimalFormat;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class PlanetController {
    String imagePath = "images/no_image.png";
    private PlanetFactory planetFactory;
    private Planet planet;
    private ImageLoader imageLoader = new ImageLoader();
    private Image image;
    
    @FXML
    private ImageView planetImage;

    @FXML
    private Button selectImageButton;

    @FXML
    private TextField planetName;

    @FXML
    private TextField planetDiameterKM;

    @FXML
    private TextField planetDiameterM;

    @FXML
    private TextField planetMeanSurfaceTempC;

    @FXML
    private TextField planetMeanSurfaceTempF;

    @FXML
    private TextField planetNumberOfMoons;

    @FXML
    private Label fancyPlanetName;
    
    @FXML
    void UpdateFancy(KeyEvent event) {
    	fancyPlanetName.setText(planetName.getText());
    }
    
    @FXML
    void DiameterChange(KeyEvent event) {
    	DecimalFormat decimalFormat = new DecimalFormat("#,###");  
    	String kmString = planetDiameterKM.getText();
    	kmString = kmString.replaceAll(",", "");
    	Double kmValue = Double.parseDouble(kmString);
    	Double mConvert = kmValue * 1000; 
    	String numberAsString = decimalFormat.format(mConvert);
    	planetDiameterM.setText(numberAsString);
    	try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	numberAsString = decimalFormat.format(kmValue);
    	planetDiameterKM.setText(numberAsString);
    }
    
    @FXML
    void TempChange(KeyEvent event) {
    	String cString = planetMeanSurfaceTempC.getText();
    	Double cValue = Double.parseDouble(cString);
    	Double fConvert = (cValue * 9/5) + 32;
    	planetMeanSurfaceTempF.setText(""+ fConvert);

    }
    
    @FXML
    void moonChanged(KeyEvent event) {
    	DecimalFormat decimalFormat = new DecimalFormat("#,###");  
    	String moonString = planetNumberOfMoons.getText();
    	moonString = moonString.replaceAll(",", "");
    	int moonValue = Integer.parseInt(moonString);
//    	try {
//			TimeUnit.SECONDS.sleep(1);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
    	String numberAsString = decimalFormat.format(moonValue);
    	planetNumberOfMoons.setText(numberAsString);
    	
    }

    @FXML
    void selectImage(ActionEvent event) {
        imagePath = imageLoader.getImagePath();
        planetImage.setImage(imageLoader.getImage(imagePath));

    }

    @FXML
    void loadPlanet(ActionEvent event) {
    	if(displayLoadMessage() == false) {
    		return;
    	}
        PlanetFactory planetFactory = new StandardPlanetFactory();
        Planet planet = planetFactory.getPlanet("");
        if(planet == null){
            return;
        }
        
        planetImage.setImage(imageLoader.getImage(planet.getImagePath()));
        planetName.setText(planet.getName());
        planetDiameterKM.setText(""+planet.getDiameterKM());
        planetDiameterM.setText(""+planet.getDiametersInMeters());
        planetMeanSurfaceTempC.setText(""+planet.getTempC());
        planetMeanSurfaceTempF.setText(""+planet.getTempInF());
        planetNumberOfMoons.setText(""+ planet.getNumberOfMoons());
        fancyPlanetName.setText(planet.getName());
    }
    
    @FXML
    void savePlanet(ActionEvent event) {
        PlanetFactory planetFactory = new StandardPlanetFactory();
        Planet planet = planetFactory.makePlanet(planetName.getText(),planetDiameterKM.getText(),planetMeanSurfaceTempC.getText(),planetNumberOfMoons.getText(),imagePath);
        if(planet == null){
            return;
        }
        WriteObject writeObject = new WriteObject();
        writeObject.savePlanet(planet);
    }
    
    public void initialize() {
    	FileInputStream inputstream;
		try {
			inputstream = new FileInputStream(imagePath);
			image = new Image(inputstream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    	planetImage.setImage(image);
    	planetDiameterM.setDisable(true);
    	planetDiameterM.setStyle("-fx-opacity: 1;");
    	planetMeanSurfaceTempF.setDisable(true);
    	planetMeanSurfaceTempF.setStyle("-fx-opacity: 1;");
    }
    
    boolean displayLoadMessage() {
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("Whoa there partner");
    	alert.setHeaderText("I reckon you consider this");
    	alert.setContentText("Are you sure you want to replace the field values in the UI");

    	Optional<ButtonType> result = alert.showAndWait();
    	if (result.get() == ButtonType.OK){
    	    return true;
    	} else {
    	    return false;
    	}
    }
    
}