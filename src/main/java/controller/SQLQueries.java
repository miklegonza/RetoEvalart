package controller;

import model.Account;
import model.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

public class SQLQueries {

    private Connection connection;
    private PreparedStatement ps;
    private Account account;
    private Client client;

    public SQLQueries() {
        this.connection = ConnectionDB.getConnection();
    }

    public Client getClient(int id) {
        ResultSet rs;
        try {
            String query = "SELECT * FROM " + ConnectionDB.DB + ".client WHERE id = ?;";

            connection = ConnectionDB.getConnection();

            ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.first())
                client = new Client(
                        rs.getInt("id"),
                        rs.getString("code"),
                        rs.getByte("male"),
                        rs.getInt("type"),
                        rs.getString("location"),
                        rs.getString("company"),
                        rs.getByte("encrypt")
                );

            return client;
        } catch (SQLException e) {
            System.out.println("SQL error. Client not found");
            System.out.println("Causa: " + e.getMessage());
            System.out.println("Causa: " + e.getSQLState());
        }
        return null;
    }

    public List<Client> listClients() {
        ResultSet rs;
        List<Client> clients = new ArrayList<>();
        try {
            String query = "SELECT * FROM " + ConnectionDB.DB + ".client;";

            connection = ConnectionDB.getConnection();

            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                clients.add(new Client(
                        rs.getInt("id"),
                        rs.getString("code"),
                        rs.getByte("male"),
                        rs.getInt("type"),
                        rs.getString("location"),
                        rs.getString("company"),
                        rs.getByte("encrypt")
                ));
            }

            return clients;
        } catch (SQLException e) {
            System.out.println("SQL error. Accounts not found");
            System.out.println("Causa: " + e.getMessage());
            System.out.println("Causa: " + e.getSQLState());
        }
        return null;
    }

    public List<Client> listClientsWithBalance() {
        ResultSet rs;
        List<Client> clients = new ArrayList<>();
        try {
            String query = "SELECT " +
                    "client.*, " +
                    "tb.balance AS total_balance " +
                    "FROM " +
                    ConnectionDB.DB + ".client AS client, " +
                    "(SELECT client_id, SUM(balance) AS balance FROM " + ConnectionDB.DB + ".account GROUP BY client_id) AS tb " +
                    "WHERE " +
                    "client.id = tb.client_id " +
                    "ORDER BY " +
                    "total_balance DESC;";

            connection = ConnectionDB.getConnection();

            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                clients.add(new Client(
                        rs.getInt("id"),
                        rs.getString("code"),
                        rs.getByte("male"),
                        rs.getInt("type"),
                        rs.getString("location"),
                        rs.getString("company"),
                        rs.getByte("encrypt"),
                        rs.getDouble("total_balance")
                ));
            }

            return clients;
        } catch (SQLException e) {
            System.out.println("SQL error. Accounts not found");
            System.out.println("Causa: " + e.getMessage());
            System.out.println("Causa: " + e.getSQLState());
        }
        return null;
    }

    public Account getAccount(int id) {
        ResultSet rs;
        try {
            String query = "SELECT * FROM " + ConnectionDB.DB + ".account WHERE id = ?;";

            connection = ConnectionDB.getConnection();

            ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.first())
                account = new Account(
                        rs.getInt("id"),
                        getClient(rs.getInt("client_id")),
                        rs.getDouble("balance")
                );

            return account;
        } catch (SQLException e) {
            System.out.println("SQL error. Account not found");
            System.out.println("Causa: " + e.getMessage());
            System.out.println("Causa: " + e.getSQLState());
            e.printStackTrace();
        }
        return null;
    }

    public List<Account> listAccounts() {
        ResultSet rs;
        List<Account> accounts = new ArrayList<>();
        try {
            String query = "SELECT * FROM " + ConnectionDB.DB + ".account;";

            connection = ConnectionDB.getConnection();

            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                accounts.add(new Account(
                        rs.getInt("id"),
                        getClient(rs.getInt("client_id")),
                        rs.getDouble("balance")
                ));
            }

            return accounts;
        } catch (SQLException e) {
            System.out.println("SQL error. Accounts not found");
            System.out.println("Causa: " + e.getMessage());
            System.out.println("Causa: " + e.getSQLState());
        }
        return null;
    }

}
