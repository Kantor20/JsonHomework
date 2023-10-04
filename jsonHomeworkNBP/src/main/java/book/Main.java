package book;

import book.model.Book;
import book.utils.ObjectMapperHolder;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

// /*
//                    Stworz klase ksiazka ktora ma autora, tytul, kategorie, cene.
//                    STworz kilka obiektów i zapisz je jako json nody a nastepnie zapisz je jako plik jsonowy.
//                    Wczytaj nastepnie ktoras ksiazke jako obiekt i wyswietl jej wartosci
//                 */
public class Main {
    private static final String PATH = "src/main/resources/";

    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = ObjectMapperHolder.INSTANCE.getMapper();

        Book b1 = Book.builder().author("JK").title("Potter").category("fantasy").price(120.5).build();
        Book b2 = Book.builder().author("AM").title("Dziady").category("Drama").price(150.5).build();
        Book b3 = Book.builder().author("AM").title("PanTadeusz").category("poeam").price(220.5).build();
        Book b4 = Book.builder().author("JK").title("Potter3").category("fantasy").price(178.5).build();

        List<Book> books = List.of(b1, b2, b3, b4);

        mapper.writeValue(new File(PATH + "books.json"), books);

        List<Book> booksFromJson = Arrays.asList(mapper.readValue(new File(PATH + "books.json"), Book[].class));
        booksFromJson.forEach(System.out::println);

        JsonNode bookJsonNode = mapper.valueToTree(books.get(0));
        System.out.println(bookJsonNode.get("author").asText());

        System.out.println(booksFromJson.get(1).getAuthor());

    }
}