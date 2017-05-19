package ohtu;

import java.util.ArrayList;

public class Submission {
    private String student_number;
    private int week;
    private int hours;
    private boolean a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15, a16, a17, a18, a19, a20, a21;
    private ArrayList<Integer> done;
    
    public String getStudent_number() {
        return student_number;
    }

    public void setStudent_number(String student_number) {
        this.student_number = student_number;
    }
    
    public int getHours() {
        return hours;
    }
    
    private ArrayList<Integer> getTasks() {
        return done;
    }
    
    private String tasksToString() {
        String tasks = "";
        for (Integer task : done) {
            tasks = tasks + " " + task;
        }
        return tasks;
    }

    public int countTasksDone() {
        int count = 0;
        done = new ArrayList();
        if(a1) {done.add(1); count++;} 
        if(a2) {done.add(2); count++;} 
        if(a3) {done.add(3); count++;} 
        if(a4) {done.add(4); count++;} 
        if(a5) {done.add(5); count++;} 
        if(a6) {done.add(6); count++;} 
        if(a7) {done.add(7); count++;} 
        if(a8) {done.add(8); count++;} 
        if(a9) {done.add(9); count++;} 
        if(a10) {done.add(10); count++;} 
        if(a11) {done.add(11); count++;} 
        if(a12) {done.add(12); count++;} 
        if(a13) {done.add(13); count++;} 
        if(a14) {done.add(14); count++;} 
        if(a15) {done.add(15); count++;} 
        if(a16) {done.add(16); count++;} 
        if(a17) {done.add(17); count++;} 
        if(a18) {done.add(18); count++;} 
        if(a18) {done.add(19); count++;} 
        if(a20) {done.add(20); count++;}
        if(a21) {done.add(21); count++;}
        return count;
    }   
    
    @Override
    public String toString() {
        return "Week " + week + ": number of tasks done: " + countTasksDone() + ", time spent: " + getHours() + " hours, done tasks: " + tasksToString() + ".";
    }
    
}