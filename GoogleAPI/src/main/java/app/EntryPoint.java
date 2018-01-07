package app;

import java.io.IOException;
import java.util.List;

import model.SpreadSheet;

public class EntryPoint {

	public static void main(String[] args) throws IOException {
		SpreadSheet ss = new SpreadSheet();
		final String range = "A1:I3";
		final List<List<Object>> values = ss.getData(range);
		if (values.isEmpty()) {
			System.out.println("No data found.");
		} else {
			System.out.println("Found Data");
			System.out.println("");
			for (final List<Object> row : values) {
				row.forEach(r -> System.out.print(r + " "));
				System.out.println("");
			}
		}
		
		String resp = ss.updateValue("Updated +1", "H3");
		
		System.out.println(resp);
	}

}
