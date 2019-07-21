package com.prabha.service;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
public class EditorService {
              Connection con = null;
              Statement st = null;
              ResultSet rs = null; 
       
 public EditorService(){

       try {
               Class.forName("oracle.jdbc.OracleDriver");
               con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "---------");
                st = con.createStatement();
} 
       catch (Exception e) {
            e.printStackTrace();
}
}
         public boolean executeQuery(String query) {
              
             boolean query_Status = false;
                  try {
                 query_Status = st.execute(query);
            }        
                  catch (Exception e) {
                     e.printStackTrace();
            }
      return query_Status;
}

                 public ArrayList<String> getData() {
                  ArrayList<String> data = new ArrayList<String>();
                   try {
                  rs = st.getResultSet();
                  ResultSetMetaData md = rs.getMetaData();
                   int col_Count = md.getColumnCount();
                    String col_Names = "";
                   for(int i = 1; i <= col_Count; i++) {
                   col_Names = col_Names + md.getColumnName(i)+" ";
}
                       data.add(col_Names);
                       data.add("------------------------");

                  while(rs.next()) {
            String rec_Data = "";
            for(int i = 1; i <= col_Count; i++) {
            rec_Data = rec_Data + rs.getString(i) + " ";
          }
       data.add(rec_Data);
    }
}
                   catch (Exception e) {
              e.printStackTrace();
          }
          return data;
     }

                 public int getRowCount() {
               int rowCount = 0;
  
               try {
                   rowCount = st.getUpdateCount();
               }
            
               catch (Exception e) {
               e.printStackTrace();
            }
 return rowCount;
}
