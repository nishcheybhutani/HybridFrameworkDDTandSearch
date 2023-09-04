package testBase;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.time.Duration;
import java.util.Collections;
import java.util.ResourceBundle;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.Command;
import org.openqa.selenium.remote.CommandExecutor;
//import org.openqa.selenium.remote.CommandExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.HttpCommandExecutor;
//import org.openqa.selenium.remote.HttpCommandExecutor;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.Response;
import org.openqa.selenium.remote.SessionId;
import org.openqa.selenium.remote.codec.w3c.W3CHttpCommandCodec;
import org.openqa.selenium.remote.codec.w3c.W3CHttpResponseCodec;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	public WebDriver driver;
	public Logger logger;// for Logging
	public ResourceBundle rb;// to read config.properties

	@BeforeClass
	public void setup() {

		rb = ResourceBundle.getBundle("config");// Load config.properties
		logger = LogManager.getLogger(this.getClass());// Dynamically get the class name which where is executing the
														// run time

//		WebDriverManager.chromedriver().setup();
//		driver = new ChromeDriver();
//		logger.info("Launched chrome Browser");

//		HttpCommandExecutor executor = (HttpCommandExecutor) ((RemoteWebDriver) driver).getCommandExecutor();
//	    URL url = executor.getAddressOfRemoteServer();
//	    SessionId session_id = ((RemoteWebDriver) driver).getSessionId();

//	    RemoteWebDriver driver2 = createDriverFromSession(session_id, url);

		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
		logger.info("Launched firefox Browser");

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

	}

	@AfterClass
	public void tearDown() {
		driver.quit();
		System.out.println("browser closed");
	}

	public void Wait() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	public void captureScreen(WebDriver driver, String tname) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File trg = new File(System.getProperty("user.dir") + "//Screenshots//" + tname + ".png");

		FileUtils.copyFile(src, trg);
		System.out.println("SS taken");
	}

//	public static RemoteWebDriver createDriverFromSession(final SessionId sessionId, URL command_executor) {
//		CommandExecutor executor = new HttpCommandExecutor(command_executor) {
//
//			@Override
//			public Response execute(Command command) throws IOException {
//				Response response = null;
//				if (command.getName() == "newSession") {
//					response = new Response();
//					response.setSessionId(sessionId.toString());
//					response.setStatus(0);
//					response.setValue(Collections.<String, String>emptyMap());
//
//					try {
//						Field commandCodec = null;
//						commandCodec = this.getClass().getSuperclass().getDeclaredField("commandCodec");
//						commandCodec.setAccessible(true);
//						commandCodec.set(this, new W3CHttpCommandCodec());
//
//						Field responseCodec = null;
//						responseCodec = this.getClass().getSuperclass().getDeclaredField("responseCodec");
//						responseCodec.setAccessible(true);
//						responseCodec.set(this, new W3CHttpResponseCodec());
//					} catch (NoSuchFieldException e) {
//						e.printStackTrace();
//					} catch (IllegalAccessException e) {
//						e.printStackTrace();
//					}
//
//				} else {
//					response = super.execute(command);
//				}
//				return response;
//			}
//		};
//
//		return new RemoteWebDriver(executor, new DesiredCapabilities());
//	}
}
