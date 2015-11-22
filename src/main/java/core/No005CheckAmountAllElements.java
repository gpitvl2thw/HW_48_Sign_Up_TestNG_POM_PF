//BEGIN
package core;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/*
 * проверка кол-во всех элементов
 */
public class No005CheckAmountAllElements extends No000ValuesAndMetods {

	// номер элемента с массива - AllElements
	private int noElemAllElements = 2;
	// кол-во элементов, которые нужно обработать
	private int stroki = 1;
	// 1-TC_ID, 2-exp., 3-act.
	private int stolbs = 3;

	public String[][] No005CheckAmountAllElementsToS2D(String strNP) {

		if (!getContinueTC()) {
			String[][] str = { { "1", getContinueErr(), "" } };
			return str;
		}

		String[][] s2d = new String[stroki][stolbs];
		s2d[0][0] = getTaseCase();

		WebDriver dr = getWebDriver();

		// URL
		dr.get(getUrl(strNP));

		// Count Elements
		s2d[0][1] = getPropertyes(getAllElements(noElemAllElements, strNP),
				strNP);

		List<WebElement> elements = dr.findElements(By.xpath("//*"));
		s2d[0][2] = "" + (elements.size() + 1);

		// отправка сообщения - в каком файле ошибка
		if (!getContinueTC()) {
			setContinueTC((s2d[0][2].equals(s2d[0][1])));
			No000ValuesAndMetods
					.setContinueErr("ERROR IN - No005CheckAmountAllElements (No005CheckAmountAllElementsToS2D)");
		}

		dr.quit();
		return s2d;
	}

	/*
	 * public static void main(String[] args) { No005CheckAmountAllElements qwe
	 * = new No005CheckAmountAllElements(); String[][] as =
	 * qwe.No005CheckAmountAllElementsToS2D("P1"); for (int i = 0; i <
	 * as.length; i++) System.out.println("i=" + i + ", as=" + as[i][0] + "###"
	 * + as[i][1] + "###" + as[i][2] + "###"); }
	 */
}
// END