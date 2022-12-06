package com.library.step_defs;

import com.library.pages.BookPage;
import com.library.pages.LoginPage;
import com.library.utilities.ConfigurationReader;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

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
    public void theLibrarianEnterAuthor(String arg0) {
    }

    @And("the librarian choose the book category {string}")
    public void theLibrarianChooseTheBookCategory(String arg0) {
    }

    @And("the librarian click to save changes")
    public void theLibrarianClickToSaveChanges() {
    }

    @Then("the librarian verify new book by {string}")
    public void theLibrarianVerifyNewBookBy(String arg0) {
    }

    @Then("the librarian verify new book from database by {string}")
    public void theLibrarianVerifyNewBookFromDatabaseBy(String arg0) {
    }
}
