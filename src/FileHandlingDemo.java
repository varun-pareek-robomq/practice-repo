import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

//
public class FileHandlingDemo {

	public static void main(String[] args) {
		System.out.println("=== JAVA FILE HANDLING COMPREHENSIVE GUIDE ===\n");

		try {
			// 1. BASIC FILE OPERATIONS
//			basicFileOperations();
//
//			// 2. READING FILES - Multiple Ways
//			readingFiles();
//
//			// 3. WRITING FILES - Multiple Ways
//			writingFiles();
//
//			// 4. FILE & DIRECTORY OPERATIONS
			fileDirectoryOperations();
//
//			// 5. BUFFERED I/O (Performance Optimized)
//			bufferedIO();
//
//			// 6. SERIALIZATION (Save Objects)
//			serializationExample();
//
//			// 7. NIO (Modern Approach - Java 7+)
//			nioOperations();
//
//			// 8. PRACTICAL SCENARIOS
//			practicalScenarios();
//
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//
//	// ========== 1. BASIC FILE OPERATIONS ==========
//	static void basicFileOperations() throws IOException {
//		System.out.println("\n--- 1. BASIC FILE OPERATIONS ---");
//
//		File file = new File("test.txt");
//
//		// Create new file
//		if (file.createNewFile()) {
//			System.out.println("File created: " + file.getName());
//		} else {
//			System.out.println("File already exists");
//		}
//
//		// File properties
//		System.out.println("Absolute path: " + file.getAbsolutePath());
//		System.out.println("Can write: " + file.canWrite());
//		System.out.println("Can read: " + file.canRead());
//		System.out.println("File size: " + file.length() + " bytes");
//		System.out.println("Is directory: " + file.isDirectory());
//	}

//
//	// ========== 2. READING FILES ==========
//	static void readingFiles() throws IOException {
//		System.out.println("\n--- 2. READING FILES ---");
//
//		// First, create a sample file
//		Files.write(Paths.get("sample.txt"), "Line 1\nLine 2\nLine 3\nThis is a test file".getBytes());
//
//		// Method 1: FileReader + BufferedReader (Most Common)
//		System.out.println("\nMethod 1: BufferedReader");
//		try (BufferedReader br = new BufferedReader(new FileReader("sample.txt"))) {
//			String line;
//			while ((line = br.readLine()) != null) {
//				System.out.println(line);
//			}
//		}
//
//		// Method 2: Scanner (Easy for parsing)
//		System.out.println("\nMethod 2: Scanner");
//		try (Scanner sc = new Scanner(new File("sample.txt"))) {
//			while (sc.hasNextLine()) {
//				System.out.println(sc.nextLine());
//			}
//		}
//
//		// Method 3: Files.readAllLines (NIO - Modern)
//		System.out.println("\nMethod 3: Files.readAllLines");
//		List<String> lines = Files.readAllLines(Paths.get("sample.txt"));
//		lines.forEach(System.out::println);
//
//	}

//
//	// ========== 3. WRITING FILES ==========
//	static void writingFiles() throws IOException {
//		System.out.println("\n--- 3. WRITING FILES ---");
//
//		// Method 1: FileWriter (Overwrites)
//		System.out.println("Method 1: FileWriter");
//		try (FileWriter fw = new FileWriter("output1.txt")) {
//			fw.write("Hello from FileWriter\n");
//			fw.write("Second line");
//		}
//
//		// Method 2: FileWriter with append mode
//		System.out.println("Method 2: FileWriter (Append)");
//		try (FileWriter fw = new FileWriter("output1.txt", true)) {
//			fw.write("\nAppended line");
//		}
//
//		// Method 3: BufferedWriter (Performance)
//		System.out.println("Method 3: BufferedWriter");
//		try (BufferedWriter bw = new BufferedWriter(new FileWriter("output2.txt"))) {
//			bw.write("Buffered writing is faster");
//			bw.newLine();
//			bw.write("For large files");
//		}
//
//		// Method 4: PrintWriter (Convenient formatting)
//		System.out.println("Method 4: PrintWriter");
//		try (PrintWriter pw = new PrintWriter("output3.txt")) {
//			pw.println("Line with println");
//			pw.printf("Formatted: %s, %d%n", "text", 42);
//		}
//
//		// Method 5: Files.write (NIO - Modern)
//		System.out.println("Method 5: Files.write");
//		List<String> content = Arrays.asList("Line 1", "Line 2", "Line 3");
//		Files.write(Paths.get("output4.txt"), content);
//
//		System.out.println("All files written successfully!");
//	}
//
//	// ========== 4. FILE & DIRECTORY OPERATIONS ==========
	static void fileDirectoryOperations() throws IOException {
		System.out.println("\n--- 4. FILE & DIRECTORY OPERATIONS ---");

		// Create directory
		File dir = new File("testDir");
		if (dir.mkdir()) {
			System.out.println("Directory created: " + dir.getName());
		}

		// Create nested directories
		File nestedDir = new File("parent/child/grandchild");
		nestedDir.mkdirs();
		System.out.println("Nested directories created");

		// List files in current directory
		File currentDir = new File(".");
		String[] files = currentDir.list();
		System.out.println("\nFiles in current directory:");
		if (files != null) {
			for (String file : files) {
				System.out.println("  " + file);
			}
		}
//
		// Copy file
		Files.copy(Paths.get("sample.txt"), Paths.get("sample_copy.txt"), StandardCopyOption.REPLACE_EXISTING);
		System.out.println("\nFile copied to sample_copy.txt");

		// Move/Rename file
		Files.move(Paths.get("sample_copy.txt"), Paths.get("sample_renamed.txt"), StandardCopyOption.REPLACE_EXISTING);
		System.out.println("File renamed to sample_renamed.txt");

		// Delete file
		Files.deleteIfExists(Paths.get("sample_renamed.txt"));
		System.out.println("File deleted");
	}
//
//	// ========== 5. BUFFERED I/O ==========
//	static void bufferedIO() throws IOException {
//		System.out.println("\n--- 5. BUFFERED I/O (Performance) ---");
//
//		// Writing large file with buffering
//		long start = System.currentTimeMillis();
//		try (BufferedWriter bw = new BufferedWriter(new FileWriter("large_file.txt"))) {
//			for (int i = 0; i < 10000; i++) {
//				bw.write("Line " + i + "\n");
//			}
//		}
//		long end = System.currentTimeMillis();
//		System.out.println("Written 10,000 lines in " + (end - start) + "ms");
//
//		// Reading with buffer
//		start = System.currentTimeMillis();
//		int lineCount = 0;
//		try (BufferedReader br = new BufferedReader(new FileReader("large_file.txt"))) {
//			while (br.readLine() != null) {
//				lineCount++;
//			}
//		}
//		end = System.currentTimeMillis();
//		System.out.println("Read " + lineCount + " lines in " + (end - start) + "ms");
//	}
//
//	// ========== 6. SERIALIZATION ==========
//	static void serializationExample() throws IOException, ClassNotFoundException {
//		System.out.println("\n--- 6. SERIALIZATION (Save Objects) ---");
//
//		// Object to serialize
//		Student student = new Student("Alice", 20, 95.5);
//
//		// Serialize (Write object)
//		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("student.ser"))) {
//			oos.writeObject(student);
//			System.out.println("Object serialized: " + student);
//		}
//
//		// Deserialize (Read object)
//		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("student.ser"))) {
//			Student loaded = (Student) ois.readObject();
//			System.out.println("Object deserialized: " + loaded);
//		}
//	}
//
//	// ========== 7. NIO OPERATIONS (Modern) ==========
//	static void nioOperations() throws IOException {
//		System.out.println("\n--- 7. NIO OPERATIONS (Java 7+) ---");
//
//		Path path = Paths.get("nio_test.txt");
//
//		// Write with NIO
//		Files.writeString(path, "NIO is the modern way\n");
//		Files.write(path, "Second line\n".getBytes(), StandardOpenOption.APPEND);
//
//		// Read with NIO
//		String content = Files.readString(path);
//		System.out.println("Content: " + content);
//
//		// Stream lines (Memory efficient for large files)
//		System.out.println("Streaming lines:");
//		try (var lines = Files.lines(path)) {
//			lines.forEach(line -> System.out.println("  " + line));
//		}
//
//		// Walk directory tree
//		System.out.println("\nDirectory tree:");
//		try (var stream = Files.walk(Paths.get("."), 2)) {
//			stream.filter(Files::isRegularFile).filter(p -> p.toString().endsWith(".txt"))
//					.forEach(p -> System.out.println("  " + p));
//		}
//	}
//
//	// ========== 8. PRACTICAL SCENARIOS ==========
//	static void practicalScenarios() throws IOException {
//		System.out.println("\n--- 8. PRACTICAL SCENARIOS ---");
//
//		// Scenario 1: CSV Processing
//		csvProcessing();
//
//		// Scenario 2: Configuration File
//		configFileHandling();
//
//		// Scenario 3: Log File Writing
//		logFileWriting();
//	}
//
//	static void csvProcessing() throws IOException {
//		System.out.println("\nScenario 1: CSV Processing");
//
//		// Write CSV
//		try (PrintWriter pw = new PrintWriter("data.csv")) {
//			pw.println("Name,Age,Score");
//			pw.println("Alice,20,95.5");
//			pw.println("Bob,22,88.0");
//			pw.println("Charlie,21,92.5");
//		}
//
//		// Read and parse CSV
//		List<String[]> records = new ArrayList<>();
//		try (BufferedReader br = new BufferedReader(new FileReader("data.csv"))) {
//			String line;
//			boolean header = true;
//			while ((line = br.readLine()) != null) {
//				if (header) {
//					header = false;
//					continue;
//				}
//				String[] values = line.split(",");
//				records.add(values);
//			}
//		}
//
//		System.out.println("CSV Records:");
//		for (String[] record : records) {
//			System.out.printf("  Name: %s, Age: %s, Score: %s%n", record[0], record[1], record[2]);
//		}
//	}
//
//	static void configFileHandling() throws IOException {
//		System.out.println("\nScenario 2: Configuration File");
//
//		// Write properties
//		Properties props = new Properties();
//		props.setProperty("database.url", "localhost:3306");
//		props.setProperty("database.user", "admin");
//		props.setProperty("max.connections", "100");
//
//		try (FileOutputStream fos = new FileOutputStream("config.properties")) {
//			props.store(fos, "Application Configuration");
//		}
//
//		// Read properties
//		Properties loadedProps = new Properties();
//		try (FileInputStream fis = new FileInputStream("config.properties")) {
//			loadedProps.load(fis);
//		}
//
//		System.out.println("Configuration:");
//		loadedProps.forEach((k, v) -> System.out.println("  " + k + " = " + v));
//	}
//
//	static void logFileWriting() throws IOException {
//		System.out.println("\nScenario 3: Log File Writing");
//
//		// Append to log file with timestamp
//		try (PrintWriter pw = new PrintWriter(new FileWriter("app.log", true))) {
//			pw.printf("[%s] Application started%n", new Date());
//			pw.printf("[%s] User logged in%n", new Date());
//			pw.printf("[%s] Operation completed%n", new Date());
//		}
//
//		System.out.println("Log entries written to app.log");
//	}
//}
//
//// Serializable class for demonstration
//class Student implements Serializable {
//	private static final long serialVersionUID = 1L;
//	private String name;
//	private int age;
//	private double score;
//
//	public Student(String name, int age, double score) {
//		this.name = name;
//		this.age = age;
//		this.score = score;
//	}
//
//	@Override
//	public String toString() {
//		return String.format("Student{name='%s', age=%d, score=%.1f}", name, age, score);
//	}
}