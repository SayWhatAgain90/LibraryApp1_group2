package com.library.pages;

import com.library.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePage {

    public BasePage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//span[.='Dashboard']")
    public WebElement dashboard;

    @FindBy(xpath = "//span[.='Users']")
    public WebElement users;

    @FindBy(xpath="//span[.='Books']")
    public WebElement books;

    @FindBy(xpath = "//a[@class='navbar-brand']")
    public WebElement library;

    @FindBy(css="a[class='nav-link dropdown-toggle']>span")
    public WebElement accountName;

    @FindBy(xpath = "//a[@class='dropdown-item']")
    public WebElement logoutBtn;

   public void logout(){
       accountName.click();
       logoutBtn.click();
   }

   public void navigateMenu(String option){
       Driver.getDriver().findElement(By.xpath("//span[.='"+option + "']")).click();
   }


}
