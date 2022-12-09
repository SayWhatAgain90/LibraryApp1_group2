package com.library.pages;

import com.library.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BorrowingBooksPage extends BasePage{

    public BorrowingBooksPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    public void returnBookByName(String bookName){
        String xpath = "//td[.='NOT RETURNED ']/preceding-sibling::td[.='"+bookName+"']/../td/a";
        Driver.getDriver().findElement(By.xpath(xpath)).click();
    }

    @FindBy(xpath = "//td[.='NOT RETURNED ']/preceding-sibling::td[4]")
    public WebElement bookToReturn;
}
