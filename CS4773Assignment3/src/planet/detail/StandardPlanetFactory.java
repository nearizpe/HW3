package planet.detail;

public class StandardPlanetFactory implements PlanetFactory {

    //Creates by creating or loading. Planet factory should create not worry about saving

    PlanetValidator planetValidator;
    LoadObject objectLoader;
    public StandardPlanetFactory(){
        planetValidator = new PlanetValidator();
        objectLoader = new LoadObject();
    }

    @Override
    public Planet getPlanet(String name, String diametterKm, String tempc, String numOfMoons , String imagePath) {
        try {
            Planet planet = new Planet(name,Double.parseDouble(diametterKm),Double.parseDouble(tempc),Integer.parseInt(numOfMoons),imagePath);
            if(planetValidator.isValidPlanet(planet)){
                return planet;
            }
        }catch (Exception e){
            return null;
        }
        return null;
    }

    public Planet getPlanet(String filePath) {
        Planet planet = objectLoader.getPlanet(filePath);
        return planet;
    }
}
