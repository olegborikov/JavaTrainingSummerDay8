package test.borikov.day8.creator;

import com.borikov.day8.model.entity.Book;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BookStorage {
    private static BookStorage instance;
    private static List<Book> books;

    private BookStorage() {
        books = new ArrayList<>();
        List<String> authors1 = new ArrayList<>();
        authors1.add("Лев Толстой");
        List<String> authors2 = new ArrayList<>();
        authors2.add("Эрнест Хемингуэй");
        authors2.add("Vladimir Nabokov");
        authors2.add("Фрэнсис Скотт Фицджеральд");
        List<String> authors3 = new ArrayList<>();
        authors3.add("Брюс Эккель");
        List<String> authors4 = new ArrayList<>();
        authors4.add("Robert");
        authors4.add("Oleg");
        List<String> authors5 = new ArrayList<>();
        authors5.add("Лев Толстой");
        List<String> authors6 = new ArrayList<>();
        authors6.add("Robert Sapolsky");
        authors6.add("Robert");
        authors6.add("Sapolsky");
        List<String> authors7 = new ArrayList<>();
        List<String> authors8 = new ArrayList<>();
        authors8.add("Oleg");
        List<String> authors9 = new ArrayList<>();
        authors9.add("Oleg");
        authors9.add("Alex Black");
        List<String> authors10 = new ArrayList<>();
        authors10.add("Дмитрий Глуховский");
        Book book1 = new Book(1L, "Война и мир", 1984, "Минск", authors1);
        Book book2 = new Book(2L, "Преступление", 2020, "Минск", authors2);
        Book book3 = new Book(3L, "Философия Java", 1000, "Питер", authors3);
        Book book4 = new Book(4L, "It is ...", 1500, "Питер", authors4);
        Book book5 = new Book(5L, "Война и мир", 1990, "Москва", authors5);
        Book book6 = new Book(6L, "Behavior", 1750, "London", authors6);
        Book book7 = new Book(7L, "История Минска", 1000, "Минск", authors7);
        Book book8 = new Book(8L, "Я", 1600, "Москва", authors8);
        Book book9 = new Book(9L, "Introduction to Java", 100, "London", authors9);
        Book book10 = new Book(10L, "Метро 2033", 2015, "Киев", authors10);
        books.add(book1);
        books.add(book2);
        books.add(book3);
        books.add(book4);
        books.add(book5);
        books.add(book6);
        books.add(book7);
        books.add(book8);
        books.add(book9);
        books.add(book10);
    }

    public static BookStorage getInstance() {
        if (instance == null) {
            instance = new BookStorage();
        }
        return instance;
    }

    public List<Book> getCreatedBooks() {
        return Collections.unmodifiableList(books);
    }
}
