package model;

public class Trainer {

    private int id;
    private String name;
    private String specialization;
    private int salary;

    public Trainer(int id, String name, String specialization, int salary) {
        this.id = id;
        this.name = name;
        this.specialization = specialization;
        this.salary = salary;
    }

    public int getTrainerId() {
        return id; // или trainerId — смотри, как поле называется
    }

    public String getName() {
        return name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public int getSalary() {
        return salary;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public void setSalary(int salary) {
        if (salary < 0)
            throw new IllegalArgumentException("Salary cannot be negative");
        this.salary = salary;
    }
}

