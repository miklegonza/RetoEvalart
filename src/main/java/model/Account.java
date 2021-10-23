package model;

public class Account {

    private Integer id; // Identificador interno
    private Client clientId; // Id del cliente
    private Double balance; // Monto del cliente en la cuenta

    public Account(Integer id, Client clientId, Double balance) {
        this.id = id;
        this.clientId = clientId;
        this.balance = balance;
    }

    public Account() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Client getClientId() {
        return clientId;
    }

    public void setClientId(Client clientId) {
        this.clientId = clientId;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account: \n" +
                "id = " + id + '\n' +
                "clientId = { \n" + clientId.toString() + "}\n" +
                "balance = " + balance + '\n';
    }

}
