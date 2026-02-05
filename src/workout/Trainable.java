package workout;

public interface Trainable {
    void train();
    String getPlan();
}

class WorkoutClass implements Trainable {
    private String name;
    private int duration;

    public WorkoutClass(String name, int duration) {
        this.name = name;
        this.duration = duration;
    }

    @Override
    public void train() {
        System.out.println("Doing " + name + " for " + duration + " minutes ");
    }

    @Override
    public String getPlan() {
        return "Plan for " + name + " duration: " + duration + " minutes ";
    }
}
