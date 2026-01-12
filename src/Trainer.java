public class Trainer extends Member {

    private String specialization;
    private int experienceYears;

    public Trainer(int id, String name, int age, String specialization, int experienceYears) {
        super(id, name, age, "Trainer");
        this.specialization = specialization;
        this.experienceYears = experienceYears;
    }

    @Override
    public void attendTraining() {
        System.out.println("Trainer " + name + " is conducting a " + specialization + " training.");
    }

    @Override
    public String getRole() {
        return "Trainer";
    }

    public boolean isExperienced() {
        return experienceYears >= 5;
    }
}
