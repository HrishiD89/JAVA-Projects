package com.practice;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.core.type.TypeReference;

public class JsonFileApp {
	static Path FILE_PATH = Path.of("src/main/java/com/practice/users.json");
	static ObjectMapper mapper = new ObjectMapper();

	public static void main(String[] args) throws Exception {
		writeUser(new User(1, "Hrishi"));
		writeUser(new User(2, "Rahul"));

		readUsers().forEach(u -> System.out.println(u.id + "-" + u.name));
	}

	public static void writeUser(User newUser) throws Exception {
		List<User> users = new ArrayList<>();
		if (Files.exists(FILE_PATH)) {
			users = mapper.readValue(Files.readString(FILE_PATH), new TypeReference<List<User>>() {
			});
		}

		users.add(newUser);

		Files.writeString(FILE_PATH, mapper.writerWithDefaultPrettyPrinter().writeValueAsString(users));
	}

	public static List<User> readUsers() throws Exception {

		if (!Files.exists(FILE_PATH))
			return new ArrayList<>();

		return mapper.readValue(Files.readString(FILE_PATH), new TypeReference<List<User>>() {
		});
	}

}
