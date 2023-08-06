package model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Customer {
    private int customerId;
    private int bookId;
    private String name;
    private String surname;
    private String userName;
    private String password;
    private LocalDate birthday;
    private LocalDateTime registerDate;
    private LocalDateTime updateDate;

    public Customer(int customerId, String name, String surname, String userName,String password,LocalDate birthday, LocalDateTime registerDate, LocalDateTime updateDate) {
        this.customerId = customerId;
        this.name = name;
        this.surname = surname;
        this.userName = userName;
        this.password = password;
        this.birthday = birthday;
        this.registerDate = registerDate;
        this.updateDate = updateDate;
    }
    public Customer() {

    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public LocalDateTime getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(LocalDateTime registerDate) {
        this.registerDate = registerDate;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", userName='" + userName + '\'' +
                ", birthday=" + birthday +
                ", registerDate=" + registerDate +
                ", updateDate=" + updateDate +
                '}';
    }
    public void getInfo() {
        System.out.println(
                ", name='" + name + '\'' +
                        ", surname='" + surname + '\'' +
                        ", username='" + userName + '\'' +
                        ", birthday='" + birthday + '\''
        );
    }
}
