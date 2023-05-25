package com.google.interaf;

import java.sql.ResultSet;
import java.util.ArrayList;

public interface InsertQuery{
ArrayList<String> returnQuerySet(ResultSet rs,String tName);
	

}
