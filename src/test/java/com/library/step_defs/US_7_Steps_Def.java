package com.library.step_defs;

import com.library.pages.BookPage;
import com.library.pages.BooksPage;
import com.library.pages.BorrowingBooksPage;
import com.library.pages.LoginPage;
import com.library.utilities.BrowserUtils;
import com.library.utilities.ConfigurationReader;
import com.library.utilities.DB_Util;
import com.library.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;

import javax.swing.*;
import java.util.Map;

public class US_7_Steps_Def {
    LoginPage loginPage = new LoginPage();
    BookPage bookPage = new BookPage();
    BorrowingBooksPage borrowingBooksPage = new BorrowingBooksPage();

    String bookName;

    @Given("I login as a student")
    public void i_login_as_a_student() {
        loginPage.login(ConfigurationReader.getProperty("student_username"), ConfigurationReader.getProperty("student_password"));
    }

    @Given("I search book name called {string}")
    public void i_search_book_name_called(String bookName) {
        this.bookName = bookName;
        bookPage.searchBox.sendKeys(bookName);
        bookPage.expandSearchResult("50");
        BrowserUtils.waitFor(1);

    }

    @When("I click Borrow Book")
    public void i_click_borrow_book() {
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        js.executeScript("arguments[0].scrollIntoView(true);", bookPage.borrowBook);
        js.executeScript("arguments[0].click();", bookPage.borrowBook);

       //bookPage.borrowBook.click();
    }

    @Then("verify that book is shown in {string} page")
    public void verify_that_book_is_shown_in_borrowing_books_page(String pageName) {
        bookPage.navigateMenu(pageName);
        Assertions.assertEquals(bookName, borrowingBooksPage.bookToReturn.getText());



    }

    @Then("verify logged student has same book in database")
    public void verify_logged_student_has_same_book_in_database() {

        DB_Util.runQuery("select full_name, b.name, bb.borrowed_date\n" +
                "from users u\n" +
                "         inner join book_borrow bb on u.id = bb.user_id\n" +
                "         inner join books b on bb.book_id = b.id\n" +
                "where full_name = 'Test Student 3'\n" +
                "order by 3 desc;");

        Map<String, String> firstRow = DB_Util.getRowMap(1);

        Assertions.assertEquals(bookName,firstRow.get("name"));

       borrowingBooksPage.returnBookByName(bookName);
    }
}
