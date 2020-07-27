package test.borikov.day8.controller.command.impl;

import com.borikov.day8.controller.command.DataKeyName;
import com.borikov.day8.controller.command.ResponseKeyName;
import com.borikov.day8.controller.command.impl.FindBooksByPublishingYearIntervalCommand;
import com.borikov.day8.model.entity.Book;
import org.testng.annotations.*;
import test.borikov.day8.creator.BookStorage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.*;

public class FindBooksByPublishingYearIntervalCommandTest {
    private FindBooksByPublishingYearIntervalCommand findBooksByPublishingYearIntervalCommand;
    private BookStorage bookStorage;

    @BeforeClass
    public void setUpClass() {
        findBooksByPublishingYearIntervalCommand = new FindBooksByPublishingYearIntervalCommand();
        bookStorage = BookStorage.getInstance();
    }

    @AfterClass
    public void tearDownClass() {
        findBooksByPublishingYearIntervalCommand = null;
        bookStorage = null;
    }

    @DataProvider(name = "executePositiveData")
    public Object[][] createExecutePositiveData() {
        Map<String, String> data1 = new HashMap<>();
        data1.put(DataKeyName.PUBLISHING_YEAR_INTERVAL_BEGIN, "1000");
        data1.put(DataKeyName.PUBLISHING_YEAR_INTERVAL_END, "1000");
        List<Book> filteredBooks1 = new ArrayList<>();
        filteredBooks1.add(bookStorage.getCreatedBooks().get(2));
        filteredBooks1.add(bookStorage.getCreatedBooks().get(6));
        Map<String, Object> expected1 = new HashMap<>();
        expected1.put(ResponseKeyName.FILTERED_BOOKS, filteredBooks1);
        Map<String, String> data2 = new HashMap<>();
        data2.put(DataKeyName.PUBLISHING_YEAR_INTERVAL_BEGIN, "1984");
        data2.put(DataKeyName.PUBLISHING_YEAR_INTERVAL_END, "1990");
        List<Book> filteredBooks2 = new ArrayList<>();
        filteredBooks2.add(bookStorage.getCreatedBooks().get(0));
        filteredBooks2.add(bookStorage.getCreatedBooks().get(4));
        Map<String, Object> expected2 = new HashMap<>();
        expected2.put(ResponseKeyName.FILTERED_BOOKS, filteredBooks2);
        Map<String, String> data3 = new HashMap<>();
        data3.put(DataKeyName.PUBLISHING_YEAR_INTERVAL_BEGIN, "1");
        data3.put(DataKeyName.PUBLISHING_YEAR_INTERVAL_END, "2020");
        List<Book> filteredBooks3 = new ArrayList<>();
        filteredBooks3.add(bookStorage.getCreatedBooks().get(8));
        filteredBooks3.add(bookStorage.getCreatedBooks().get(2));
        filteredBooks3.add(bookStorage.getCreatedBooks().get(6));
        filteredBooks3.add(bookStorage.getCreatedBooks().get(3));
        filteredBooks3.add(bookStorage.getCreatedBooks().get(7));
        filteredBooks3.add(bookStorage.getCreatedBooks().get(5));
        filteredBooks3.add(bookStorage.getCreatedBooks().get(0));
        filteredBooks3.add(bookStorage.getCreatedBooks().get(4));
        filteredBooks3.add(bookStorage.getCreatedBooks().get(9));
        filteredBooks3.add(bookStorage.getCreatedBooks().get(1));
        Map<String, Object> expected3 = new HashMap<>();
        expected3.put(ResponseKeyName.FILTERED_BOOKS, filteredBooks3);
        Map<String, String> data4 = new HashMap<>();
        data4.put(DataKeyName.PUBLISHING_YEAR_INTERVAL_BEGIN, "0");
        data4.put(DataKeyName.PUBLISHING_YEAR_INTERVAL_END, "2100");
        List<Book> filteredBooks4 = new ArrayList<>();
        Map<String, Object> expected4 = new HashMap<>();
        expected4.put(ResponseKeyName.FILTERED_BOOKS, filteredBooks4);
        Map<String, String> data5 = new HashMap<>();
        List<Book> filteredBooks5 = new ArrayList<>();
        Map<String, Object> expected5 = new HashMap<>();
        expected5.put(ResponseKeyName.FILTERED_BOOKS, filteredBooks5);
        return new Object[][]{
                {data1, expected1},
                {data2, expected2},
                {data3, expected3},
                {data4, expected4},
                {data5, expected5}
        };
    }

    @Test(dataProvider = "executePositiveData")
    public void executePositiveTest(
            Map<String, String> data, Map<String, Object> expected) {
        Map<String, Object> actual =
                findBooksByPublishingYearIntervalCommand.execute(data);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "executeNegativeTest")
    public Object[][] createExecuteNegativeData() {
        Map<String, String> data1 = new HashMap<>();
        data1.put(DataKeyName.PUBLISHING_YEAR_INTERVAL_BEGIN, "1000");
        data1.put(DataKeyName.PUBLISHING_YEAR_INTERVAL_END, "1000");
        List<Book> filteredBooks1 = new ArrayList<>();
        filteredBooks1.add(bookStorage.getCreatedBooks().get(6));
        filteredBooks1.add(bookStorage.getCreatedBooks().get(2));
        Map<String, Object> expected1 = new HashMap<>();
        expected1.put(ResponseKeyName.FILTERED_BOOKS, filteredBooks1);
        Map<String, String> data2 = new HashMap<>();
        data2.put(DataKeyName.PUBLISHING_YEAR_INTERVAL_BEGIN, "1984");
        data2.put(DataKeyName.PUBLISHING_YEAR_INTERVAL_END, "1990");
        List<Book> filteredBooks2 = new ArrayList<>();
        filteredBooks2.add(bookStorage.getCreatedBooks().get(0));
        Map<String, Object> expected2 = new HashMap<>();
        expected2.put(ResponseKeyName.FILTERED_BOOKS, filteredBooks2);
        Map<String, String> data3 = new HashMap<>();
        data3.put(DataKeyName.PUBLISHING_YEAR_INTERVAL_BEGIN, "1");
        data3.put(DataKeyName.PUBLISHING_YEAR_INTERVAL_END, "2020");
        List<Book> filteredBooks3 = new ArrayList<>();
        Map<String, Object> expected3 = new HashMap<>();
        expected3.put(ResponseKeyName.FILTERED_BOOKS, filteredBooks3);
        Map<String, String> data4 = new HashMap<>();
        data4.put(DataKeyName.PUBLISHING_YEAR_INTERVAL_BEGIN, "0");
        data4.put(DataKeyName.PUBLISHING_YEAR_INTERVAL_END, "2100");
        List<Book> filteredBooks4 = new ArrayList<>();
        filteredBooks4.add(bookStorage.getCreatedBooks().get(8));
        filteredBooks4.add(bookStorage.getCreatedBooks().get(2));
        filteredBooks4.add(bookStorage.getCreatedBooks().get(6));
        filteredBooks4.add(bookStorage.getCreatedBooks().get(3));
        filteredBooks4.add(bookStorage.getCreatedBooks().get(7));
        filteredBooks4.add(bookStorage.getCreatedBooks().get(5));
        filteredBooks4.add(bookStorage.getCreatedBooks().get(0));
        filteredBooks4.add(bookStorage.getCreatedBooks().get(4));
        filteredBooks4.add(bookStorage.getCreatedBooks().get(9));
        filteredBooks4.add(bookStorage.getCreatedBooks().get(1));
        Map<String, Object> expected4 = new HashMap<>();
        expected4.put(ResponseKeyName.FILTERED_BOOKS, filteredBooks4);
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
                findBooksByPublishingYearIntervalCommand.execute(data);
        assertNotEquals(actual, expected);
    }
}
