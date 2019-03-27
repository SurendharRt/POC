package Test_data;

import java.util.ArrayList;

import Excel_lib.Xls_Reader;

public class testutility_xl {

	static Xls_Reader reader;

	public static ArrayList<Object[]> getdata() {

		ArrayList<Object[]> data = new ArrayList<Object[]>();

		reader = new Xls_Reader(
				"C:\\Users\\suthanthira_devi\\workspace\\POC\\src\\main\\java\\Test_data\\Test_data.xlsx");

		for (int i = 2; i <= reader.getRowCount("Sheet1"); i++) {

			// for(int j=2;j<=reader.getRowCount(i).getLastCellNum();j++)
			// int i=2;

			String code = reader.getCellData("Sheet1", "code", i);

			String desc = reader.getCellData("Sheet1", "desc", i);

			String mar = reader.getCellData("Sheet1", "mar", i);

			String frq = reader.getCellData("Sheet1", "frq", i);

			String week = reader.getCellData("Sheet1", "week", i);

			String due = reader.getCellData("Sheet1", "due", i);

			String amt_typ = reader.getCellData("Sheet1", "amt_typ", i);

			String sdate = reader.getCellData("Sheet1", "sdate", i);

			String per = reader.getCellData("Sheet1", "per", i);

			String edate = reader.getCellData("Sheet1", "edate", i);

			Object obj[] = { code, desc, mar, frq, week, due, amt_typ, sdate, per, edate };

			data.add(obj);

		}
		return data;

	}

}
