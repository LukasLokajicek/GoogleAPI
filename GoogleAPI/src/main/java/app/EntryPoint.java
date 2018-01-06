package app;

import java.io.IOException;
import java.util.List;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.ValueRange;

import model.GoogleAPI;

public class EntryPoint {

	public static void main(String[] args) throws IOException {
		// Build a new authorized API client service.
		final Sheets service = GoogleAPI.getSheetsService();

		// Prints the names and majors of students in a sample spreadsheet:
		// https://docs.google.com/spreadsheets/d/1M7Fibb8o28DEp2CFCLH5e90-xhGuLASy7W5uNlKbW08/edit#gid=0
		final String spreadsheetId = "1M7Fibb8o28DEp2CFCLH5e90-xhGuLASy7W5uNlKbW08";
		final String range = "A1:I3";
		final ValueRange response = service.spreadsheets().values().get(spreadsheetId, range).execute();
		final List<List<Object>> values = response.getValues();
		if (values == null || values.isEmpty()) {
			System.out.println("No data found.");
		} else {
			System.out.println("Found Data");
			System.out.println("");
			for (final List<Object> row : values) {
				row.forEach(r -> System.out.print(r + " "));
				System.out.println("");
			}
		}
	}

}
