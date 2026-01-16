package StreamsDemo;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamDemo {

	public static void main(String[] args) {
		List<String> list = Arrays.asList("Varun", "Milli", "RoboMQ", "Nothing", "Apple");
		System.out.println(list.stream().filter(x -> x.length() == 5).collect(Collectors.toList()));

		List<Integer> list2 = Arrays.asList(11, 5, 23, 47, 5);

		System.out.println(list2.stream().map(x -> x * x).sorted().collect(Collectors.toList()));

		List<Integer> list3 = Arrays.asList(1, 2, 3, 4, 5);
		System.out.println(list3.stream().reduce(Integer::sum).get());

		String name = "Hello World";
		System.out.println(name.chars().filter(x -> x == 'l').count());

	}

}
