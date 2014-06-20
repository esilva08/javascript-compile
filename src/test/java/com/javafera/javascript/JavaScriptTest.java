package com.javafera.javascript;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.Test;
import org.mozilla.javascript.NativeJavaObject;

import com.javafera.javascript.domain.Row;
import com.javafera.javascript.domain.Table;
import com.javafera.javascript.executor.JavaScript;

public class JavaScriptTest {
 
	@Test
	public void shouldSuccessfullyExecutePrinterConsole() throws IOException{
		JavaScript.execute(FileUtils.readFileToString(new File("scripts/script-print-console.js")));
	}
	
	@Test
	public void shouldSuccessfullyCreateObjectPerson() throws IOException{
		JavaScript.execute(FileUtils.readFileToString(new File("scripts/script-create-person.js")));
	}
	
	@Test
	public void shouldSuccessfullyUpdateWithParameters() throws IOException{
		Object result = JavaScript.execute(FileUtils.readFileToString(new File("scripts/script-create-table.js")));
		NativeJavaObject nativeJavaObject = (NativeJavaObject) result;
		Table tableResultOne = (Table) nativeJavaObject.unwrap();
		
		assertThat(tableResultOne.getRows().size(), is(2));
		
		Row row = new Row("ABC", "123");
		String script = "table.getRowAt(0).set('GHI', '789'); table.add(row); table;";

		result = JavaScript.execute(script, mountingParams(tableResultOne, row));
		Table tableResultTwo = (Table) result;
		
		assertThat(tableResultTwo.getRows().size(), is(3));
		assertThat(tableResultTwo.getRowAt(0).get("GHI"), equalTo("789"));
	}

	private Map<String, Object> mountingParams(Table tableResultOne, Row row) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("table", tableResultOne);
		parameters.put("row", row);
		return parameters;
	}
}
