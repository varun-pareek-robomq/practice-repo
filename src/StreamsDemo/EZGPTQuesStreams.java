package StreamsDemo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Employee {
	private final String empName;
	private final String dept;
	private final int empSalary;

	public Employee(String empName, String dept, int empSalary) {
		super();
		this.empName = empName;
		this.dept = dept;
		this.empSalary = empSalary;
	}

	public String getEmpName() {
		return empName;
	}

	public String getDept() {
		return dept;
	}

	public int getEmpSalary() {
		return empSalary;
	}

}

public class EZGPTQuesStreams {

	public static void main(String[] args) {

//		1. Even numbers in a list(distinct)
		List<Integer> l3 = Arrays.asList(3, 4, 6, 32, 3, 643, 12, 36, 76, 89, 76, 642, 4, 4, 643);
		List<String> strsList = Arrays.asList("Hello", "world", "These", "Are", "streams", "These");

		System.out.println(l3.stream().distinct().filter(x -> x % 2 == 0).collect(Collectors.toList()));

//		2.  All string to uppercase
		System.out.println(strsList.stream().map(String::toUpperCase).collect(Collectors.toList()));

//		3.  Count how many numbers are greater than 10
		System.out.println(l3.stream().filter(x -> x > 10).count());

//		4. strings start with A
		System.out.println(strsList.stream().filter(s -> s.startsWith("A")).collect(Collectors.toList()));

//		5. Sum of a list
		System.out.println(l3.stream().reduce(Integer::sum).get());

//		6. Max and min
		System.out.println("Maximum element present in the list " + l3.stream().max(Integer::compareTo).get());

		System.out.println("Minimum element present in the list " + l3.stream().min((x1, x2) -> x1 - x2).get());

//		7. Squares of distinct numbers
		System.out.println(l3.stream().distinct().map(x -> x * x).collect(Collectors.toList()));

//		8. Remove duplicate strings and sort them
		System.out.println(strsList.stream().distinct().sorted().collect(Collectors.toList()));

//		9. Find the average of numbers in a list.
		System.out.println(l3.stream().collect(Collectors.averagingInt(x -> x)));

//		10. Given a list of strings, join them using ,
//		Example: ["Java","Spring","Docker"] → "Java,Spring,Docker"
		System.out.println(strsList.stream().collect(Collectors.joining(",")));

//		11. Count the frequency of each number in a list.
//		👉 Output: Map<Integer, Long>
		System.out.println(l3.stream().collect(Collectors.groupingBy(x -> x, Collectors.counting())));

//		12. Group strings by their length.
//		👉 Output: Map<Integer, List<String>>
		System.out.println(strsList.stream().collect(Collectors.groupingBy(String::length)));
//		13. Partition numbers into even and odd.
//		👉 Output: Map<Boolean, List<Integer>>
		System.out.println(l3.stream().collect(Collectors.partitioningBy(x -> x % 2 == 0)));

//		14. Given a list of words, find the longest word.
		System.out.println(strsList.stream().max(Comparator.comparingInt(String::length)).get());

//		15. Find the second highest number in a list.
		System.out.println(l3.stream().distinct().sorted(Collections.reverseOrder()).skip(1).findFirst().get());

//		16. First non repeated character
		String str = "stress";
		System.out.print("First non repeated character in string " + str + " is ");
		Character firstNonRepeatedCharacterInString = str.chars().mapToObj(c -> (char) c)
				.collect(Collectors.groupingBy(c -> c, LinkedHashMap::new, Collectors.counting())).entrySet().stream()
				.filter(e -> e.getValue() == 1).map(Map.Entry::getKey).findFirst().get();

		System.out.println(firstNonRepeatedCharacterInString);

//		17. Check if any number appears more than twice
//		System.out.println(l3.stream());
		System.out.println(l3.stream().collect(Collectors.groupingBy(x -> x, Collectors.counting())));

//		18. Flatten a 2d list
		List<List<Integer>> twoDList = new ArrayList<>();
		twoDList.add(Arrays.asList(1, 2));
		twoDList.add(Arrays.asList(3, 4));
		twoDList.add(Arrays.asList(5, 6));

		System.out.println("Flatten list: " + twoDList.stream().flatMap(x -> x.stream()).collect(Collectors.toList()));

//		System.out.println(twoDList.stream().flatMap(x -> x));

//		19. Group by department
		List<Employee> employees = new ArrayList<>();
		Employee e1 = new Employee("Varun", "Backend", 15000);
		Employee e2 = new Employee("Karan", "Frontend", 15000);
		Employee e3 = new Employee("Disha", "Backend", 15000);
		employees.add(e3);
		employees.add(e2);
		employees.add(e1);

		System.out.println(employees.stream().collect(Collectors.groupingBy(Employee::getDept,
				Collectors.mapping(Employee::getEmpName, Collectors.toList()))));

		// Highest paid employee by salary

	}

}
