package test.borikov.day8.controller;

import com.borikov.day8.controller.BookController;
import com.borikov.day8.controller.command.DataKeyName;
import com.borikov.day8.controller.command.ResponseKeyName;
import com.borikov.day8.model.entity.Book;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test.borikov.day8.creator.BookStorage;

import java.util.*;

import static org.testng.Assert.*;

public class BookControllerTest {
    private BookController bookController;
    private BookStorage bookStorage;

    @BeforeClass
    public void setUpClass() {
        bookController = new BookController();
        bookStorage = BookStorage.getInstance();
    }

    @AfterClass
    public void tearDownClass() {
        bookController = null;
        bookStorage = null;
    }

    @DataProvider(name = "processRequestNegativeData")
    public Object[][] createProcessRequestNegativeData() {
        String request1 = "addbook";
        String request2 = "addBook";
        String request3 = "add book";
        Map<String, String> data = new HashMap<>();
        Map<String, Object> expected = new HashMap<>();
        expected.put(ResponseKeyName.ALL_BOOKS, bookStorage.getCreatedBooks());
        return new Object[][]{
                {request1, data, expected},
                {request2, data, expected},
                {request3, data, expected}
        };
    }

    @Test(dataProvider = "processRequestNegativeData")
    public void processRequestNegativeTest(
            String request, Map<String, String> data, Map<String, Object> expected) {
        Map<String, Object> actual = bookController.processRequest(request, data);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "processRequestFindAllBooksPositiveData")
    public Object[][] createProcessRequestFindAllBooksPositiveData() {
        String request = "find_all_books";
        Map<String, String> data1 = null;
        Map<String, String> data2 = new HashMap<>();
        Map<String, String> data3 = new HashMap<>();
        data3.put("first", "first");
        Map<String, Object> expected = new HashMap<>();
        expected.put(ResponseKeyName.ALL_BOOKS, bookStorage.getCreatedBooks());
        return new Object[][]{
                {request, data1, expected},
                {request, data2, expected},
                {request, data3, expected},
        };
    }

    @Test(dataProvider = "processRequestFindAllBooksPositiveData")
    public void processRequestFindAllBooksPositiveTest(
            String request, Map<String, String> data, Map<String, Object> expected) {
        Map<String, Object> actual = bookController.processRequest(request, data);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "processRequestFindAllBooksNegativeData")
    public Object[][] createProcessRequestFindAllBooksNegativeData() {
        String request = "find_book_by_id";
        Map<String, String> data1 = null;
        Map<String, String> data2 = new HashMap<>();
        Map<String, Object> expected1 = new HashMap<>();
        expected1.put(ResponseKeyName.ALL_BOOKS, bookStorage.getCreatedBooks());
        Map<String, Object> expected2 = new HashMap<>();
        return new Object[][]{
                {request, data1, expected1},
                {request, data2, expected2},
        };
    }

    @Test(dataProvider = "processRequestFindAllBooksNegativeData")
    public void processRequestFindAllBooksNegativeTest(
            String request, Map<String, String> data, Map<String, Object> expected) {
        Map<String, Object> actual = bookController.processRequest(request, data);
        assertNotEquals(actual, expected);
    }

    @DataProvider(name = "processRequestFindBooksByIdPositiveData")
    public Object[][] createProcessRequestFindBooksByIdPositiveData() {
        String request = "find_book_by_id";
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
                {request, data1, expected1},
                {request, data2, expected2},
                {request, data3, expected3},
                {request, data4, expected4},
                {request, data5, expected5},
                {request, data6, expected6}
        };
    }

    @Test(dataProvider = "processRequestFindBooksByIdPositiveData")
    public void processRequestFindBooksByIdPositiveTest(
            String request, Map<String, String> data, Map<String, Object> expected) {
        Map<String, Object> actual = bookController.processRequest(request, data);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "processRequestFindBooksByIdNegativeData")
    public Object[][] createProcessRequestFindBooksByIdNegativeData() {
        String request = "find_book_by_id";
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
                {request, data1, expected1},
                {request, data2, expected2},
                {request, data3, expected3}
        };
    }

    @Test(dataProvider = "processRequestFindBooksByIdNegativeData")
    public void processRequestFindBooksByIdNegativeTest(
            String request, Map<String, String> data, Map<String, Object> expected) {
        Map<String, Object> actual = bookController.processRequest(request, data);
        assertNotEquals(actual, expected);
    }

    @DataProvider(name = "processRequestFindBooksByAuthorPositiveData")
    public Object[][] createProcessRequestFindBooksByAuthorPositiveData() {
        String request = "find_books_by_author";
        Map<String, String> data1 = new HashMap<>();
        data1.put(DataKeyName.AUTHOR, "Oleg");
        List<Book> filteredBooks1 = new ArrayList<>();
        filteredBooks1.add(bookStorage.getCreatedBooks().get(7));
        filteredBooks1.add(bookStorage.getCreatedBooks().get(3));
        filteredBooks1.add(bookStorage.getCreatedBooks().get(8));
        Map<String, Object> expected1 = new HashMap<>();
        expected1.put(ResponseKeyName.FILTERED_BOOKS, filteredBooks1);
        Map<String, String> data2 = new HashMap<>();
        data2.put(DataKeyName.AUTHOR, "Sapolsky");
        List<Book> filteredBooks2 = new ArrayList<>();
        filteredBooks2.add(bookStorage.getCreatedBooks().get(5));
        Map<String, Object> expected2 = new HashMap<>();
        expected2.put(ResponseKeyName.FILTERED_BOOKS, filteredBooks2);
        Map<String, String> data3 = new HashMap<>();
        data3.put(DataKeyName.AUTHOR, "qwe");
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
        data6.put(DataKeyName.AUTHOR, "");
        List<Book> filteredBooks6 = new ArrayList<>();
        filteredBooks6.add(bookStorage.getCreatedBooks().get(6));
        filteredBooks6.add(bookStorage.getCreatedBooks().get(7));
        filteredBooks6.add(bookStorage.getCreatedBooks().get(0));
        filteredBooks6.add(bookStorage.getCreatedBooks().get(2));
        filteredBooks6.add(bookStorage.getCreatedBooks().get(4));
        filteredBooks6.add(bookStorage.getCreatedBooks().get(3));
        filteredBooks6.add(bookStorage.getCreatedBooks().get(8));
        filteredBooks6.add(bookStorage.getCreatedBooks().get(9));
        filteredBooks6.add(bookStorage.getCreatedBooks().get(5));
        filteredBooks6.add(bookStorage.getCreatedBooks().get(1));
        Map<String, Object> expected6 = new HashMap<>();
        expected6.put(ResponseKeyName.FILTERED_BOOKS, filteredBooks6);
        return new Object[][]{
                {request, data1, expected1},
                {request, data2, expected2},
                {request, data3, expected3},
                {request, data4, expected4},
                {request, data5, expected5},
                {request, data6, expected6}
        };
    }

    @Test(dataProvider = "processRequestFindBooksByAuthorPositiveData")
    public void processRequestFindBooksByAuthorPositiveTest(
            String request, Map<String, String> data, Map<String, Object> expected) {
        Map<String, Object> actual = bookController.processRequest(request, data);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "processRequestFindBooksByAuthorNegativeData")
    public Object[][] createProcessRequestFindBooksByAuthorNegativeData() {
        String request = "find_books_by_author";
        Map<String, String> data1 = new HashMap<>();
        data1.put(DataKeyName.AUTHOR, "Oleg");
        List<Book> filteredBooks1 = new ArrayList<>();
        filteredBooks1.add(bookStorage.getCreatedBooks().get(3));
        filteredBooks1.add(bookStorage.getCreatedBooks().get(7));
        Map<String, Object> expected1 = new HashMap<>();
        expected1.put(ResponseKeyName.FILTERED_BOOKS, filteredBooks1);
        Map<String, String> data2 = new HashMap<>();
        data2.put(DataKeyName.AUTHOR, "Sapolsky");
        List<Book> filteredBooks2 = new ArrayList<>();
        filteredBooks2.add(bookStorage.getCreatedBooks().get(5));
        filteredBooks2.add(bookStorage.getCreatedBooks().get(5));
        Map<String, Object> expected2 = new HashMap<>();
        expected2.put(ResponseKeyName.FILTERED_BOOKS, filteredBooks2);
        Map<String, String> data3 = new HashMap<>();
        data3.put(DataKeyName.AUTHOR, "Oleg");
        List<Book> filteredBooks3 = new ArrayList<>();
        Map<String, Object> expected3 = new HashMap<>();
        expected3.put(ResponseKeyName.FILTERED_BOOKS, filteredBooks3);
        Map<String, String> data4 = null;
        List<Book> filteredBooks4 = new ArrayList<>();
        Map<String, Object> expected4 = new HashMap<>();
        expected4.put(ResponseKeyName.ERROR, filteredBooks4);
        return new Object[][]{
                {request, data1, expected1},
                {request, data2, expected2},
                {request, data3, expected3},
                {request, data4, expected4}
        };
    }

    @Test(dataProvider = "processRequestFindBooksByAuthorNegativeData")
    public void processRequestFindBooksByAuthorNegativeTest(
            String request, Map<String, String> data, Map<String, Object> expected) {
        Map<String, Object> actual = bookController.processRequest(request, data);
        assertNotEquals(actual, expected);
    }

    @DataProvider(name = "processRequestFindBooksByNamePositiveData")
    public Object[][] createProcessRequestFindBooksByNamePositiveData() {
        String request = "find_books_by_name";
        Map<String, String> data1 = new HashMap<>();
        data1.put(DataKeyName.NAME, "Война и мир");
        List<Book> filteredBooks1 = new ArrayList<>();
        filteredBooks1.add(bookStorage.getCreatedBooks().get(0));
        filteredBooks1.add(bookStorage.getCreatedBooks().get(4));
        Map<String, Object> expected1 = new HashMap<>();
        expected1.put(ResponseKeyName.FILTERED_BOOKS, filteredBooks1);
        Map<String, String> data2 = new HashMap<>();
        data2.put(DataKeyName.NAME, "Метро 2033");
        List<Book> filteredBooks2 = new ArrayList<>();
        filteredBooks2.add(bookStorage.getCreatedBooks().get(9));
        Map<String, Object> expected2 = new HashMap<>();
        expected2.put(ResponseKeyName.FILTERED_BOOKS, filteredBooks2);
        Map<String, String> data3 = new HashMap<>();
        data3.put(DataKeyName.NAME, "qwe");
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
        data6.put(DataKeyName.NAME, "");
        List<Book> filteredBooks6 = new ArrayList<>();
        filteredBooks6.add(bookStorage.getCreatedBooks().get(5));
        filteredBooks6.add(bookStorage.getCreatedBooks().get(8));
        filteredBooks6.add(bookStorage.getCreatedBooks().get(3));
        filteredBooks6.add(bookStorage.getCreatedBooks().get(0));
        filteredBooks6.add(bookStorage.getCreatedBooks().get(4));
        filteredBooks6.add(bookStorage.getCreatedBooks().get(6));
        filteredBooks6.add(bookStorage.getCreatedBooks().get(9));
        filteredBooks6.add(bookStorage.getCreatedBooks().get(1));
        filteredBooks6.add(bookStorage.getCreatedBooks().get(2));
        filteredBooks6.add(bookStorage.getCreatedBooks().get(7));
        Map<String, Object> expected6 = new HashMap<>();
        expected6.put(ResponseKeyName.FILTERED_BOOKS, filteredBooks6);
        return new Object[][]{
                {request, data1, expected1},
                {request, data2, expected2},
                {request, data3, expected3},
                {request, data4, expected4},
                {request, data5, expected5},
                {request, data6, expected6}
        };
    }

    @Test(dataProvider = "processRequestFindBooksByNamePositiveData")
    public void processRequestFindBooksByNamePositiveTest(
            String request, Map<String, String> data, Map<String, Object> expected) {
        Map<String, Object> actual = bookController.processRequest(request, data);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "processRequestFindBooksByNameNegativeData")
    public Object[][] createProcessRequestFindBooksByNameNegativeData() {
        String request = "find_books_by_name";
        Map<String, String> data1 = new HashMap<>();
        data1.put(DataKeyName.NAME, "Война и мир");
        List<Book> filteredBooks1 = new ArrayList<>();
        filteredBooks1.add(bookStorage.getCreatedBooks().get(0));
        Map<String, Object> expected1 = new HashMap<>();
        expected1.put(ResponseKeyName.FILTERED_BOOKS, filteredBooks1);
        Map<String, String> data2 = new HashMap<>();
        data2.put(DataKeyName.NAME, "Метро 2033");
        List<Book> filteredBooks2 = new ArrayList<>();
        filteredBooks2.add(bookStorage.getCreatedBooks().get(8));
        filteredBooks2.add(bookStorage.getCreatedBooks().get(8));
        Map<String, Object> expected2 = new HashMap<>();
        expected2.put(ResponseKeyName.FILTERED_BOOKS, filteredBooks2);
        Map<String, String> data3 = new HashMap<>();
        data3.put(DataKeyName.NAME, "Война и мир");
        List<Book> filteredBooks3 = new ArrayList<>();
        Map<String, Object> expected3 = new HashMap<>();
        expected3.put(ResponseKeyName.FILTERED_BOOKS, filteredBooks3);
        Map<String, String> data4 = null;
        List<Book> filteredBooks4 = new ArrayList<>();
        Map<String, Object> expected4 = new HashMap<>();
        expected4.put(ResponseKeyName.ERROR, filteredBooks4);
        return new Object[][]{
                {request, data1, expected1},
                {request, data2, expected2},
                {request, data3, expected3},
                {request, data4, expected4}
        };
    }

    @Test(dataProvider = "processRequestFindBooksByNameNegativeData")
    public void processRequestFindBooksByNameNegativeTest(
            String request, Map<String, String> data, Map<String, Object> expected) {
        Map<String, Object> actual = bookController.processRequest(request, data);
        assertNotEquals(actual, expected);
    }

    @DataProvider(name = "processRequestFindBooksByPublishingHousePositiveData")
    public Object[][] createProcessRequestFindBooksByPublishingHousePositiveData() {
        String request = "find_books_by_publishing_house";
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
        Map<String, Object> expected6 = new HashMap<>();
        expected6.put(ResponseKeyName.FILTERED_BOOKS, filteredBooks6);
        return new Object[][]{
                {request, data1, expected1},
                {request, data2, expected2},
                {request, data3, expected3},
                {request, data4, expected4},
                {request, data5, expected5},
                {request, data6, expected6}
        };
    }

    @Test(dataProvider = "processRequestFindBooksByPublishingHousePositiveData")
    public void processRequestFindBooksByPublishingHousePositiveTest(
            String request, Map<String, String> data, Map<String, Object> expected) {
        Map<String, Object> actual = bookController.processRequest(request, data);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "processRequestFindBooksByPublishingHouseNegativeData")
    public Object[][] createProcessRequestFindBooksByPublishingHouseNegativeData() {
        String request = "find_books_by_publishing_house";
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
                {request, data1, expected1},
                {request, data2, expected2},
                {request, data3, expected3},
                {request, data4, expected4}
        };
    }

    @Test(dataProvider = "processRequestFindBooksByPublishingHouseNegativeData")
    public void processRequestFindBooksByPublishingHouseNegativeTest(
            String request, Map<String, String> data, Map<String, Object> expected) {
        Map<String, Object> actual = bookController.processRequest(request, data);
        assertNotEquals(actual, expected);
    }

    @DataProvider(name = "processRequestFindBooksByPublishingYearIntervalPositiveData")
    public Object[][] createProcessRequestFindBooksByPublishingYearIntervalPositiveData() {
        String request = "find_books_by_publishing_year_interval";
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
                {request, data1, expected1},
                {request, data2, expected2},
                {request, data3, expected3},
                {request, data4, expected4},
                {request, data5, expected5},
        };
    }

    @Test(dataProvider = "processRequestFindBooksByPublishingYearIntervalPositiveData")
    public void processRequestFindBooksByPublishingYearIntervalPositiveTest(
            String request, Map<String, String> data, Map<String, Object> expected) {
        Map<String, Object> actual = bookController.processRequest(request, data);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "processRequestFindBooksByPublishingYearIntervalNegativeData")
    public Object[][] createProcessRequestFindBooksByPublishingYearIntervalNegativeData() {
        String request = "find_books_by_publishing_year_interval";
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
                {request, data1, expected1},
                {request, data2, expected2},
                {request, data3, expected3},
                {request, data4, expected4}
        };
    }

    @Test(dataProvider = "processRequestFindBooksByPublishingYearIntervalNegativeData")
    public void processRequestFindBooksByPublishingYearIntervalNegativeTest(
            String request, Map<String, String> data, Map<String, Object> expected) {
        Map<String, Object> actual = bookController.processRequest(request, data);
        assertNotEquals(actual, expected);
    }
}
