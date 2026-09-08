/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

/*
 * @author matth
 */
public class User {

    private String username;
    private String password;
    private String permission;
    private int idNumber;

    public User(int inIdNumber, String inUsername, String inPassword, String inPermission) {
        idNumber = inIdNumber;
        username = inUsername;
        password = inPassword;
        permission = inPermission;
    }

    public int getIdNumber() {
        return idNumber;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getPermission() {
        return permission;
    }

    public void setIdnumber(int inIdNumber) {
        idNumber = inIdNumber;
    }

    public void setUsername(String inUsername) {
        username = inUsername;
    }

    public void setPassword(String inPassword) {
        password = inPassword;
    }

    public void setAdmin(String inPermission) {
        permission = inPermission;
    }
    

    @Override
    public String toString() {
        return idNumber + ";" + username + ";" + password + ";" + permission;
    }

}
