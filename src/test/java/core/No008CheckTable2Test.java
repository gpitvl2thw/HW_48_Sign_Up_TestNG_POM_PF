package core;

import java.io.IOException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

// import static org.testng.Assert.assertEquals;
import org.testng.Assert;

public class No008CheckTable2Test {

	public String zapolnStr(String str, int j) {
		String newStr = "";
		int llengthStr = str.length();
		for (byte i = 0; i < j - llengthStr; i++)
			newStr = newStr + "0";
		newStr = newStr + str;
		return newStr;
	} // public void zapolnStr(String str) {

	@BeforeMethod
	public void initialize() {
	} // public void initialize() {

	@DataProvider(name = "test")
	public static String[][] prNumber() throws IOException {
		String strNP = "P1";
		No008CheckTable2 numberChecker = new No008CheckTable2();

		String[][] str = numberChecker.No008CheckTable2ToS2D(strNP);
		return str;
	} // public static Object[][] prNumber() {

	@Test(dataProvider = "test")
	public void TestNumberChecker(String tc, String acResult, String expResult) {
		System.out.println("Tase Case=" + zapolnStr(tc, 5) + "; expResult-"
				+ expResult + "; acResult-" + acResult + "; result-"
				+ expResult.equals(acResult));
		Assert.assertEquals(expResult, acResult);
	} // public void TestNumberChecker(String tc, String acResult, String
}