package members;

public class VIPMember extends Member {

    private String specialClass;

    public VIPMember(int id, String name, int age, String membershipType, String specialClass) {
        super(id, name, age, membershipType);
        setSpecialClass(specialClass);
    }

    @Override
    public void attendTraining() {
        System.out.println("VIP " + name + " is attending a premium session.");
    }

    @Override
    public String getRole() {
        return "VIP Member";
    }

    public void setSpecialClass(String specialClass) {
        if (specialClass == null || specialClass.trim().isEmpty()) {
            throw new IllegalArgumentException("Special class can't be empty!");
        }
        this.specialClass = specialClass;
    }

    public void attendSpecialClass() {
        System.out.println(name + " attends special class: " + specialClass);
    }
}

