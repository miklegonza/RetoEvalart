package model;

import java.util.ArrayList;
import java.util.List;

public class Table {

    private String name;
    private Integer tc;
    private Integer ug;
    private Double ri;
    private Double rf;
    private List<Client> guests;

    public Table(String name, Integer tc, Integer ug, Double ri, Double rf, List<Client> guests) {
        this.name = name;
        this.tc = tc;
        this.ug = ug;
        this.ri = ri;
        this.rf = rf;
        this.guests = guests;
    }

    public Table(String name) {
        this(name, 0, 0, 0.0, 0.0, new ArrayList<>());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTc() {
        return tc;
    }

    public void setTc(Integer tc) {
        this.tc = tc;
    }

    public Integer getUg() {
        return ug;
    }

    public void setUg(Integer ug) {
        this.ug = ug;
    }

    public Double getRi() {
        return ri;
    }

    public void setRi(Double ri) {
        this.ri = ri;
    }

    public Double getRf() {
        return rf;
    }

    public void setRf(Double rf) {
        this.rf = rf;
    }

    public List<Client> getGuests() {
        return guests;
    }

    public void setGuests(List<Client> guests) {
        this.guests = guests;
    }

    @Override
    public String toString() {
        return "<" + name + "> \n" +
                "TC:" + tc + '\n' +
                "UG:" + ug + '\n' +
                "RI:" + ri + '\n' +
                "RF:" + rf + '\n' +
                "Clients: " + temp(guests) + '\n';
    }

    String temp(List<Client> clients) {
        String salida = "\n";
        for (Client client : clients)
            salida += client + "\n";
        return salida;
    }

}
