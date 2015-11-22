//BEGIN
package core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashSet;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/*
 * проверка table #2
 */
public class No008CheckTable2 extends No000ValuesAndMetods {

	// номер элемента с массива - AllElements
	private int noElemAllElements = 5;
	// кол-во элементов, которые нужно обработать
	private int stroki = 1;
	// 1-TC_ID, 2-exp., 3-act.
	private int stolbs = 3;
	// кол-во строк для каждого элемента
	// size, contains
	private int amount = 2;
	// запись quoteS
	String quotesFile = getUrlQuotes();
	// Length of quotes
	byte minLength = 36;
	byte maxLength = 103;

	public String[][] No008CheckTable2ToS2D(String strNP) throws IOException {

		if (!getContinueTC()) {
			String[][] str = { { "1", getContinueErr(), "" } };
			return str;
		}

		String[][] s2d = new String[stroki * amount][stolbs];
		for (int i = 0; i < stroki * amount; i++)
			s2d[i][0] = getTaseCase();

		// открыть новый браузер
		WebDriver dr = getWebDriver();

		// ##################quotes###############################
		// URL - quotes
		// dr.get(getUrlQuotes());

		// запись quoteS
		// String quotesFile = "./src/main/resources/quotes.txt";

		/*
		 * PrintWriter out = null; File file = new File(quotesFile); try { if
		 * (!file.exists()) file.createNewFile(); out = new
		 * PrintWriter(file.getAbsoluteFile());
		 * out.print(dr.getPageSource().trim()); } catch (Exception e) { }
		 * finally { // После чего мы должны закрыть файл // Иначе файл не
		 * запишется out.close(); }
		 */

		// чтение quoteS
		URL url = null;
		URLConnection urlc = null;
		try {
			url = new URL(quotesFile);
			urlc = url.openConnection();
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}

		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(
					urlc.getInputStream(), "UTF8"));
		} catch (Exception e) {
			try {
				br.close();
			} catch (Exception ee) {
			}
			String[][] str = { { "1", "Can't open file - " + quotesFile, "" } };
			return str;
		}

		// чтение самого файла
		String line = null;
		String SplitBy = "\"";
		// запись quotes в массив
		Set<String> setQuotes = new HashSet<String>();
		try {
			while ((line = br.readLine()) != null) {
				String[] csv = line.split(SplitBy);
				if (csv.length == 2)
					setQuotes.add(csv[1]);
			}
			br.close();
		} catch (Exception e) {
			try {
				br.close();
			} catch (Exception ee) {
			}
			String[][] str = { { "1", "Can't read file - " + quotesFile, "" } };
			return str;
		}
		// ##################quotes###############################

		// URL - learn2test
		dr.get(getUrl(strNP));

		// Driver Class
		String drClass = getBrowser(dr);

		// поиск элемента
		String nameElem = getAllElements(noElemAllElements, strNP);
		String nameElemLook = nameElem + "_look";
		String nameElemFile = getPropertyes(nameElem, strNP);

		WebElement element = null;
		if (getPropertyes(nameElemLook, strNP).equals("xpath")) {
			element = dr.findElement(By.xpath(nameElemFile));
		} else if (getPropertyes(nameElemLook, strNP).equals("id")) {
			element = dr.findElement(By.id(nameElemFile));
		}
		// показать элемент
		if (drClass.equals("Firefox"))
			showElement(dr, element);

		// проверка элемента
		String quote = element.getText().trim();
		// size - #1 (0)
		s2d[0][1] = "Length of " + nameElemFile + " is between " + minLength
				+ " and " + maxLength;
		if (quote != null && quote.length() < maxLength
				&& quote.length() > minLength)
			s2d[0][2] = "Length of " + nameElemFile + " is between "
					+ minLength + " and " + maxLength;
		else
			s2d[0][2] = "Length of " + nameElemFile + " is between "
					+ minLength + " and " + maxLength;

		// contains - #2 (1)
		s2d[1][1] = nameElemFile + " has correct value - " + quote;
		if (setQuotes.contains(quote))
			s2d[1][2] = nameElemFile + " has correct value - "
					+ element.getText().trim();
		else
			s2d[1][2] = nameElemFile + " has not correct value - "
					+ element.getText().trim();

		// проверка
		if (!getContinueTC()) {
			setContinueTC((s2d[0][2].equals(s2d[0][1])));
			No000ValuesAndMetods
					.setContinueErr("ERROR IN - No008CheckTable2ToS2D");
		}

		dr.quit();
		return s2d;
	}

	/*
	 * public static void main(String[] args) { No008CheckTable2 qwe = new
	 * No008CheckTable2(); String[][] as = null; try { as =
	 * qwe.No008CheckTable2ToS2D("P1"); } catch (IOException e) {
	 * e.printStackTrace(); } for (int i = 0; i < as.length; i++)
	 * System.out.println("i=" + i + ", as=" + as[i][0] + "---" + as[i][1] +
	 * "---" + as[i][2] + "---"); }
	 */
}
// END