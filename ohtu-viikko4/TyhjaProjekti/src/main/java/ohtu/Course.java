
package ohtu;

public class Course {
    private String name;
    private String term;
    private int week1, week2, week3, week4, week5, week6;
    
    public String getCourseInfo() {
        return name + ", " + term;
    }
    
    public int getWeek(int week) {
        switch (week) {
            case 1:
                return week1;
            case 2:
                return week2;
            case 3:
                return week3;
            case 4:
                return week4;
            case 5:
                return week5;
            default:
                return week6;
        }
    }
    
}
