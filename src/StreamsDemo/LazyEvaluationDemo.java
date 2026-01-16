package StreamsDemo;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LazyEvaluationDemo {

	public static void main(String[] args) {
		List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "Paul");

		Stream<String> namesStream = names.stream().filter(name -> {
			System.out.println("Filtering " + name);
			return name.length() > 3;
		});

		System.out.println("Before terminal op ");

		List<String> result = namesStream.collect(Collectors.toList());

		System.out.println("After terminal op");
		System.out.println(result);
	}

}
