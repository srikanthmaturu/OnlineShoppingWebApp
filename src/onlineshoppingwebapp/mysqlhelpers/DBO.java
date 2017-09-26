package onlineshoppingwebapp.mysqlhelpers;

import onlineshoppingwebapp.data.*;

public abstract class DBO {
    
	public DBOConfig dboConfig;
    public Datatype values[];
    public boolean selected[];
    public String tablename;
    
    public DBO(){
    	
    }
    
    public void initialize(DBOConfig dboConfig) {
    	this.dboConfig = dboConfig;
		selected = new boolean[dboConfig.fieldnames.length];
		values = new Datatype[dboConfig.fieldnames.length];
		
	    for(int i =0; i<dboConfig.nooffields;i++) {
	        values[i] = new Datatype();
	        try {
				values[i].setType(dboConfig.fieldtypes[i]);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
	    }

    }
    
    public void setFieldValue(String fieldName, String value){
    	System.out.println("Size: " + selected.length + " FieldName: "+fieldName+" index: " + dboConfig.map.get(fieldName) + " Value: " + value);
    	selected[dboConfig.map.get(fieldName)] = true;
    	//System.out.println(" Here: " + map.get(fieldName) + " Value: " + value);
    	values[dboConfig.map.get(fieldName)].setValue(value);
    	//System.out.println("success");
    }
    
    public String getFieldStringTypeValue(String fieldName){
    	return	values[dboConfig.map.get(fieldName)].getStringValue(); 
    }
    
    public Object getFieldValue(String fieldName){
    	return	values[dboConfig.map.get(fieldName)].getValue(); 
    }
    
    public void setFieldValueByIndex(int index, String value){
    	selected[index] = true;
    	values[index].setValue(value);
    }
    
    public String getFieldStringTypeValueByIndex(int index){
    	return	values[index].getStringValue(); 
    }
    
    public Object getFieldValueByIndex(int index){
    	return	values[index].getValue(); 
    }
    
    public String getFieldNameByIndex(int index){
    	return dboConfig.fieldnames[index];
    }
    
    public String getFieldSQLStringValue(String fieldName){
    	return	values[dboConfig.map.get(fieldName)].getSQLStringValue(); 
    }
    
    public String getFieldSQLStringValueByIndex(int index){
    	//System.out.println(values[index].getSQLStringValue());
    	return	values[index].getSQLStringValue(); 
    }
    
    public void resetField(String fieldName){
    	selected[dboConfig.map.get(fieldName)] = false;
    	values[dboConfig.map.get(fieldName)].setValue(null);
    }
    
    public void resetFieldByIndex(Integer index){
    	selected[index] = false;
    	values[index].setValue(null);
    }
    
    
}
