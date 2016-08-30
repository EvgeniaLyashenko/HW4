import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Program assignment: Homework A1, A3
 * Created by: Evgenia Lyashenko
 * Date: 21/08/2016
 */


public class FileHandler {
    /*
            flag to define which action on the screen:
            1 - there is data on screen;
            2 - what the next step;
            3 - what the next step, the file is ended;
         */
    private static int flgWTD = 0;
    /** This method is to read data from
     * file and write it into array
     */

    public static void read(String filename, List<Double> list) throws NumberFormatException{
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = br.readLine()) != null) {
                list.add(Double.parseDouble(line));
            }
            br.close();
        } catch (FileNotFoundException e) {
            System.out.println("File is not found");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void write(String filename, List<Double> list) {
        BufferedWriter bw = null;
        try {
            File file = new File(filename);
            if (!file.exists())
                file.createNewFile();

            bw = new BufferedWriter(new FileWriter(file.getAbsoluteFile()));
            bw.write("");
            for (Double aList : list) {
                bw.write(String.valueOf(aList) + '\n');
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void modify(String filename, List<Double> list) {
        List<Double> newDataList = new ArrayList<>();
        System.out.println(list.get(0));
        int position = 0;
        flgWTD = 1;

        while (position < list.size()){
            switch(flgWTD){
                case 1:
                    actionWithData(list, position, newDataList);
                    position++;
                    break;
                case 2:
                    nextStep(list,position,newDataList);
                    break;
                case 3:
                    saveFile(filename,newDataList);
                    flgWTD = 0;
                    break;
                case 0:
                    return;
            }
        }
        saveFile(filename,newDataList);
        System.out.println("The end!");
    }
    public static double enterValue(){
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the value:");
        boolean flgEntered = false; //helps to enter the value
        double var = 0;
        while (!flgEntered) {
            try {
                var = in.nextDouble();
                flgEntered = true;
            } catch (InputMismatchException e) {
                System.out.print("The value entered is not real number. Please, enter correct number: ");
                in.next();
                flgEntered = false;
            }
        }
        return var;
    }

    public static void actionWithData(List<Double> origList, int pos, List<Double> newList){
        Scanner in = new Scanner(System.in);
        System.out.print("Select an action (a - Accept; r - Replace;  d - Delete): ");
        String action = in.nextLine();
        switch(action){
            case "a":
                newList.add(origList.get(pos));
                flgWTD = 2;
                break;
            case "r":
                newList.add(enterValue());
                flgWTD = 2;
                break;
            case "d":
                flgWTD = 2;
                break;
            default:
                System.out.println("The mode entered is not correct");
                flgWTD = 0;
                return;
        }
    }

    public static void nextStep(List<Double> origList, int pos, List<Double> newList) {
        Scanner in = new Scanner(System.in);
        System.out.print("Select an action (r - read the next number, i - insert new number, a - accept the remainder): ");
        String action = in.nextLine();
        switch(action){
            case "r":
                System.out.println(origList.get(pos));
                flgWTD = 1;
                break;
            case "i":
                newList.add(enterValue());
                flgWTD = 2;
                break;
            case "a":
                for (int i = pos; i < origList.size(); i++) {
                newList.add(origList.get(i));
                }
                flgWTD = 3;
                break;
            default:
                System.out.println("The mode entered is not correct");
                flgWTD = 0;
                return;
        }

    }

    public static void saveFile(String oldFileName, List<Double> newList){
        Scanner in = new Scanner(System.in);
        System.out.println("What do you want to do with file? \n " +
                "n - store with new name \n" +
                "o - rewrite old file" );
        String action = in.nextLine();
        switch (action){
            case "n":
                System.out.print("Enter the file name: ");
                String newFileName = in.nextLine();
                write(newFileName,newList);
                break;
            case "o":
                write(oldFileName,newList);
                break;
            default:
                System.out.println("The mode entered is not correct");
                flgWTD = 0;
                return;

        }
    }

}
