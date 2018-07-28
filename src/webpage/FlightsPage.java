package webpage;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import generics.TestBase;
import generics.Utils;

public class FlightsPage {
	WebDriver driver;

	public FlightsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "flight-origin")
	private WebElement OriginTextBox;

	@FindBy(id = "flight-destination")
	private WebElement DestinationTextBox;

	@FindBy(id = "flight-departing")
	private WebElement departingDateTextBox;

	@FindBy(id = "flight-returning")
	private WebElement returningDateTextBox;

	@FindBy(id = "search-button")
	private WebElement searchButton;

	@FindBy(css=".secondary-content.no-wrap")
	private List<WebElement> searchedFlightResults;

	public WebElement getOriginTextBox() {
		return OriginTextBox;
	}

	public WebElement getDestinationTextBox() {
		return DestinationTextBox;
	}

	public WebElement getDepartingDateTextBox() {
		return departingDateTextBox;
	}

	public WebElement getReturningDateTextBox() {
		return returningDateTextBox;
	}

	public WebElement getSearchButton() {
		return searchButton;
	}

	public List<WebElement> getSearchedFlightResults() {
		return searchedFlightResults;
	}

}