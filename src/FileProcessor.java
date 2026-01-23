import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class FileProcessor {

    public List<Integer> readNumbers(String filePath) throws InvalidNumberException, FileNotFoundException, EmptyFileException {
        List<Integer> numbers = new ArrayList<>();

        // Creation of File is not necessary
//        File file = new File(filePath);

//        if (!file.exists()) {
//            throw new FileNotFoundException("File Not found: " + filePath);
//        }

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                try {
                    numbers.add(Integer.parseInt(line.trim()));
                } catch (NumberFormatException e1) {
                    throw new InvalidNumberException("Number not in format: " + line);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading the file " + e);
        }
        if (numbers.isEmpty()) {
            throw new EmptyFileException("No numbers found in the file: " + filePath);
        }
        return numbers;
    }

    public int sumOfNumbers(List<Integer> list) {
        // NOT NECESSARY IN REAL CODE
//        Optional<Integer> sum = list.stream().reduce(Integer::sum);
//        if (sum.isPresent()) return sum.get();
//        return -1;

        return list.stream().mapToInt(Integer::intValue).sum();

//        The better way to do it
//        return sum.orElse(-1);
    }


    public static void main(String[] args) {
        FileProcessor fileProcessor = new FileProcessor();
        FileProcessorService fileProcessorService = new FileProcessorService();
        List<String> list=new ArrayList<>();
        Map<String, String> map;
        //Multiple Files Reading
        list.add("numbers.txt");
        list.add("numbers2.txt");
        list.add("you.txt");

        // When there was only one file
//        List<Integer> numbers = fileProcessor.readNumbers("numbers.txt");
//        System.out.println("Sum of numbers in the file: "+fileProcessor.sumOfNumbers(numbers));

            try {
                map = fileProcessorService.processFiles(list);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        for(Map.Entry<String,String> entry: map.entrySet()){
            System.out.println("File name: "+entry.getKey()+" has the following content:\n"+entry.getValue());
        }




    }

}
