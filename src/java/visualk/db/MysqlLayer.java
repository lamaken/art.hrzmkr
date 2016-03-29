package visualk.db;


/** Capa pel mySQL
*
* @autor alex
*/
import java.sql.*;

public class MysqlLayer {
	
   protected Connection dbConn=null;
   private String dbServer=null;
   private String dbUser=null;
   private String dbPassword=null;
   private String dbDataBase=null;
   
   protected ResultSet result=null;
   
   /** Crea la instÃ ncia de sqlLayer */
   public MysqlLayer() {
	   result = null;
   }

   public void setDBValues(String dbServer, String dbUser, String dbPassword, String dbDataBase) {
       this.dbPassword=dbPassword;
       this.dbServer=dbServer;
       this.dbPassword=dbPassword;
       this.dbDataBase=dbDataBase;
       this.dbUser=dbUser;
   }
   public void executeDB(String sqlQuery){
	   PreparedStatement sql=null;
	   try {
		   Class.forName("com.mysql.jdbc.Driver");
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   String cadenaConn="jdbc:mysql://"+dbServer+":3306/"+dbDataBase;
	   try {
		dbConn = DriverManager.getConnection(cadenaConn,dbUser,dbPassword);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   try {
		sql = dbConn.prepareStatement(sqlQuery);
                dbConn.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   try {
		sql.execute();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
        
   }
   public ResultSet queryDB(String sqlQuery){
       PreparedStatement sql=null;
        try {
		   Class.forName("com.mysql.jdbc.Driver");
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	try
	   {
	       String cadenaConn="jdbc:mysql://"+dbServer+":3306/"+dbDataBase;
	       dbConn = DriverManager.getConnection(cadenaConn,dbUser,dbPassword);
	       sql = dbConn.prepareStatement(sqlQuery);
	       result=sql.executeQuery();
               // Close the result set, statement and the connection
               dbConn.close();
	       
	   }
	   catch (SQLException s)
	   {
		s.printStackTrace();
	    System.out.println("SQL Error on Open");
            //TODO:control d'excepcio
            
	   }
        
      return(result);
   }
}