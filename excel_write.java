package Test_data;

import java.util.ArrayList;

import Excel_lib.Xls_Reader;

public class excel_write {
	
	static Xls_Reader reader;
	public static ArrayList<Object[]> getdata() {

		ArrayList<Object[]> data = new ArrayList<Object[]>();

		reader = new Xls_Reader(
				"C:\\Users\\suthanthira_devi\\Downloads\\ItemUpload.xlsx");

		for (int i = 2; i <= reader.getRowCount("Sheet1"); i++) {

			// for(int j=2;j<=reader.getRowCount(i).getLastCellNum();j++)
			// int i=2;

			boolean code = reader.setCellData("Sheet1","ItemNumber", 1, "testing");
			boolean code1 = reader.setCellData("Sheet1","ItemNumber", 1, "testing");
			boolean code2 = reader.setCellData("Sheet1","ItemNumber", 1, "testing");
			Object obj[] = { code,code1,code2};
			data.add(obj);

}
		return data;
	}
}
