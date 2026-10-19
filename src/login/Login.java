/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package login;

public class Login {
    private String username;
    private String password;
    private String cellPhoneNumber;
    private String firstName;
    private String lastName;

    public Login(String username, String password, String cellPhoneNumber, String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.cellPhoneNumber = cellPhoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public boolean checkUserName() {
        return username != null && username.contains("_") && username.length() <= 5;
    }

    public boolean checkPasswordComplexity() {
           if (password == null || password.length() < 8) {
        return false;
    }
           boolean hasCapital = false;
    boolean hasNumber = false;
    boolean hasSpecial = false;
    for (int i = 0; i < password.length(); i++) {
        char c = password.charAt(i);
        
        if (Character.isUpperCase(c)) {
            hasCapital = true;
              } else if (Character.isDigit(c)) {
            hasNumber = true;
               } else if (!Character.isLetterOrDigit(c)) {
            hasSpecial = true;
               }
    }

    return hasCapital && hasNumber && hasSpecial;
}
    public boolean checkCellPhoneNumber() {
        // Regex pour valider le format international (ex: +27...) sans dépasser 10 chiffres hors code
        return cellPhoneNumber != null && cellPhoneNumber.matches("^\\+27[0-9]{9}$");
    }

    public String registerUser() {
        if (!checkUserName()) {
            return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than 5 characters in length.";
        }
        if (!checkPasswordComplexity()) {
            return "Password is not correctly formatted; please ensure that the password contains at least 8 characters, a capital letter, a number, and a special character.";
        }
        if (!checkCellPhoneNumber()) {
            return "Cell phone number incorrectly formatted or does not contain international code.";
        }
        return "User registered successfully.";
    }

    public boolean loginUser(String enteredUsername, String enteredPassword) {
        return this.username.equals(enteredUsername) && this.password.equals(enteredPassword);
    }

    public String returnLoginStatus(boolean loginStatus) {
        if (loginStatus) {
            return "Welcome " + firstName + ", " + lastName + " it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }
}