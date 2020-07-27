package test.borikov.day8.controller.command;

import com.borikov.day8.controller.command.Command;
import com.borikov.day8.controller.command.CommandProvider;
import com.borikov.day8.controller.command.CommandType;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CommandProviderTest {
    private CommandProvider commandProvider;

    @BeforeClass
    public void setUpClass() {
        commandProvider = new CommandProvider();
    }

    @AfterClass
    public void tearDownClass() {
        commandProvider = null;
    }

    @DataProvider(name = "defineCommandPositiveData")
    public Object[][] createDefineCommandPositiveData() {
        String request1 = "ADD_BOOK";
        Command expected1 = CommandType.ADD_BOOK.getCommand();
        String request2 = "find_books_by_publishing_house";
        Command expected2 = CommandType.FIND_BOOKS_BY_PUBLISHING_HOUSE.getCommand();
        String request3 = "AddBook";
        Command expected3 = CommandType.FIND_ALL_BOOKS.getCommand();
        String request4 = null;
        Command expected4 = CommandType.FIND_ALL_BOOKS.getCommand();
        String request5 = "qwe";
        Command expected5 = CommandType.FIND_ALL_BOOKS.getCommand();
        return new Object[][]{
                {request1, expected1},
                {request2, expected2},
                {request3, expected3},
                {request4, expected4},
                {request5, expected5}
        };
    }

    @Test(dataProvider = "defineCommandPositiveData")
    public void defineCommandPositiveTest(String request, Command expected) {
        Command actual = commandProvider.defineCommand(request);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "defineCommandNegativeData")
    public Object[][] createDefineCommandNegativeData() {
        String request1 = "ADD_BOOK";
        Command expected1 = CommandType.FIND_ALL_BOOKS.getCommand();
        String request2 = "FIND_ALLBOOKS";
        Command expected2 = CommandType.FIND_BOOKS_BY_PUBLISHING_HOUSE.getCommand();
        String request3 = "AddBook";
        Command expected3 = CommandType.ADD_BOOK.getCommand();
        String request4 = null;
        Command expected4 = null;
        return new Object[][]{
                {request1, expected1},
                {request2, expected2},
                {request3, expected3},
                {request4, expected4}
        };
    }

    @Test(dataProvider = "defineCommandNegativeData")
    public void defineCommandNegativeTest(String request, Command expected) {
        Command actual = commandProvider.defineCommand(request);
        assertNotEquals(actual, expected);
    }
}
