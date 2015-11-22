//BEGIN
package core;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/*
 * проверка No024PushSubmitElementS
 */
public class No024PushSubmitElementS extends No000ValuesAndMetods {

	// номер элемента с массива - AllElementsP1 - id_submit_button
	private int noElemAllElements = 9;
	// кол-во элементов, которые нужно обработать
	// private int stroki = 10;
	// номер элемента - button = 9 - id_submit_button
	private String button = getAllElements(noElemAllElements, "P1");
	// 1-TC_ID, 2-exp., 3-act.
	// private int stolbs = 3;
	// кол-во строк для каждого элемента
	// private int amount = 1;
	// смещение, для пропуска первого элемента
	byte smew = 1;
	// name file - где хранятся истенные значения
	private String nameFileTrueP1 = "./src/main/resources/AllElementsP1.csv";
	private String nameFileTrueP2 = "./src/main/resources/AllElementsP2.csv";

	public String[][] No024PushSubmitElementSToS2D(String strNP) {

		if (!getContinueTC()) {
			String[][] str = { { "1", getContinueErr(), "" } };
			return str;
		} // if (!getContinueTC()) {

		// открыть новый браузер
		WebDriver dr = getWebDriver();

		// URL - learn2test
		dr.get(getUrl(strNP));

		// Driver Class
		String drClass = getBrowser(dr);

		// элементы для работы, создаем массив для хранения истенных значений
		// Array 1111
		ArrayList<String[]> arr = new ArrayList<String[]>();
		BufferedReader br = null;
		String line = null;
		String SplitBy = null;
		String[] csv = null;
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

		// Array 2222
		arr.clear();
		try {
			br = new BufferedReader(new FileReader(nameFileTrueP2));
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
		String[][] EllSTrueP2 = new String[arr.size()][];
		arr.toArray(EllSTrueP2);

		// 1111
		// сначала находим элементы и заполняем для массива - EllSTrue
		WebElement element = null;
		for (int i = 0; i < EllSTrue.length; i++) {
			try {
				// поиск элемента
				element = dr.findElement(By.id(EllSTrue[i][0]));
				// показать элемент
				if (drClass.equals("Firefox"))
					showElement(dr, element);
				// заполняем
				if (EllSTrue[i][1].equals("input"))
					element.sendKeys(EllSTrue[i][2]);
				else if (EllSTrue[i][1].equals("radio")
						|| EllSTrue[i][1].equals("checkbox")) {
					if (EllSTrue[i][2].equals("true"))
						element.click();
				} else if (EllSTrue[i][1].equals("button"))
					;
				else {
					EllSTrue[i][2] = null; // если не нашел
				} // if (EllSTrue[i][1].equals("input"))
			} catch (Exception e) { // try {
				EllSTrue[i][2] = null;
			} // try {
		} // for (int i = 0; i < EllSTrue.length; i++) {

		// 2222
		// нажимаем кнопку - button = 9 - id_submit_button
		// поиск элемента
		try {
			element = dr.findElement(By.id(button));
			// показать элемент
			if (drClass.equals("Firefox"))
				showElement(dr, element);
			// нажать на кнопку
			element.click();
		} catch (Exception e) {
			if (!getContinueTC()) {
				String[][] str = { { "1", getContinueErr(), "" } };
				dr.quit();
				return str;
			} // if (!getContinueTC()) {
			else {
				String[][] str = { { "1", "can't found element - " + button, "" } };
				dr.quit();
				return str;
			} // if (!getContinueTC()) {
		} // try {

		// 3333
		// создаем массив для хранения результата
		String[][] s2d = new String[EllSTrueP2.length + smew][EllSTrueP2[0].length];
		for (int i = 0; i < (EllSTrueP2.length + smew); i++) {
			s2d[i][0] = getTaseCase();
		} // for (int i = 0; i < (stroki * amount + smew); i++) {

		// 4444
		// проверяем все элементы должны быть равными второму массиву
		byte b = 0; // для подстчета элементов
		for (int i = 0; i < EllSTrueP2.length; i++) {
			try {
				// поиск элемента
				element = dr.findElement(By.id(EllSTrueP2[i][0]));
				b++;

				// показать элемент
				if (drClass.equals("Firefox"))
					showElement(dr, element);

				// проверяем
				if (EllSTrueP2[i][1].equals("text")) {
					s2d[i + smew][1] = EllSTrueP2[i][0] + " has '"
							+ EllSTrueP2[i][2] + "'";
					s2d[i + smew][2] = EllSTrueP2[i][0] + " has '"
							+ element.getText() + "'";
				} else if (EllSTrueP2[i][1].equals("button")) {
					s2d[i + smew][1] = "has " + EllSTrueP2[i][0];
					s2d[i + smew][2] = "has " + EllSTrueP2[i][0];
				} else {
					s2d[i + smew][1] = EllSTrueP2[i][0]
							+ " - can't found element";
					s2d[i + smew][2] = "''";
				} // if (EllSTrueP2[i][1].equals("text"))
			} catch (Exception e) { // try {
				s2d[i + smew][1] = EllSTrueP2[i][0] + " - can't found element";
				s2d[i + smew][2] = "''";
				continue;
			} // try {
		} // for (int i = 0; i < (EllSTrue.length); i++) {

		// 5555
		// сравнение кол-ва элементов
		s2d[0][1] = "has " + EllSTrueP2.length + " elements";
		s2d[0][2] = "has " + b + " elements";

		// выход
		dr.quit();
		return s2d;
	}

	/*
	 * public static void main(String[] args) { No024PushSubmitElementS qwe =
	 * new No024PushSubmitElementS(); String[][] as =
	 * qwe.No024PushSubmitElementSToS2D("P1"); for (int i = 0; i < as.length;
	 * i++) System.out.println("i=" + i + ", as=" + as[i][0] + "---" + as[i][1]
	 * + "---" + as[i][2] + "---"); }
	 */
}
// END