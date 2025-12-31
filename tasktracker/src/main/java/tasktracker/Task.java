package tasktracker;

import java.time.LocalDateTime;

public class Task {
	private String id;
	private String description;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	public Task(String id, String description, LocalDateTime createdAt, LocalDateTime updatedAt) {
		this.id = id;
		this.description = description;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

}
