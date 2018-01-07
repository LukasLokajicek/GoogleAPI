package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.UpdateValuesResponse;
import com.google.api.services.sheets.v4.model.ValueRange;

public class SpreadSheet {

	// Build a new authorized API client service.
	private final Sheets service;

	// Prints the names and majors of students in a sample spreadsheet:
	// https://docs.google.com/spreadsheets/d/1M7Fibb8o28DEp2CFCLH5e90-xhGuLASy7W5uNlKbW08/edit#gid=0
	final String spreadsheetId = "1M7Fibb8o28DEp2CFCLH5e90-xhGuLASy7W5uNlKbW08";

	public SpreadSheet() {
		Sheets ser = null;
		try {
			ser = GoogleAPI.getSheetsService();
		} catch (IOException e) {
			System.out.println("Cannot create SpreadSheet");
			e.printStackTrace();
		}
		service = ser;
	}

	/**
	 * @param range
	 *            Determines what cells will be read. E.g. A1:E2
	 * @return If there is no data, empty list will be returned.
	 */
	public List<List<Object>> getData(String range) {
		ValueRange response;
		if (service != null)
			try {
				response = service.spreadsheets().values().get(spreadsheetId, range).execute();
				return response.getValues();
			} catch (IOException e) {
				System.out.println("Cannot read data at given range");
				e.printStackTrace();
			}

		return new ArrayList<>();
	}
	
	
	public String updateValue(String value, String range) {
		List<List<Object>> values = Arrays.asList(Arrays.asList(value));
		
		
		ValueRange body = new ValueRange().setValues(values);
		
		try {
			UpdateValuesResponse response = service.spreadsheets().values().update(spreadsheetId, range, body).setValueInputOption("USER_ENTERED").execute();
			return response.getUpdatedCells().toString();
		} catch (IOException e) {
			e.printStackTrace();
			
		}
		
		return "Nothing";
	
	}
}
