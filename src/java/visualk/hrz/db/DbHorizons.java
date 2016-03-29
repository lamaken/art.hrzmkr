package visualk.hrz.db;

import java.awt.Color;
import java.sql.*;

import visualk.db.MysqlLayer;
import visualk.hrz.objects.Horizon;

public class DbHorizons {

    private MysqlLayer mySQL = new MysqlLayer();

    public DbHorizons() {
        prepareDB("server-name", "user-name", "passwd", "database-name");
    }

    public void prepareDB(String dbServer, String dbUser, String dbPassword, String dbDataBase) {
        this.mySQL.setDBValues(dbServer, dbUser, dbPassword, dbDataBase);
    }

    public void addHorizon(Horizon hrz, String authorName) {
        mySQL.executeDB("insert into hrzns (" + "nameHrz," + "dt," + "topHrz,"
                + "topHrzColor," + "bottomHrzColor," + "canvasWidth,"
                + "canvasHeigth," + "authorHrz," + "xPal," + "yPal," + "hPalx,"
                + "hPaly," + "alcada," + "colPal," + "horizontal,"
                + "aureaProp," + "superX," + "superY," + "textura," + "version) values ('"
                + hrz.getNameHrz()
                + "', "
                + "NOW()"
                + ", '"
                + hrz.getTopHrz()
                + "', '"
                + hrz.getTopHrzColor().getRGB()
                + "', '"
                + hrz.getBottomHrzColor().getRGB()
                + "', '"
                + hrz.getCanvasWidth()
                + "', '"
                + hrz.getCanvasHeigth()
                + "', '"
                + authorName
                + "', '"
                + hrz.getxPal()
                + "', '"
                + hrz.getyPal()
                + "', '"
                + hrz.gethPalx()
                + "', '"
                + hrz.gethPaly()
                + "', '"
                + hrz.getAlçada()
                + "', '"
                + hrz.getColPal().getRGB()
                + "', '"
                + hrz.isHorizontal()
                + "', '"
                + hrz.isAureaProp()
                + "', '"
                + hrz.getSuperX()
                + "', '"
                + hrz.getSuperY()
                + "', '"
                + hrz.isTextura()
                + "', '"
                + hrz.getVersion() + "')");
    }

    public ResultSet listHrzns() {
        ResultSet myResult;
        myResult = mySQL.queryDB("SELECT * FROM hrzns WHERE namehrz<>'wellcome' order by dt desc;");
        return (myResult);

    }

    public Horizon getHrznBD(String name) {

        Horizon temp = new Horizon(name);
        ResultSet myResult;
        myResult = mySQL.queryDB("SELECT * FROM hrzns where nameHrz='" + name + "'");
        if (myResult == null) {
            return temp;
        } else {
            try {
                while (myResult.next()) {
                    String nameHrz = "";
                    String topHrz = "";
                    String topHrzColor = "";
                    String bottomHrzColor = "";
                    String canvasWidth = "";
                    String canvasHeigth = "";
                    String authorHrz = "";
                    String xPal = "";
                    String yPal = "";
                    String hPalx = "";
                    String hPaly = "";
                    String alcada = "";
                    String horizontal = "";
                    String aureaProp = "";
                    String colPal = "";
                    String superX = "";
                    String superY = "";
                    String textura = "";

                    String version = "";
                    try {
                        nameHrz = myResult.getString("nameHrz");
                        topHrz = myResult.getString("topHrz");
                        topHrzColor = myResult.getString("topHrzColor");
                        bottomHrzColor = myResult.getString("bottomHrzColor");
                        canvasWidth = myResult.getString("canvasWidth");
                        canvasHeigth = myResult.getString("canvasHeigth");
                        authorHrz = myResult.getString("authorHrz");
                        xPal = myResult.getString("xPal");
                        yPal = myResult.getString("yPal");
                        hPalx = myResult.getString("hPalx");
                        hPaly = myResult.getString("hPaly");
                        alcada = myResult.getString("alcada");
                        colPal = myResult.getString("colPal");
                        horizontal = myResult.getString("horizontal");
                        aureaProp = myResult.getString("aureaProp");

                        superX = myResult.getString("superX");
                        superY = myResult.getString("superY");
                        textura = myResult.getString("textura");

                        version = myResult.getString("version");
                    } catch (SQLException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                    temp.setNameHrz(nameHrz);
                    temp.setTopHrz(Integer.parseInt(topHrz));
                    temp.setTopHrzColor(new Color(Integer.parseInt(topHrzColor)));
                    temp.setBottomHrzColor(new Color(Integer.parseInt(bottomHrzColor)));
                    temp.setCanvasWidth(Integer.parseInt(canvasWidth));
                    temp.setCanvasHeigth(Integer.parseInt(canvasHeigth));
                    temp.setAuthorHrz(authorHrz);
                    temp.setxPal(Integer.parseInt(xPal));
                    temp.setyPal(Integer.parseInt(yPal));
                    temp.sethPalx(Integer.parseInt(hPalx));
                    temp.sethPaly(Integer.parseInt(hPaly));
                    temp.setAlcada(Integer.parseInt(alcada));
                    temp.setColPal(new Color(Integer.parseInt(colPal)));
                    temp.setHorizontal(horizontal.equals("true"));
                    temp.setAureaProp(aureaProp.equals("true"));

                    temp.setSuperX(Integer.parseInt(superX));
                    temp.setSuperY(Integer.parseInt(superY));
                    temp.setTextura(textura.equals("true"));

                    temp.setVersion(version);
                }
            } catch (NumberFormatException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return (temp);
    }

}
