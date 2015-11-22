//BEGIN
package core;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/*
 * проверка кол-во видемых элементов
 */
public class No006CheckDisplayedlElements extends No000ValuesAndMetods {

	// номер элемента с массива - AllElements
	private int noElemAllElements = 3;
	// кол-во элементов, которые нужно обработать
	private int stroki = 1;
	// 1-TC_ID, 2-exp., 3-act.
	private int stolbs = 3;

	public String[][] No006CheckDisplayedlElementsToS2D(String strNP) {

		if (!getContinueTC()) {
			String[][] str = { { "1", getContinueErr(), "" } };
			return str;
		}

		String[][] s2d = new String[stroki][stolbs];
		s2d[0][0] = getTaseCase();

		WebDriver dr = getWebDriver();

		// URL
		dr.get(getUrl(strNP));

		// Driver Class
		String qw = dr.getClass().toString();
		String drClass = null;
		if (qw.equals("class org.openqa.selenium.htmlunit.HtmlUnitDriver"))
			drClass = "Unit";
		else if (qw.equals("class org.openqa.selenium.firefox.FirefoxDriver"))
			drClass = "Fox";
		else
			drClass = "Unit";

		s2d[0][1] = getPropertyes(getAllElements(noElemAllElements, strNP),
				strNP);

		// список элементов
		List<WebElement> elementS = dr.findElements(By.xpath("//*"));
		int i = 1;
		for (WebElement element : elementS) {
			if (element.isDisplayed()) {
				if (drClass.equals("Fox"))
					showElement(dr, element);
				// os.write(i + " - " + element.getTagName().toString() +
				// " - "
				// + element.getText().toString() + "\n");
				i++;
			}
		}
		// os.close();

		s2d[0][2] = "" + i;

		// проверка
		if (!getContinueTC()) {
			setContinueTC((s2d[0][2].equals(s2d[0][1])));
			No000ValuesAndMetods
					.setContinueErr("ERROR IN - No006CheckDisplayedlElements");
		}

		dr.quit();
		return s2d;
	}

	/*
	 * public static void main(String[] args) { No006CheckDisplayedlElements qwe
	 * = new No006CheckDisplayedlElements(); String[][] as =
	 * qwe.No006CheckDisplayedlElementsToS2D("P1"); for (int i = 0; i <
	 * as.length; i++) System.out.println("i=" + i + ", as=" + as[i][0] + "###"
	 * + as[i][1] + "###" + as[i][2] + "###"); }
	 */
}
// END