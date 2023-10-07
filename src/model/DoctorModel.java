/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Predicate;
import validate.Validator;

/**
 *
 * @author ADMIN
 */
public class DoctorModel {

    private Scanner sc = new Scanner(System.in);
    private Map<String, Doctor> doctorMap = new HashMap<>();
    private Validator valid = new Validator();

    public DoctorModel() {
    }

    public String enterCode() {
        System.out.print("Enter code: ");
        return sc.nextLine();
    }

    public String enterName() {
        System.out.print("Enter name: ");
        return sc.nextLine();
    }

    public String enterSpecialization() {
        System.out.print("Enter specialization: ");
        return sc.nextLine();
    }

    public int enterAvailability() {
        while (true) {
            try {
                System.out.print("Enter availability: ");
                int availability = Integer.parseInt(sc.nextLine());
                if (availability >= 0) {
                    return availability;
                } else {
                    throw new IllegalArgumentException("Availability must be >= 0");
                }
            } catch (NumberFormatException e) {
                System.err.println("Invalid input. Please enter a valid integer.");
            } catch (IllegalArgumentException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public void addDoctor(Doctor doctor) {
        doctorMap.put(doctor.getCode(), doctor);
    }

    public Map<String, Doctor> getAllDoctors() {
        return doctorMap;
    }

    public void creatDoctor(Doctor doctor) {

        String code = doctor.getCode();
        String name = doctor.getName();
        String specialization = doctor.getSpecialization();
        int availability = doctor.getAvailability();

        if (valid.validateCode(code)
                && valid.validateName(name)
                && valid.validateSpecialization(specialization)) {
            addDoctor(doctor);
            System.out.println("Creat a doctor Successfully!");
        } else {
            System.err.println("Information is not allowed to be left blank and names cannot contain numbers");
            System.out.println("Invalid data. Doctor not added.");
        }
    }

    public List<Doctor> searchDoctors(Predicate<Doctor> searchPredicate) {
        List<Doctor> searchResults = new ArrayList<>();
        for (Doctor doctor : doctorMap.values()) {
            if (searchPredicate.test(doctor)) {
                searchResults.add(doctor);
            }
        }
        return searchResults;
    }

    public boolean deleteDoctor(String code) {
        if (doctorMap.containsKey(code)) {
            doctorMap.remove(code);
            return true;
        }
        return false;
    }
    public String enterAvailabilityInput(){
        System.out.println("Enter new availibility : ");
        return sc.nextLine();
    }

public boolean updateDoctor(String oldCode, String newCode, String name, String specialization, String availabilityInput) {
    if (doctorMap.containsKey(oldCode)) {
        Doctor doctor = doctorMap.get(oldCode);
        
        if (!newCode.isEmpty()) {
            doctorMap.remove(oldCode); // Xóa dữ liệu cũ với mã oldCode
            doctor.setCode(newCode); // Cập nhật mã mới
            doctorMap.put(newCode, doctor); // Thêm dữ liệu mới với mã mới
        }

        if (!name.isEmpty()) {
            doctor.setName(name);
        }

        if (!specialization.isEmpty()) {
            doctor.setSpecialization(specialization);
        }

        if (!availabilityInput.isEmpty()) {
            try {
                int newAvailability = Integer.parseInt(availabilityInput);
                if (newAvailability >= 0) {
                    doctor.setAvailability(newAvailability);
                } else {
                    System.err.println("Invalid input for availability. Doctor not updated.");
                    return false;
                }
            } catch (NumberFormatException e) {
                System.err.println("Invalid input for availability. Doctor not updated.");
                return false;
            }
        }

        return true;
    } else {
        System.err.println("Doctor code does not exist.");
        return false;
    }
}


}
