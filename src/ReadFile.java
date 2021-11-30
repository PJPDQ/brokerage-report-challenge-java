
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;

public class ReadFile {

	public ArrayList<String> read(String args) {
		ArrayList<String> res = new ArrayList<String>();
		try {
			File txtFile = new File(args);
			Scanner scanner = new Scanner(txtFile);
			while (scanner.hasNextLine()) {
				String data = scanner.nextLine();
				res.add(data);
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("The file cannot be found.");
			e.printStackTrace();
		}
		return res;
	}
}
