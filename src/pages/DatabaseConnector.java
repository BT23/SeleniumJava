package pages;

import java.sql.DriverManager;
import java.sql.Connection;
import  java.sql.ResultSet;	
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnector {
    private static String dbusername = "sa";
    private static String dbpassword = "xxx";
	Connection connection;
	Statement stmt;
    
    //Creating a connection to the database
   private static String dbURL = "jdbc:sqlserver://BonnieLaptop\\SQL2014;DatabaseName=AutoTest";

   private void connectDB(){
	   try {
           Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
       }catch(ClassNotFoundException e) {
           e.printStackTrace();
       }
	   
	   try {
           connection = DriverManager.getConnection(dbURL,dbusername,dbpassword);
           if(connection!=null) {
               System.out.println("Connected to the database...");
           }else {
               System.out.println("Database connection failed");
           }
       }catch(SQLException sqlEx) {
           System.out.println( "SQL Exception:" +sqlEx.getStackTrace());
       }
   }
   
   public void executeSQLQuery(String sqlQuery){
	   try {
           this.connectDB();
           stmt = connection.createStatement();
           stmt.executeQuery(sqlQuery);

           connection.close();
       }catch(SQLException sqlEx) {
           System.out.println( "SQL Exception:" +sqlEx.getStackTrace());
       }
   }
   
   public void getResultSet(String sqlQuery){
	   try{
		   this.connectDB();
		   stmt = connection.createStatement();
		   
		   // Execute the SQL Query. Store results in ResultSet		
			ResultSet rs= stmt.executeQuery(sqlQuery);							
	
			// While Loop to iterate through all data and print results		
			while (rs.next()){
		        		String myName = rs.getString(1);								        
	                   String myAge = rs.getString(2);					                               
	                   System. out.println(myName+"  "+myAge);		
	           }		
				 // closing DB Connection		
				connection.close();
	   }
	   catch(SQLException sqlEx) {
           System.out.println( "SQL Exception:" +sqlEx.getStackTrace());
       }
   }
}
