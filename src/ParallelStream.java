import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ParallelStream {

	public static void main(String[] args) {
		List<Integer> list = Stream.iterate(1, x -> x + 1).limit(20000).collect(Collectors.toList());

		Long startTime = System.currentTimeMillis();
		List<Long> factorials = list.stream().map(ParallelStream::factorial).collect(Collectors.toList());
		Long endTime = System.currentTimeMillis();
		System.out.println("Executing Time with sequential stream " + (endTime - startTime) + " ms");

		startTime = System.currentTimeMillis();
		factorials = list.parallelStream().map(ParallelStream::factorial).collect(Collectors.toList());
		endTime = System.currentTimeMillis();
		System.out.println("Executing Time with sequential stream " + (endTime - startTime) + " ms");

	}

	static Long factorial(int x) {
		Long fact = 1l;
		for (int i = 2; i <= x; i++) {
			fact = fact * i;
		}
		return fact;
	}

}
