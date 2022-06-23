package studyMateSuperPackage.ControllerClasses;

public class GPAGradeItem {

private String GPAScore;

    public GPAGradeItem(String GPAScore) {
        this.GPAScore = GPAScore;
    }

    public String getGPAScore() {
        return GPAScore;
    }

    @Override
    public String toString() {
        return  GPAScore;
    }
}
