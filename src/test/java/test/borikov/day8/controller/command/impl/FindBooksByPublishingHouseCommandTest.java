package test.borikov.day8.controller.command.impl;

import com.borikov.day8.controller.command.DataKeyName;
import com.borikov.day8.controller.command.ResponseKeyName;
import com.borikov.day8.controller.command.impl.FindBooksByPublishingHouseCommand;
import com.borikov.day8.model.entity.Book;
import org.testng.annotations.*;
import test.borikov.day8.creator.BookStorage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.*;

public class FindBooksByPublishingHouseCommandTest {
    private FindBooksByPublishingHouseCommand findBooksByPublishingHouseCommand;
    private BookStorage bookStorage;

    @BeforeClass
    public void setUpClass() {
        findBooksByPublishingHouseCommand = new FindBooksByPublishingHouseCommand();
        bookStorage = BookStorage.getInstance();
    }

    @AfterClass
    public void tearDownClass() {
        findBooksByPublishingHouseCommand = null;
        bookStorage = null;
    }

    @DataProvider(name = "executePositiveData")
    public Object[][] createExecutePositiveData() {
        Map<String, String> data1 = new HashMap<>();
        data1.put(DataKeyName.PUBLISHING_HOUSE, "Минск");
        List<Book> filteredBooks1 = new ArrayList<>();
        filteredBooks1.add(bookStorage.getCreatedBooks().get(0));
        filteredBooks1.add(bookStorage.getCreatedBooks().get(1));
        filteredBooks1.add(bookStorage.getCreatedBooks().get(6));
        Map<String, Object> expected1 = new HashMap<>();
        expected1.put(ResponseKeyName.FILTERED_BOOKS, filteredBooks1);
        Map<String, String> data2 = new HashMap<>();
        data2.put(DataKeyName.PUBLISHING_HOUSE, "Москва");
        List<Book> filteredBooks2 = new ArrayList<>();
        filteredBooks2.add(bookStorage.getCreatedBooks().get(4));
        filteredBooks2.add(bookStorage.getCreatedBooks().get(7));
        Map<String, Object> expected2 = new HashMap<>();
        expected2.put(ResponseKeyName.FILTERED_BOOKS, filteredBooks2);
        Map<String, String> data3 = new HashMap<>();
        data3.put(DataKeyName.PUBLISHING_HOUSE, "qwe");
        List<Book> filteredBooks3 = new ArrayList<>();
        Map<String, Object> expected3 = new HashMap<>();
        expected3.put(ResponseKeyName.FILTERED_BOOKS, filteredBooks3);
        Map<String, String> data4 = null;
        List<Book> filteredBooks4 = new ArrayList<>();
        Map<String, Object> expected4 = new HashMap<>();
        expected4.put(ResponseKeyName.FILTERED_BOOKS, filteredBooks4);
        Map<String, String> data5 = new HashMap<>();
        List<Book> filteredBooks5 = new ArrayList<>();
        Map<String, Object> expected5 = new HashMap<>();
        expected5.put(ResponseKeyName.FILTERED_BOOKS, filteredBooks5);
        Map<String, String> data6 = new HashMap<>();
        data6.put(DataKeyName.PUBLISHING_HOUSE, "");
        List<Book> filteredBooks6 = new ArrayList<>();
        Map<String, Object> expected6 = new HashMap<>();
        filteredBooks6.add(bookStorage.getCreatedBooks().get(5));
        filteredBooks6.add(bookStorage.getCreatedBooks().get(8));
        filteredBooks6.add(bookStorage.getCreatedBooks().get(9));
        filteredBooks6.add(bookStorage.getCreatedBooks().get(0));
        filteredBooks6.add(bookStorage.getCreatedBooks().get(1));
        filteredBooks6.add(bookStorage.getCreatedBooks().get(6));
        filteredBooks6.add(bookStorage.getCreatedBooks().get(4));
        filteredBooks6.add(bookStorage.getCreatedBooks().get(7));
        filteredBooks6.add(bookStorage.getCreatedBooks().get(2));
        filteredBooks6.add(bookStorage.getCreatedBooks().get(3));
        expected6.put(ResponseKeyName.FILTERED_BOOKS, filteredBooks6);
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
            Map<String, String> data, Map<String, Object> expected) {
        Map<String, Object> actual =
                findBooksByPublishingHouseCommand.execute(data);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "executeNegativeTest")
    public Object[][] createExecuteNegativeData() {
        Map<String, String> data1 = new HashMap<>();
        data1.put(DataKeyName.PUBLISHING_HOUSE, "Минск");
        List<Book> filteredBooks1 = new ArrayList<>();
        filteredBooks1.add(bookStorage.getCreatedBooks().get(0));
        filteredBooks1.add(bookStorage.getCreatedBooks().get(1));
        Map<String, Object> expected1 = new HashMap<>();
        expected1.put(ResponseKeyName.FILTERED_BOOKS, filteredBooks1);
        Map<String, String> data2 = new HashMap<>();
        data2.put(DataKeyName.PUBLISHING_HOUSE, "Москва");
        List<Book> filteredBooks2 = new ArrayList<>();
        filteredBooks2.add(bookStorage.getCreatedBooks().get(4));
        filteredBooks2.add(bookStorage.getCreatedBooks().get(7));
        filteredBooks2.add(bookStorage.getCreatedBooks().get(7));
        Map<String, Object> expected2 = new HashMap<>();
        expected2.put(ResponseKeyName.FILTERED_BOOKS, filteredBooks2);
        Map<String, String> data3 = new HashMap<>();
        data3.put(DataKeyName.PUBLISHING_HOUSE, "Киев");
        List<Book> filteredBooks3 = new ArrayList<>();
        Map<String, Object> expected3 = new HashMap<>();
        expected3.put(ResponseKeyName.FILTERED_BOOKS, filteredBooks3);
        Map<String, String> data4 = null;
        List<Book> filteredBooks4 = new ArrayList<>();
        Map<String, Object> expected4 = new HashMap<>();
        expected4.put(ResponseKeyName.ERROR, filteredBooks4);
        return new Object[][]{
                {data1, expected1},
                {data2, expected2},
                {data3, expected3},
                {data4, expected4}
        };
    }

    @Test(dataProvider = "executeNegativeTest")
    public void executeNegativeTest(
            Map<String, String> data, Map<String, Object> expected) {
        Map<String, Object> actual =
                findBooksByPublishingHouseCommand.execute(data);
        assertNotEquals(actual, expected);
    }
}
