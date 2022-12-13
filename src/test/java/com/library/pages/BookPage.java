package com.library.pages;

import com.library.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class BookPage extends BasePage {

    public BookPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//a[@class='btn btn-lg btn-outline btn-primary btn-sm add_book_btn']")
    public WebElement addBook;


    @FindBy(xpath = "//input[@name='name']")
    public WebElement bookName;

    @FindBy(xpath = "//input[@name='isbn']")
    public WebElement isbn;

    @FindBy(xpath = "//input[@name='year']")
    public WebElement year;

    @FindBy(xpath = "//input[@name='author']")
    public WebElement author;

    @FindBy(xpath = "//textarea[@name='description']")
    public WebElement description;

    @FindBy(xpath = "//select[@id='book_group_id']")
    public WebElement categoryDropdown;

    @FindBy(xpath = "//button[@type='submit']")
    public WebElement saveButton;

    @FindBy(css = "input[type='search']")
    public WebElement searchBox;

    @FindBy(xpath = "(//a[@class='btn btn-primary btn-sm  '])[1]")
    public WebElement borrowBook;

    public WebElement editBook(String bookName) {
        return Driver.getDriver().findElement(By.xpath("//td[3][.='" + bookName + "']/../td/a"));
    }

    public void chooseCategory(String categoryName) {
        Select select = new Select(categoryDropdown);

        select.selectByVisibleText(categoryName);
    }

    @FindBy(id = "book_categories")
    public WebElement mainCategoryElement;

    public static List<String> dropdownOptionsAsString(WebElement dropdownElement) {

        Select select = new Select(dropdownElement);

        //List of all ACTUAL month <options> as a WebElement
        List<WebElement> actualOptionsAsWebElement = select.getOptions();

        //List of all ACTUAL month options as a String
        List<String> actualOptionsAsString = new ArrayList<>();

        // with using for loop we will convert each WebElement of options to String wit using getText() method
        // with using add() method we will add each String option in List<String> actual options as String
        for (WebElement each : actualOptionsAsWebElement) {
            actualOptionsAsString.add(each.getText());
        }
        actualOptionsAsString.remove(0);
        return actualOptionsAsString;

    }

    @FindBy (xpath = "//table/tbody/tr/td[3]")
    public WebElement authorName;

    public void expandSearchResult(String numberOfRows){
        // parameter can be only 5,10,15,50,100 or 200
        Select select = new Select(Driver.getDriver().findElement(By.xpath("//select[@name='tbl_books_length']")));

        select.selectByVisibleText(numberOfRows);
    }
}
