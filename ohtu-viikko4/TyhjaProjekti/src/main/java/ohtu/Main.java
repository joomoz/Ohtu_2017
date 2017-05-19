package ohtu;

import com.google.gson.Gson;
import java.io.IOException;
import org.apache.http.client.fluent.Request;

public class Main {

    public static void main(String[] args) throws IOException {
        // vaihda oma opiskelijanumerosi seuraavaan
        String studentNr = "013185831";
        if ( args.length>0) {
            studentNr = args[0];
        }

        String url = "http://ohtustats2017.herokuapp.com/students/"+studentNr+"/submissions";
        String bodyText = Request.Get(url).execute().returnContent().asString();
        String courseUrl = "https://ohtustats2017.herokuapp.com/courses/1.json";
        String courseText = Request.Get(courseUrl).execute().returnContent().asString();

        //System.out.println( courseText );

        Gson mapper = new Gson();
        Submission[] subs = mapper.fromJson(bodyText, Submission[].class);
        Course c = mapper.fromJson(courseText, Course.class);
        
        System.out.println("Course: " + c.getCourseInfo() +"\n");
        System.out.println("Student: " + subs[0].getStudent_number());
        int totalHours = 0;
        int totalTasks = 0;
        int week = 1;
        for (Submission submission : subs) {
            System.out.print(submission + " ");
            System.out.println("Max points: " + c.getWeek(week));
            totalHours += submission.getHours();
            totalTasks += submission.countTasksDone();
            week++;
        }
        System.out.println("In total: " + totalTasks + " tasks and " + totalHours + " hours.");
    }
}