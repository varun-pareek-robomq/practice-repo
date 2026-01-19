package StreamsDemo;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class PracticeStreams {

	public static void main(String[] args) {
		// 1. Collecting Strings by length
		List<String> names = Arrays.asList("Hello", "World", "to", "your", "World", "from", "my", "Macbook Air M4");
		System.out.println(names.stream().collect(Collectors.groupingBy(String::length)));

		// 2. Counting Word occurrences
		String sentence = "hello world hello java world this is varun , this is pareek";
//		System.out.println(
//				Arrays.stream(sentence.split(" ")).collect(Collectors.groupingBy(x -> x, Collectors.counting())));

		// 3. Partitioning even and odd numbers
		List<Integer> l2 = Arrays.asList(1, 4, 6, 32, 3, 643, 12, 36, 76, 89);
//		System.out.println(l2.stream().collect(Collectors.partitioningBy(x -> x % 2 == 0)));

		// 4. Summing values to a map
		HashMap<String, Integer> fruits = new HashMap<>();
		fruits.put("Apple", 2);
		fruits.put("Banana", 3);
		fruits.put("Orange", 12);

		// Without streams
//		System.out.print("Total items in the fruits map without streams: ");
		int sum = 0;
		for (int f : fruits.values()) {
			sum += f;
		}
//		System.out.println(sum);

		// With streams
//		System.out.print("Total items in the fruits map with streams: ");
//		System.out.println(fruits.values().stream().reduce(Integer::sum).get());

		// Find duplicate numbers

		List<Integer> l3 = Arrays.asList(3, 4, 6, 32, 3, 643, 12, 36, 76, 89, 76, 642, 4, 4, 4, 643);

		// Distinct numbers, not duplicate numbers
//		System.out.println(l3.stream().distinct().collect(Collectors.toList()));

//		System.out.println(l3.stream().filter(e -> Collections.frequency(l3, e) > 1).collect(Collectors.toList()));

		// Max and min of a list
		System.out.println("Maximum element present in the list " + l3.stream().max((x1, x2) -> x1 - x2).get());

		System.out.println("Minimum element present in the list " + l3.stream().min((x1, x2) -> x1 - x2).get());

		System.out.println("Second max element in the list "
				+ l3.stream().distinct().sorted(Collections.reverseOrder()).skip(1).findFirst().get());

		System.out
				.println("Second min element in the list " + l3.stream().distinct().sorted().skip(1).findFirst().get());
	}

}
