package com.owen;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

@RunWith(BlockJUnit4ClassRunner.class)
public class BaseTest {

    private static PhantomJSDriverService service;

    private static File configFile = new File("config.properties");

    protected WebDriver driver;

    @BeforeClass
    public static void createAndStartService() throws IOException {

        FileReader configReader = new FileReader(configFile);
        Properties config = new Properties();
        config.load(configReader);
        String phantomJSExecutable = config.getProperty("phantomJSExecutable");
        String phantomJSPort = config.getProperty("phantomJSPort");

        service = new PhantomJSDriverService.Builder().
                withLogFile(null)
                .usingCommandLineArguments(null)
                .usingPhantomJSExecutable(new File(phantomJSExecutable))
                .usingPort(Integer.parseInt(phantomJSPort))
                .build();

        service.start();

    }

    @AfterClass
    public static void createAndStopService() {
        service.stop();
    }

    @Before
    public void createDriver() {
        this.driver = new RemoteWebDriver(service.getUrl(), DesiredCapabilities.phantomjs());
    }

    @After
    public void quitDriver() {
        this.driver.quit();
    }

}
