package model;

public class Trainer extends Member {

    private String specialization;
    private int experienceYears;

    public Trainer(int id, String name, int age, String specialization, int experienceYears) {
        super(id, name, age, "Trainer");
        setSpecialization(specialization);
        setExperienceYears(experienceYears);
    }

    @Override
    public void attendTraining() {
        System.out.println("Trainer " + name + " is conducting a " + specialization + " training.");
    }

    @Override
    public String getRole() {
        return "Trainer";
    }

    public void setSpecialization(String specialization) {
        if (specialization == null || specialization.trim().isEmpty()) {
            throw new IllegalArgumentException("Specialization can't be empty!");
        }
        this.specialization = specialization;
    }

    public void setExperienceYears(int experienceYears) {
        if (experienceYears < 0) {
            throw new IllegalArgumentException("Experience years can't be negative!");
        }
        this.experienceYears = experienceYears;
    }

    public boolean isExperienced() {
        return experienceYears >= 5;
    }
}
