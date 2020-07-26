package test.borikov.day8.model.service.impl;

import com.borikov.day8.exception.ServiceException;
import com.borikov.day8.model.entity.Book;
import com.borikov.day8.model.service.BookService;
import com.borikov.day8.model.service.impl.BookServiceImpl;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test.borikov.day8.creator.BookStorage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.testng.Assert.*;

public class BookServiceImplTest {
    private BookService bookService;
    private BookStorage bookStorage;

    @BeforeClass
    public void setUpClass() {
        bookService = new BookServiceImpl();
        bookStorage = BookStorage.getInstance();
    }

    @AfterClass
    public void tearDownClass() {
        bookService = null;
        bookStorage = null;
    }

    @Test(priority = 1)
    public void addBookPositiveTest() {
        try {
            boolean actual = bookService.addBook("мир", "2000", "Минск", Arrays.asList("Alex"));
            assertTrue(actual);
        } catch (ServiceException e) {
            fail("Incorrect input");
        }
    }

    @DataProvider(name = "addBookNegativeData")
    public Object[][] createAddBookNegativeData() {
        String name1 = "мир";
        String publishingYear1 = "2020";
        String publishingHouse1 = "Минск";
        List<String> authors1 =
                Arrays.asList("This is very very long line with 43 symbols");
        String name2 = "This is very very long line with 43 symbols";
        String publishingYear2 = "2020";
        String publishingHouse2 = "Минск";
        List<String> authors2 = Arrays.asList("Лев");
        String name3 = "мир";
        String publishingYear3 = "2020";
        String publishingHouse3 = "This is very very long line with 43 symbols";
        List<String> authors3 = Arrays.asList("Лев");
        String name4 = "мир";
        String publishingYear4 = "ac";
        String publishingHouse4 = "Минск";
        List<String> authors4 = Arrays.asList("Лев");
        return new Object[][]{
                {name1, publishingYear1, publishingHouse1, authors1},
                {name2, publishingYear2, publishingHouse2, authors2},
                {name3, publishingYear3, publishingHouse3, authors3},
                {name4, publishingYear4, publishingHouse4, authors4},
        };
    }

    @Test(dataProvider = "addBookNegativeData")
    public void addNegativeTest(String name, String publishingYear, String publishingHouse, List<String> authors) {
        try {
            boolean actual = bookService.addBook(name, publishingYear, publishingHouse, authors);
            assertFalse(actual);
        } catch (ServiceException e) {
            fail("Incorrect input");
        }
    }

    @Test(priority = 2)
    public void updateBookPositiveTest() {
        try {
            List<Book> books = bookService.findAllBooks();
            Long id = books.get(books.size() - 1).getBookId();
            boolean actual = bookService.updateBook(id.toString(), "Qwe", "1920", "Piter", new ArrayList<>());
            assertTrue(actual);
        } catch (ServiceException e) {
            fail("Incorrect input");
        }
    }

    @DataProvider(name = "updateBookNegativeData")
    public Object[][] createUpdateBookNegativeData() throws ServiceException {
        String id1 = "4";
        String name1 = "мир";
        String publishingYear1 = "2020";
        String publishingHouse1 = "Минск";
        List<String> authors1 =
                Arrays.asList("This is very very long line with 43 symbols");
        String id2 = "4";
        String name2 = "This is very very long line with 43 symbols";
        String publishingYear2 = "2020";
        String publishingHouse2 = "Минск";
        List<String> authors2 = Arrays.asList("Лев");
        String id3 = "4";
        String name3 = "мир";
        String publishingYear3 = "2020";
        String publishingHouse3 = "This is very very long line with 43 symbols";
        List<String> authors3 = Arrays.asList("Лев");
        String id4 = "4";
        String name4 = "мир";
        String publishingYear4 = "ac";
        String publishingHouse4 = "Минск";
        List<String> authors4 = Arrays.asList("Лев");
        String id5 = "4.3dd";
        String name5 = "мир";
        String publishingYear5 = "2020";
        String publishingHouse5 = "Минск";
        List<String> authors5 = Arrays.asList("Лев");
        return new Object[][]{
                {id1, name1, publishingYear1, publishingHouse1, authors1},
                {id2, name2, publishingYear2, publishingHouse2, authors2},
                {id3, name3, publishingYear3, publishingHouse3, authors3},
                {id4, name4, publishingYear4, publishingHouse4, authors4},
                {id5, name5, publishingYear5, publishingHouse5, authors5}
        };
    }

    @Test(dataProvider = "updateBookNegativeData")
    public void updateBookNegativeTest(String id, String name, String publishingYear, String publishingHouse, List<String> authors) {
        try {
            boolean actual = bookService.updateBook(id, name, publishingYear, publishingHouse, authors);
            assertFalse(actual);
        } catch (ServiceException e) {
            fail("Incorrect input");
        }
    }

    @Test(priority = 3)
    public void removeBookPositiveTest() {
        try {
            List<Book> books = bookService.findAllBooks();
            Long id = books.get(books.size() - 1).getBookId();
            boolean actual = bookService.removeBook(id.toString());
            assertTrue(actual);
        } catch (ServiceException e) {
            fail("Incorrect input");
        }
    }

    @DataProvider(name = "removeBookNegativeData")
    public Object[][] createRemoveBookNegativeData() throws ServiceException {
        List<Book> books = bookService.findAllBooks();
        Long id1 = books.get(books.size() - 1).getBookId() + 1;
        return new Object[][]{
                {"name"},
                {"100_000_000"},
                {"2002.1"},
                {id1.toString()},
        };
    }

    @Test(dataProvider = "removeBookNegativeData")
    public void removeBookNegativeTest(String id) {
        try {
            boolean actual = bookService.removeBook(id);
            assertFalse(actual);
        } catch (ServiceException e) {
            fail("Incorrect input");
        }
    }

    @Test
    public void findAllBooksPositiveTest() {
        try {
            List<Book> actual = bookService.findAllBooks();
            List<Book> expected = bookStorage.getCreatedBooks();
            assertEquals(actual, expected);
        } catch (ServiceException e) {
            fail("Incorrect input");
        }
    }

    @Test
    public void findAllBooksNegativeTest() {
        try {
            List<Book> actual = bookService.findAllBooks();
            List<Book> expected =
                    new ArrayList<>(bookStorage.getCreatedBooks());
            expected.add(null);
            assertNotEquals(actual, expected);
        } catch (ServiceException e) {
            fail("Incorrect input");
        }
    }

    @DataProvider(name = "findBooksByIdPositiveData")
    public Object[][] createFindBooksByIdPositiveData() {
        String id1 = "1";
        Optional<Book> expected1 =
                Optional.of(bookStorage.getCreatedBooks().get(0));
        String id2 = "9";
        Optional<Book> expected2 =
                Optional.of(bookStorage.getCreatedBooks().get(8));
        String id3 = "11";
        Optional<Book> expected3 = Optional.empty();
        String id4 = "a";
        Optional<Book> expected4 = Optional.empty();
        return new Object[][]{
                {id1, expected1},
                {id2, expected2},
                {id3, expected3},
                {id4, expected4}
        };
    }

    @Test(dataProvider = "findBooksByIdPositiveData")
    public void findBooksByIdPositiveTest(String id, Optional<Book> expected) {
        try {
            Optional<Book> actual = bookService.findBookById(id);
            assertEquals(actual, expected);
        } catch (ServiceException e) {
            fail("Incorrect input");
        }
    }

    @DataProvider(name = "findBooksByIdNegativeData")
    public Object[][] createFindBooksByIdNegativeData() {
        String id1 = "1";
        Optional<Book> expected1 =
                Optional.of(bookStorage.getCreatedBooks().get(3));
        String id2 = "9";
        Optional<Book> expected2 = Optional.empty();
        String id3 = "11";
        return new Object[][]{
                {id1, expected1},
                {id2, expected2},
                {id3, null}
        };
    }

    @Test(dataProvider = "findBooksByIdNegativeData")
    public void findBooksByIdNegativeTest(String id, Optional<Book> expected) {
        try {
            Optional<Book> actual = bookService.findBookById(id);
            assertNotEquals(actual, expected);
        } catch (ServiceException e) {
            fail("Incorrect input");
        }
    }

    @DataProvider(name = "findBooksByNamePositiveData")
    public Object[][] createFindBooksByNamePositiveData() {
        String name1 = "Война и мир";
        List<Book> expected1 = new ArrayList<>();
        expected1.add(bookStorage.getCreatedBooks().get(0));
        expected1.add(bookStorage.getCreatedBooks().get(4));
        String name2 = "Метро 2033";
        List<Book> expected2 = new ArrayList<>();
        expected2.add(bookStorage.getCreatedBooks().get(9));
        String name3 = "";
        List<Book> expected3 = new ArrayList<>();
        expected3.add(bookStorage.getCreatedBooks().get(5));
        expected3.add(bookStorage.getCreatedBooks().get(8));
        expected3.add(bookStorage.getCreatedBooks().get(3));
        expected3.add(bookStorage.getCreatedBooks().get(0));
        expected3.add(bookStorage.getCreatedBooks().get(4));
        expected3.add(bookStorage.getCreatedBooks().get(6));
        expected3.add(bookStorage.getCreatedBooks().get(9));
        expected3.add(bookStorage.getCreatedBooks().get(1));
        expected3.add(bookStorage.getCreatedBooks().get(2));
        expected3.add(bookStorage.getCreatedBooks().get(7));
        String name4 = "я";
        List<Book> expected4 = new ArrayList<>();
        expected4.add(bookStorage.getCreatedBooks().get(6));
        expected4.add(bookStorage.getCreatedBooks().get(2));
        expected4.add(bookStorage.getCreatedBooks().get(7));
        String name5 = "This is very very long line with 43 symbols";
        List<Book> expected5 = new ArrayList<>();
        String name6 = "    ";
        List<Book> expected6 = new ArrayList<>();
        return new Object[][]{
                {name1, expected1},
                {name2, expected2},
                {name3, expected3},
                {name4, expected4},
                {name5, expected5},
                {name6, expected6}
        };
    }

    @Test(dataProvider = "findBooksByNamePositiveData")
    public void findBooksByNamePositiveTest(String name, List<Book> expected) {
        try {
            List<Book> actual = bookService.findBooksByName(name);
            assertEquals(actual, expected);
        } catch (ServiceException e) {
            fail("Incorrect input");
        }
    }

    @DataProvider(name = "findBooksByNameNegativeData")
    public Object[][] createFindBooksByNameNegativeData() {
        String name1 = "Война и мир";
        List<Book> expected1 = new ArrayList<>();
        expected1.add(bookStorage.getCreatedBooks().get(0));
        String name2 = "Метро 2033";
        List<Book> expected2 = new ArrayList<>();
        expected2.add(bookStorage.getCreatedBooks().get(9));
        expected2.add(bookStorage.getCreatedBooks().get(9));
        String name3 = "";
        List<Book> expected3 = new ArrayList<>();
        String name4 = "я";
        List<Book> expected4 = new ArrayList<>();
        expected4.add(bookStorage.getCreatedBooks().get(6));
        return new Object[][]{
                {name1, expected1},
                {name2, expected2},
                {name3, expected3},
                {name4, expected4}
        };
    }

    @Test(dataProvider = "findBooksByNameNegativeData")
    public void findBooksByNameNegativeTest(String name, List<Book> expected) {
        try {
            List<Book> actual = bookService.findBooksByName(name);
            assertNotEquals(actual, expected);
        } catch (ServiceException e) {
            fail("Incorrect input");
        }
    }

    @DataProvider(name = "findBooksByPublishingYearPositiveData")
    public Object[][] createFindBooksByPublishingYearPositiveData() {
        String publishingYear1 = "1000";
        List<Book> expected1 = new ArrayList<>();
        expected1.add(bookStorage.getCreatedBooks().get(2));
        expected1.add(bookStorage.getCreatedBooks().get(6));
        String publishingYear2 = "1984";
        List<Book> expected2 = new ArrayList<>();
        expected2.add(bookStorage.getCreatedBooks().get(0));
        String publishingYear3 = "kj";
        List<Book> expected3 = new ArrayList<>();
        String publishingYear4 = "2025";
        List<Book> expected4 = new ArrayList<>();
        String publishingYear5 = "1";
        List<Book> expected5 = new ArrayList<>();
        expected5.add(bookStorage.getCreatedBooks().get(8));
        expected5.add(bookStorage.getCreatedBooks().get(2));
        expected5.add(bookStorage.getCreatedBooks().get(6));
        expected5.add(bookStorage.getCreatedBooks().get(3));
        expected5.add(bookStorage.getCreatedBooks().get(7));
        expected5.add(bookStorage.getCreatedBooks().get(5));
        expected5.add(bookStorage.getCreatedBooks().get(0));
        expected5.add(bookStorage.getCreatedBooks().get(4));
        expected5.add(bookStorage.getCreatedBooks().get(9));
        return new Object[][]{
                {publishingYear1, expected1},
                {publishingYear2, expected2},
                {publishingYear3, expected3},
                {publishingYear4, expected4},
                {publishingYear5, expected5}
        };
    }

    @Test(dataProvider = "findBooksByPublishingYearPositiveData")
    public void findBooksByPublishingYearPositiveTest(String publishingYear,
                                                      List<Book> expected) {
        try {
            List<Book> actual = bookService.findBooksByPublishingYear(publishingYear);
            assertEquals(actual, expected);
        } catch (ServiceException e) {
            fail("Incorrect input");
        }
    }

    @DataProvider(name = "findBooksByPublishingYearNegativeData")
    public Object[][] createFindBooksByPublishingYearNegativeData() {
        String publishingYear1 = "1000";
        List<Book> expected1 = new ArrayList<>();
        expected1.add(bookStorage.getCreatedBooks().get(6));
        expected1.add(bookStorage.getCreatedBooks().get(2));
        String publishingYear2 = "1984";
        List<Book> expected2 = new ArrayList<>();
        expected2.add(bookStorage.getCreatedBooks().get(0));
        expected2.add(bookStorage.getCreatedBooks().get(0));
        String publishingYear3 = "2021";
        List<Book> expected3 = new ArrayList<>();
        expected3.add(bookStorage.getCreatedBooks().get(9));
        String publishingYear4 = "as";
        List<Book> expected4 = new ArrayList<>();
        expected4.add(bookStorage.getCreatedBooks().get(0));
        String publishingYear5 = "1";
        List<Book> expected5 = new ArrayList<>();
        return new Object[][]{
                {publishingYear1, expected1},
                {publishingYear2, expected2},
                {publishingYear3, expected3},
                {publishingYear4, expected4},
                {publishingYear5, expected5}
        };
    }

    @Test(dataProvider = "findBooksByPublishingYearNegativeData")
    public void findBooksByPublishingYearNegativeTest(String publishingYear,
                                                      List<Book> expected) {
        try {
            List<Book> actual = bookService.findBooksByPublishingYear(publishingYear);
            assertNotEquals(actual, expected);
        } catch (ServiceException e) {
            fail("Incorrect input");
        }
    }

    @DataProvider(name = "findBooksByPublishingHousePositiveData")
    public Object[][] createFindBooksByPublishingHousePositiveData() {
        String publishingHouse1 = "Минск";
        List<Book> expected1 = new ArrayList<>();
        expected1.add(bookStorage.getCreatedBooks().get(0));
        expected1.add(bookStorage.getCreatedBooks().get(1));
        expected1.add(bookStorage.getCreatedBooks().get(6));
        String publishingHouse2 = "Москва";
        List<Book> expected2 = new ArrayList<>();
        expected2.add(bookStorage.getCreatedBooks().get(4));
        expected2.add(bookStorage.getCreatedBooks().get(7));
        String publishingHouse3 = "Витебск";
        List<Book> expected3 = new ArrayList<>();
        String publishingHouse4 = "";
        List<Book> expected4 = new ArrayList<>();
        expected4.add(bookStorage.getCreatedBooks().get(5));
        expected4.add(bookStorage.getCreatedBooks().get(8));
        expected4.add(bookStorage.getCreatedBooks().get(9));
        expected4.add(bookStorage.getCreatedBooks().get(0));
        expected4.add(bookStorage.getCreatedBooks().get(1));
        expected4.add(bookStorage.getCreatedBooks().get(6));
        expected4.add(bookStorage.getCreatedBooks().get(4));
        expected4.add(bookStorage.getCreatedBooks().get(7));
        expected4.add(bookStorage.getCreatedBooks().get(2));
        expected4.add(bookStorage.getCreatedBooks().get(3));
        String publishingHouse5 = "   ";
        List<Book> expected5 = new ArrayList<>();
        String publishingHouse6 = "This is very very long line with 43 symbols";
        List<Book> expected6 = new ArrayList<>();
        return new Object[][]{
                {publishingHouse1, expected1},
                {publishingHouse2, expected2},
                {publishingHouse3, expected3},
                {publishingHouse4, expected4},
                {publishingHouse5, expected5},
                {publishingHouse6, expected6}
        };
    }

    @Test(dataProvider = "findBooksByPublishingHousePositiveData")
    public void findBooksByPublishingHousePositiveTest(String publishingHouse,
                                                       List<Book> expected) {
        try {
            List<Book> actual = bookService.findBooksByPublishingHouse(publishingHouse);
            assertEquals(actual, expected);
        } catch (ServiceException e) {
            fail("Incorrect input");
        }
    }

    @DataProvider(name = "findBooksByPublishingHouseNegativeData")
    public Object[][] createFindBooksByPublishingHouseNegativeData() {
        String publishingHouse1 = "Минск";
        List<Book> expected1 = new ArrayList<>();
        expected1.add(bookStorage.getCreatedBooks().get(0));
        expected1.add(bookStorage.getCreatedBooks().get(1));
        String publishingHouse2 = "Москва";
        List<Book> expected2 = new ArrayList<>();
        expected2.add(bookStorage.getCreatedBooks().get(4));
        expected2.add(bookStorage.getCreatedBooks().get(4));
        String publishingHouse3 = "Витебск";
        List<Book> expected3 = null;
        String publishingHouse4 = "";
        List<Book> expected4 = new ArrayList<>();
        return new Object[][]{
                {publishingHouse1, expected1},
                {publishingHouse2, expected2},
                {publishingHouse3, expected3},
                {publishingHouse4, expected4}
        };
    }

    @Test(dataProvider = "findBooksByPublishingHouseNegativeData")
    public void findBooksByPublishingHouseNegativeTest(String publishingHouse,
                                                       List<Book> expected) {
        try {
            List<Book> actual = bookService.findBooksByPublishingHouse(publishingHouse);
            assertNotEquals(actual, expected);
        } catch (ServiceException e) {
            fail("Incorrect input");
        }
    }

    @DataProvider(name = "findBooksByAuthorPositiveData")
    public Object[][] createFindBooksByAuthorPositiveData() {
        String author1 = "Oleg";
        List<Book> expected1 = new ArrayList<>();
        expected1.add(bookStorage.getCreatedBooks().get(7));
        expected1.add(bookStorage.getCreatedBooks().get(3));
        expected1.add(bookStorage.getCreatedBooks().get(8));
        String author2 = "Sapolsky";
        List<Book> expected2 = new ArrayList<>();
        expected2.add(bookStorage.getCreatedBooks().get(5));
        String author3 = "Robert";
        List<Book> expected3 = new ArrayList<>();
        expected3.add(bookStorage.getCreatedBooks().get(3));
        expected3.add(bookStorage.getCreatedBooks().get(5));
        String author4 = "oleg";
        List<Book> expected4 = new ArrayList<>();
        expected4.add(bookStorage.getCreatedBooks().get(7));
        expected4.add(bookStorage.getCreatedBooks().get(3));
        expected4.add(bookStorage.getCreatedBooks().get(8));
        String author5 = "Alexander";
        List<Book> expected5 = new ArrayList<>();
        String author6 = "";
        List<Book> expected6 = new ArrayList<>();
        expected6.add(bookStorage.getCreatedBooks().get(6));
        expected6.add(bookStorage.getCreatedBooks().get(7));
        expected6.add(bookStorage.getCreatedBooks().get(0));
        expected6.add(bookStorage.getCreatedBooks().get(2));
        expected6.add(bookStorage.getCreatedBooks().get(4));
        expected6.add(bookStorage.getCreatedBooks().get(3));
        expected6.add(bookStorage.getCreatedBooks().get(8));
        expected6.add(bookStorage.getCreatedBooks().get(9));
        expected6.add(bookStorage.getCreatedBooks().get(5));
        expected6.add(bookStorage.getCreatedBooks().get(1));
        String publishingHouse7 = "   ";
        List<Book> expected7 = new ArrayList<>();
        String publishingHouse8 = "This is very very long line with 43 symbols";
        List<Book> expected8 = new ArrayList<>();
        return new Object[][]{
                {author1, expected1},
                {author2, expected2},
                {author3, expected3},
                {author4, expected4},
                {author5, expected5},
                {author6, expected6},
                {publishingHouse7, expected7},
                {publishingHouse8, expected8}
        };
    }

    @Test(dataProvider = "findBooksByAuthorPositiveData")
    public void findBooksByAuthorPositiveTest(String author, List<Book> expected) {
        try {
            List<Book> actual = bookService.findBooksByAuthor(author);
            assertEquals(actual, expected);
        } catch (ServiceException e) {
            fail("Incorrect input");
        }
    }

    @DataProvider(name = "findBooksByAuthorNegativeData")
    public Object[][] createFindBooksByAuthorNegativeData() {
        String author1 = "Oleg";
        List<Book> expected1 = new ArrayList<>();
        expected1.add(bookStorage.getCreatedBooks().get(3));
        expected1.add(bookStorage.getCreatedBooks().get(7));
        expected1.add(bookStorage.getCreatedBooks().get(8));
        String author2 = "Sapolsky";
        List<Book> expected2 = new ArrayList<>();
        String author3 = "Robert";
        List<Book> expected3 = new ArrayList<>();
        expected3.add(bookStorage.getCreatedBooks().get(3));
        expected3.add(bookStorage.getCreatedBooks().get(3));
        String author4 = "oleg";
        List<Book> expected4 = new ArrayList<>();
        String author5 = "Alex";
        List<Book> expected5 = new ArrayList<>();
        String author6 = "";
        List<Book> expected6 = new ArrayList<>();
        return new Object[][]{
                {author1, expected1},
                {author2, expected2},
                {author3, expected3},
                {author4, expected4},
                {author5, expected5},
                {author6, expected6},
        };
    }

    @Test(dataProvider = "findBooksByAuthorNegativeData")
    public void findBooksByAuthorNegativeTest(String author, List<Book> expected) {
        try {
            List<Book> actual = bookService.findBooksByAuthor(author);
            assertNotEquals(actual, expected);
        } catch (ServiceException e) {
            fail("Incorrect input");
        }
    }
}