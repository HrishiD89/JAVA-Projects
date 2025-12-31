package com.practice;

import java.io.Serializable;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class Json {

	public static void main(String[] args) throws JsonMappingException, JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		Person person = new Person();
		person.name = "Alice";
		person.age = 30;
		person.isActive = true;

		String jsonOutput = objectMapper.writeValueAsString(person);
		System.out.println("JSON Output: " + jsonOutput);
	}

}
