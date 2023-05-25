package com.google.interaf;

import java.sql.ResultSet;
import java.util.ArrayList;

public interface DeleteQuery {
	//return type is array list to return set of queries
	ArrayList<String> returnQuerySet(ResultSet rs,String tName);
}
