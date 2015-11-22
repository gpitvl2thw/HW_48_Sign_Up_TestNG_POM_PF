//BEGIN
package core;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/*
 * проверка table #4
 */
public class No009CheckTable4 extends No000ValuesAndMetods {

	// номер элемента с массива - AllElements
	private int noElemAllElements = 6;
	// кол-во элементов, которые нужно обработать
	private int stroki = 3;
	private int strokiP2 = 1;
	// 1-TC_ID, 2-exp., 3-act.
	private int stolbs = 3;
	// кол-во строк для каждого элемента
	private int amount = 5;
	// смещение, для пропуска первого элемента
	byte smew = 1;

	public String[][] No009CheckTable4ToS2D(String strNP) {

		if (!getContinueTC()) {
			String[][] str = { { "1", getContinueErr(), "" } };
			return str;
		}

		if (strNP.equals("P2"))
			stroki = strokiP2;
		String[][] s2d = new String[stroki * amount + smew][stolbs];
		for (int i = 0; i < (stroki * amount + smew); i++) {
			s2d[i][0] = getTaseCase();
		}

		// открыть новый браузер
		WebDriver dr = getWebDriver();

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

		int countEl = 0;
		String nElOld = null;
		String nElNew = null;
		String nElNewLook = null;
		// поиск элемента
		for (int i = 0;; i++) {
			try {
				nElOld = nameElem + "_" + (i + 1); // table4_1
				nElNew = getPropertyes(nElOld, strNP); // timestamp
				nElNewLook = getPropertyes(nElOld + "_look", strNP); // table4_1_look

				// работа с новым элементом
				try {
					// println(nElNewLook.toString());
					if (nElNewLook.equals("xpath")) {
						element = dr.findElement(By.xpath(nElNew));
					} else if (nElNewLook.equals("id")) {
						element = dr.findElement(By.id(nElNew));
					} else {
						// не знаю как искать
						throw new Exception("не знаю как искать");
					}

					// element.isEnabled(); - #1 - (+0)
					s2d[i * amount + smew][1] = nElNew + " is Enabled";
					if (element.isEnabled())
						s2d[i * amount + smew][2] = nElNew + " is Enabled";
					else
						s2d[i * amount + smew][2] = nElNew + " isn't Enabled";

					// element.isDisplayed(); - #2 - (+1)
					s2d[i * amount + 1 + smew][1] = nElNew + " is Displayed";
					if (element.isDisplayed()) {
						s2d[i * amount + 1 + smew][2] = nElNew
								+ " is Displayed";
						// показать элемент
						if (drClass.equals("Firefox"))
							showElement(dr, element);
					} else
						s2d[i * amount + 1 + smew][2] = nElNew
								+ " isn't Displayed";

					// locate - #3 - (+2)
					nElOld = nameElem + "_" + (i + 1); // table4_1
					nElNew = getPropertyes(nElOld, strNP); // timestamp
					nElNewLook = getPropertyes(nElOld + "_look", strNP); // table4_1_look

					s2d[i * amount + 2 + smew][1] = "Locate of " + nElNew
							+ " is " + getPropertyes(nElOld + "_locate", strNP);
					s2d[i * amount + 2 + smew][2] = "Locate of " + nElNew
							+ " is " + element.getLocation();

					// size - #4 - (+3)
					s2d[i * amount + 3 + smew][1] = "Size of " + nElNew
							+ " is " + getPropertyes(nElOld + "_size", strNP);
					s2d[i * amount + 3 + smew][2] = "Size of " + nElNew
							+ " is " + element.getSize();
					//
					// Value - #5 - (+4)
					// разделение на 3 части
					// дата - timestamp
					Date d = new Date();
					String ye = new SimpleDateFormat("YYYY").format(d);
					if (nElNew.equals("timestamp")) {
						String qws2d = new SimpleDateFormat("MMMM").format(d)
								+ " " + new SimpleDateFormat("d").format(d)
								+ ", " + ye;
						s2d[i * amount + 4 + smew][1] = "Value of " + nElNew
								+ " is " + qws2d;
						s2d[i * amount + 4 + smew][2] = "Value of " + nElNew
								+ " is " + element.getText().trim();
					}
					// copyright
					else if (nElNew.equals("copyright")) {
						String qws2d = "© " + ye + " learn2test.net";
						s2d[i * amount + 4 + smew][1] = "Value of " + nElNew
								+ " is " + qws2d;
						s2d[i * amount + 4 + smew][2] = "Value of " + nElNew
								+ " is " + element.getText().trim();
					}
					// os_browser
					else if (nElNew.equals("os_browser")) {
						/*
						 * // OS String OS = null; if (isWindows()) { OS =
						 * "Windows"; } else if (isMac()) { OS = "Mac"; } else
						 * if (isUnix()) { OS = "Unix"; } else { OS =
						 * "Unknown OS"; } // OS version String osVer =
						 * getOSVerion();
						 */
						// s2d[i * amount + 4 + smew][1] = "Value of " + nElNew
						// + " is " + OS + " " + osVer + " & " + drClass;
						s2d[i * amount + 4 + smew][1] = "Value of " + nElNew
								+ " is " + System.getProperty("os.name")
								+ " & " + drClass;
						s2d[i * amount + 4 + smew][2] = "Value of " + nElNew
								+ " is " + element.getText().trim();
					}
				} catch (Exception e) {
					// если не нашел элемента
					s2d[i * amount + smew][1] = s2d[i * amount + 1 + smew][1] = s2d[i
							* amount + 2 + smew][1] = s2d[i * amount + 3 + smew][1] = s2d[i
							* amount + 4 + smew][1] = "111111111111";
					s2d[i * amount + smew][2] = s2d[i * amount + 1 + smew][2] = s2d[i
							* amount + 2 + smew][2] = s2d[i * amount + 3 + smew][2] = s2d[i
							* amount + 4 + smew][2] = "222222222222";
				}
				countEl++;
			} catch (Exception e) {
				// выходим из цикла если больше нет элементов
				break;
			}
		} // for (int i = 0;; i++) {

		// запись кол-во элементов
		s2d[0][1] = "Number of elements in " + nameElem + " is "
				+ getPropertyes(nameElem + "_0", strNP);
		s2d[0][2] = "Number of elements in " + nameElem + " is " + countEl;

		// проверка
		if (!getContinueTC()) {
			setContinueTC((s2d[0][2].equals(s2d[0][1])));
			setContinueErr("ERROR IN - No009CheckTable4");
		}

		dr.quit();
		return s2d;
	}

	/*
	 * public static void main(String[] args) { No009CheckTable4 qwe = new
	 * No009CheckTable4(); String[][] as = qwe.No009CheckTable4ToS2D("P2"); for
	 * (int i = 0; i < as.length; i++) System.out.println("i=" + i + ", as=" +
	 * as[i][0] + "---" + as[i][1] + "---" + as[i][2] + "---"); }
	 */
}
// END