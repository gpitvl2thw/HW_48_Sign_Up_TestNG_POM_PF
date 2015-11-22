//BEGIN
package core;

import java.util.ArrayList;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/*
 * проверка table #3
 */
public class No010CheckTable3 extends No000ValuesAndMetods {

	// номер элемента с массива - AllElements
	private int noElemAllElements = 7;
	// кол-во элементов, которые нужно обработать
	private int stroki = 27;
	private int strokiP2 = 16;
	// 1-TC_ID, 2-exp., 3-act.
	private int stolbs = 3;
	// кол-во строк для каждого элемента
	private int amount = 5;
	// смещение, для пропуска первого элемента
	byte smew = 1;

	public String[][] No010CheckTable3ToS2D(String strNP) {

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
		String nElNewType = null;
		String nElNewVar = null;
		// поиск элемента
		for (int i = 0;; i++) {
			try {
				nElOld = nameElem + "_" + (i + 1); // table2_1
				nElNew = getPropertyes(nElOld, strNP); // id_f_title
				nElNewLook = getPropertyes(nElOld + "_look", strNP); // table2_1_look

				// работа с новым элементом
				try {
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
					try {
						nElNewType = getPropertyes(nElOld + "_type", strNP); // val
					} catch (Exception e) {
						nElNewType = "";
					}

					// ///////читать один раз для всех////////////
					try {
						nElNewVar = getPropertyes(nElOld + "_val", strNP);
					} catch (Exception e) {
						nElNewVar = "";
					}

					// разделение на части
					if (nElNewType.equals("radio")
							|| nElNewType.equals("checkbox")) {
						// //////////////////////////////////////
						// если есть значение - radio or checkbox
						s2d[i * amount + 4 + smew][1] = nElNew
								+ " is not selected";
						s2d[i * amount + 4 + smew][2] = nElNew + " is "
								+ (element.isSelected() ? "" : "not")
								+ " selected";
					} else if (nElNewType.equals("input")) {
						// //////////////////////////////////////
						// если тип - input
						s2d[i * amount + 4 + smew][1] = nElNew + " has '"
								+ nElNewVar + "'";
						s2d[i * amount + 4 + smew][2] = nElNew + " has '"
								+ element.getAttribute("value") + "'";
					} else if (nElNewType.equals("text")) {
						// //////////////////////////////////////
						// если тип - text
						if (nElNew.equals("id_state")) {
							// id_state
							s2d[i * amount + 4 + smew][1] = nElNew + " has "
									+ nElNewVar.trim();
							// String qweasd = element.getText().trim()
							// .replaceAll(" ", "");
							s2d[i * amount + 4 + smew][2] = nElNew
									+ " has "
									+ element.getText().trim()
											.replaceAll("\n", " ");
						} else {
							// если тип - text
							s2d[i * amount + 4 + smew][1] = nElNew + " has '"
									+ nElNewVar.trim() + "'";
							s2d[i * amount + 4 + smew][2] = nElNew + " has '"
									+ element.getText().trim() + "'";
						}
					} else if (nElNewType.equals("button")) {
						// //////////////////////////////////////
						// если тип - button
						s2d[i * amount + 4 + smew][1] = nElNew + " has '"
								+ nElNewVar.trim() + "'";
						s2d[i * amount + 4 + smew][2] = nElNew + " has '"
								+ element.getAttribute("value").trim() + "'";
					} else if (nElNewType.equals("link")) {
						// //////////////////////////////////////
						// если есть значение - link
						String now = dr.getWindowHandle();
						byte bnow = 0;
						ArrayList<String> allTabs = null;
						try {
							// кликаем
							element.click();
							allTabs = new ArrayList<String>(
									dr.getWindowHandles());
							// переходим на новую вкладку
							// переключение окна
							if (allTabs.get(0).equals(now)) {
								bnow = 0;
								dr.switchTo().window(allTabs.get(1));
							} else {
								bnow = 1;
								dr.switchTo().window(allTabs.get(0));
							}
							// записываем
							s2d[i * amount + 4 + smew][1] = nElNew
									+ " has title=" + nElNewVar;
							s2d[i * amount + 4 + smew][2] = nElNew
									+ " has title=" + dr.getTitle();
							// закрываем
							dr.close();

							// возвращаем прежнюю вкладку
							dr.switchTo().window(allTabs.get(bnow));
						} catch (Exception e) {
							s2d[i * amount + 4 + smew][1] = nElNew + " has "
									+ "error";
							s2d[i * amount + 4 + smew][2] = nElNew + " has "
									+ "can't make click";
						}
					} else if (!nElNewType.isEmpty()) {
						// если есть значение в файле, то его и берем за exp
						s2d[i * amount + 4 + smew][1] = "Value of " + nElNew
								+ " is " + nElNewType;
						s2d[i * amount + 4 + smew][2] = "Value of " + nElNew
								+ " is " + element.getText().trim();
					} else {
						// если нету значения в файле
						s2d[i * amount + 4 + smew][1] = "Value of " + nElNew
								+ " is ";
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
			setContinueErr("ERROR IN - No6CheckTable2");
		}

		dr.quit();
		return s2d;
	}

	/*
	 * public static void main(String[] args) { No010CheckTable3 qwe = new
	 * No010CheckTable3(); String[][] as = qwe.No010CheckTable3ToS2D("P2"); for
	 * (int i = 0; i < as.length; i++) System.out.println("i=" + i + ", as=" +
	 * as[i][0] + "---" + as[i][1] + "---" + as[i][2] + "---"); }
	 */
}
// END