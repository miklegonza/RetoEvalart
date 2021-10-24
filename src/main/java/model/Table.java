package model;

public class Table {

    private String name;
    private Integer tc;
    private Integer ug;
    private Double ri;
    private Double rf;

    public Table(String name, Integer tc, Integer ug, Double ri, Double rf) {
        this.name = name;
        this.tc = tc;
        this.ug = ug;
        this.ri = ri;
        this.rf = rf;
    }

    public Table(String name) {
        this.name = name;
        this.tc = 0;
        this.ug = 0;
        this.ri = 0.0;
        this.rf = 0.0;
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

    @Override
    public String toString() {
        return "<" + name + "> \n" +
                "TC:" + tc + '\n' +
                "UG:" + ug + '\n' +
                "RI:" + ri + '\n' +
                "RF:" + rf + '\n';
    }

}
