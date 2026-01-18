package model;

public class StudentMember extends Member {

    private String university;

    public StudentMember(int id, String name, int age, String membershipType, String university) {
        super(id, name, age, membershipType);
        setUniversity(university);
    }

    @Override
    public void attendTraining() {
        System.out.println("Student " + name + " is attending a discounted student workout.");
    }

    @Override
    public String getRole() {
        return "Student Member";
    }

    public void setUniversity(String university) {
        if (university == null || university.trim().isEmpty()) {
            throw new IllegalArgumentException("University can't be empty!");
        }
        this.university = university;
    }

    public void showUniversity() {
        System.out.println("University: " + university);
    }
}
