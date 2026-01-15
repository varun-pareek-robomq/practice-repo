import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class IOPractice {

	public static void main(String[] args) {

		try (BufferedReader br = new BufferedReader(new FileReader("words.txt"))) {
			String data;
			HashMap<String, Integer> map = new HashMap<>();

			while ((data = br.readLine()) != null) {
				map.put(data, map.getOrDefault(data, 0) + 1);
			}

			System.out.println(map);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

//	private static Map<String, Integer> countOfEachWords(String data) {
//		
//		return map;
//	}

//	private static void countNumberOfWords(char c) {
//
//	}

}
