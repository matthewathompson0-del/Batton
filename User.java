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

    public User(String inUsername, String inPassword, String inPermission) {
        username = inUsername;
        password = inPassword;
        permission = inPermission;
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
        return "User: " + "username: " + username + ", password: " + password + ", permission: " + permission;
    }

}
