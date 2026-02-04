package Database;
import Database.TrainerDAO;
import model.Trainer;

public class TestInsertTrainer {
    public static void main(String[] args) {
        Trainer trainer = new Trainer(
                0,
                "Aibek",
                "Fitness",
                450000
        );

        TrainerDAO dao = new TrainerDAO();
        dao.insertTrainer(trainer);


    }
}

