package generics;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

public class OrbitzListner implements ITestListener {

	@Override
	public void onTestStart(ITestResult result) {
		Reporter.log(result.getName() + " : test starting", true);

	}

	@Override
	public void onTestSuccess(ITestResult result) {

		Reporter.log(result.getName() + " : test passed", true);
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		Reporter.log(result.getName() + " : test failed", true);

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		Reporter.log(result.getName() + " : test skipped", true);

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		Reporter.log(result.getName() + " : Test Failed but within success percentage", true);
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub

	}

}
