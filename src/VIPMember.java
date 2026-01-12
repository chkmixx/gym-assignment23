public class VIPMember extends Member {
    private String specialClass;

    public VIPMember(int id, String name, int age, String membershipType, String specialClass) {
        super(id, name, age, membershipType);
        this.specialClass = specialClass;
    }

    public void attendSpecialClass() {
        System.out.println(name + " attends special class: " + specialClass);
    }
}

