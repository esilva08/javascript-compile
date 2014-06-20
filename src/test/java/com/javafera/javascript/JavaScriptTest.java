package com.javafera.javascript;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.mozilla.javascript.NativeJavaObject;
import org.mozilla.javascript.Undefined;

import com.javafera.javascript.domain.Row;
import com.javafera.javascript.domain.Table;
import com.javafera.javascript.executor.JavaScript;

public class JavaScriptTest {
 
	@Test
	public void shouldSuccessfullyExecutePrinterConsole() throws IOException{
		Object result = JavaScript.execute(FileUtils.readFileToString(new File("scripts/script-print-console.js")));
		assertThat(result, instanceOf(Undefined.class));
	}
	
	@Test
	public void shouldSuccessfullyCreateObjectPerson() throws IOException{
		Object result = JavaScript.execute(FileUtils.readFileToString(new File("scripts/script-create-person.js")));
		assertThat(result, instanceOf(Undefined.class));
	}
	
	@Test
	public void shouldSuccessfullyResultScript() throws IOException{
		Object result = JavaScript.execute(FileUtils.readFileToString(new File("scripts/script-create-table.js")));
		NativeJavaObject nativeJavaObject = (NativeJavaObject) result;
		Table tableResultOne = (Table) nativeJavaObject.unwrap();
		
		assertThat(tableResultOne.getRows().size(), is(2));
		assertThat(tableResultOne.getRowAt(0).get("ABC"), equalTo("123"));
		assertThat(tableResultOne.getRowAt(1).get("DEF"), equalTo("456"));
	}
	
	@Test
	public void shouldSuccessfullyUpdateObjectUsingScript(){
		Row row = new Row("DEF", "456");
		String script = "table.getRowAt(0).set('GHI', '789'); table.add(row);table;";

		List<Row> rows = new ArrayList<Row>();
		rows.add(new Row("ABC", "123"));
		Table table = new Table(rows);
		
		Object result = JavaScript.execute(script, mountingParamsWith(table, row));
		Table tableResultTwo = (Table) result;
		
		assertThat(tableResultTwo.getRows().size(), is(2));
		assertThat(tableResultTwo.getRowAt(0).get("GHI"), equalTo("789"));
		assertThat(tableResultTwo.getRowAt(1).get("DEF"), equalTo("456"));
	}

	private Map<String, Object> mountingParamsWith(Table tableResultOne, Row row) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("table", tableResultOne);
		parameters.put("row", row);
		return parameters;
	}
}
