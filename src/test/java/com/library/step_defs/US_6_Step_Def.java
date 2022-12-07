package com.library.step_defs;

import com.library.pages.BooksPage;
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

    BooksPage booksPage = new BooksPage();
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
        booksPage.addBook.click();
    }

    @And("the librarian enter book name {string}")
    public void theLibrarianEnterBookName(String name) {
        booksPage.bookName.sendKeys(name);
    }

    @When("the librarian enter ISBN {string}")
    public void theLibrarianEnterISBN(String isbn) {
        booksPage.isbn.sendKeys(isbn);
    }

    @And("the librarian enter year {string}")
    public void theLibrarianEnterYear(String year) {
        booksPage.year.sendKeys(year);
    }

    @When("the librarian enter author {string}")
    public void theLibrarianEnterAuthor(String author) {
        booksPage.author.sendKeys(author);
    }

    @And("the librarian choose the book category {string}")
    public void theLibrarianChooseTheBookCategory(String category) {
        booksPage.chooseCategory(category);
    }

    @And("the librarian click to save changes")
    public void theLibrarianClickToSaveChanges() {
        booksPage.saveButton.click();
        BrowserUtils.waitFor(2);
    }

    @Then("the librarian verify new book by {string}")
    public void theLibrarianVerifyNewBookBy(String expectedBookName) {
        booksPage.searchBox.sendKeys(expectedBookName);
        BrowserUtils.waitFor(2);
        booksPage.editBook(expectedBookName).click();

        String actualBookName = booksPage.bookName.getAttribute("value");
       Assertions.assertEquals(expectedBookName, actualBookName);



    }

    @Then("the librarian verify new book from database by {string}")
    public void theLibrarianVerifyNewBookFromDatabaseBy(String expectedBookName) {
       DB_Util.runQuery("select id, name, isbn, author, added_date from books where name = '" + expectedBookName + "' order by id desc;");

       String actualBookName = DB_Util.getRowMap(1).get("name");

       Assertions.assertEquals(expectedBookName,actualBookName);






    }
}
