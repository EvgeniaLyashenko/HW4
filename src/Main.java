import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Program assignment: Homework A2, A3
 * Created by: Evgenia Lyashenko
 * Date: 21/08/2016
 */

public class Main {
    private static void displayData(List<Double> list){
        list.forEach(System.out::println);
    }

    private static void enterData(List<Double> list, int quantity) throws InputMismatchException {
        System.out.println("Enter the values");
        Scanner in = new Scanner(System.in);
        for (int i = 0; i < quantity; i++) {
            list.add(in.nextDouble());
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the file name:");
        String fileName = in.nextLine();
        System.out.println("Enter the mode (r - Read; w - Write; m - Modify):");
        String mode = in.nextLine();

        FileHandler file= new FileHandler();
        List<Double> datalist = new ArrayList<>();
        switch(mode){
            case "r":
                try
                {
                    file.read(fileName, datalist);
                }catch (NumberFormatException e) {
                    System.out.println("File data is not correct");
                    return;
                }
                displayData(datalist);
                break;
            case "w":
                System.out.println("Enter the quantity of numbers");
                int number_quantity = in.nextInt();
                try{
                    enterData(datalist,number_quantity);
                } catch (InputMismatchException e) {
                    System.out.println("The value entered is not real number");
                    return;
                }
                file.write(fileName,datalist);
                break;
            case "m":
                try{
                    file.read(fileName,datalist);
                    file.modify(fileName,datalist);
                }catch(IndexOutOfBoundsException e){
                    return;
                }

                break;
            default:
                System.out.println("The mode entered is not correct");
        }

    }
}
