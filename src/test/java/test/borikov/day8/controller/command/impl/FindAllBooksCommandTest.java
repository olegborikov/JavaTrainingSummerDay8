package test.borikov.day8.controller.command.impl;

import com.borikov.day8.controller.command.ResponseKeyName;
import com.borikov.day8.controller.command.impl.FindAllBooksCommand;
import com.borikov.day8.model.entity.Book;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import test.borikov.day8.creator.BookStorage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.*;

public class FindAllBooksCommandTest {
    private FindAllBooksCommand findAllBooksCommand;
    private BookStorage bookStorage;

    @BeforeClass
    public void setUpClass() {
        findAllBooksCommand = new FindAllBooksCommand();
        bookStorage = BookStorage.getInstance();
    }

    @AfterClass
    public void tearDownClass() {
        findAllBooksCommand = null;
        bookStorage = null;
    }

    @Test
    public void executePositiveTest() {
        Map<String, String> data = new HashMap<>();
        Map<String, Object> actual = findAllBooksCommand.execute(data);
        List<Book> allBooks = bookStorage.getCreatedBooks();
        Map<String, Object> expected = new HashMap<>();
        expected.put(ResponseKeyName.ALL_BOOKS, allBooks);
        assertEquals(actual, expected);
    }

    @Test
    public void executeNegativeTest() {
        Map<String, String> data = new HashMap<>();
        Map<String, Object> actual = findAllBooksCommand.execute(data);
        List<Book> allBooks =
                new ArrayList<>(bookStorage.getCreatedBooks());
        allBooks.add(null);
        Map<String, Object> expected = new HashMap<>();
        expected.put(ResponseKeyName.ALL_BOOKS, allBooks);
        assertNotEquals(actual, expected);
    }
}
