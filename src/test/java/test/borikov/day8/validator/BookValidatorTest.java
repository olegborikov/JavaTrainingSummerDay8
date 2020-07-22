package test.borikov.day8.validator;

import com.borikov.day8.validator.BookValidator;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

public class BookValidatorTest {
    private BookValidator bookValidator;

    @BeforeClass
    public void setUp() {
        bookValidator = new BookValidator();
    }

    @AfterClass
    public void tearDown() {
        bookValidator = null;
    }

    @DataProvider(name = "isIdCorrectPositiveData")
    public Object[][] createIsIdCorrectPositiveData() {
        return new Object[][]{
                {1},
                {100_000},
                {123}
        };
    }

    @Test(dataProvider = "isIdCorrectPositiveData")
    public void isIdCorrectPositiveTest(long id) {
        boolean actual = bookValidator.isIdCorrect(id);
        assertTrue(actual);
    }

    @DataProvider(name = "isIdCorrectNegativeData")
    public Object[][] createIsIdCorrectNegativeData() {
        return new Object[][]{
                {0},
                {100_001},
                {-123}
        };
    }

    @Test(dataProvider = "isIdCorrectNegativeData")
    public void isIdCorrectNegativeTest(long id) {
        boolean actual = bookValidator.isIdCorrect(id);
        assertFalse(actual);
    }

    @DataProvider(name = "isNameCorrectPositiveData")
    public Object[][] createIsNameCorrectPositiveData() {
        return new Object[][]{
                {"Я, ты"},
                {"War and peace part 1"},
                {"Я"},
                {"\"Любовь\" - this is ... or not?"},
                {"this is long line that have 40 symbols.."}
        };
    }

    @Test(dataProvider = "isNameCorrectPositiveData")
    public void isNameCorrectPositiveTest(String name) {
        boolean actual = bookValidator.isNameCorrect(name);
        assertTrue(actual);
    }

    @DataProvider(name = "isNameCorrectNegativeData")
    public Object[][] createIsNameCorrectNegativeData() {
        return new Object[][]{
                {""},
                {"    "},
                {"this is very long line that have 43 symbols"},
                {null}
        };
    }

    @Test(dataProvider = "isNameCorrectNegativeData")
    public void isNameCorrectNegativeTest(String name) {
        boolean actual = bookValidator.isNameCorrect(name);
        assertFalse(actual);
    }

    @DataProvider(name = "isPublishingYearCorrectPositiveData")
    public Object[][] createIsPublishingYearCorrectPositiveData() {
        return new Object[][]{
                {1},
                {1999},
                {2020}
        };
    }

    @Test(dataProvider = "isPublishingYearCorrectPositiveData")
    public void isPublishingYearCorrectPositiveTest(int publishingYear) {
        boolean actual = bookValidator.isPublishingYearCorrect(publishingYear);
        assertTrue(actual);
    }

    @DataProvider(name = "isPublishingYearCorrectNegativeData")
    public Object[][] createIsPublishingYearCorrectNegativeData() {
        return new Object[][]{
                {0},
                {-123},
                {2021}
        };
    }

    @Test(dataProvider = "isPublishingYearCorrectNegativeData")
    public void isPublishingYearCorrectNegativeTest(int publishingYear) {
        boolean actual = bookValidator.isPublishingYearCorrect(publishingYear);
        assertFalse(actual);
    }

    @DataProvider(name = "isPublishingHouseCorrectPositiveData")
    public Object[][] createIsPublishingHouseCorrectPositiveData() {
        return new Object[][]{
                {"\"Минск\" печать"},
                {"Московская печатная студия"},
                {"Москва\\Питер"},
                {"Я"},
                {"Минское. Независимое. Печатное. агенство"}
        };
    }

    @Test(dataProvider = "isPublishingHouseCorrectPositiveData")
    public void isPublishingHouseCorrectPositiveTest(String publishingHouse) {
        boolean actual = bookValidator.isPublishingHouseCorrect(publishingHouse);
        assertTrue(actual);
    }

    @DataProvider(name = "isPublishingHouseCorrectNegativeData")
    public Object[][] createIsPublishingHouseCorrectNegativeData() {
        return new Object[][]{
                {"   "},
                {null},
                {""},
                {"Минское государственное независимое печатное агенство"}
        };
    }

    @Test(dataProvider = "isPublishingHouseCorrectNegativeData")
    public void isPublishingHouseCorrectNegativeTest(String publishingHouse) {
        boolean actual = bookValidator.isPublishingHouseCorrect(publishingHouse);
        assertFalse(actual);
    }

    @DataProvider(name = "isAuthorsCorrectPositiveData")
    public Object[][] createIsAuthorsCorrectPositiveData() {
        List<String> authors1 = new ArrayList<>();
        authors1.add("Олег");
        authors1.add("Oleg");
        List<String> authors2 = new ArrayList<>();
        authors2.add("Alex");
        authors2.add("Дунин-Марцинкевич В.Дунин-Марцинкевич В.");
        List<String> authors3 = new ArrayList<>();
        authors3.add("Я");
        List<String> authors4 = new ArrayList<>();
        authors4.add("Qwerty");
        authors4.add("Qwerty");
        authors4.add("Qwerty");
        authors4.add("Qwerty");
        authors4.add("Qwerty");
        authors4.add("Qwerty");
        authors4.add("Qwerty");
        authors4.add("Qwerty");
        authors4.add("Qwerty");
        authors4.add("Qwerty");
        return new Object[][]{
                {authors1},
                {authors2},
                {authors3},
                {authors4}
        };
    }

    @Test(dataProvider = "isAuthorsCorrectPositiveData")
    public void isAuthorsCorrectPositiveTest(List<String> authors) {
        boolean actual = bookValidator.isAuthorsCorrect(authors);
        assertTrue(actual);
    }

    @DataProvider(name = "isAuthorsCorrectNegativeData")
    public Object[][] createIsAuthorsCorrectNegativeData() {
        List<String> authors1 = new ArrayList<>();
        authors1.add(null);
        authors1.add("Oleg");
        List<String> authors2 = new ArrayList<>();
        authors2.add("this is very long line that have 43 symbols");
        List<String> authors3 = new ArrayList<>();
        authors3.add("");
        authors3.add("Oleg");
        List<String> authors4 = new ArrayList<>();
        authors4.add("Qwerty");
        authors4.add("Qwerty");
        authors4.add("Qwerty");
        authors4.add("Qwerty");
        authors4.add("Qwerty");
        authors4.add("Qwerty");
        authors4.add("Qwerty");
        authors4.add("Qwerty");
        authors4.add("Qwerty");
        authors4.add("Qwerty");
        authors4.add("Qwerty");
        return new Object[][]{
                {authors1},
                {authors2},
                {authors3},
                {authors4},
                {null}
        };
    }

    @Test(dataProvider = "isAuthorsCorrectNegativeData")
    public void isAuthorsCorrectNegativeTest(List<String> authors) {
        boolean actual = bookValidator.isAuthorsCorrect(authors);
        assertFalse(actual);
    }

    @DataProvider(name = "isAuthorCorrectPositiveData")
    public Object[][] createIsAuthorCorrectPositiveData() {
        return new Object[][]{
                {"Олег"},
                {"Qwerty"},
                {"Alex"},
                {"Дунин-Марцинкевич В.Дунин-Марцинкевич В."},
                {"Я"},
        };
    }

    @Test(dataProvider = "isAuthorCorrectPositiveData")
    public void isAuthorCorrectPositiveTest(String author) {
        boolean actual = bookValidator.isAuthorCorrect(author);
        assertTrue(actual);
    }

    @DataProvider(name = "isAuthorCorrectNegativeData")
    public Object[][] createIsAuthorCorrectNegativeData() {
        return new Object[][]{
                {"    "},
                {null},
                {""},
                {"Дунин-Марцинкевич ВикентийДунин-Марцинкевич Викентий"}
        };
    }

    @Test(dataProvider = "isAuthorCorrectNegativeData")
    public void isAuthorCorrectNegativeTest(String author) {
        boolean actual = bookValidator.isAuthorCorrect(author);
        assertFalse(actual);
    }
}
