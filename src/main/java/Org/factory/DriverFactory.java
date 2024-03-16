package Org.factory;

import java.io.File;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.SystemUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class DriverFactory {
	private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	public  WebDriver  init_driver(String browser) {
		try {
			System.out.println("browser value is: " + browser);
			if (browser.equals("chrome")) {
				ChromeOptions options = new ChromeOptions();
				File userHome = SystemUtils.getUserHome();
				System.out.println("User Home: " + userHome.getCanonicalPath());
				String path = new File(userHome + "/download").getCanonicalPath();
				System.out.println("PATH FOR DOWNLOAD:- " + path);
				Map<String, Object> chromePrefs = new HashMap<String, Object>();
				chromePrefs.put("profile.default_content_settings.popups", 0);
				chromePrefs.put("download.default_directory", path);
				chromePrefs.put("download.prompt_for_download", false);
				chromePrefs.put("plugins.plugins_disabled", "Chrome PDF Viewer");
//				options.addArguments("--headless=new");
				options.addArguments("--window-size=1720,1080");
				options.addArguments("--force-device-scale-factor=0.8");
				options.addArguments("--test-type");
				options.addArguments("--disable-gpu");
				options.addArguments("--no-sandbox");
				options.addArguments("--disable-dev-shm-usage");
				options.addArguments("--disable-software-rasterizer");
				options.addArguments("--disable-popup-blocking");
				options.addArguments("--disable-extensions");
				chromePrefs.put("safebrowsing.enabled", "false");
				chromePrefs.put("safebrowsing.disable_download_protection", "true");
				options.setExperimentalOption("prefs", chromePrefs);
				options.addArguments(
						"--user-agent=Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, likeGecko) Chrome/114.0.4606.54 Safari/537.36");
				options.addArguments("--start-maximized");
				options.addArguments("--remote-allow-origins=*");
				options.addArguments("--start-fullscreen");
				tlDriver.set(new ChromeDriver(options));
			} else if (browser.equals("firefox")) {
				tlDriver.set(new FirefoxDriver());
			} else if (browser.equals("safari")) {
				tlDriver.set(new SafariDriver());
			} else if (browser.equals("edge")) {
				tlDriver.set(new EdgeDriver());
			} else {
				System.out.println("Please pass the correct browser value: " + browser);
			}	
			getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(90));
			getDriver().manage().deleteAllCookies();
			getDriver().manage().window().maximize();
			getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
			return getDriver();
		} catch (Exception ex) {
			ex.printStackTrace();
			ex.fillInStackTrace();
			System.out.println("*****************BROWSER ERROR*****************");
			System.out.println(ex.getMessage());
			return null;
		}
	}
	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}
	
}
