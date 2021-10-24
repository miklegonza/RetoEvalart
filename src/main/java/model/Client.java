package model;

public class Client {

    private Integer id; // Identificador interno
    private String code; // Código del cliente
    private Byte male; // 1 si es hombre, 0 si es mujer
    private Integer type; // Código de tipo de cliente
    private String location; // Código de ubicación del cliente
    private String company; // Empresa a la que pertenece
    private Byte encrypt; // 0 si no está encriptado, 1 si se debe desencriptar con el servicio web

    public Client(Integer id, String code, Byte male, Integer type, String location, String company, Byte encrypt) {
        this.id = id;
        this.code = code;
        this.male = male;
        this.type = type;
        this.location = location;
        this.company = company;
        this.encrypt = encrypt;
    }

    public Client() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Byte getMale() {
        return male;
    }

    public void setMale(Byte male) {
        this.male = male;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Byte getEncrypt() {
        return encrypt;
    }

    public void setEncrypt(Byte encrypt) {
        this.encrypt = encrypt;
    }

    @Override
    public String toString() {
        return "Client { " +
                "id = " + id +
                ", code = " + code +
                ", male = " + male +
                ", type = " + type +
                ", location = " + location +
                ", company = " + company +
                ", encrypt = " + encrypt + " }";
    }

}
