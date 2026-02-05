package members;

public class BasicMember extends Member {

    public BasicMember(int id, String name, int age, String membershipType) {
        super(id, name, age, membershipType);
    }

    @Override
    public void attendTraining() {
        System.out.println(name + " is attending a general training");
    }

    @Override
    public String getRole() {
        return "Basic Member";
    }
}

