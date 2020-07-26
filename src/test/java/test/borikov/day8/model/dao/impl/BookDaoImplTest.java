package test.borikov.day8.model.dao.impl;

import com.borikov.day8.exception.DaoException;
import com.borikov.day8.model.dao.BookDao;
import com.borikov.day8.model.dao.impl.BookDaoImpl;
import com.borikov.day8.model.entity.Book;
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

public class BookDaoImplTest {
    private BookDao bookDao;
    private BookStorage bookStorage;

    @BeforeClass
    public void setUpClass() {
        bookDao = new BookDaoImpl();
        bookStorage = BookStorage.getInstance();
    }

    @AfterClass
    public void tearDownClass() {
        bookDao = null;
        bookStorage = null;
    }

    @Test(priority = 1)
    public void addTest() {
        try {
            Book book = new Book(null, "мир", 2000, "Минск", Arrays.asList("Alex"));
            bookDao.add(book);
            List<Book> actual = bookDao.findAll();
            List<Book> expected = new ArrayList<>(bookStorage.getCreatedBooks());
            expected.add(book);
            assertEquals(actual, expected);
        } catch (DaoException e) {
            fail("Incorrect input");
        }
    }

    @Test(priority = 2)
    public void updatePositiveTest() {
        try {
            List<Book> books = bookDao.findAll();
            long id = books.get(books.size() - 1).getBookId();
            Book newBook = new Book(id, "Qwe", 1920, "Piter", new ArrayList<>());
            bookDao.update(newBook);
            List<Book> actual = bookDao.findAll();
            List<Book> expected = new ArrayList<>(bookStorage.getCreatedBooks());
            expected.add(newBook);
            assertEquals(actual, expected);
        } catch (DaoException e) {
            fail("Incorrect input");
        }
    }

    @Test
    public void updateNegativeTest() {
        try {
            List<Book> books = bookDao.findAll();
            long id = books.get(books.size() - 1).getBookId();
            Book newBook = new Book(id + 1, "Qwe", 1920, "Piter", new ArrayList<>());
            boolean actual = bookDao.update(newBook);
            assertFalse(actual);
        } catch (DaoException e) {
            fail("Incorrect input");
        }
    }

    @Test(priority = 3)
    public void removePositiveTest() {
        try {
            List<Book> books = bookDao.findAll();
            long id = books.get(books.size() - 1).getBookId();
            bookDao.remove(id);
            List<Book> actual = bookDao.findAll();
            List<Book> expected = bookStorage.getCreatedBooks();
            assertEquals(actual, expected);
        } catch (DaoException e) {
            fail("Incorrect input");
        }
    }

    @Test
    public void removeNegativeTest() {
        try {
            List<Book> books = bookDao.findAll();
            long id = books.get(books.size() - 1).getBookId();
            boolean actual = bookDao.remove(id + 1);
            assertFalse(actual);
        } catch (DaoException e) {
            fail("Incorrect input");
        }
    }

    @Test
    public void findAllPositiveTest() {
        try {
            List<Book> actual = bookDao.findAll();
            List<Book> expected = bookStorage.getCreatedBooks();
            assertEquals(actual, expected);
        } catch (DaoException e) {
            fail("Incorrect input");
        }
    }

    @Test
    public void findAllNegativeTest() {
        try {
            List<Book> actual = bookDao.findAll();
            List<Book> expected =
                    new ArrayList<>(bookStorage.getCreatedBooks());
            expected.add(null);
            assertNotEquals(actual, expected);
        } catch (DaoException e) {
            fail("Incorrect input");
        }
    }

    @DataProvider(name = "findByIdPositiveData")
    public Object[][] createFindByIdPositiveData() {
        long id1 = 1;
        Optional<Book> expected1 =
                Optional.of(bookStorage.getCreatedBooks().get(0));
        long id2 = 9;
        Optional<Book> expected2 =
                Optional.of(bookStorage.getCreatedBooks().get(8));
        long id3 = 11;
        Optional<Book> expected3 = Optional.empty();
        return new Object[][]{
                {id1, expected1},
                {id2, expected2},
                {id3, expected3}
        };
    }

    @Test(dataProvider = "findByIdPositiveData")
    public void findByIdPositiveTest(long id, Optional<Book> expected) {
        try {
            Optional<Book> actual = bookDao.findById(id);
            assertEquals(actual, expected);
        } catch (DaoException e) {
            fail("Incorrect input");
        }
    }

    @DataProvider(name = "findByIdNegativeData")
    public Object[][] createFindByIdNegativeData() {
        long id1 = 1;
        Optional<Book> expected1 =
                Optional.of(bookStorage.getCreatedBooks().get(3));
        long id2 = 9;
        Optional<Book> expected2 = Optional.empty();
        long id3 = 11;
        return new Object[][]{
                {id1, expected1},
                {id2, expected2},
                {id3, null}
        };
    }

    @Test(dataProvider = "findByIdNegativeData")
    public void findByIdNegativeTest(long id, Optional<Book> expected) {
        try {
            Optional<Book> actual = bookDao.findById(id);
            assertNotEquals(actual, expected);
        } catch (DaoException e) {
            fail("Incorrect input");
        }
    }

    @DataProvider(name = "findByNamePositiveData")
    public Object[][] createFindByNamePositiveData() {
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
        return new Object[][]{
                {name1, expected1},
                {name2, expected2},
                {name3, expected3},
                {name4, expected4}
        };
    }

    @Test(dataProvider = "findByNamePositiveData")
    public void findByNamePositiveTest(String name, List<Book> expected) {
        try {
            List<Book> actual = bookDao.findByName(name);
            assertEquals(actual, expected);
        } catch (DaoException e) {
            fail("Incorrect input");
        }
    }

    @DataProvider(name = "findByNameNegativeData")
    public Object[][] createFindByNameNegativeData() {
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

    @Test(dataProvider = "findByNameNegativeData")
    public void findByNameNegativeTest(String name, List<Book> expected) {
        try {
            List<Book> actual = bookDao.findByName(name);
            assertNotEquals(actual, expected);
        } catch (DaoException e) {
            fail("Incorrect input");
        }
    }

    @DataProvider(name = "findByPublishingYearPositiveData")
    public Object[][] createFindByPublishingYearPositiveData() {
        int publishingYear1 = 1000;
        List<Book> expected1 = new ArrayList<>();
        expected1.add(bookStorage.getCreatedBooks().get(2));
        expected1.add(bookStorage.getCreatedBooks().get(6));
        int publishingYear2 = 1984;
        List<Book> expected2 = new ArrayList<>();
        expected2.add(bookStorage.getCreatedBooks().get(0));
        int publishingYear3 = 2015;
        List<Book> expected3 = new ArrayList<>();
        expected3.add(bookStorage.getCreatedBooks().get(9));
        int publishingYear4 = 2016;
        List<Book> expected4 = new ArrayList<>();
        int publishingYear5 = 1;
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

    @Test(dataProvider = "findByPublishingYearPositiveData")
    public void findByPublishingYearPositiveTest(int publishingYear,
                                                 List<Book> expected) {
        try {
            List<Book> actual = bookDao.findByPublishingYear(publishingYear);
            assertEquals(actual, expected);
        } catch (DaoException e) {
            fail("Incorrect input");
        }
    }

    @DataProvider(name = "findByPublishingYearNegativeData")
    public Object[][] createFindByPublishingYearNegativeData() {
        int publishingYear1 = 1000;
        List<Book> expected1 = new ArrayList<>();
        expected1.add(bookStorage.getCreatedBooks().get(6));
        expected1.add(bookStorage.getCreatedBooks().get(2));
        int publishingYear2 = 1984;
        List<Book> expected2 = new ArrayList<>();
        expected2.add(bookStorage.getCreatedBooks().get(0));
        expected2.add(bookStorage.getCreatedBooks().get(0));
        int publishingYear3 = 2021;
        List<Book> expected3 = new ArrayList<>();
        expected3.add(bookStorage.getCreatedBooks().get(9));
        int publishingYear4 = 2016;
        List<Book> expected4 = new ArrayList<>();
        expected4.add(bookStorage.getCreatedBooks().get(0));
        int publishingYear5 = 1;
        List<Book> expected5 = new ArrayList<>();
        return new Object[][]{
                {publishingYear1, expected1},
                {publishingYear2, expected2},
                {publishingYear3, expected3},
                {publishingYear4, expected4},
                {publishingYear5, expected5}
        };
    }

    @Test(dataProvider = "findByPublishingYearNegativeData")
    public void findByPublishingYearNegativeTest(int publishingYear,
                                                 List<Book> expected) {
        try {
            List<Book> actual = bookDao.findByPublishingYear(publishingYear);
            assertNotEquals(actual, expected);
        } catch (DaoException e) {
            fail("Incorrect input");
        }
    }

    @DataProvider(name = "findByPublishingHousePositiveData")
    public Object[][] createFindByPublishingHousePositiveData() {
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
        return new Object[][]{
                {publishingHouse1, expected1},
                {publishingHouse2, expected2},
                {publishingHouse3, expected3},
                {publishingHouse4, expected4}
        };
    }

    @Test(dataProvider = "findByPublishingHousePositiveData")
    public void findByPublishingHousePositiveTest(String publishingHouse,
                                                  List<Book> expected) {
        try {
            List<Book> actual = bookDao.findByPublishingHouse(publishingHouse);
            assertEquals(actual, expected);
        } catch (DaoException e) {
            fail("Incorrect input");
        }
    }

    @DataProvider(name = "findByPublishingHouseNegativeData")
    public Object[][] createFindByPublishingHouseNegativeData() {
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

    @Test(dataProvider = "findByPublishingHouseNegativeData")
    public void findByPublishingHouseNegativeTest(String publishingHouse,
                                                  List<Book> expected) {
        try {
            List<Book> actual = bookDao.findByPublishingHouse(publishingHouse);
            assertNotEquals(actual, expected);
        } catch (DaoException e) {
            fail("Incorrect input");
        }
    }

    @DataProvider(name = "findByAuthorPositiveData")
    public Object[][] createFindByAuthorPositiveData() {
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
        return new Object[][]{
                {author1, expected1},
                {author2, expected2},
                {author3, expected3},
                {author4, expected4},
                {author5, expected5},
                {author6, expected6},
        };
    }

    @Test(dataProvider = "findByAuthorPositiveData")
    public void findByAuthorPositiveTest(String author, List<Book> expected) {
        try {
            List<Book> actual = bookDao.findByAuthor(author);
            assertEquals(actual, expected);
        } catch (DaoException e) {
            fail("Incorrect input");
        }
    }

    @DataProvider(name = "findByAuthorNegativeData")
    public Object[][] createFindByAuthorNegativeData() {
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

    @Test(dataProvider = "findByAuthorNegativeData")
    public void findByAuthorNegativeTest(String author, List<Book> expected) {
        try {
            List<Book> actual = bookDao.findByAuthor(author);
            assertNotEquals(actual, expected);
        } catch (DaoException e) {
            fail("Incorrect input");
        }
    }
}