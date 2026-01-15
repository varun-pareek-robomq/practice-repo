import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ListOfStringSorting {

	public static void main(String[] args) {

		Comparator<String> customComp = new Comparator<String>() {
			@Override
			public int compare(String a, String b) {
				if (a.length() > b.length()) {
					return 1;
				} else {
					return -1;
				}
			}
		};

		List<String> list = new ArrayList<>();

		list.add("Hello");
		list.add("World!");
		list.add("Automatic");
		list.add("No");
		list.add("MeraAadharMeriPehchaan");

		Collections.sort(list, customComp);
		System.out.println(list);

	}

}
