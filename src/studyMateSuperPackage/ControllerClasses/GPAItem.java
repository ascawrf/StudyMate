package studyMateSuperPackage.ControllerClasses;

public class GPAItem extends  GPAController{
    private String courseTitle;
    private String creditHours;
    private String grade;

    public GPAItem(String courseTitle, String creditHours, String grade) {
        this.courseTitle = courseTitle;
        this.creditHours = creditHours;
        this.grade = grade;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public String getCreditHours() {
        return creditHours;
    }

    public String getGrade() {
        return grade;
    }
}
