package planet.detail;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class PlanetController {
    String imagePath = null;

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
    private Label fancyPlanetName; /*"Any change to planet name should also change the fancy planet name label at
the top" Not sure if he means when saving or real time. If real time we would have to have a listener for evrytime the name is change and change the label too

Planet diameter (km) should default to an invalid diameter and the diameter text
field should default to an empty string
⁃ Planet diameter (km) should update Planet diameter (mi) any time it changes other stuff like this? Maybe he wants more than just the 3 funcs

*/

    @FXML
    void selectImage(ActionEvent event) {
        //Ran into the problem where once we load the image we need to save the file since thats what will be saved when they save a planet
        //so i made a filed for it. since this is the controller i guess its ok?
        ImageLoader imageLoader = new ImageLoader();
        imagePath = imageLoader.getImagePath();
        planetImage.setImage(imageLoader.getImage(imagePath));

    }

    @FXML
    void loadPlanet(ActionEvent event) {
        PlanetFactory planetFactory = new StandardPlanetFactory();
        //we should load up the file path for the user here and get the path to pass into the factory. Maybe make a file path opener? Since we keep doing that
        Planet planet = planetFactory.getPlanet("");
        if(planet == null){
            return;
        }
        ImageLoader imageLoader = new ImageLoader();
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
        //if imagepath is null it should i guess be passed the location of the no image?
        Planet planet = planetFactory.getPlanet(planetName.getText(),planetDiameterKM.getText(),planetMeanSurfaceTempC.getText(),planetNumberOfMoons.getText(),imagePath);
        if(planet == null){
            //Alert user invalid planet
            return;
        }
        WriteObject writeObject = new WriteObject();
        writeObject.savePlanet(planet);
    }
}