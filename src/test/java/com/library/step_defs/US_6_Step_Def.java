package com.library.step_defs;

import com.library.pages.BookPage;
import com.library.pages.LoginPage;
import com.library.utilities.BrowserUtils;
import com.library.utilities.ConfigurationReader;
import com.library.utilities.DB_Util;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

public class US_6_Step_Def {

    LoginPage loginPage = new LoginPage();

    BookPage bookPage = new BookPage();
    @Given("I login as a librarian")
    public void iLoginAsALibrarian() {
        loginPage.login(ConfigurationReader.getProperty("librarian_username"), ConfigurationReader.getProperty("librarian_password"));
    }

    @And("I navigate to {string} page")
    public void iNavigateToPage(String option) {
        loginPage.navigateMenu(option);
    }

    @When("the librarian click to add book")
    public void theLibrarianClickToAddBook() {
        bookPage.addBook.click();
    }

    @And("the librarian enter book name {string}")
    public void theLibrarianEnterBookName(String name) {
        bookPage.bookName.sendKeys(name);
    }

    @When("the librarian enter ISBN {string}")
    public void theLibrarianEnterISBN(String isbn) {
        bookPage.isbn.sendKeys(isbn);
    }

    @And("the librarian enter year {string}")
    public void theLibrarianEnterYear(String year) {
        bookPage.year.sendKeys(year);
    }

    @When("the librarian enter author {string}")
    public void theLibrarianEnterAuthor(String author) {
        bookPage.author.sendKeys(author);
    }

    @And("the librarian choose the book category {string}")
    public void theLibrarianChooseTheBookCategory(String category) {
        bookPage.chooseCategory(category);
    }

    @And("the librarian click to save changes")
    public void theLibrarianClickToSaveChanges() {
        bookPage.saveButton.click();
        BrowserUtils.waitFor(2);
    }

    @Then("the librarian verify new book by {string}")
    public void theLibrarianVerifyNewBookBy(String expectedBookName) {
        bookPage.searchBox.sendKeys(expectedBookName);
        BrowserUtils.waitFor(2);
        bookPage.editBook(expectedBookName).click();

        String actualBookName = bookPage.bookName.getAttribute("value");
       Assertions.assertEquals(expectedBookName, actualBookName);



    }

    @Then("the librarian verify new book from database by {string}")
    public void theLibrarianVerifyNewBookFromDatabaseBy(String expectedBookName) {
       DB_Util.runQuery("select id, name, isbn, author, added_date from books where name = '" + expectedBookName + "' order by id desc;");

       String actualBookName = DB_Util.getRowMap(1).get("name");

       Assertions.assertEquals(expectedBookName,actualBookName);






    }
}
