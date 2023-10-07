/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package validate;
public class Validator {
    public static boolean validateCode(String code) {
        return !code.isEmpty();
    }

    public static boolean validateName(String name) {
        return !name.isEmpty() && !name.matches(".*\\d+.*");
    }

    public static boolean validateSpecialization(String specialization) {
        return !specialization.isEmpty();
    }
}
