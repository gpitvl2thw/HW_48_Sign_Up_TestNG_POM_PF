//BEGIN
package core;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/*
 * проверка No022PushResetElementS
 */
public class No027ErrorHandling extends No000ValuesAndMetods {

	// номер элемента с массива - AllElementsP1 - id_submit_button
	private int noElemAllElements = 9;
	// номер элемента с массива - AllElementsP1 - ulr
	// private int noElemAllElementsUrl = 0;
	// кол-во элементов, которые нужно обработать
	// private int stroki = 10;
	// номер элемента - button = 8 - id_reset_button
	private String button = getAllElements(noElemAllElements, "P1");
	// номер элемента - url = 0 - id_reset_button
	// private String URL = getAllElements(
	// noElemAllElementsUrl, "P1");
	// 1-TC_ID, 2-exp., 3-act.
	private int stolbs = 3;
	// кол-во строк для каждого элемента
	// private int amount = 1;
	// смещение, для пропуска первого элемента
	byte smew = 0;
	// name file - где хранятся истенные значения
	private String nameFileTrueP1 = "./src/main/resources/AllElementsP1.csv";
	// name file - где хранятся истенные значения
	private String nameFileErrorHandling = "./src/main/resources/errorHandling.csv";
	//
	private String addErr = "_error";
	//
	private String errLine = "ErrorLine";

	public String[][] No027ErrorHandlingToS2D(String strNP) {

		if (!getContinueTC()) {
			String[][] str = { { "1", getContinueErr(), "" } };
			return str;
		} // if (!getContinueTC()) {

		// открыть новый браузер
		WebDriver dr = getWebDriver();

		// URL - learn2test
		// dr.get(getUrl(strNP));

		// Driver Class
		String drClass = getBrowser(dr);

		// элементы для работы, создаем массив для хранения истенных значений
		// String[][] EllSTrue = new String[stroki][stolbs];
		ArrayList<String[]> arr = new ArrayList<String[]>();
		BufferedReader br = null;
		String line = null;
		String SplitBy = null;
		String[] csv = null; // чтение с файла массива
		String name_error = null; //
		String[] csv2 = new String[stolbs];
		try {
			br = new BufferedReader(new FileReader(nameFileTrueP1));
			SplitBy = br.readLine();
			while ((line = br.readLine()) != null) {
				csv = line.split(SplitBy);
				arr.add(csv);
			} // while ((line = br.readLine()) != null) {
			br.close();
		} catch (Exception e) { // try {
			e.printStackTrace();
			// br.close();
		} // try {

		// преобразование ArrayList в String
		String[][] EllSTrue = new String[arr.size()][];
		arr.toArray(EllSTrue);

		// 1111
		// читаем данные с файла - errorHandling
		ArrayList<String[]> arrErrH = new ArrayList<String[]>();
		br = null;
		line = null;
		SplitBy = null;
		csv = null;
		// читаем SplitBy
		try {
			br = new BufferedReader(new FileReader(nameFileErrorHandling));
			SplitBy = br.readLine();
		} catch (Exception e) { // try {
			if (!getContinueTC()) {
				String[][] str = { { "1", getContinueErr(), "" } };
				return str;
			} else {
				String[][] str = { {
						"1",
						"Can't read 'SplitBy' from file - "
								+ nameFileErrorHandling, "" } };
				return str;
			} // if (!getContinueTC()) {
		} // try {

		// читаем Error Handling
		WebElement element = null;
		try {
			while ((line = br.readLine()) != null) {
				// URL - learn2test
				dr.get(getUrl(strNP));

				// separate первую строку
				csv = line.split(SplitBy);

				try {
					element = dr.findElement(By.id(csv[1]));
					// находим I(id) элемента в массиве EllSTrue
					// и заполняем все элементы до данного элемента
					for (int i = 0; i < EllSTrue.length; i++) {
						element = dr.findElement(By.id(EllSTrue[i][0]));
						element.clear();
						// показать элемент
						if (drClass.equals("Firefox"))
							showElement(dr, element);
						if (csv[1].equals(EllSTrue[i][0])
								&& (EllSTrue[i][1].equals("input"))) {
							element.sendKeys(csv[2]);
							break;
						} else if (EllSTrue[i][1].equals("input")) {
							element.sendKeys(EllSTrue[i][2]);
						} // if (csv[0].equals(EllSTrue[i][0])) {
					} // for(int i = 0; i < EllSTrue.length; i++)
				} catch (Exception e) {
					// не получается найти элемент для заполнения
					csv2 = new String[stolbs];
					csv2[0] = getTaseCase();
					csv2[1] = "Can't find lement - " + csv[1];
					csv2[2] = "";
					arrErrH.add(csv2);
					continue;
				} // try {

				// нажимаем кнопку - button = 9 - id_submit_button
				element = dr.findElement(By.id(button));
				// нажать на кнопку
				element.click();

				try {
					// addErr = "_error";
					name_error = csv[1].substring(3) + addErr;

					element = dr.findElement(By.id(name_error));
					// показать элемент
					if (drClass.equals("Firefox"))
						showElement(dr, element);

					// isEnabled
					csv2 = new String[stolbs];
					csv2[0] = getTaseCase();
					csv2[1] = "If " + csv[1] + "='" + csv[2] + "', Element "
							+ element.getAttribute("id") + " is Enabled";
					csv2[2] = "If "
							+ csv[1]
							+ "='"
							+ csv[2]
							+ "', Element "
							+ element.getAttribute("id")
							+ (element.isEnabled() ? " is Enabled"
									: " isn't Enabled");
					arrErrH.add(csv2);

					// isDisplayed
					csv2 = new String[stolbs];
					csv2[0] = getTaseCase();
					csv2[1] = "If " + csv[1] + "='" + csv[2] + "', Element "
							+ element.getAttribute("id") + " is Displayed";
					csv2[2] = "If "
							+ csv[1]
							+ "='"
							+ csv[2]
							+ "', Element "
							+ element.getAttribute("id")
							+ (element.isDisplayed() ? " is Displayed"
									: " isn't Displayed");
					arrErrH.add(csv2);
				} catch (Exception e) {
					// не получается найти ErrorLine элемент
					csv2 = new String[stolbs];
					csv2[0] = getTaseCase();
					csv2[1] = "Can't find lement - " + name_error;
					csv2[2] = "";
					arrErrH.add(csv2);
				} // try {

				try {
					// errLine = "ErrorLine";
					element = dr.findElement(By.id(errLine));
					// показать элемент
					if (drClass.equals("Firefox"))
						showElement(dr, element);

					// isEnabled
					csv2 = new String[stolbs];
					csv2[0] = getTaseCase();
					csv2[1] = "If " + csv[1] + "='" + csv[2] + "', Element "
							+ element.getAttribute("id") + " is Enabled";
					csv2[2] = "If "
							+ csv[1]
							+ "='"
							+ csv[2]
							+ "', Element "
							+ element.getAttribute("id")
							+ (element.isEnabled() ? " is Enabled"
									: " isn't Enabled");
					arrErrH.add(csv2);

					// isDisplayed
					csv2 = new String[stolbs];
					csv2[0] = getTaseCase();
					csv2[1] = "If " + csv[1] + "='" + csv[2] + "', Element "
							+ element.getAttribute("id") + " is Displayed";
					csv2[2] = "If "
							+ csv[1]
							+ "='"
							+ csv[2]
							+ "', Element "
							+ element.getAttribute("id")
							+ (element.isDisplayed() ? " is Displayed"
									: " isn't Displayed");
					arrErrH.add(csv2);

					// getText - massage
					csv2 = new String[stolbs];
					csv2[0] = getTaseCase();
					csv2[1] = "If " + csv[1] + "='" + csv[2] + "', Element "
							+ element.getAttribute("id") + " has massage='"
							+ csv[3] + "'";
					csv2[2] = "If " + csv[1] + "='" + csv[2] + "', Element "
							+ element.getAttribute("id") + " has massage='"
							+ element.getText() + "'";
					arrErrH.add(csv2);
				} catch (Exception e) {
					// не получается найти ErrorLine элемент
					csv2 = new String[stolbs];
					csv2[0] = getTaseCase();
					csv2[1] = "Can't find lement - " + errLine;
					csv2[2] = "";
					arrErrH.add(csv2);
				} // try {
			} // while ((line = br.readLine()) != null) {
			br.close();
		} catch (Exception e) { // try {
			if (!getContinueTC()) {
				String[][] str = { { "1", getContinueErr(), "" } };
				return str;
			} else {
				String[][] str = { { "1",
						"Can't read file - " + nameFileErrorHandling, "" } };
				dr.quit();
				return str;
			} // if (!getContinueTC()) {
		} // try {

		// получение выходного массива
		// преобразование ArrayList в String
		String[][] s2d = new String[arrErrH.size()][];
		arrErrH.toArray(s2d);

		// выход
		dr.quit();
		return s2d;
	}

	/*
	 * public static void main(String[] args) { No027ErrorHandling qwe = new
	 * No027ErrorHandling(); String[][] as = qwe.No027ErrorHandlingToS2D("P1");
	 * for (int i = 0; i < as.length; i++) System.out.println("i=" + i + ", as="
	 * + as[i][0] + "###" + as[i][1] + "###" + as[i][2] + "###"); }
	 */
}
// END