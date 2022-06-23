package studyMateSuperPackage.ControllerClasses;

public class StudentListController {
    String ID;
    String firstName;
    String LastName;
    String Sex;
    String DateOfBirth;
    String WardenName;
    String WardenNumber;

    public StudentListController(String ID, String firstName, String lastName, String sex, String dateOfBirth, String wardenName, String wardenNumber) {
        this.ID = ID;
        this.firstName = firstName;
        LastName = lastName;
        Sex = sex;
        DateOfBirth = dateOfBirth;
        WardenName = wardenName;
        WardenNumber = wardenNumber;
    }

    public String getID() {
        return ID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public String getSex() {
        return Sex;
    }

    public String getDateOfBirth() {
        return DateOfBirth;
    }

    public String getWardenName() {
        return WardenName;
    }

    public String getWardenNumber() {
        return WardenNumber;
    }
}
