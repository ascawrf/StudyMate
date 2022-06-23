package studyMateSuperPackage.ControllerClasses;

public class ExamDetailLocalEvent {

    private final String coursetitle;
    private final String credithours;
    private final String grade;

    public ExamDetailLocalEvent(String coursetitle, String credithours, String grade) {
        this.coursetitle = coursetitle;
        this.credithours = credithours;
        this.grade = grade;
    }

    public String getCoursetitle() {
        return coursetitle;
    }

    public String getCredithours() {
        return credithours;
    }

    public String getGrade() {
        return grade;
    }

    @Override
    public String toString(){
        return this.getCoursetitle();
    }
}
