package tasktracker;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TaskTracker {

	public static void main(String[] args) {
		List<Task> tasks = new ArrayList<>();

		int id = 0;
		try {
			if (args.length > 0) {

				if (args[0].equals("add")) {
					String taskId = String.valueOf(id++);
					Task newTask = new Task(taskId, args[1]);
					tasks.add(newTask);
					System.out.printf("Task added successfully (ID: %s)\n", taskId);
				} else if (args[0].equals("udpate")) {
					String idx = String.valueOf(args[1]);
					String description = args[2];
					if (idx.isEmpty()) {
						System.out.println("Please metion the taskId");
						return;
					}

					if (description.isEmpty()) {
						System.out.println("Please metion the task description");
						return;
					}

					for (Task t : tasks) {
						if (t.getId().equals(idx)) {
							t.setDescription(description);
						}
					}

					System.out.println(" Task updated successfully");

				} else if (args[0].equals("delete")) {
					String idx = String.valueOf(args[1]);
					if (idx.isEmpty()) {
						System.out.println("Please metion the taskId");
						return;
					}

					Iterator<Task> iterator = tasks.iterator();
					while (iterator.hasNext()) {
						Task t = iterator.next();
						if (t.getId().equals(idx)) {
							iterator.remove();
							System.out.printf("Task ID %s deleted successfully\n", idx);
						}
					}
				} else if (args[0].equals("list")) {
					String statusFilter = args[1];
					 if (statusFilter.equals("todo")) {
						tasks.stream().filter(t->t.getStatus().equals("todo")).forEach(System.out::println);
					}else if (statusFilter.equals("in-progress")) {
						tasks.stream().filter(t->t.getStatus().equals("in-progress")).forEach(System.out::println);
					}else if (statusFilter.equals("done")) {
						tasks.stream().filter(t->t.getStatus().equals("done")).forEach(System.out::println);
					} else {
						tasks.forEach(System.out::println);
					}
					

				} else if (args[0].equals("mark-in-progress")) {
					String idx = String.valueOf(args[1]);
					if (idx.isEmpty()) {
						System.out.println("Please metion the taskId");
						return;
					}

					for (Task t : tasks) {
						if (t.getId().equals(idx)) {
							t.setStatus("in-progress");
							System.out.printf("Task ID %s marked as in-progress\n", idx);
						}
					}

				} else if (args[0].equals("mark-done")) {
					String idx = String.valueOf(args[1]);
					if (idx.isEmpty()) {
						System.out.println("Please metion the taskId");
						return;
					}

					for (Task t : tasks) {
						if (t.getId().equals(idx)) {
							t.setStatus("done");
							System.out.printf("Task ID %s marked as done\n", idx);
						}
					}
				}

			} else {
				System.out.println("No argument provided!");
			}
		}catch(Exception e) {
			System.out.println("An error occurred: " + e.getMessage());
		}

	}
}
