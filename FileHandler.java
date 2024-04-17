import java.io.*;
import java.util.*;

public class FileHandler {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to File Handler!");
        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Read a Text File");
            System.out.println("2. Write to a Text File");
            System.out.println("3. Analyze File Content");
            System.out.println("4. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    readTextFile();
                    break;
                case 2:
                    writeTextFile();
                    break;
                case 3:
                    analyzeFileContent();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void readTextFile() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the file path:");
        String filePath = scanner.nextLine();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            StringBuilder content = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
            System.out.println("File content:\n" + content);
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filePath);
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }
    }

    private static void writeTextFile() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the file path to write to:");
        String filePath = scanner.nextLine();
        System.out.println("Enter the text to write:");
        String text = scanner.nextLine();
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath, true))) {
            writer.println(text);
            System.out.println("Text written to file successfully.");
        } catch (IOException e) {
            System.out.println("Error writing to the file: " + e.getMessage());
        }
    }

    private static void analyzeFileContent() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the file path to analyze:");
        String filePath = scanner.nextLine();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            Map<String, Integer> wordFrequency = new HashMap<>();
            int totalWords = 0;
            while ((line = reader.readLine()) != null) {
                String[] words = line.split("\\s+");
                for (String word : words) {
                    word = word.toLowerCase(); 
                    wordFrequency.put(word, wordFrequency.getOrDefault(word, 0) + 1);
                    totalWords++;
                }
            }
            System.out.println("Total words: " + totalWords);
            System.out.println("Unique words: " + wordFrequency.size());
            System.out.println("Word frequencies:");
            for (Map.Entry<String, Integer> entry : wordFrequency.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filePath);
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }
    }


}