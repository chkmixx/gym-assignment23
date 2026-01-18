package model;

public class WorkoutSession {

    private int sessionId;
    private String memberName;
    private String trainerName;
    private int durationMinutes;

    public WorkoutSession(int sessionId, String memberName, String trainerName, int durationMinutes) {
        setSessionId(sessionId);
        setMemberName(memberName);
        setTrainerName(trainerName);
        setDurationMinutes(durationMinutes);
    }

    public void setSessionId(int sessionId) {
        if (sessionId <= 0) throw new IllegalArgumentException("Session ID must be positive!");
        this.sessionId = sessionId;
    }

    public void setMemberName(String memberName) {
        if (memberName == null || memberName.trim().isEmpty())
            throw new IllegalArgumentException("Member name can't be empty!");
        this.memberName = memberName;
    }

    public void setTrainerName(String trainerName) {
        if (trainerName == null || trainerName.trim().isEmpty())
            throw new IllegalArgumentException("Trainer name can't be empty!");
        this.trainerName = trainerName;
    }

    public void setDurationMinutes(int durationMinutes) {
        if (durationMinutes <= 0)
            throw new IllegalArgumentException("Duration must be more than 0 minutes!");
        this.durationMinutes = durationMinutes;
    }

    @Override
    public String toString() {
        return "WorkoutSession{" +
                "id=" + sessionId +
                ", member='" + memberName + '\'' +
                ", trainer='" + trainerName + '\'' +
                ", duration=" + durationMinutes +
                " min}";
    }
}
