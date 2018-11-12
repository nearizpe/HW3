package planet.detail;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class WriteObject {
    public void savePlanet(Planet planet){
    	String fileName = planet.getName() + ".txt";
    	try {
			ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName));
			outputStream.writeObject(planet);
			outputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
