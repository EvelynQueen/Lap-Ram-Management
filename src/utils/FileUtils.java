package utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileUtils {

    public static Scanner sc = new Scanner(System.in);

    public static String getString(String inpMsg, String errMsg, String regex) {
        while (true) {
            try {
                System.out.println(inpMsg);
                String str = sc.nextLine();
                if (str.isEmpty() || !str.matches(regex)) {
                    throw new Exception();
                }
                return str.trim();
            } catch (Exception e) {
                System.out.println(errMsg);
            }
        }
    }

    public static String getString(String inpMsg, String errMsg) {
        while (true) {
            try {
                System.out.println(inpMsg);
                String str = sc.nextLine().trim();
                if (str.isEmpty()) {
                    throw new Exception();
                }
                return str.trim();
            } catch (Exception e) {
                System.out.println(errMsg);
            }
        }
    }

    public static int getAnPosInteger(String inpMSG, String errorMsg) {
        int result;
        while (true) {
            System.out.print(inpMSG);
            try {
                result = Integer.parseInt(sc.nextLine().trim());
                if (result > 0) {
                    return result;
                } else {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                System.out.println(errorMsg);
            }
        }
    }

    public static String getTime() {
        LocalDate currentDate = LocalDate.now();
        boolean validate = false;
        while (!validate) {
            System.out.print("Enter production month and year (MM/YYYY): ");
            String productionMonthYear = sc.nextLine().trim();
            if (productionMonthYear.matches("^(0[1-9]|1[0-2])/\\d{4}$")) {
                int monthEntered = Integer.parseInt(productionMonthYear.split("/")[0]);
                int yearEntered = Integer.parseInt(productionMonthYear.split("/")[1]);
                if (yearEntered < currentDate.getYear()
                        || (yearEntered == currentDate.getYear() && monthEntered <= currentDate.getMonthValue())) {
                    validate = true;
                    return productionMonthYear;
                } else {
                    System.out.println("The production year cannot be in the future. Please try again.");
                }
            } else {
                System.out.println("Invalid format. Please enter in MM/YYYY format.");
            }
        }
        return null;
    }

    public static <T> void saveToFile(List<T> items, String filePath) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(items);
            System.out.println("Data saved successfully to " + filePath);
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    public static <T> List<T> loadFromFile(String filePath) throws ClassNotFoundException {
        List<T> items = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            items = (List<T>) ois.readObject();
            System.out.println("Data loaded successfully from " + filePath);
        } catch (IOException e) {
            System.out.println("Error loading data: " + e.getMessage());
        }
        return items;
    }

}
