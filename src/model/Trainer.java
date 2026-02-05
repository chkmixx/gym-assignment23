package model;
public class Trainer extends Staff {

    private String specialization;

    public Trainer(int staffId, String name, double salary, int experienceYears, String specialization) {
        super(staffId, name, salary, experienceYears);
        this.specialization = specialization;
    }

    @Override
    public void work() {
        System.out.println("Trainer " + name + " is training clients.");
    }

    @Override
    public String getRole() {
        return "TRAINER";
    }

    public String getSpecialization() {
        return specialization;
    }
}
