package com.library.step_defs;

import com.library.pages.BookPage;
import com.library.utilities.BrowserUtils;
import com.library.utilities.DB_Util;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class US4_StepDef {

    BookPage bookPage = new BookPage();
    @When("I open book {string}")
    public void i_open_book(String string) {

        bookPage.searchBox.sendKeys(string);
        BrowserUtils.sleep(2);

    }
    @Then("book information must match the Database")
    public void book_information_must_match_the_database() {

        DB_Util.createConnection();
        DB_Util.runQuery("select name, author, year from books\n" +
                "where name = 'Chordeiles minor';");

        String actualResult = DB_Util.getFirstRowFirstColumn();
        String expected = bookPage.authorName.getText();

        Assertions.assertEquals(expected,actualResult);

    }
}
