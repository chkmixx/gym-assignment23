package model;

public class AdminStaff extends Staff {

    public AdminStaff(int staffId, String name, double salary, int experienceYears) {
        super(staffId, name, salary, experienceYears);
    }

    @Override
    public void work() {
        System.out.println("Admin staff " + getName() + " is managing gym operations.");
    }

    @Override
    public String getRole() {
        return "ADMIN";
    }
    @Override
    public String toString() {
        return "ID: " + getStaffId() +
                ", Name: " + getName() +
                ", Salary: " + getSalary() +
                ", Experience: " + getExperienceYears() + " years";
    }

}
