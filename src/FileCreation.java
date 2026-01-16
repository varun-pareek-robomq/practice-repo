import java.io.FileOutputStream;
import java.io.IOException;

public class FileCreation {

	public static void main(String[] args) throws IOException {
		FileOutputStream fos = new FileOutputStream("output.dat");
		fos.write(65); // writes byte value 65 (character 'A')
		fos.close();

	}

}
