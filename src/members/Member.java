package members;

public abstract class Member {

    protected int id;
    protected String name;
    protected int age;
    protected String membershipType;

    public Member(int id, String name, int age, String membershipType) {
        setId(id);
        setName(name);
        setAge(age);
        setMembershipType(membershipType);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getMembershipType() {
        return membershipType;
    }

    public void setId(int id) {
        if (id <= 0)
            throw new IllegalArgumentException("ID must be positive");
        this.id = id;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty())
            throw new IllegalArgumentException("Name can't be empty");
        this.name = name;
    }

    public void setAge(int age) {
        if (age < 0 || age > 120)
            throw new IllegalArgumentException("Invalid age");
        this.age = age;
    }

    public void setMembershipType(String membershipType) {
        if (membershipType == null || membershipType.trim().isEmpty())
            throw new IllegalArgumentException("Membership type can't be empty");
        this.membershipType = membershipType;
    }


    public boolean isAdult() {
        return age >= 17;
    }


    public abstract void attendTraining();
    public abstract String getRole();

    @Override
    public String toString() {
        return "[" + getRole() + "] " + name +
                " | Age: " + age +
                " | Membership: " + membershipType;
    }
}
