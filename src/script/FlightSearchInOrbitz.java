package script;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import generics.TestBase;
import generics.Utils;
import webpage.FlightsPage;
import webpage.OrbitzHomePage;

public class FlightSearchInOrbitz extends TestBase {

	@Test
	public void flightSearch() throws InterruptedException {

		// getting the source and destination city form property file
		String sourceCityName = Utils.getPropertyValue(CONFIG_PATH, "SOURCE");
		String destinyCityName = Utils.getPropertyValue(CONFIG_PATH, "DESTINATION");

		OrbitzHomePage oHP = new OrbitzHomePage(driver);

		// printing the Orbitz home page in log
		Reporter.log(driver.getTitle() + "Page is Displayed", true);

		// navigating to search flight page
		oHP.getFlightsLink().click();

		// Entering the source city name in the flight origin text box
		FlightsPage fP = new FlightsPage(driver);
		fP.getOriginTextBox().clear();
		fP.getOriginTextBox().sendKeys(sourceCityName);
		Thread.sleep(4000);
		fP.getOriginTextBox().sendKeys(Keys.ENTER);

		String sourceCity = fP.getOriginTextBox().getAttribute("value");
		String sourceCityCode = sourceCity.substring(sourceCity.indexOf("(") + 1, sourceCity.indexOf("(") + 4);

		// Entering the destination city name in the destination flight text box
		fP.getDestinationTextBox().clear();
		fP.getDestinationTextBox().sendKeys(destinyCityName);
		Thread.sleep(4000);
		fP.getDestinationTextBox().sendKeys(Keys.ENTER);

		String destCity = fP.getDestinationTextBox().getAttribute("value");
		String destCityCode = destCity.substring(destCity.indexOf("(") + 1, destCity.indexOf("(") + 4);

		// selecting departing date
		fP.getDepartingDateTextBox()
				.sendKeys(Utils.getFutureDate(Utils.getPropertyValue(CONFIG_PATH, "DEPARTUREDAYFROMTODAY")));
		fP.getReturningDateTextBox().clear();

		// selecting returning date
		fP.getReturningDateTextBox()
				.sendKeys(Utils.getFutureDate(Utils.getPropertyValue(CONFIG_PATH, "RETURNDAYFROMTODAY")) + Keys.ESCAPE);
		fP.getSearchButton().click();

		// getting all the searched flight details and printing total flights from
		// source to dest.
		List<WebElement> totalFlights = fP.getSearchedFlightResults();
		int noOfFlights = totalFlights.size();
		Reporter.log(String.format("Total flights from %s to %s = %d", sourceCity, destCity, noOfFlights), true);

		// below code to verify the first two flights
		if (noOfFlights > 0) {
			int VerifiedFlights = 0;

			for (WebElement result : totalFlights) {
				String flight = result.getText();
				if (flight.contains(sourceCityCode) && flight.endsWith(destCityCode)) {
					VerifiedFlights++;
					if (VerifiedFlights > 2)
						break;
				}
			}

			// verifying the result of searched flights
			if (VerifiedFlights > 2) {
				Reporter.log("first two searched flight is from " + sourceCityName + " to " + destinyCityName, true);
			} else {
				Assert.fail("first two searched flight is not from " + sourceCityName + " to " + destinyCityName);
			}
		}

		else {
			Reporter.log("Searched flights are not available", true);
		}

	}

}
