package org.fis.maven.Models;

public class User {
    private String name;
    private String username;
    private String password;
    private int credit;
    private String mail;
    private String role;
    private String status = "";
    private String city;

    private boolean logged = false;
    private boolean confirmed = false;

    public User(String name, String username, String password, String mail, String role, int credit, boolean confirmed, String city){
        this.name=name;
        this.username=username;
        this.password=password;
        this.mail=mail;
        this.role=role;
        this.credit=credit;
        this.confirmed = confirmed;
        this.city = city;
    }

    public User(String name, String username, String password, String mail, String role, int credit, String status, boolean confirmed, String city) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.credit = credit;
        this.mail = mail;
        this.role = role;
        this.status = status;
        this.confirmed = confirmed;
        this.city = city;
    }

    public String getName(){return name;}

    public void setName(String name){this.name=name;}

    public String getUsername() {return username;}

    public void setUsername(String username) {this.username=username;}

    public String getPassword() {return password;}

    public void setPassword(String password) {this.password=password;}

    public String getMail() {return mail;}

    public void setMail(String mail) {this.mail = mail;}

    public String getRole() {return role;}

    public void setRole(String role) {this.role = role;}

    public int getCredit() {return credit;}

    public void setCredit(int credit) {this.credit=credit;}

    public boolean isLogged() {return logged;}

    public void setLogged(boolean logged) {this.logged=logged;}

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        if(!name.equals(user.name)) return false;
        if(!username.equals(user.username)) return false;
        if(!password.equals(user.password)) return false;
        if(!mail.equals(user.mail)) return false;
        if(!role.equals(user.role)) return false;
        if(!confirmed==user.confirmed) return false;
        if(!city.equals(user.city)) return false;
        return credit==user.credit;
    }

    @Override
    public int hashCode(){
        int result = name.hashCode();
        result = 31*result + username.hashCode();
        result = 31*result + password.hashCode();
        result = 31*result + mail.hashCode();
        result = 31*result + role.hashCode();
        result = 31*result + city.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", credit=" + credit +
                ", mail='" + mail + '\'' +
                ", role='" + role + '\'' +
                ", status='" + status + '\'' +
                ", city='" + city + '\'' +
                ", logged=" + logged +
                ", confirmed=" + confirmed +
                '}';
    }
}
