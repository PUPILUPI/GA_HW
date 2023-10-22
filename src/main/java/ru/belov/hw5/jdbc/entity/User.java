package ru.belov.hw5.jdbc.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "user", schema = "homework")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "second_name")
    private String secondName;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "birth_date")
    private LocalDate birthDate;
    @Column(name = "salary")
    private Double salary;
    public User() {

    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", secondName='" + secondName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", salary=" + salary +
                '}';
    }

    public User(long id, String secondName, String firstName, String lastName, LocalDate birthDate, double salary) {
        this.id = id;
        this.secondName = secondName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.salary = salary;
    }
    public User(String secondName, String firstName, String lastName, LocalDate birthDate, double salary) {
        this.secondName = secondName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.salary = salary;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

}
