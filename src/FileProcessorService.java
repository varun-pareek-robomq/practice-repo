//Processes multiple files concurrently using threads
//Collects results from all files
//Handles exceptions from individual threads without crashing the entire system
//Returns a map of filename → sum (or error message)


import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class FileProcessorService{

    public Map<String, String> processFiles(List<String> filePaths) throws InterruptedException {
        Map<String,String> map = new HashMap<>();
        List<Thread> threads = new ArrayList<>();
        for (String filePath : filePaths) {
            Thread t = new Thread(new FileProcessingTask(filePath, map));
            t.start();
            threads.add(t); //SAVE A REFERENCE
//            t.start();
//            t.join(); NOT A RIGHT WAY TO RUN MULTIPLE THREADS AS T1 RUNS -> WAITS THEN T2 RUNS-> WAITS AND SO ON
        for (Thread t1: threads){
            t1.join();
        }
        }
        return map;
    }


    static class FileProcessingTask implements Runnable{
        private final String filePath;
        private final FileProcessor fileProcessor;
        private Map<String ,String> map;

        public FileProcessingTask(String filePath, Map<String,String> map) {
            this.filePath = filePath;
            this.fileProcessor=new FileProcessor();
            this.map = map;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()+" processing file: "+filePath );
            List<Integer> numbers;
            int sum=0;
            try {
                numbers=fileProcessor.readNumbers(filePath);
                sum=fileProcessor.sumOfNumbers(numbers);
            } catch (InvalidNumberException | FileNotFoundException | EmptyFileException e) {
                map.putIfAbsent(filePath,"Error: "+ e.getMessage());
                throw new RuntimeException(e);
            }
            map.put(filePath,String.valueOf(sum));
        }
    }
}
