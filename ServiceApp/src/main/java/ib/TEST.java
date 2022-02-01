package ib;

import java.sql.*;

public class TEST {
    public static void main(String[] args) {

        DBUtil dbAccess = new DBUtil("root", "Magicalnature2");
        try {
            dbAccess.dbConnect();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            ResultSet rsTable = dbAccess.dbExecuteQuery("SELECT * FROM Reclamations");
            printResultSet(rsTable);
            rsTable.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    public static void printResultSet(ResultSet resultSet) throws SQLException {
        ResultSetMetaData rsmd = resultSet.getMetaData();
        int columnCount = rsmd.getColumnCount();
        while (resultSet.next()) {

            for (int i = 1; i <= columnCount; i++) {
                String colVal = resultSet.getString(i);
                System.out.print(colVal + "\t");
            }
            System.out.println();
        }

    }
}


