package com.library.pages;

import com.library.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class BookPage extends BasePage{

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

    public WebElement editBook(String bookName){
        return Driver.getDriver().findElement(By.xpath("//td[3][.='" + bookName + "']/../td/a"));
    }

    public void chooseCategory(String categoryName){
        Select select = new Select(categoryDropdown);

        select.selectByVisibleText(categoryName);
    }
}
