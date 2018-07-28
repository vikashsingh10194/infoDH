package generics;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

public class Utils {

	public static String getPropertyValue(String pathOfPropertyFile, String key) {
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream(pathOfPropertyFile));
		} catch (Exception e) {
			e.printStackTrace();
		}

		final String value = properties.getProperty(key);
		return value;
	}

	public static void takeScreenshot(WebDriver driver, String fileName) {
		TakesScreenshot t = ((TakesScreenshot) driver);
		File srcFile = t.getScreenshotAs(OutputType.FILE);
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("dd_MM_yyyy&hh_MM_ss");
		String dateWithFormat = format.format(date);
		File destFile = new File("screenshots/" + fileName + "_" + dateWithFormat + ".png");
		try {
			FileUtils.copyFile(srcFile, destFile);
			Reporter.log("Screenshot saved to " + destFile.getAbsolutePath(), true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getFutureDate(String addDayInTodayDate) {
		Date m = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(m);
		cal.add(Calendar.DATE, Integer.parseInt(addDayInTodayDate)); // 10 is the days you want to add or subtract
		m = cal.getTime();
		return new SimpleDateFormat("MM/dd/yyyy").format(m);
	}

}