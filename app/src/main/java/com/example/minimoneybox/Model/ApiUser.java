package com.example.minimoneybox.Model;

public class ApiUser {
    private String email;
    private String password;
    private String idfa;

    public ApiUser(String email, String password, String idfa) {
        this.email = email;
        this.password = password;
        this.idfa = idfa;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIdfa() {
        return idfa;
    }

    public void setIdfa(String idfa) {
        this.idfa = idfa;
    }
}
