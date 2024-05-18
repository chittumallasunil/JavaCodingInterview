package com.example.demo.Model;

public class Employee {




    private Integer id;

    public Employee(Integer id, String name,String dept, Integer salary) {
        this.dept = dept;
        this.id = id;
        this.name = name;
        this.salary=salary;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    private Integer salary;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    public String getDept() {
        return dept;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", salary=" + salary +
                ", name='" + name + '\'' +
                ", dept='" + dept + '\'' +
                '}';
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    private String dept;
}
