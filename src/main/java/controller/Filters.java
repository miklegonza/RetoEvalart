package controller;

import model.Account;
import model.Client;
import model.Table;

import java.util.ArrayList;
import java.util.List;

public class Filters {

    SQLQueries sqlQueries;
    List<Client> clients;
    List<Account> accounts;
    List<Table> tables;

    final int SEATS = 8;

    int group;
    int nMen;
    int nWomen;
    String table;
    String input;

    public Filters(String input) {
        this.input = input;
        fillLists();
        initVariables();
    }

    private void fillLists() {
        this.sqlQueries = new SQLQueries();
        this.clients = sqlQueries.listClients();
        this.accounts = sqlQueries.listAccounts();
    }

    private void initVariables() {
        this.group = 0;
        this.nMen = 0;
        this.nWomen = 0;
        this.table = "";
        this.tables = new ArrayList<>();
    }

    private void getTableData() {
        Table table = null;
        String[] lines = input.split("\\n");
        for (String line : lines) {
            if (line.charAt(0) == '<') {
                table = new Table(line.substring(1, line.length() - 1));
                tables.add(table);
            }
            if (table != null && line.charAt(0) == 'T' && line.charAt(1) == 'C')
                table.setTc(Integer.parseInt(line.substring(3)));
            if (table != null && line.charAt(0) == 'U' && line.charAt(1) == 'G')
                table.setUg(Integer.parseInt(line.substring(3)));
            if (table != null && line.charAt(0) == 'R' && line.charAt(1) == 'I')
                table.setRi(Double.parseDouble(line.substring(3)));
            if (table != null && line.charAt(0) == 'R' && line.charAt(1) == 'F')
                table.setRf(Double.parseDouble(line.substring(3)));

            for (Table tbl : tables) {
                System.out.println(tbl);
            }
        }
    }

    public void procesar() {
        getTableData();
    }

    public void printOutput() {

    }

}
