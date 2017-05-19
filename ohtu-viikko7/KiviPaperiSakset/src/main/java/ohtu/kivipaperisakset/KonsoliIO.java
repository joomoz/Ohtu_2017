package ohtu.kivipaperisakset;

import java.util.Scanner;

public class KonsoliIO implements IO {
    private final Scanner scanner = new Scanner(System.in);
    
    @Override
    public void print(String toPrint) {
        System.out.println(toPrint);
    }

    @Override
    public String readLine() {
        return scanner.nextLine();
    }
    
}
