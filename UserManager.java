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
import java.util.Scanner;

public class UserManager {

    private static boolean loaded = false;
    private static User[] userArr = new User[100];

    private static int countUsers() {
        int count = 0;
        try {
            Scanner scFile = new Scanner(new File("userIDs.txt"));
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
            Scanner scFile = new Scanner(new File("userIDs.txt"));
            while (scFile.hasNextLine()) {
                Scanner scLine = new Scanner(scFile.nextLine()).useDelimiter(";");
                String username = scLine.next();
                String password = scLine.next();
                String permission = scLine.next();
                userArr[count] = new User(username, password, permission);
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
        if (!loaded) {
            loadInfomation();
        }
        for (int i = 0; i < userArr.length; i++) {
            if (inUsername.equals(userArr[i].getUsername()) && inPassword.equals(userArr[i].getPassword())) {
                return userArr[i];
            }
        }
        return null;
    }
}
