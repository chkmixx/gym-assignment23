public class Member {

    protected int id;
    protected String name;
    protected int age;
    protected String membershipType;

    public Member(int id, String name, int age, String membershipType) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.membershipType = membershipType;
    }

    public void attendTraining() {
        System.out.println(name + " is attending a general gym training.");
    }

    public String getRole() {
        return "Member";
    }

    public boolean isAdult() {
        return age >= 17;
    }

    @Override
    public String toString() {
        return "[" + getRole() + "] " + name +
                " | Age: " + age +
                " | Membership: " + membershipType;
    }
}
