package ib;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class ClientDAO {

    private int newIdForClient;

    public int getNewIdForClient() {
        return newIdForClient;
    }

    private DBUtil dbUtil;

    public ClientDAO(DBUtil dbUtil) {
        this.dbUtil = dbUtil;
    }

    private ObservableList<ReclamationStatus> getReclamationList(ResultSet resultSet) throws SQLException {

        ObservableList<ReclamationStatus> reclamations = FXCollections.observableArrayList();

        while (resultSet.next()) {
            ReclamationStatus status = new ReclamationStatus();
            status.setClientID(resultSet.getInt("client_id"));
            status.setDeviceName(resultSet.getString("device_name"));
            status.setSerialNo(resultSet.getString("serial_no"));
            status.setPrice(resultSet.getDouble("price"));
            status.setReclamationStatus(resultSet.getString("reclamation_status"));
            reclamations.add(status);
        }
        return reclamations;
    }


    public ObservableList<ReclamationStatus> showReclamations(String id) throws SQLException, ClassNotFoundException {

        String selectClient = "SELECT * FROM reclamation_status_view WHERE client_id = " + id + ";";

        try {
            ResultSet resultSet = dbUtil.dbExecuteQuery(selectClient);
            ObservableList<ReclamationStatus> reclamationStatus = getReclamationList(resultSet);
            return reclamationStatus;
        } catch (SQLException e) {
            throw e;
        }

    }


    public void insertReclamation(String serialNo, String device, String type, String price, String date, String id) throws SQLException, ClassNotFoundException {

        String status = "reported";
        String recPrice = "0.0";
        LocalDate recDate = LocalDate.now();
        String recDate2 = recDate.toString();

        StringBuilder dev = new StringBuilder("INSERT INTO Device(serial_no, device_name, type, device_price, bought_date) VALUES('");
        dev.append(serialNo);
        dev.append("','");
        dev.append(device);
        dev.append("','");
        dev.append(type);
        dev.append("','");
        dev.append(price);
        dev.append("','");
        dev.append(date);
        dev.append("')");
        String insertDevice = dev.toString();

        StringBuilder rec = new StringBuilder("INSERT INTO Reclamations(price, reclamation_date, client_id, related_device, reclamation_status) VALUES('");
        rec.append(recPrice);
        rec.append("','");
        rec.append(recDate2);
        rec.append("','");
        rec.append(id);
        rec.append("','");
        rec.append(serialNo);
        rec.append("','");
        rec.append(status);
        rec.append("')");
        String insertReclamation = rec.toString();

        try {
            dbUtil.dbExecuteUpdate(insertDevice);
            dbUtil.dbExecuteUpdate(insertReclamation);
        } catch (SQLException e) {
            throw e;
        }

    }

    public int getNewID() throws SQLException, ClassNotFoundException {

        String selectClient = "SELECT MAX(id) FROM Clients;";
        int result = 0;

        try {
            ResultSet resultSet = dbUtil.dbExecuteQuery(selectClient);
            if (resultSet.next()) {
                result = resultSet.getInt(1);
            }
            return result;
        } catch (SQLException | ClassNotFoundException e) {
            throw e;
        }

    }

    public void insertNewClient(String firstName, String lastName, String email, String phone) throws SQLException, ClassNotFoundException {

        int newID = getNewID() + 1;
        newIdForClient = newID;

        StringBuilder client = new StringBuilder("INSERT INTO Clients(id, name, phone_number, email) VALUES('");
        client.append(newID);
        client.append("','");
        client.append(firstName);
        client.append(" ");
        client.append(lastName);
        client.append("','");
        client.append(phone);
        client.append("','");
        client.append(email);
        client.append("')");
        String insertClient = client.toString();

        try {
            dbUtil.dbExecuteUpdate(insertClient);
        } catch (SQLException e) {
            throw e;
        }

    }


    public void setZero() throws SQLException, ClassNotFoundException {

        String set_0 = "SET FOREIGN_KEY_CHECKS=0;";

        try {
            dbUtil.dbExecuteUpdate(set_0);
        } catch (SQLException | ClassNotFoundException e) {
            throw e;
        }

    }

    public void setOne() throws SQLException, ClassNotFoundException {

        String set_1 = "SET FOREIGN_KEY_CHECKS=1;";

        try {
            dbUtil.dbExecuteUpdate(set_1);
        } catch (SQLException | ClassNotFoundException e) {
            throw e;
        }
    }






}
