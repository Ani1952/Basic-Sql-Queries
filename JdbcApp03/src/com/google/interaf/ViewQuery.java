package com.google.interaf;

import java.sql.ResultSet;

public interface ViewQuery {
	String viewTable(ResultSet rs,String tName);

}
