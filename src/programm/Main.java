package programm;

import data.RAMManagementSystem;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        RAMManagementSystem system = new RAMManagementSystem();
        Scanner scanner = new Scanner(System.in);
        system.loadData();

        while (true) {
            System.out.println("1. Add RAM Item");
            System.out.println("2. Search RAM Items");
            System.out.println("3. Update RAM Item");
            System.out.println("4. Delete RAM Item");
            System.out.println("5. Display All RAM Items");
            System.out.println("6. Save Data");
            System.out.println("7. Exit");

            System.out.print("Enter your choice: ");
            int choice;
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 7.");
                scanner.nextLine();
                continue;
            }

            switch (choice) {
                case 1:
                    system.addRAMItem();
                    break;
                case 2:
                    system.searchRAMItems();
                    break;
                case 3:
                    system.updateRAMItem();
                    break;
                case 4:
                    system.deleteRAMItem();
                    break;
                case 5:
                    system.displayAllActiveRAMItems();
                    break;
                case 6:
                    system.saveData();
                    System.out.println("Data saved successfully.");
                    break;
                case 7:
                    system.saveData();//luu roi moi thoat
                    System.out.println("Exiting program...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 7.");
            }
        }
    }
}
