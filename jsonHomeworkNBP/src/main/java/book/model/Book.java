package book.model;


import lombok.*;

@ToString
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {
    private String author;
    private String title;
    private String category;
    private double price;
}
