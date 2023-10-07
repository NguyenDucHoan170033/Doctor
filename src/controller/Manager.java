/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import model.Doctor;
import model.DoctorModel;
import view.Menu;


public class Manager extends Menu<Object> {
    private DoctorModel doctorModel;

    public Manager() {
        this.doctorModel = new DoctorModel();
        String[] menuOptions = {
            "Add doctor",
            "Update doctor",
            "Delete doctor",
            "Search doctor",
            "Exit"
        };
        super.title = "Doctor Management System";
        super.mChon = new ArrayList<>(Arrays.asList(menuOptions));
    }

    public void execute(int n) {
        switch (n) {
            case 1:
                addDoctor();
                break;
            case 2:
                updateDoctor();
                break;
            case 3:
                deleteDoctor();
                break;
            case 4:
                searchDoctor();
                break;
            case 5:
                System.out.println("Goodbye!");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

private void addDoctor() {
    String code = doctorModel.enterCode();

    if (!doctorModel.getAllDoctors().containsKey(code)) {
        String name = doctorModel.enterName();
        String specialization = doctorModel.enterSpecialization();
        int availability = doctorModel.enterAvailability();

        Doctor newDoctor = new Doctor(code, name, specialization, availability);
        doctorModel.addDoctor(newDoctor);
    } else {
        System.err  .println("Doctor code already exists. Please choose 'Update' or enter a different code.");
    }
}


  private void updateDoctor() {
    String oldCode = doctorModel.enterCode();

    if (doctorModel.getAllDoctors().containsKey(oldCode)) {
        System.out.print("Enter new code (leave blank to keep old code): ");
        String newCode = doctorModel.enterCode(); // Nhập mã mới
        System.out.print("Enter new name (leave blank to keep old name): ");
        String newName = doctorModel.enterName();
        System.out.print("Enter new specialization (leave blank to keep old specialization): ");
        String newSpecialization = doctorModel.enterSpecialization();
        System.out.print("Enter new availability (0 or greater, leave blank to keep old availability): ");
        String newAvailabilityInput = doctorModel.enterAvailabilityInput(); 

        if (doctorModel.updateDoctor(oldCode, newCode, newName, newSpecialization, newAvailabilityInput)) {
            System.out.println("Doctor updated successfully.");
        } else {
            System.err.println("Invalid data. Doctor not updated.");
        }
    } else {
        System.err.println("Doctor code does not exist.");
    }
}


    private void deleteDoctor() {
        String code = doctorModel.enterCode();

        if (doctorModel.deleteDoctor(code)) {
            System.out.println("Doctor deleted successfully.");
        } else {
            System.err.println("Doctor code does not exist.");
        }
    }

    private void searchDoctor() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter code : ");
        String searchCode = scanner.nextLine();

        List<Doctor> searchResults = doctorModel.searchDoctors(doctor -> doctor.getCode().equals(searchCode));

        if (searchResults.isEmpty()) {
            System.out.println("No matching doctors found.");
        } else {
            System.out.println("Search results:");
            for (Doctor doctor : searchResults) {
                System.out.println(doctor);
            }
        }
    }
}

