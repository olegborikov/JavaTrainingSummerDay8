package test.borikov.day8.controller;

import com.borikov.day8.controller.BookController;
import com.borikov.day8.controller.command.impl.FindAllBooksCommand;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import test.borikov.day8.creator.BookStorage;

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
}
