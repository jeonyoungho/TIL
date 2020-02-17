package com.mjart.block.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ChromeDriverContext {
	private WebDriver driver;
	private static final String CHROME_DRIVER_PATH = "/Library/chromedriver";
	// 
	@Bean
	public WebDriver setupChromeDriver(){ // throws Exception 
		System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);

		ChromeOptions options = new ChromeOptions();
		options.setCapability("ignoreProtectedModeSettings", true);
		
//		options.addArguments("--window-size=1366,768");
//		options.addArguments("--disable-extensions");
//		options.addArguments("--headless");
//		options.addArguments("--disable-gpu");
//		options.addArguments("--no-sandbox");
	//	DesiredCapabilities capabilities = new DesiredCapabilities();
	//	capabilities.setCapability(ChromeOptions.CAPABILITY, options);
	//	capabilities.setCapability("pageLoadStrategy", "none");
	//	options.merge(capabilities);
	
		driver = new ChromeDriver(options);
		return driver;
	}
	
	@Bean
	public WebDriver getDriver() {
		return driver;
	}


}
