import java.util.*;
import java.io.*;

public class Main {
    static final String FILE_NAME = "tasks.txt";
    static List<String> tasks = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        loadTasks();
        while (true) {
            System.out.println("\n--- TO-DO LIST ---");
            System.out.println("1. View tasks");
            System.out.println("2. Add task");
            System.out.println("3. Remove task");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    viewTasks();
                    break;
                case "2":
                    addTask();
                    break;
                case "3":
                    removeTask();
                    break;
                case "4":
                    saveTasks();
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    static void loadTasks() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                tasks.add(line);
            }
        } catch (IOException e) {
            // File might not exist yet — that's OK
        }
    }

    static void saveTasks() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (String task : tasks) {
                bw.write(task);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks.");
        }
    }

    static void viewTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks yet!");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
    }

    static void addTask() {
        System.out.print("Enter new task: ");
        String task = scanner.nextLine();
        tasks.add(task);
        System.out.println("Task added.");
    }

    static void removeTask() {
        viewTasks();
        System.out.print("Enter task number to remove: ");
        try {
            int index = Integer.parseInt(scanner.nextLine()) - 1;
            if (index >= 0 && index < tasks.size()) {
                tasks.remove(index);
                System.out.println("Task removed.");
            } else {
                System.out.println("Invalid task number.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
        }
    }
}
