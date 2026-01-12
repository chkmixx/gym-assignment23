public class WorkoutSession {

    private int sessionId;
    private String memberName;
    private String trainerName;
    private int durationMinutes;

    public WorkoutSession(int sessionId,String memberName,String trainerName,int durationMinutes){
        this.sessionId=sessionId;
        this.memberName=memberName;
        this.trainerName=trainerName;
        this.durationMinutes=durationMinutes;
    }

    @Override
    public String toString() {
        return "WorkoutSession{" +
                "id=" + sessionId +
                ", member='" + memberName + '\'' +
                ", trainer='" + trainerName + '\'' +
                ", duration=" + durationMinutes +
                '}';
    }
}
