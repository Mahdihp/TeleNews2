package com.telenews.models.role;

public enum RoleName {

    Role_Author("ROLE_AUTHOR"),
    Role_Admin("ROLE_ADMIN"),
    Role_User("ROLE_USER");

    private String name;

    RoleName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
