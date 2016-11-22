package com.hazelcast.hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.Table;

/**
 * @author Esref Ozturk <esrefozturk93@gmail.com>
 */
@SuppressWarnings({ "unused", "deprecation" })
@Entity
@Table(appliesTo = "employee")
public class Employee {

	@Id
    private int id;
    @Column(name = "first_name", nullable = false, length = 10)
    private String firstName;
    @Column(name = "last_name", nullable = false, length = 10)
    private String lastName;
    private int salary;

    public Employee() {
    }

    public Employee(int id, String fname, String lname, int salary) {
        this.id = id;
        this.firstName = fname;
        this.lastName = lname;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}
