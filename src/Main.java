import java.io.*;
import java.util.Scanner;

public class Main {

    static String dir = System.getProperty("user.dir");

    public static void main(String[] args) throws IOException {

        MergeFiles merge = new MergeFiles();

        File files = new File(dir + "\\Files");
        // calling a method to list of all files and directories
        merge.listOfFiles(files);
        // calling a method to read all files
        merge.readFiles();


        Scanner scan = new Scanner(System.in);

        System.out.print("Enter the path to the directory: ");
        String path = scan.nextLine();

        System.out.println("Enter the name of the output file");
        String fileOutPut = scan.nextLine();

        merge.writeOutputFile(dir + "\\" + path +"\\" + fileOutPut);


    }

}