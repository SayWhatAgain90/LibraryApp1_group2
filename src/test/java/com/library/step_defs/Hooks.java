package com.library.step_defs;


import com.library.utilities.ConfigurationReader;
import com.library.utilities.DB_Util;
import com.library.utilities.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.util.concurrent.TimeUnit;

public class Hooks {
    @Before("@wip")
    public void setupScenario() {
        System.out.println("Setting up browser using cucumber @Before each scenario");
        Driver.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Driver.getDriver().get(ConfigurationReader.getProperty("library_url"));
    }


    @After
    public void teardownScenario(Scenario scenario) {
        //if (scenario.isFailed()) {
        byte[] screenShot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
        scenario.attach(screenShot, "image/png", scenario.getName());
        //}


        Driver.closeDriver();
    }


    @Before("@db")
    public void setUpDB() {
        DB_Util.createConnection();
        System.out.println("Connecting to database...");
    }


    @After("@db")
    public void destroyDB() {
        DB_Util.destroy();
        System.out.println("Closing connection...");
    }


//    //@Before (value = "@login")
//    public void setupForLogin(){
//        // If you want any code to run before any specific feature/scenario,
//        // you can use value= "@tagname" to determine this
//        System.out.println("Setting up browser using cucumber @Before @login scenario");
//    }
//
//    //@Before (value="@db" , order=3)
//    public void setupDatabaseScenario(){
//
//    }
//
//    //@BeforeStep
//    public void setupScenarioStep(){
//        System.out.println("Setting up browser using cucumber @Before each scenario step");
//    }
//
//    // @BeforeStep
//    public void setupScenarioStepForLogin(){
//        System.out.println("Setting up browser using cucumber @Before each scenario step for login");
//    }

}
