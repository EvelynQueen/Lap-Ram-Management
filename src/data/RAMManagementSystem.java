package data;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import utils.FileUtils;

public class RAMManagementSystem {

    public List<RAMItem> ramItems;

    public RAMManagementSystem(List<RAMItem> ramItems) {
        this.ramItems = ramItems;
    }

    public RAMManagementSystem() {
        ramItems = new ArrayList<>();
    }

    public void addRAMItem() {
        String code = FileUtils.getString(
                "Enter RAM code <RAMxyz>, where xyz is a positive number:",
                "This field is required and must match <RAMxyz>.",
                "^RAM\\d{3}$"
        );

        while (ramItemExists(code)) {
            System.out.println("RAM item with the same code already exists. Please enter a different code.");
            code = FileUtils.getString(
                    "Enter RAM code <RAMxyz>, where xyz is a positive number:",
                    "This field is required and must match <RAMxyz>.",
                    "^RAM\\d{3}$"
            );
        }

        String type = FileUtils.getString("Enter RAM Type: ", "This field is required.");
        int busSpeed = FileUtils.getAnPosInteger(
                "Enter RAM bus speed (must be a positive integer in MHz): ",
                "Bus speed must be a positive integer. Please try again."
        );
        String brand = FileUtils.getString("Enter RAM Brand: ", "This field is required.");
        int quantity = FileUtils.getAnPosInteger(
                "Enter RAM quantity (must be a positive integer): ",
                "Quantity must be a positive integer. Please try again."
        );
        String productionMonthYear = FileUtils.getTime();

        RAMItem ramItem = new RAMItem(code, type, busSpeed, brand, quantity, productionMonthYear);
        ramItems.add(ramItem);
        System.out.println("RAM item added successfully.");
    }

    public boolean ramItemExists(String code) {
        for (RAMItem ramItem : ramItems) {
            if (code.matches(ramItem.getCode())) {
                return true;
            }
        }
        return false;
    }

    public void searchRAMItems() {
        System.out.println("Search by:");
        System.out.println("1. Type");
        System.out.println("2. Bus Speed");
        System.out.println("3. Brand");

        int searchChoice = FileUtils.getAnPosInteger(
                "Choose an option (1-3): ", "");

        String searchValue;
        List<RAMItem> results = new ArrayList<>();

        switch (searchChoice) {
            case 1:
                searchValue = FileUtils.getString("Enter RAM Type: ", "This field is required.");
                results = filterByType(searchValue);
                break;
            case 2:
                int minSpeed = FileUtils.getAnPosInteger("Enter minSpeed: ", "This field is required");
                int maxSpeed = FileUtils.getAnPosInteger("Enter maxSpeed: ", "This field is required");
                results = filterByBusSpeed(minSpeed, maxSpeed);
                break;
            case 3:
                searchValue = FileUtils.getString("Enter RAM Brand: ", "This field is required.");
                results = filterByBrand(searchValue);
                break;
            default:
                System.out.println("Invalid choice. Please select 1, 2, or 3.");
                return;
        }

        displayResults(results);
    }

    public List<RAMItem> filterByType(String type) {
        List<RAMItem> filteredItems = new ArrayList<>();
        for (RAMItem item : ramItems) {
            if (item.isActive() && item.getType().equalsIgnoreCase(type)) {
                filteredItems.add(item);
            }
        }
        return filteredItems;
    }

    public List<RAMItem> filterByBusSpeed(int minSpeed, int maxSpeed) {
        List<RAMItem> filteredItems = new ArrayList<>();
        for (RAMItem item : ramItems) {
            if (item.isActive() == true) {
                if (minSpeed <= item.getBusSpeed() && maxSpeed >= item.getBusSpeed()) {
                    filteredItems.add(item);
                }
            }
        }
        return filteredItems;
    }

    public List<RAMItem> filterByBrand(String brand) {
        List<RAMItem> filteredItems = new ArrayList<>();
        for (RAMItem item : ramItems) {
            if (item.isActive() && item.getBrand().equalsIgnoreCase(brand)) {
                filteredItems.add(item);
            }
        }
        return filteredItems;
    }

    public void displayResults(List<RAMItem> results) {
        if (results.isEmpty()) {
            System.out.println("No matching items found.");
        } else {
            results.forEach(System.out::println);
        }
    }

    public void updateRAMItem() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter code of the item to update: ");
        String code = sc.nextLine().trim();

        RAMItem item = getItemByCode(code); // Get the item by code
        if (item == null) {
            System.out.println("Error: Item not found.");
            return;
        }

        System.out.print("Enter new type (or press Enter to skip): ");
        String type = sc.nextLine().trim();
        if (!type.isEmpty()) {
            item.setType(type);
        }

        System.out.print("Enter new bus speed (or press Enter to skip): ");
        String bus = sc.nextLine().trim();
        if (!bus.isEmpty()) {
            item.setBusSpeed(Integer.parseInt(bus));
        }

        System.out.print("Enter new brand (or press Enter to skip): ");
        String brand = sc.nextLine().trim();
        if (!brand.isEmpty()) {
            item.setBrand(brand);
        }

        System.out.print("Enter new quantity (or press Enter to skip): ");
        String qtyStr = sc.nextLine().trim();
        if (!qtyStr.isEmpty()) {
            item.setQuantity(Integer.parseInt(qtyStr));
        }

        System.out.print("Enter new production month/year (or press Enter to skip): ");
        String prodMonthYear = sc.nextLine().trim();
        if (!prodMonthYear.isEmpty()) {
            item.setProductionMonthYear(prodMonthYear);
        }

        System.out.println("Item updated successfully.");
    }

    public void deleteRAMItem() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter code of the item to delete: ");
        String code = sc.nextLine().trim();

        RAMItem item = getItemByCode(code);
        if (item == null) {
            System.out.println("Error: Item not found.");
            return;
        }
        System.out.print("Are you sure you want to delete this item? (yes/no): ");
        String confirmation = sc.nextLine().trim().toLowerCase();
        if (confirmation.equals("yes")) {
            item.setActive(false);
            System.out.println("RAM item marked as inactive.");
        } else {
            System.out.println("Deletion canceled.");
        }
    }

    public void displayAllActiveRAMItems() {
        List<RAMItem> activeItems = new ArrayList<>();
        for (RAMItem item : ramItems) {
            if (item.isActive()) {
                activeItems.add(item);
            }
        }

        activeItems.sort(Comparator.comparing(RAMItem::getType)
                .thenComparing(RAMItem::getBusSpeed)
                .thenComparing(RAMItem::getBrand));

        if (activeItems.isEmpty()) {
            System.out.println("No active RAM items to display.");
        } else {
            System.out.println("Active RAM Items:");
            activeItems.forEach(System.out::println);
        }
    }

    public RAMItem getItemByCode(String code) {
        for (RAMItem item : ramItems) {
            if (item.getCode().equals(code)) {
                return item;
            }
        }
        return null;
    }

    public void saveData() {
        String filePath = "D:\\LAB211\\lapRAMManagement\\ram.dat";
        FileUtils.saveToFile(ramItems, filePath);
    }

    public void loadData() {
        String filePath = "D:\\LAB211\\lapRAMManagement\\ram.dat";

        try {
            ramItems = FileUtils.loadFromFile(filePath);
            if (ramItems == null || ramItems.isEmpty()) {
                System.out.println("No data found or file is empty.");
            } else {
                System.out.println(ramItems.size() + " items loaded successfully.");
            }
        } catch (Exception ex) {
            System.out.println("Error loading data: " + ex.getMessage());
        }
    }

}
