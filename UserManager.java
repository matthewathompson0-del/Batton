/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

/*
 * @author matth
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class UserManager {

    private static boolean loaded = false;
    private static User[] userArr = new User[100];

    private static int countUsers() {
        int count = 0;
        try {
            Scanner scFile = new Scanner(new File("users.txt"));
            while (scFile.hasNextLine()) {
                count++;
                scFile.nextLine();
            }

        } catch (FileNotFoundException ex) {
            System.out.println("Error counting users.");
            System.out.println(ex);
        }
        return count;
    }

    public static boolean loadInfomation() {
        int count = 0;
        int size = countUsers();
        if (size == 0) {
            return false;
        }
        userArr = new User[size];
        try {
            Scanner scFile = new Scanner(new File("users.txt"));
            while (scFile.hasNextLine()) {
                Scanner scLine = new Scanner(scFile.nextLine()).useDelimiter(";");
                int idNumber = scLine.nextInt();
                String username = scLine.next();
                String password = scLine.next();
                String permission = scLine.next();
                userArr[count] = new User(idNumber, username, password, permission);
                count++;
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Error loading file");
            System.out.println(ex);
            return false;
        }
        loaded = true;
        return true;
    }

    public static User checkLogin(String inUsername, String inPassword) {

        for (int i = 0; i < userArr.length; i++) {
            if (inUsername.equals(userArr[i].getUsername()) && inPassword.equals(userArr[i].getPassword())) {
                return userArr[i];
            }
        }
        return null;
    }

    public static User checkUserId(int inID) {
        if (!loaded) {
            loadInfomation();
        }
        for (int i = 0; i < userArr.length; i++) {
            if (inID == userArr[i].getIdNumber()) {
                return userArr[i];
            }
        }
        return null;
    }

    public static User findUser(int position) {
        if (!loaded) {
            loadInfomation();
        }

        User u = UserManager.checkUserId(position);
        return u;
    }

    public static void changePassword(int inIdNumber, String newPassword) {
        loadInfomation();
        int size = countUsers();
        try (FileWriter fw = new FileWriter("Users.txt"); PrintWriter pw = new PrintWriter(fw)) {
            for (int i = 0; i < size; i++) {
                if (inIdNumber == userArr[i].getIdNumber()) {
                    userArr[i] = new User(userArr[i].getIdNumber(), userArr[i].getUsername(), newPassword, userArr[i].getPermission());
                    System.out.println(userArr[i].toString());

                } else {
                    System.out.println("didnt work");
                }
                pw.println(userArr[i].getIdNumber() + ";" + userArr[i].getUsername() + ";" + userArr[i].getPassword() + ";" + userArr[i].getPermission());

            }
            pw.close();

        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void saveUsertoFile(User u) {

        try (PrintWriter pw = new PrintWriter(new FileWriter("users.txt", true))) {

            String line = u.getIdNumber() + ";" + u.getUsername() + ";" + u.getPassword() + ";" + u.getPermission();

            pw.print("\n" + line);

            loadInfomation();

        } catch (IOException e) {
            System.out.println("Could not write to file: " + e.getMessage());
        }
    }

    public static int findLastUser() {
        if (!loaded) {
            loadInfomation();
        }
        if (userArr == null || userArr.length == 0 || userArr[0] == null) {
            return 100;
        }
        return userArr[userArr.length - 1].getIdNumber();

    }

   
}
