/*
 * Bassil Alcheikh, 2019
 * Takes an arraylist of values and their proper datatypes and encapsulates them accordingly, before placing them in 
 * an "insert to" statement
*/
import java.util.*;

public class GenSQLStatements {

	private String tableName; // do we want this to be an object later?
	//private int valueLimit; // maybe add later? Ask professor how?
	
	// (will probably not use)
	public GenSQLStatements(String given_tableName) {
		tableName = given_tableName;
	}

	public String encapsulation(String data_type, String data_value) {
		String substring = "";
		
		if(data_type.equals("int") || data_type.equals("double")) {
			substring += data_value; 
		}
		else if (data_type.equals("String") || data_type.equals("char")) {
			substring += "'"+data_value+"'";
		}
		else {
			substring += "''";
		}
		return substring;
	}
	
	public String getValueStatement(ArrayList<String> given_data_vals, ArrayList<String> given_data_types) {
		
		int dimension = given_data_vals.size();
		
		String insert_statement = "(";
		for(int i = 0; i < dimension-1; i++) {
			String val = given_data_vals.get(i);
			String val_type = given_data_types.get(i);
			
			insert_statement += encapsulation(val_type, val)+", "; 
		}
		return insert_statement+encapsulation(given_data_types.get(dimension-1), given_data_vals.get(dimension-1))+")";
	}
	/*
	public String getInsertStatement(ArrayList<String> given_data_types, ArrayList<String> given_data_vals) {
		return "INSERT INTO "+tableName+" VALUES "+getValueStatement(given_data_vals, given_data_types);
	}
	*/
	public String getInsertStatement(String[][] given_data_vals, ArrayList<String> given_data_types) {
		String masterInsertStatement = "INSERT INTO "+tableName+" VALUES ";
		int data_count = given_data_vals.length;
		
		for(int datapoint = 0; datapoint < data_count-1; datapoint++) {
			ArrayList<String> singleDataArray = (new ArrayList<String>(Arrays.asList(given_data_vals[datapoint])));
			masterInsertStatement += getValueStatement(singleDataArray, given_data_types)+",\n";
		}
		masterInsertStatement += getValueStatement((new ArrayList<String>(Arrays.asList(given_data_vals[data_count-1]))), given_data_types);
	
		return masterInsertStatement+";";
	}

}
