import java.util.Scanner;

public class JavaVersionTest {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter your age");
		int age = sc.nextInt();
		if (age >= 18) {
			System.out.print("You are an eligible voter as your age is " + age);
		} else {
			System.out.println("You can not vote minor!");
		}
	}
}
