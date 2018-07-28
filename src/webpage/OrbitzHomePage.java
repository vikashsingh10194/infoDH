package webpage;

import org.openqa.selenium.WebDriver;	
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrbitzHomePage {
	
	public OrbitzHomePage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "a#primary-header-flight")
	private WebElement flightsLink;

	public WebElement getFlightsLink() {
		return flightsLink;
	}

}
