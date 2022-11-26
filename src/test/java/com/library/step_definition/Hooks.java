package com.library.step_definition;


import com.library.utilities.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Hooks {
    //@Before

    public void setupScenario(){
        System.out.println("Setting up browser using cucumber @Before each scenario");
    }


    @After
    public void teardownScenario(Scenario scenario){
        //if (scenario.isFailed()) {
            byte[] screenShot = ((TakesScreenshot) Driver.getDriverPool()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenShot, "image/png", scenario.getName());
        //}


        Driver.closeDriver();
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
