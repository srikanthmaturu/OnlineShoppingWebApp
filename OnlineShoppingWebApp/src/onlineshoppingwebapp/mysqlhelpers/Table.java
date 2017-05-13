package onlineshoppingwebapp.mysqlhelpers;

import onlineshoppingwebapp.logger.LogPrinter;
import onlineshoppingwebapp.models.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Srikanth
 */
public class Table<T extends DBO> {

	public Connection connection = null;
	public String tablename;
	public Class<T> uDBOClass;
	public DBOConfig uDBOConfig;

	public Table(String serverName, Class<T> uDBOClass, DBOConfig uDBOConfig, String tableName){
		this.uDBOClass = uDBOClass;
		this.uDBOConfig = uDBOConfig;
		tablename = tableName;
		if(connection == null){
			connection = Database.connect(serverName);
		}
	}

	public ArrayList<T> select(String whereclause, long min_id, int count) {

		ArrayList<T> dbos = new ArrayList<T>();
		String statement = "SELECT * ";
		statement =statement.concat(" from "+tablename);
		if(whereclause!=null){
			statement =statement.concat(" where "+whereclause); }
		else{
			if(min_id > 0){
				statement =statement.concat(" where ");
				statement =statement.concat(" id >"+ min_id);
			}
		}
		
		statement =statement.concat(" ORDER BY Id ASC ");
		if(count >= 0){
			statement =statement.concat(" LIMIT "+count);
		}
		statement = statement.concat(";");
		ResultSet rs;
		try{
			LogPrinter.printLog(" Select Statement Prepared "+statement);

			PreparedStatement ps = connection.prepareStatement(statement);

			rs = ps.executeQuery();

			while(rs.next()){
				System.out.println(uDBOClass.getName());
				T dbo = uDBOClass.newInstance();
				
				for(int i=0;i<uDBOConfig.nooffields;i++){
					String value = rs.getString(i+1);  
					if(value!=null) {
						dbo.setFieldValueByIndex(i, value);
					}
				}
				dbos.add(dbo);
			}
			ps.close();
		}
		catch(Exception e){
			e.printStackTrace();

		}
		return dbos;
	}

	public ArrayList<T> select(boolean[] selected, String whereclause, long min_id, int count) {
		ArrayList<T> dbos = new ArrayList<T>();
		String statement = "SELECT ";
		boolean firstentry = true;
		for(int i=0; i<uDBOConfig.nooffields;i++)
		{
			if(selected[i]){
				if(!firstentry){
					statement =statement.concat(",");
				}
				statement =statement.concat(uDBOConfig.fieldnames[i]);
				firstentry = false;
			}
		}
		statement = statement.concat(" from "+tablename);

		if(whereclause!=null){
			statement = statement.concat(" where "+whereclause+" AND "); }
		else{
			statement = statement.concat(" where ");
		}
		statement = statement.concat(" id >"+ min_id);
		statement = statement.concat(" ORDER BY id ASC ");
		statement = statement.concat(" LIMIT "+count);
		LogPrinter.printLog(statement);
		ResultSet rs;
		try{
			PreparedStatement ps = connection.prepareStatement(statement);
			rs = ps.executeQuery();

			while(rs.next()){
				T dbo = uDBOClass.newInstance();
				int index = 0;
				for(int i=0;i<uDBOConfig.nooffields;i++){
					if(selected[i]){
						String value = rs.getString(index+1);
						index++;
						if(value!=null)
							dbo.setFieldValueByIndex(i, value);
					}
				}
				dbos.add(dbo);
			}
			ps.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}

		return dbos;
	}

	public ArrayList<T> select(boolean[] selected, T sdbo, boolean[] dboSelectionForWhereClause, int count) {
		if(selected == null){
			selected = new boolean[dboSelectionForWhereClause.length];
			Arrays.fill(selected, true);
		}

		ArrayList<T> dbos = new ArrayList<T>();
		String statement = "SELECT ";
		boolean firstentry = true;
		for(int i=0; i<uDBOConfig.nooffields;i++)
		{
			if(selected[i]){
				if(!firstentry){
					statement =statement.concat(", ");
				}
				statement = statement.concat(uDBOConfig.fieldnames[i]);
				firstentry = false;
			}
		}
		statement = statement.concat(" from "+tablename);

		String whereClause = null;
		for(int i=0; i<dboSelectionForWhereClause.length; i++){
			if(dboSelectionForWhereClause[i]){
				if(whereClause == null){
					whereClause = " where ";
				}
				else{
					whereClause = whereClause.concat(" AND ");
				}
				whereClause = whereClause.concat(" " + sdbo.getFieldNameByIndex(i) + " = " + sdbo.getFieldSQLStringValueByIndex(i) );
			}
		}

		if(whereClause!=null){
			statement = statement.concat(whereClause); 
		}

		statement = statement.concat(" ORDER BY id ASC ");
		statement = statement.concat(" LIMIT "+count);
		ResultSet rs;
		try{
			PreparedStatement ps = connection.prepareStatement(statement);
			LogPrinter.printLog(statement);
			rs = ps.executeQuery();

			while(rs.next()){
				T dbo = uDBOClass.newInstance();
				int index = 0;
				for(int i=0;i<uDBOConfig.nooffields;i++){
					if(selected[i]){
						String value = rs.getString(index+1);
						index++;
						if(value!=null)
							dbo.setFieldValueByIndex(i, value);
					}
				}
				dbos.add(dbo);
			}

			ps.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}

		return dbos;
	}


	public void insert(T dbo) {
		String statement = "insert into "+tablename+"(";
		String values = " values(";
		boolean firstentry = true;
		for(int i=0; i<uDBOConfig.nooffields;i++) {
			if(dbo.values[i].used){
				if(!firstentry) {
					statement = statement.concat(",");
					values = values.concat(",");
				}
				else{
					firstentry = false;
				}
				statement = statement.concat(""+uDBOConfig.fieldnames[i]+"");
				values = values.concat(dbo.values[i].getSQLStringValue());
			}
			if(i==uDBOConfig.nooffields-1) {
				statement = statement.concat(") ");
				values = values.concat(");");
			}
		}
		try {
			PreparedStatement ps = connection.prepareStatement(statement+values);
			System.out.println(statement+values);
			ps.executeUpdate();
			ps.close();
		}
		catch(Exception e){
			e.printStackTrace();
		} 
	}

	public void insert(T dbo, boolean[] selection) {
		String statement = "insert into "+tablename+"(";
		String values = " values(";
		boolean firstentry = true;
		for(int i=0; i<uDBOConfig.nooffields;i++) {
			if(selection[i]){
				if(!firstentry) {
					statement = statement.concat(",");
					values = values.concat(",");
				}
				else{
					firstentry = false;
				}
				statement = statement.concat(""+uDBOConfig.fieldnames[i]+"");
				values = values.concat(dbo.values[i].getSQLStringValue());
			}
			if(i==uDBOConfig.nooffields-1) {
				statement = statement.concat(") ");
				values = values.concat(");");
			}
		}
		try {
			PreparedStatement ps = connection.prepareStatement(statement+values);
			//LogPrinter.printLog(statement+values);
			ps.executeUpdate();
			ps.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}   
	}

	public Integer createNewEntryReturnId(){
		try {
			String statement = "INSERT into " +  tablename + "() VALUES () ;";
			LogPrinter.printLog(statement);
			PreparedStatement ps;
			ResultSet rs;
			ps = connection.prepareStatement(statement);
			ps.executeUpdate();
			ps = connection.prepareStatement("SELECT LAST_INSERT_ID();");
			rs = ps.executeQuery();

			rs.next();
			Integer rowIndex = rs.getInt(1);
			//LogPrinter.printLog(rowIndex.toString());
			ps.close();
			return rowIndex;
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return -1;

	}

	public void update(T dbo, boolean[] selected, String whereclause) {
		String statement = "UPDATE "+tablename;
		boolean firstentry = true;
		for(int i=0; i<uDBOConfig.nooffields;i++){
			if(selected[i]){
				if(!firstentry){
					statement = statement.concat(",");
				}
				else{
					statement = statement.concat(" set ");
					firstentry = false;
				}
				statement = statement.concat(uDBOConfig.fieldnames[i]+"="+dbo.values[i].getSQLStringValue());
			}
		}
		if(whereclause!=null){
			statement = statement.concat(" where ");
			statement = statement.concat(whereclause);
		}
		statement = statement.concat(";");
		LogPrinter.printLog(statement);
		try {
			PreparedStatement ps = connection.prepareStatement(statement);
			ps.executeUpdate();
			ps.close();
		}
		catch(Exception e){

		}
	}

	public void delete(T dbo, boolean[] dboSelectionForWhereClause) {
		String statement = "DELETE FROM " + tablename + " where ";
		boolean firstentry = true;
		for(int i=0; i < uDBOConfig.nooffields; i++){
			if(dboSelectionForWhereClause[i]){
				if(!firstentry){
					statement = statement.concat(" AND ");
				}else {
					firstentry = false;
				}
				statement = statement.concat(uDBOConfig.fieldnames[i]+"="+dbo.values[i].getSQLStringValue());
			}
		}
		statement = statement.concat(";");
		LogPrinter.printLog(statement);
		try {
			PreparedStatement ps = connection.prepareStatement(statement);
			ps.executeUpdate();
			ps.close();
		}
		catch(Exception e){

		}
	}
	
	public ArrayList<T> search(boolean[] selected, String[] searchWords){
		ArrayList<T> dbos = new ArrayList<T>();
		StringBuffer statement = new StringBuffer();
		statement.append("SELECT * FROM "+tablename+" WHERE ");
		boolean firstEntry = true;
		for(int i = 0; i < selected.length; i++){
			if(selected[i]){
				for(String word : searchWords){
					if(!firstEntry){
						statement.append(" OR ");
					}
					else{
						firstEntry = false;
					}
					statement.append(uDBOConfig.fieldnames[i]+" LIKE \"%"+word+"%\"");
				}
			}
		}
		statement.append(";");
		ResultSet rs;
		try{
			LogPrinter.printLog(statement.toString());

			PreparedStatement ps = connection.prepareStatement(statement.toString());
			rs = ps.executeQuery();

			while(rs.next()){
				System.out.println(uDBOClass.getName());
				T dbo = uDBOClass.newInstance();
				
				for(int i=0;i<uDBOConfig.nooffields;i++){
					String value = rs.getString(i+1);  
					if(value!=null) {
						dbo.setFieldValueByIndex(i, value);
					}
				}
				dbos.add(dbo);
			}
			ps.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return dbos;
	}
	
	public void modifyFieldValueByOperator(String fieldName, Double value, String operator, String whereClause){
		StringBuffer statement = new StringBuffer();
		statement.append(" UPDATE " + tablename + " SET " + fieldName + " = " + fieldName + " "+operator+" " + value.toString() + " WHERE " + whereClause + " ;");
		int rs;
		try{
			LogPrinter.printLog(statement.toString());

			PreparedStatement ps = connection.prepareStatement(statement.toString());
			rs = ps.executeUpdate();
			ps.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

}
