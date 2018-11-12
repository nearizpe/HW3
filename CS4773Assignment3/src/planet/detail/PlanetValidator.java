package planet.detail;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class PlanetValidator {
	Alert alert = new Alert(AlertType.ERROR);
	
    public boolean isValidPlanet(Planet planet){
    	alert.setTitle("Invalid!");
    	alert.setHeaderText("Oof!");
        if(planet.getName().length() > 255 || planet.getName().length() < 1){
        	alert.setContentText("Invalid Name Length!");
        	alert.showAndWait();
            return false;
        }
        if(planet.getName().matches("^.*[^a-zA-Z0-9 -.].*$")){
        	alert.setContentText("Invalid Characters Used!");
        	alert.showAndWait();
            return false;
        }
        if(planet.getDiameterKM() < 0 || planet.getDiameterKM() > 200000 ){
        	alert.setContentText("Invalid Planet Size!");
        	alert.showAndWait();
            return false;
        }
        if(planet.getTempC() < -273.15 || planet.getTempC() > 500){
        	alert.setContentText("Invalid temperature!");
        	alert.showAndWait();
            return false;
        }
        if(planet.getNumberOfMoons() < 0 || planet.getNumberOfMoons() > 1000){
        	alert.setContentText("Invalid Number of Moons!");
        	alert.showAndWait();
            return false;
        }


        return true;
    }
}
