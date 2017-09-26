package onlineshoppingwebapp.mysqlhelpers;

import java.util.HashMap;

public class DBOConfig {
	public String tableName;
	public int nooffields;
	public String fieldnames[];
	public String fieldtypes[];
	public HashMap<String,Integer> map;
	public boolean initialized = false;

	DBOConfig(String tableName, String[] fieldNames, String[] fieldTypes){
		this.tableName = tableName;
		fieldnames = fieldNames;
		fieldtypes = fieldTypes;
		nooffields = fieldNames.length;
		initialized = true;

		if(map==null){
			map = new HashMap<>();
			for(int i =0; i<nooffields; i++){
				map.put(fieldnames[i], i);
			}
		}
	}

}
