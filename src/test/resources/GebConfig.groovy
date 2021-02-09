import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.firefox.FirefoxOptions
import org.openqa.selenium.remote.RemoteWebDriver

waiting {
	timeout = 2
}

environments {
	// run via “./gradlew chromeTest”
	// See: http://code.google.com/p/selenium/wiki/ChromeDriver
	chrome {
		driver = { new ChromeDriver() }
	}

	// run via “./gradlew chromeHeadlessTest”
	// See: http://code.google.com/p/selenium/wiki/ChromeDriver
	chromeHeadless {
		driver = {
			ChromeOptions o = new ChromeOptions()
			o.addArguments('headless')
			new ChromeDriver(o)
		}
	}

	// run via “./gradlew chromeRemoteTest”
	chromeRemote {
		driver = {
			ChromeOptions o = new ChromeOptions()
			def host = System.getenv('SELENIUM_HUB_HOST') ?: 'localhost'
			def port = System.getenv('SELENIUM_HUB_PORT') ?: '4444'
			new RemoteWebDriver(new URL("http://${host}:${port}/wd/hub"), o);
		}
	}
	
	// run via “./gradlew firefoxTest”
	// See: http://code.google.com/p/selenium/wiki/FirefoxDriver
	firefox {
		atCheckWaiting = 1
		driver = { new FirefoxDriver() }
	}

	// run via “./gradlew firefoxRemoteTest”
	firefoxRemote {
		driver = {
			FirefoxOptions o = new FirefoxOptions()
			def host = System.getenv('SELENIUM_HUB_HOST') ?: 'localhost'
			def port = System.getenv('SELENIUM_HUB_PORT') ?: '4444'
			new RemoteWebDriver(new URL("http://${host}:${port}/wd/hub"), o);
		}
	}
}

baseUrl = "https://www.qaware.de"