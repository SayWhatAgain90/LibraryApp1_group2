package com.library.step_defs;

import com.library.pages.BookPage;
import com.library.pages.LoginPage;
import com.library.utilities.ConfigurationReader;
import com.library.utilities.DB_Util;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class US3_StepDef {

    BookPage bookPage = new BookPage();
    LoginPage loginPage = new LoginPage();
    //ResultSet dbResult;
    List<String> expectedResult;
    @When("I take all book categories in UI")
    public void i_take_all_book_categories_in_ui() {

        expectedResult = bookPage.dropdownOptionsAsString(bookPage.mainCategoryElement);


    }
    @When("I execute query to get book categories")
    public void i_execute_query_to_get_book_categories() {

        DB_Util.createConnection();

        DB_Util.runQuery("select name from book_categories;");

    }
    @Then("verify book categories must match book_categories table from db")
    public void verify_book_categories_must_match_book_categories_table_from_db() {

        List<String> actualDB = DB_Util.getColumnDataAsList(1);

        Assertions.assertEquals(actualDB,  expectedResult, "Assertion has failed");

    }
}
