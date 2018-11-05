package planet.detail;

public class Planet {
    private String name;
    private double diameterKM;
    private double tempC;
    private int numberOfMoons;


    public Planet(String name, double diameterKM, double tempC, int numberOfMoons, String imagePath) {
        this.name = name;
        this.diameterKM = diameterKM;
        this.tempC = tempC;
        this.numberOfMoons = numberOfMoons;
        this.imagePath = imagePath;
    }

    public double getDiametersInMeters(){
        return  diameterKM *1000;
    }

    public double getTempInF(){
        return (tempC * 9/5) + 32 ;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDiameterKM() {
        return diameterKM;
    }

    public void setDiameterKM(double diameterKM) {
        this.diameterKM = diameterKM;
    }

    public double getTempC() {
        return tempC;
    }

    public void setTempC(double tempC) {
        this.tempC = tempC;
    }

    public int getNumberOfMoons() {
        return numberOfMoons;
    }

    public void setNumberOfMoons(int numberOfMoons) {
        this.numberOfMoons = numberOfMoons;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    private String imagePath;

}
