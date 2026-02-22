package entity;

import util.IdGenerator;

public class Patron {
    private final int id;
    private String name;
    private String email;

    public Patron(String name, String email) {
        this.id = IdGenerator.nextPatronId();
        this.name = name;
        this.email = email;
    }

    public int getId() {
        return id;
    }

//    public void setId(Integer id) {
//        this.id = id;
//    }

//    public String getName() {
//        return name;
//    }

    public void setName(String name) {
        this.name = name;
    }

//    public String getEmail() {
//        return email;
//    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Patron{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
