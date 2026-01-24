public class Trainer {
    protected String name;
    protected String specialization;
    protected int price;

    public Trainer(String name, String specialization, int price) {
        this.name = name;
        this.specialization = specialization;
        this.price = price;
    }


    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }


    public void work() {
    }
}
