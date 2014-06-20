package com.javafera.javascript.domain;

import java.util.ArrayList;
import java.util.List;

public class Table {
	
	private List<Row> rows;
	
	public List<Row> getRows() {
		return rows;
	}
	
	public Table(){
		this.rows = new ArrayList<Row>();
	}

	public Table(List<Row> rows) {
		this.rows = rows;
	}
	
	public Row getRowAt(Integer index){
		return rows.get(index);
	}
	
	public void add(Row row){
		rows.add(row);
	}

}