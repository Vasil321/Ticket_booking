package org.example;

import java.util.ArrayList;
import java.util.List;

public class Person {
    private String name;

    private String email;

    private String surname;

    public Person(String name, String email, String surname) {
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname(){
        return surname;
    }

    public void setSurname(String surname){
        this.surname = surname;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



}
