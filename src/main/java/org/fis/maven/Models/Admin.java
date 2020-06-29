package org.fis.maven.Models;

public class Admin {
    private String name;
    private String username;
    private String password;
    private String mail;

    public Admin(String name, String username, String password, String mail) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.mail = mail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Admin admin = (Admin) o;
        return name.equals(((Admin) o).name)&&username.equals(((Admin) o).username)&&password.equals(((Admin) o).password)&&mail.equals(((Admin) o).mail);
    }

    @Override
    public int hashCode() {
        int result = username.hashCode();
        result = 31*result + name.hashCode();
        result = 31*result + password.hashCode();
        result = 31*result + mail.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", mail='" + mail + '\'' +
                '}';
    }
}

