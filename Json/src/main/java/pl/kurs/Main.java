package pl.kurs;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import pl.kurs.model.*;
import pl.kurs.utils.ObjectMapperHolder;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Main {
    private static final String PATH = "src/main/resources/";

    public static void main(String[] args) throws IOException {
        //   ObjectMapper mapper = new ObjectMapper();
        ObjectMapper mapper = ObjectMapperHolder.INSTANCE.getMapper();

        // deserializacja
        // tworzenie obiektu java z jsona
//        Person person = mapper.readValue(new File(PATH + "adamnowak.json"), Person.class);
//        System.out.println(person);

        Person janKowalski = new Person("Jan", "Kowalski", 50, false, List.of("pilka nozna", "siatkowka"),
                List.of(new Kid("LUki", "Kowalski", 15), new Kid("Alicja", "Kowalska", 60)),
                LocalDate.now());

        // serializacja
        // zapis obiektu java do pliku jsonowego
        mapper.writeValue(new File(PATH + "kowalski.json"), janKowalski);

//        // zapisanie obiektu javowego jako string z jsonem
//        Kid kid = new Kid("Franek", "Kimono", 10);
//        String kidJsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(kid);
//        System.out.println(kidJsonString);
//
//        // stworzenie pobietk ujava na podstawie stringa z jsoem
//        String kidJson = "{\"firstName\":\"Franek\",\"lastName\":\"Kimono\",\"age\":10}";
//        Kid kidFromString = mapper.readValue(kidJson, Kid.class);
//        System.out.println(kidFromString);
//
//        // https://soundcloud.com/oembed?url=http%3A//soundcloud.com/forss/flickermood&format=json
//        URL url = new URL("https://soundcloud.com/oembed?url=http%3A//soundcloud.com/forss/flickermood&format=json");
//        SoundCloud soundCloud = mapper.readValue(url, SoundCloud.class);
//        System.out.println(soundCloud.getAuthor_name());
//        System.out.println(soundCloud.getDescription());
//
//        DateKeeper dk = new DateKeeper(new Date());
//        mapper.writeValue(new File(PATH + "date.json"), dk);

        // JsonNode
        // zmiana jsonowego stringa w json node
        String kidJson = "{\"firstName\":\"Franek\",\"lastName\":\"Kimono\",\"age\":10}";
        JsonNode jsonNode = mapper.readTree(kidJson);
        System.out.println(jsonNode.get("firstName").asText());
        System.out.println(jsonNode.get("age").asInt());

        // zmiana json noda na jsona w stringu
        String stringFromJsonNode = jsonNode.toString();
        System.out.println(stringFromJsonNode);

        // zamian obiektu java na json node
        Kid kid = new Kid("Franek", "Kimono", 10);
        JsonNode kidJsonNode = mapper.valueToTree(kid);
        System.out.println(kidJsonNode.get("firstName").asText());

        // zmiana json noda na obiekt java
        Kid kidFromJsonNode = mapper.treeToValue(kidJsonNode, Kid.class);
        System.out.println(kidFromJsonNode);

        Car car = new Car("Polonez", 4);
        mapper.writeValue(new File(PATH + "polonez.json"), car);

    }
}
