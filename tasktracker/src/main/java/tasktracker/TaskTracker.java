package tasktracker;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TaskTracker {
    private static List<Task> tasks = new ArrayList<>();
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final File file = new File("tasks.json");

    public static String generateNewId() {
        if (tasks.isEmpty()) {
            return "1";
        }
        int maxId = 0;
        for (Task t : tasks) {
            try {
                maxId = Math.max(maxId, Integer.parseInt(t.getId()));
            } catch (NumberFormatException ignored) {
            }
        }
        return String.valueOf(maxId + 1);
    }

    public static void main(String[] args) {
        try {
            loadTasks();
            if (args.length > 0) {
                String command = args[0];
                String idx;
                boolean updated;
                switch (command) {
                    case "help":
                        printHelp();
                        break;
                    case "add":
                        if (args.length < 2) {
                            System.out.println("Usage: add <description>");
                            return;
                        }
                        idx = generateNewId();
                        String description = args[1];
                        if (description.isBlank()) {
                            System.out.println("Description cannot be empty.");
                            return;
                        }
                        Task newTask = new Task(idx, description, TaskStatus.TODO, String.valueOf(LocalDateTime.now()), "");
                        tasks.add(newTask);
                        saveTask();
                        System.out.printf("Task added successfully (ID: %s)\n", idx);
                        break;
                    case "list":
                        String filter = args.length > 1 ? args[1] : "";
                        boolean found = false;
                        if (tasks.isEmpty()) {
                            System.out.println("No tasks found.");
                            return;
                        }
                        for (Task t : tasks) {
                            if (filter.isEmpty()) {
                                found = true;
                                System.out.println(t);
                            } else {
                                try {
                                    TaskStatus statusFilter = TaskStatus.fromString(filter);
                                    if (t.getStatus() == statusFilter) {
                                        found = true;
                                        System.out.println(t);
                                    }
                                } catch (IllegalArgumentException e) {
                                    System.out.println("Invalid status filter: " + filter);
                                    return;
                                }
                            }

                        }
                        if (!found) {
                            System.out.println("No tasks found with the given filter");
                        }
                        break;
                    case "delete":
                        if (args.length != 2) {
                            System.out.println("Usage: delete <id>");
                            return;
                        }
                        idx = args[1];
                        if (!isValidId(idx)) {
                            System.out.println("Invalid ID: " + idx + ". ID must be a numeric value.");
                            return;
                        }
                        boolean deleted = false;
                        Iterator<Task> it = tasks.iterator();
                        while (it.hasNext()) {
                            Task task = it.next();
                            if (task.getId().equals(idx)) {
                                it.remove();
                                deleted = true;
                                break;
                            }
                        }
                        if (deleted) {
                            saveTask();
                            System.out.println("Task deleted successfully");
                        } else {
                            System.out.println("No task found with ID " + idx);
                        }
                        break;
                    case "update":
                        if (args.length < 3) {
                            System.out.println("Usage: update <id> <new-description>");
                            return;
                        }
                        idx = args[1];
                        if (!isValidId(idx)) {
                            System.out.println("Invalid ID: " + idx + ". ID must be a numeric value.");
                            return;
                        }
                        String updatedDescription = args[2];
                        if (updatedDescription.isBlank()) {
                            System.out.println("Description cannot be empty.");
                            return;
                        }
                        updated = false;
                        for (Task t : tasks) {
                            if (t.getId().equals(idx)) {
                                t.setDescription(updatedDescription);
                                t.setUpdatedAt(String.valueOf(LocalDateTime.now()));
                                updated = true;
                                break;
                            }
                        }
                        if (!updated) {
                            System.out.println("No task found with ID " + idx);
                            return;
                        }
                        saveTask();
                        System.out.printf("Task updated successfully (ID: %s)\n", idx);
                        break;
                    case "mark-in-progress":
                        if (args.length != 2) {
                            System.out.println("Usage: mark-in-progress <id>");
                            return;
                        }
                        idx = args[1];
                        if (!isValidId(idx)) {
                            System.out.println("Invalid ID: " + idx + ". ID must be a numeric value.");
                            return;
                        }
                        updated = false;
                        for (Task t : tasks) {
                            if (t.getId().equals(idx)) {
                                t.setStatus(TaskStatus.IN_PROGRESS);
                                t.setUpdatedAt(String.valueOf(LocalDateTime.now()));
                                updated = true;
                                break;
                            }
                        }
                        if (!updated) {
                            System.out.println("No task found with ID " + idx);
                            return;
                        }
                        saveTask();
                        System.out.printf("Task updated successfully (ID: %s)\n", idx);
                        break;
                    case "mark-done":
                        if (args.length != 2) {
                            System.out.println("Usage: mark-done <id>");
                            return;
                        }
                        idx = args[1];
                        if (!isValidId(idx)) {
                            System.out.println("Invalid ID: " + idx + ". ID must be a numeric value.");
                            return;
                        }
                        updated = false;
                        for (Task t : tasks) {
                            if (t.getId().equals(idx)) {
                                t.setStatus(TaskStatus.DONE);
                                t.setUpdatedAt(String.valueOf(LocalDateTime.now()));
                                updated = true;
                                break;
                            }
                        }

                        if (!updated) {
                            System.out.println("No task found with ID " + idx);
                            return;
                        }
                        saveTask();
                        System.out.printf("Task updated successfully (ID: %s)\n", idx);
                        break;
                    default:
                        System.out.println("Invalid Command! Type 'help' for usage instructions.");
                }

            } else {
                printHelp();
            }

        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        }

    }

    private static void printHelp() {
        System.out.println("Task Tracker CLI");
        System.out.println("Usage:");
        System.out.println("  add <description>              Add a new task");
        System.out.println("  update <id> <description>     Update a task description");
        System.out.println("  delete <id>                   Delete a task");
        System.out.println("  mark-in-progress <id>         Mark a task as in-progress");
        System.out.println("  mark-done <id>                Mark a task as done");
        System.out.println("  list                          List all tasks");
        System.out.println("  list <status>                 List tasks by status (todo, in-progress, done)");
        System.out.println("  help                          Display this help message");
    }

    private static boolean isValidId(String id) {
        try {
            Integer.parseInt(id);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void saveTask() throws IOException {
        objectMapper.writeValue(file, tasks);
        System.out.println("Saved to Json!");
    }

    public static void loadTasks() throws IOException {
        if (!file.exists()) {
            file.createNewFile();
            objectMapper.writeValue(file, new ArrayList<Task>());
        }
        if (file.length() == 0) return;
        tasks = objectMapper.readValue(file, new TypeReference<List<Task>>() {
        });
    }
}
