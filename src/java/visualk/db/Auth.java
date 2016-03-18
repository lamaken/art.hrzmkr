package visualk.db;

import java.sql.*;


public class Auth {
    
    private MysqlLayer mySQL=new MysqlLayer();
    private ResultSet myResult;
    
    public void prepareDB(String dbServer,String dbUser, String dbPassword,String dbDataBase){   
    	this.mySQL.setDBValues(dbServer, dbUser, dbPassword, dbDataBase);
        
    } 
                     
    public Auth(){
    	prepareDB("localhost","git-user","password","github");
    }
  
    public boolean canEnter(String alias, String clau){ 
        this.myResult=mySQL.queryDB("select * from login where USER_NAME='"+alias+"' and USER_PASSW='"+clau+"'");
        if(myResult!=null){
            try{
                if(this.myResult.first()){
                	this.myResult.close();
                	mySQL.dbConn.close();
                	return true;
                }
                else return false;
            }
            catch (SQLException s)
            {
            	s.printStackTrace();
            	System.out.println("Error en myResult");
            }
        }
        return false;
    }

}