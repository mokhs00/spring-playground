package com.mokhs.springplayground.objectmapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mokhs.springplayground.objectmapper.dto.Car;
import com.mokhs.springplayground.objectmapper.dto.User;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();

        User user = new User();

        user.setName("김한수");
        user.setAge(22);


        List<Car> carList = new ArrayList<>();

        carList.add(new Car("K5", "11가 1234", "SEDAN"));
        carList.add(new Car("Q5", "11가 1234", "SUV"));

        user.setCars(carList);

        System.out.println(user);

        String json = mapper.writeValueAsString(user);
        System.out.println(json);

        JsonNode jsonNode = mapper.readTree(json);

        String _name = jsonNode.get("name").asText();
        int _age = jsonNode.get("age").asInt();

        System.out.println("name = " + _name);
        System.out.println("age = " + _age);

        ArrayNode cars = (ArrayNode) jsonNode.get("cars");
        List<Car> _cars = mapper.convertValue(cars, new TypeReference<List<Car>>() {});

        System.out.println(_cars);

        ObjectNode objectNode = (ObjectNode) jsonNode;
        objectNode.put("name", "mokhs");

        System.out.println(objectNode.toPrettyString());

    }
}
