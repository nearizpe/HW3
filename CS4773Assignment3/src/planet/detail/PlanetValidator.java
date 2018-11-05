package planet.detail;

public class PlanetValidator {

    public boolean isValidPlanet(Planet planet){
        if(planet.getName().length()>255 || planet.getName().length() < 1 ){
            return false;
        }
        if(!planet.getName().matches("^.*[^a-zA-Z0-9 -.].*$")){
            return false;
        }
        if(planet.getDiameterKM()<0 || planet.getDiameterKM()>200000 ){
            return false;
        }
        if(planet.getTempC()<  -273.15 || planet.getTempC()>500){
            return false;
        }
        if(planet.getNumberOfMoons()<0 | planet.getNumberOfMoons()>1000){
            return false;
        }


        return true;
    }
}
