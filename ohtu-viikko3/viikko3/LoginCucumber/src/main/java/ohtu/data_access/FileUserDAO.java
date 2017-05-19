package ohtu.data_access;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import ohtu.domain.User;
import org.springframework.stereotype.Component;

@Component
public class FileUserDAO implements UserDao {

    private File file;

    public FileUserDAO(){
    }
    
    public FileUserDAO(String file) throws Exception {
        this.file = new File(file);
    }

    @Override
    public List<User> listAll() {
        List<User> users = new ArrayList<>();
        try {
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String row = reader.nextLine();
                String username = row.split(" ")[0];
                String password = row.split(" ")[1];
                users.add(new User(username, password));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Can't read... " + e.getMessage());
        }
        return users;
    }

    @Override
    public User findByName(String name) {
        try {
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String row = reader.nextLine();
                String username = row.split(" ")[0];
                String password = row.split(" ")[1];
                if (username.equals(name)) {
                    return new User(username, password);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No file...: " + e.getMessage());
        }
        return null;
    }

    @Override
    public void add(User user) {
        try (FileWriter writer = new FileWriter(file, true)) {
            writer.write(user.getUsername() + " " + user.getPassword()+ "\n");
            writer.close();
        } catch (Exception e) {
            System.out.println("Writer out of office or something... " + e.getMessage());
        }
    }

}
