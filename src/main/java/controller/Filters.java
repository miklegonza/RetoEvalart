package controller;

import model.Account;
import model.Client;
import model.Table;

import java.io.*;

import java.net.HttpURLConnection;
import java.net.URL;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Filters {

    SQLQueries sqlQueries;
    List<Client> clients;
    List<Account> accounts;
    List<Table> tables;
    List<Client> guests;

    final int SEATS = 8;

    int group;
    int nMen;
    int nWomen;
    String input;

    public Filters(String input) {
        this.input = input;
        fillLists();
        initVariables();
    }

    private void fillLists() {
        this.sqlQueries = new SQLQueries();
        this.clients = sqlQueries.listClientsWithBalance();
        this.accounts = sqlQueries.listAccounts();
    }

    private void initVariables() {
        this.group = 0;
        this.nMen = 0;
        this.nWomen = 0;
        this.tables = new ArrayList<>();
    }

    private boolean same() {
        return nWomen == nMen;
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
        }
    }

    public void applyFilters() {
        getTableData();

        List<Client> auxTc;
        List<Client> auxUg;
        List<Client> auxRi;
        List<Client> auxRf;
        List<Client> auxDef;

        for (Table table : tables) {
            auxTc = clients.stream()
                    .filter(client -> table.getTc() != 0 && client.getType().equals(table.getTc()))
                    .collect(Collectors.toList());
            auxUg = clients.stream()
                    .filter(client -> table.getUg() != 0 && client.getLocation().equals(String.valueOf(table.getUg())))
                    .collect(Collectors.toList());
            auxRi = clients.stream()
                    .filter(client -> table.getRi() != 0 && client.getTotalBalance() > (table.getRi()))
                    .collect(Collectors.toList());
            auxRf = clients.stream()
                    .filter(client -> table.getRf() != 0 && client.getTotalBalance() < (table.getRf()))
                    .collect(Collectors.toList());

            if (lists(auxTc, auxUg, auxRi, auxRf))
                auxDef = Stream.of(auxTc, auxUg, auxRi, auxRf)
                        .flatMap(Collection::stream)
                        .collect(Collectors.groupingBy(element -> element))
                        .entrySet()
                        .stream()
                        .filter(repeated -> repeated.getValue().size() > 1)
                        .map(Map.Entry::getKey)
                        .collect(Collectors.toList());
            else
                auxDef = Stream.of(auxTc, auxUg, auxRi, auxRf)
                        .flatMap(Collection::stream)
                        .collect(Collectors.toList());

            Comparator<Client> comparator = Comparator.comparing(client -> client.getTotalBalance());
            auxDef = auxDef.stream()
                    .sorted(comparator.reversed())
                    .collect(Collectors.toList());

            int count = Math.min(auxDef.size(), SEATS);
            guests = table.getGuests();
            for (int i = 0; i < count; i++) {
                Client client = auxDef.get(i);
                String code = client.getCode();
                if (client.getEncrypt() == 1)
                    client.setCode(decrypt(code));
                client.setSelected(true);
                guests.add(client);
                if (isMale(client))
                    nMen++;
                else
                    nWomen++;

            }
            switchClients(guests, auxDef);
            selectCompanies(guests);

            nMen = nWomen = 0;
        }
    }

    @SafeVarargs
    private boolean lists(List<Client>... list) {
        int counter = 0;
        for (List<Client> c : list) {
            if (c.size() > 0) counter++;
        }
        return counter > 1;
    }

    private boolean isMale(Client client) {
        return client.getMale() == 1;
    }

    private void switchClients(List<Client> guests, List<Client> clients) {
        if (same() || guests.size() < 4)
            return;

        if (nMen > nWomen) {
            if (clients.size() > guests.size()) {
                int positionIn = guests.size();
                int positionOut = guests.size() - 1;
                Client aux;
                while (positionIn < clients.size() && !same()) {
                    if (!isMale(clients.get(positionIn))) {
                        aux = clients.get(positionIn);
                        if (aux.getEncrypt() == 1)
                            aux.setCode(decrypt(aux.getCode()));
                        while (true) {
                            if (isMale(guests.get(positionOut))) {
                                clients.get(clients.indexOf(guests.get(positionOut))).setSelected(false);
                                guests.remove(positionOut);
                                nMen--;
                                break;
                            } else
                                positionOut--;
                        }
                        aux.setSelected(true);
                        guests.add(aux);
                        nWomen++;
                    }
                    positionIn++;
                }
            } else {
                int position = clients.size() - 1;
                while (true) {
                    if (isMale(clients.get(position))) {
                        clients.get(position).setSelected(false);
                        guests.remove(position);
                        nMen--;
                        break;
                    } else
                        position--;
                }

            }
        }

        if (nWomen > nMen) {
            if (clients.size() > guests.size()) {
                int positionIn = guests.size();
                int positionOut = guests.size() - 1;
                Client aux;
                while (positionIn < clients.size() && !same()) {
                    if (isMale(clients.get(positionIn))) {
                        aux = clients.get(positionIn);
                        if (aux.getEncrypt() == 1)
                            aux.setCode(decrypt(aux.getCode()));
                        while (true) {
                            if (!isMale(guests.get(positionOut))) {
                                clients.get(clients.indexOf(guests.get(positionOut))).setSelected(false);
                                guests.remove(positionOut);
                                nWomen--;
                                break;
                            } else
                                positionOut--;
                        }
                        aux.setSelected(true);
                        guests.add(aux);
                        nMen++;
                    }
                    positionIn++;
                }
            } else {
                int position = clients.size() - 1;
                while (true) {
                    if (!isMale(clients.get(position))) {
                        clients.get(position).setSelected(false);
                        guests.remove(position);
                        nWomen--;
                        break;
                    } else
                        position--;
                }
            }
        }
    }

    private void selectCompanies(List<Client> guests) {
        Client first;
        Client second;
        Client deleted;
        for (int i = 0; i < guests.size(); i++) {
            first = guests.get(i);
            for (int j = 0; j < guests.size(); j++) {
                deleted = null;
                second = guests.get(j);
                if (first.getCompany().equals(second.getCompany())) {
                    if (first.getTotalBalance() > second.getTotalBalance())
                        deleted = guests.remove(j);
                    else if (first.getTotalBalance() < second.getTotalBalance())
                        deleted = guests.remove(i);
                    if (deleted != null)
                        if (isMale(deleted))
                            nMen--;
                        else
                            nWomen--;
                }
            }
        }
    }

    public String printOutput() {
        StringBuilder builder = new StringBuilder();
        for (Table table : tables) {
            builder.append("<" + table.getName() + ">\n");
            if (table.getGuests().size() < 4)
                builder.append("CANCELADA\n");
            else {
                for (Client client : table.getGuests())
                    builder.append(client.getCode() + ",");
                builder.append("\n");
            }
        }
        return builder.toString();
    }

    private String decrypt(String encryptedCode) {
        String webServiceURL = "https://test.evalartapp.com/extapiquest/code_decrypt/" + encryptedCode;

        try {
            URL url = new URL(webServiceURL);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setRequestMethod("GET");
            http.setDoOutput(true);

            InputStreamReader response = new InputStreamReader(http.getInputStream());
            BufferedReader reader = new BufferedReader(response);

            return reader.readLine().replace("\"", "");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
