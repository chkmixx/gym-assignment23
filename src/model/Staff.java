package model;

public abstract class Staff {
    protected int staffId;
    protected String name;
    protected double salary;
    protected int experienceYears;

    public Staff(int staffId, String name, double salary, int experienceYears) {
        this.staffId = staffId;
        this.name = name;
        this.salary = salary;
        this.experienceYears = experienceYears;
    }

    public int getStaffId() { return staffId; }
    public String getName() { return name; }
    public double getSalary() { return salary; }
    public int getExperienceYears() { return experienceYears; }

    public abstract void work();   // для полиморфизма
    public abstract String getRole();
}

