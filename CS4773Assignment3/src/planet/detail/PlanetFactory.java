package planet.detail;

public interface PlanetFactory {
    Planet getPlanet(String name, String diametterKm, String tempc, String numOfMoons, String imagePath);
    public Planet getPlanet(String filePath);
}
