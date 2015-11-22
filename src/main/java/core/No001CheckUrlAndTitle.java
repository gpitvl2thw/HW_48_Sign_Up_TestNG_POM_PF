// BEGIN
package core;

import org.openqa.selenium.WebDriver;

/**
 * http://learn2test.net/qa/apps/sign_up/v1/ ---------------------------
 * проверка ulr и title
 */
public class No001CheckUrlAndTitle extends No000ValuesAndMetods {

	// номер элемента с массива - AllElements
	private int noElemAllElements = 1;
	// кол-во элементов, которые нужно обработать
	private int stroki = 1;
	// 1-TC_ID, 2-exp., 3-act.
	private int stolbs = 3;

	//
	public String[][] No001CheckUrlToS2D(String addString, String strNP) {

		if (!getContinueTC()) {
			String[][] str = { { "1", getContinueErr(), "" } };
			return str;
		}

		String[][] s2dp1ForCheckUrl = new String[stroki][stolbs];
		s2dp1ForCheckUrl[0][0] = getTaseCase();

		WebDriver dr = getWebDriver();

		// URL
		try {
			dr.get(getUrl(strNP));

			s2dp1ForCheckUrl[0][1] = getUrl(strNP);
			s2dp1ForCheckUrl[0][2] = dr.getCurrentUrl() + addString;
		} catch (Exception e) {

			if (!getContinueTC()) {
				setContinueTC(false);
				No000ValuesAndMetods
						.setContinueErr("CAN'T MAKE SET URL  FOR WebDriver (No001CheckUrlToS2D)");
			}
		}

		// отправка сообщения - в каком файле ошибка
		if (!getContinueTC()) {
			setContinueTC(false);
			No000ValuesAndMetods
					.setContinueErr("CAN'T MAKE SET URL  FOR WebDriver 2 (No001CheckUrlToS2D)");
		}

		dr.quit();
		return s2dp1ForCheckUrl;
	}

	public String[][] No001CheckTitleToS2D(String addString, String strNP) {

		if (!getContinueTC()) {
			String[][] str = { { "1", getContinueErr(), "" } };
			return str;
		}

		String[][] s2dp1ForCheckTitle = new String[stroki][stolbs];
		s2dp1ForCheckTitle[0][0] = getTaseCase();

		WebDriver dr = getWebDriver();

		// URL
		dr.get(getUrl(strNP));

		// TITLE
		s2dp1ForCheckTitle[0][1] = getPropertyes(
				getAllElements(noElemAllElements, strNP), strNP);
		s2dp1ForCheckTitle[0][2] = dr.getTitle() + addString;

		// отправка сообщения - в каком файле ошибка
		if (!getContinueTC()) {
			setContinueTC((s2dp1ForCheckTitle[0][2]
					.equals(s2dp1ForCheckTitle[0][1] + addString)));
			No000ValuesAndMetods
					.setContinueErr("ERROR IN - No001CheckTitleToS2D");
		}

		dr.quit();
		return s2dp1ForCheckTitle;
	}

	/*
	 * public static void main(String[] args) { No001CheckUrlAndTitle qwe = new
	 * No001CheckUrlAndTitle(); String[][] as = qwe.No001CheckUrlToS2D("",
	 * "P2"); for (int i = 0; i < as.length; i++) System.out.println("i=" + i +
	 * ", as=" + as[i][0] + "###" + as[i][1] + "###" + as[i][2] + "###"); }
	 */
}
// END