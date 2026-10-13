import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class CarPerformanceProject {

    static Scanner input = new Scanner(System.in);

    static String[] parts = new String[100];
    static double[] prices = new double[100];
    static int partCount = 0;

    public static void main(String[] args) {

        int choice;

        do {

            displayMenu();
            choice = getMenuChoice();

            switch(choice) {

                case 1:
                    addPart();
                    break;

                case 2:
                    viewParts();
                    break;

                case 3:
                    calculateTotalCost();
                    break;

                case 4:
                    horsepowerCalculator();
                    break;

                case 5:
                    quarterMileCalculator();
                    break;

                case 6:
                    powerToWeightCalculator();
                    break;

                case 7:
                    saveBuildSummary();
                    break;

                case 8:
                    System.out.println("Exiting program...");
                    break;

                default:
                    System.out.println("Invalid option.");
            }

        } while(choice != 8);
    }

    public static void displayMenu() {

        System.out.println("\n===== Automotive Performance Tool =====");
        System.out.println("1. Add Car Part");
        System.out.println("2. View Parts");
        System.out.println("3. Calculate Total Build Cost");
        System.out.println("4. Horsepower Calculator");
        System.out.println("5. Quarter Mile Estimator");
        System.out.println("6. Power-to-Weight Calculator");
        System.out.println("7. Save Build Summary");
        System.out.println("8. Exit");
        System.out.print("Choose an option: ");
    }

    public static int getMenuChoice() {

        while(!input.hasNextInt()) {
            System.out.println("Invalid input.");
            input.next();
        }

        return input.nextInt();
    }

    public static void addPart() {

        input.nextLine();

        System.out.print("Enter part name: ");
        String partName = input.nextLine();

        double price;

        do {

            System.out.print("Enter part price: ");

            while(!input.hasNextDouble()) {
                System.out.println("Invalid price.");
                input.next();
            }

            price = input.nextDouble();

        } while(price < 0);

        parts[partCount] = partName;
        prices[partCount] = price;

        partCount++;

        System.out.println("Part added.");
    }

    public static void viewParts() {

        if(partCount == 0) {
            System.out.println("No parts added.");
            return;
        }

        for(int i = 0; i < partCount; i++) {

            System.out.println((i + 1) + ". " + parts[i] + " - $" + prices[i]);
        }
    }

    public static void calculateTotalCost() {

        double total = 0;

        for(int i = 0; i < partCount; i++) {
            total += prices[i];
        }

        System.out.println("Total Build Cost: $" + total);
    }

    public static void horsepowerCalculator() {

        System.out.print("Enter current horsepower: ");
        double currentHP = getPositiveDouble();

        System.out.print("Enter horsepower added: ");
        double addedHP = getPositiveDouble();

        double finalHP = currentHP + addedHP;

        System.out.println("Estimated Horsepower: " + finalHP);
    }

    public static void quarterMileCalculator() {

        System.out.print("Enter horsepower: ");
        double horsepower = getPositiveDouble();

        System.out.print("Enter vehicle weight: ");
        double weight = getPositiveDouble();

        double estimate = 5.825 * Math.pow((weight / horsepower), 0.3333);

        System.out.printf("Estimated Quarter Mile Time: %.2f seconds\n", estimate);
    }

    public static void powerToWeightCalculator() {

        System.out.print("Enter horsepower: ");
        double horsepower = getPositiveDouble();

        System.out.print("Enter weight: ");
        double weight = getPositiveDouble();

        double ratio = horsepower / weight;

        System.out.printf("Power-to-Weight Ratio: %.4f\n", ratio);
    }

    public static double getPositiveDouble() {

        double value;

        do {

            while(!input.hasNextDouble()) {

                System.out.println("Invalid number.");
                input.next();
            }

            value = input.nextDouble();

        } while(value <= 0);

        return value;
    }

    public static void saveBuildSummary() {

        try {

            FileWriter writer = new FileWriter("build_summary.txt");

            writer.write("=== Car Build Summary ===\n");

            double total = 0;

            for(int i = 0; i < partCount; i++) {

                writer.write(parts[i] + " - $" + prices[i] + "\n");
                total += prices[i];
            }

            writer.write("\nTotal Cost: $" + total);

            writer.close();

            System.out.println("Build summary saved.");

        } catch(IOException e) {

            System.out.println("Error saving file.");
        }
    }
}
