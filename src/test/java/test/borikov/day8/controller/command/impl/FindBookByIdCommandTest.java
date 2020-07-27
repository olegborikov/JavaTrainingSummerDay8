package test.borikov.day8.controller.command.impl;

import com.borikov.day8.controller.command.DataKeyName;
import com.borikov.day8.controller.command.ResponseKeyName;
import com.borikov.day8.controller.command.impl.FindAllBooksCommand;
import com.borikov.day8.controller.command.impl.FindBookByIdCommand;
import com.borikov.day8.model.entity.Book;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test.borikov.day8.creator.BookStorage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.testng.Assert.*;

public class FindBookByIdCommandTest {
    private FindBookByIdCommand findBookByIdCommand;
    private BookStorage bookStorage;

    @BeforeClass
    public void setUpClass() {
        findBookByIdCommand = new FindBookByIdCommand();
        bookStorage = BookStorage.getInstance();
    }

    @AfterClass
    public void tearDownClass() {
        findBookByIdCommand = null;
        bookStorage = null;
    }

    @DataProvider(name = "executePositiveData")
    public Object[][] createExecutePositiveData() {
        Map<String, String> data1 = new HashMap<>();
        data1.put(DataKeyName.ID, "1");
        Optional<Book> currentBook1 =
                Optional.of(bookStorage.getCreatedBooks().get(0));
        Map<String, Object> expected1 = new HashMap<>();
        expected1.put(ResponseKeyName.CURRENT_BOOK, currentBook1);
        Map<String, String> data2 = new HashMap<>();
        data2.put(DataKeyName.ID, "9");
        Optional<Book> currentBook2 =
                Optional.of(bookStorage.getCreatedBooks().get(8));
        Map<String, Object> expected2 = new HashMap<>();
        expected2.put(ResponseKeyName.CURRENT_BOOK, currentBook2);
        Map<String, String> data3 = new HashMap<>();
        data3.put(DataKeyName.ID, "-7");
        Optional<Book> currentBook3 = Optional.empty();
        Map<String, Object> expected3 = new HashMap<>();
        expected3.put(ResponseKeyName.CURRENT_BOOK, currentBook3);
        Map<String, String> data4 = new HashMap<>();
        data4.put(DataKeyName.ID, "abc");
        Optional<Book> currentBook4 = Optional.empty();
        Map<String, Object> expected4 = new HashMap<>();
        expected4.put(ResponseKeyName.CURRENT_BOOK, currentBook4);
        Map<String, String> data5 = null;
        Optional<Book> currentBook5 = Optional.empty();
        Map<String, Object> expected5 = new HashMap<>();
        expected5.put(ResponseKeyName.CURRENT_BOOK, currentBook5);
        Map<String, String> data6 = new HashMap<>();
        Optional<Book> currentBook6 = Optional.empty();
        Map<String, Object> expected6 = new HashMap<>();
        expected6.put(ResponseKeyName.CURRENT_BOOK, currentBook6);
        return new Object[][]{
                {data1, expected1},
                {data2, expected2},
                {data3, expected3},
                {data4, expected4},
                {data5, expected5},
                {data6, expected6}
        };
    }

    @Test(dataProvider = "executePositiveData")
    public void executePositiveTest(
            Map<String, String> data, Map<String, List<Book>> expected) {
        Map<String, Object> actual = findBookByIdCommand.execute(data);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "executeNegativeData")
    public Object[][] createExecuteNegativeData() {
        Map<String, String> data1 = new HashMap<>();
        data1.put(DataKeyName.ID, "1");
        Optional<Book> currentBook1 =
                Optional.of(bookStorage.getCreatedBooks().get(1));
        Map<String, Object> expected1 = new HashMap<>();
        expected1.put(ResponseKeyName.CURRENT_BOOK, currentBook1);
        Map<String, String> data2 = new HashMap<>();
        data2.put(DataKeyName.ID, "9");
        Optional<Book> currentBook2 = Optional.empty();
        Map<String, Object> expected2 = new HashMap<>();
        expected2.put(ResponseKeyName.CURRENT_BOOK, currentBook2);
        Map<String, String> data3 = new HashMap<>();
        data3.put(DataKeyName.ID, "-7");
        Optional<Book> currentBook3 = null;
        Map<String, Object> expected3 = new HashMap<>();
        expected3.put(ResponseKeyName.CURRENT_BOOK, currentBook3);
        return new Object[][]{
                {data1, expected1},
                {data2, expected2},
                {data3, expected3}
        };
    }

    @Test(dataProvider = "executeNegativeData")
    public void executeNegativeTest(
            Map<String, String> data, Map<String, List<Book>> expected) {
        Map<String, Object> actual = findBookByIdCommand.execute(data);
        assertNotEquals(actual, expected);
    }
}
