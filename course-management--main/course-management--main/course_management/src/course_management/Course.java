/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package course_management;

/**
 *
 * @author USER
 */
public class Course {
    private int no;
    private int semester;
    private String courseName;
    private int credit;
    private String courseCode;

    public Course(int no, int semester, String courseName, int credit, String courseCode) {
        this.no = no;
        this.semester = semester;
        this.courseName = courseName;
        this.credit = credit;
        this.courseCode = courseCode;
    }

    public int getNo() { return no; }
    public int getSemester() { return semester; }
    public String getCourseName() { return courseName; }
    public int getCredit() { return credit; }
    public String getCourseCode() { return courseCode; }
}
