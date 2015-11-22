//BEGIN
package core;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Properties;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class No000ValuesAndMetods {
	// *************************** static ***************************
	private static boolean continueTC = true;
	// для хранения ошибки
	private static String continueErr = null;
	// для подсчета TC
	private static int taseCase = 0;
	// создается каждый раз новыйы
	// private static WebDriver driver = setWebDriver();
	// элементы page #1
	private static final String[] AllElementsP1 = { "url", "title",
			"AllElements", "DisplayedElements", "table", "table2", "table4",
			"table3", "id_reset_button", "id_submit_button" };
	// элементы page #2
	private static final String[] AllElementsP2 = { "url", "title",
			"AllElements", "DisplayedElements", "table", "table2", "table4",
			"table3", "id_back_button" };
	// переменная определяющая каким способом будем искать элемент
	// private static String look = "_look";
	private static String typeWebDriver = setTypeWebDriver();
	// имя properties файла
	// private static String propertiesFile =
	// "./src/main/resources/Test.properties";
	private static String propertiesFileP1 = "./src/main/resources/"
			+ setFileProperties("P1") + ".properties";
	private static String propertiesFileP2 = "./src/main/resources/"
			+ setFileProperties("P2") + ".properties";
	private static Properties propertiesP1 = setProperties(getFileProperties("P1"));
	private static Properties propertiesP2 = setProperties(getFileProperties("P2"));
	// private static String s1dp1ForUrl = setUrl("P1");
	// private static String s1dp2ForUrl = setUrl("P2");
	// LogFile and Quotes
	private static String urlQuotes = "http://learn2test.net/qa/apps/sign_up/v1/quotes.js";

	// для log file
	// private static String loFileName = "log.txt";
	private static PrintStream outPrintStream; // = setPrintStream(loFileName);

	// *************************** static ***************************

	// *************************** static metods***************************
	// continueTC
	// private static boolean continueTC = true;

	public static boolean getContinueTC() {
		return continueTC;
	}

	public static void setContinueTC(boolean b) {
		continueTC = b;
	}

	// continueErr
	public static String getContinueErr() {
		return continueErr;
	}

	public static void setContinueErr(String st) {
		continueErr = st;
	}

	// taseCase
	// private static int taseCase = 0;

	public static String getTaseCase() {
		taseCase = taseCase + 1;
		String tc = "" + taseCase;
		return tc;
	}

	// typeWebDriver
	// private static String typeWebDriver = setTypeWebDriver();

	public static String setTypeWebDriver() {
		return System.getProperties().getProperty("typeWebDriver");
	}

	public static String getTypeWebDriver() {
		return typeWebDriver;
	}

	// WebDriver
	/*
	 * private static WebDriver driver = setWebDriver(); public static WebDriver
	 * getWebDriver() { return driver; }
	 */

	public static WebDriver getWebDriver() {
		// mvn clean site test -Dtest=AllTests -Dbuild.version=1.3.1
		// -DtypeWebDriver=
		WebDriver dr = null;
		String tWD = getTypeWebDriver();

		if ((tWD == null)) {
			// class org.openqa.selenium.htmlunit.HtmlUnitDriver
			dr = new HtmlUnitDriver();
			((HtmlUnitDriver) dr).setJavascriptEnabled(true);
		} else if ((tWD.equals("Firefox")) // ---
				|| (tWD.equals("Fox"))
				|| (tWD.equals("fox"))
				|| (tWD.equals("FirefoxDriver"))) {
			// class org.openqa.selenium.firefox.FirefoxDriver
			dr = new FirefoxDriver();
		} else if ((tWD.equals("ie")) || (tWD.equals("Explorer"))
				|| (tWD.equals("InternetExplorer"))
				|| (tWD.equals("InternetExplorerDriver"))) {
			// class org.openqa.selenium.ie.InternetExplorerDriver;
			dr = new InternetExplorerDriver();
		} else if ((tWD.equals("Chrome")) || (tWD.equals("ChromeDriver"))) {
			// class org.openqa.selenium.chrome.ChromeDriver
			dr = new ChromeDriver();
		} else {
			// class org.openqa.selenium.htmlunit.HtmlUnitDriver
			dr = new HtmlUnitDriver();
			((HtmlUnitDriver) dr).setJavascriptEnabled(true);
		}
		dr.manage().window().maximize();
		return dr;
	}

	// AllElements
	// private static final String[] AllElements = { "url", "title",
	// "AllElements", "DisplayedElements", "email_or_phone_id",
	// "password_id", "login_id", "copyright_xpath", "timeline_xpath",
	// "friends_xpath", "logout_xpath" };

	public static String[] getAllElements(String strNP) {
		if (strNP.equals("P1"))
			return AllElementsP1;
		else if (strNP.equals("P2"))
			return AllElementsP2;
		return null;
	}

	public static String getAllElements(int i, String strNP) {
		if (strNP.equals("P1"))
			return AllElementsP1[i];
		else if (strNP.equals("P2"))
			return AllElementsP2[i];
		return null;
	}

	// start with i and кол-во j elemenets
	public static String[] getAllElements(int ii, int jj, String strNP) {
		String[] stNew = new String[jj];
		for (int j = 0; j < jj; j++) {
			stNew[j] = getAllElements(j + ii, strNP);
		}
		return stNew;
	}

	// Browser
	public static String getBrowser(WebDriver webDr) {
		String qw = webDr.getClass().toString();

		String str = null;
		if (qw.equals("class org.openqa.selenium.htmlunit.HtmlUnitDriver"))
			str = "Htmlunit";
		else if (qw.equals("class org.openqa.selenium.firefox.FirefoxDriver"))
			str = "Firefox";
		else if (qw
				.equals("class org.openqa.selenium.ie.InternetExplorerDriver"))
			str = "Internet Explorer";
		else
			str = "Unknown OS";
		return str;
	}

	// определение OS
	private static String getOS() {
		String os = System.getProperty("os.name").toLowerCase();
		if (os.indexOf("win") >= 0)
			os = "win";
		if (os.indexOf("mac") >= 0)
			os = "mac";
		if ((os.indexOf("nix")) >= 0 || (os.indexOf("nux") >= 0))
			os = "nix";
		return os;
	}

	public static String getOSVerion() {
		String osVer = System.getProperty("os.version");
		String version = null;
		if (osVer.equals("6.3"))
			return "8.1";
		return version;
	}

	public static boolean isWindows() {
		// windows
		return (getOS().indexOf("win") >= 0);
	}

	public static boolean isMac() {
		// Mac
		return (getOS().indexOf("mac") >= 0);
	}

	public static boolean isUnix() {
		// linux or unix
		return (getOS().indexOf("nix") >= 0 || getOS().indexOf("nux") >= 0);
	}

	// определение какой FileProperties
	private static String setFileProperties(String strNP) {
		String nameFile = null;
		WebDriver dr = null;
		try {
			// OS
			String os = getOS();
			// разрешение
			dr = getWebDriver();
			String res = dr.manage().window().getSize().toString();
			// Browser
			String br = getBrowser(dr);
			//
			nameFile = os.toLowerCase() + "-" + br.toLowerCase() + "-"
					+ res.toLowerCase() + "-" + strNP;
		} catch (Exception e) {
			nameFile = null;
		}

		dr.close();
		return nameFile;
	}

	public static String getFileProperties(String strNP) {
		if (strNP.equals("P1"))
			return propertiesFileP1;
		else if (strNP.equals("P2"))
			return propertiesFileP2;

		return null;
	}

	// propertiesFile0
	// private static String propertiesFile =
	// "./src/main/resources/Test.properties";

	// properties
	// private static Properties properties = setProperties(propertiesFile);

	public static Properties setProperties(String st) {
		Properties pr = new Properties();
		try {
			pr.load(new FileInputStream(st));
		} catch (Exception e) {
			if (getContinueTC()) {
				setContinueTC(false);
				setContinueErr("CAN'T OPEN FILE - " + st);
			}
			pr = null;
		}
		return pr;
	}

	public static String getPropertyes(String st, String strNP) {
		String stNew = null;
		try {
			if (strNP.equals("P1"))
				stNew = propertiesP1.getProperty(st);
			else if (strNP.equals("P2"))
				stNew = propertiesP2.getProperty(st);
		} catch (Exception e) {
			// url = e.toString();
			if (getContinueTC()) {
				setContinueTC(false);
				setContinueErr("CAN'T FOUND " + st + " - in file "
						+ getFileProperties(strNP));
			}
		}

		if ((stNew == null) && (getContinueTC())) {
			setContinueTC(false);
			setContinueErr("CAN'T FOUND " + st + " - in file "
					+ getFileProperties(strNP));
		}
		return stNew;
	}

	// URL
	// private static String s1dp1ForUrl = setUrl();
	/*
	 * private static String setUrl(String strNP) { String url = null; try { url
	 * = getPropertyes(getAllElements(0, strNP)); } catch (Exception e) { // url
	 * = e.toString(); if (getContinueTC()) { setContinueTC(false);
	 * setContinueErr("CAN'T MAKE SET URL - " + getFileProperties()); } }
	 * 
	 * if ((url == null) && (getContinueTC())) { setContinueTC(false);
	 * setContinueErr("CAN'T MAKE SET URL - " + getFileProperties()); } return
	 * url; }
	 */

	public static String getUrl(String strNP) {
		String url = null;
		try {
			url = getPropertyes(getAllElements(0, strNP), strNP);
		} catch (Exception e) {
			// url = e.toString();
			if (getContinueTC()) {
				setContinueTC(false);
				setContinueErr("CAN'T MAKE SET URL - "
						+ getFileProperties(strNP));
			}
		}

		if ((url == null) && (getContinueTC())) {
			setContinueTC(false);
			setContinueErr("CAN'T MAKE SET URL - " + getFileProperties(strNP));
		}
		return url;
		// return s1dp1ForUrl;
	}

	// showElement
	public static void showElement(WebDriver driver, WebElement element) {
		final String originalBackgroundColor = element
				.getCssValue("backgroundColor");
		final JavascriptExecutor myJS = ((JavascriptExecutor) driver); // java
																		// script
		myJS.executeScript(
				"arguments[0].style.backgroundColor = 'rgb(0,200,0)'", element);

		try {
			Thread.sleep(40);
		} catch (InterruptedException e) {
		}
		myJS.executeScript("arguments[0].style.backgroundColor = '"
				+ originalBackgroundColor + "'", element);
	}

	public static void showElement(WebDriver driver, WebElement element,
			int time) {
		final String originalBackgroundColor = element
				.getCssValue("backgroundColor");
		final JavascriptExecutor myJS = ((JavascriptExecutor) driver); // java
																		// script
		myJS.executeScript(
				"arguments[0].style.backgroundColor = 'rgb(0,200,0)'", element);

		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
		}
		myJS.executeScript("arguments[0].style.backgroundColor = '"
				+ originalBackgroundColor + "'", element);
	}

	// PrintStream
	public static PrintStream setPrintStream(String name) {
		PrintStream qw = null;
		try {
			qw = new PrintStream(new BufferedOutputStream(new FileOutputStream(
					name, true)));
		} catch (Exception e) {
		}
		return qw;
	}

	public static PrintStream getPrintStream() {
		return outPrintStream;

	}

	public static PrintStream getPrintStream(String name) {
		PrintStream qw = null;
		try {
			qw = new PrintStream(new BufferedOutputStream(new FileOutputStream(
					name, true)));
		} catch (Exception e) {
		}

		return qw;
	}

	public static void printStreamLn(String name, Object o) {
		PrintStream qw = getPrintStream(name);
		qw.println(o);
		qw.close();
	}

	// Url - Quotes
	public static String getUrlQuotes() {
		return urlQuotes;
	}

	// WAIT
	// }
	// *************************** static metods***************************
}
// END