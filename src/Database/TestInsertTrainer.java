package Database;

import model.Trainer;

public class TestInsertTrainer {
    public static void main(String[] args) {

        Trainer trainer = new Trainer(
                0,
                "Aibek",
                450000,
                5,
                "Fitness"
        );

        StaffDAO dao = new StaffDAO();
        dao.insertTrainer(trainer);
    }
}


