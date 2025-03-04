package akkiFrameworks.TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer  {

//	The moment we implement the interface our class will be forced to implement all the methods what this interface exposes 
//	Basic duty of class , it has to implement methods exposed by interface 
	
//	Whenever test fails then after completing the listners it will come here to check , does it needs to re run again to make sure
	
//	So no matter whenever test fails after reporting it to the extent report it will also come to this block and checks if it needs to re run
	
	int count = 0;
	int maxTry = 1;
	@Override
	public boolean retry(ITestResult result) {
		if(count < maxTry)
		{
			count++;
			return true;
		}
		return false;
	}
//	As long this method returns true our test will keep on re run again 

	
}
