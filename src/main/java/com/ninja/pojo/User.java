package com.ninja.pojo;

import io.cucumber.datatable.DataTable;
import java.util.Map;

public class User {
    private String email, password;

    public User(DataTable dataTable) {
        Map<String, String> map = dataTable.asMap();
        this.email = map.get("email");
        this.password = map.get("password");
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
