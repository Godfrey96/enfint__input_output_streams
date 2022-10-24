import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        try (Scanner scan = new Scanner(System.in)) {
            System.out.println("Enter the path to the directory");
            String path = scan.nextLine();

            System.out.println("Enter the name of the output file");
            String fileOutPut = scan.nextLine();

            MergeFiles mergeFiles = new MergeFiles(path, fileOutPut);
            mergeFiles.findFilesAndConcatenate();
        }

    }

}