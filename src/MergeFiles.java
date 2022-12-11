import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MergeFiles {

    static List<File> files = new ArrayList<>();
    static Map<File, String> dependentFiles = new HashMap<>();
    static Map<File, String> content = new HashMap<>();

    public MergeFiles() {}

    // a method to list all files in a directory and subdirectories
    public static void listOfFiles(File dirPath){
        File filesList[] = dirPath.listFiles();
        for(File file : filesList) {
            if(file.isFile()) {
                files.add(file);
//                System.out.println(file.getName());
            } else {
                listOfFiles(file);
            }
        }
    }

    // a method to read all the files found in a directory and subdirectories
    public static void readFiles() throws IOException {
        for (File file : files){
            FileReader readFile = new FileReader(file);
            BufferedReader br = new BufferedReader(readFile);
            String lineReader;
            String fileName;
            StringBuilder fileContent = new StringBuilder();

            while ((lineReader = br.readLine()) != null){
                if (lineReader.charAt(0) == '*'){
                   fileName = String.valueOf(geRequiredFileName(lineReader));
                   dependentFiles.put(file, fileName);
                }else {
                    fileContent.append(lineReader).append("\n");
                }
            }
            content.put(file, fileContent.toString());
        }
    }

    // a method to write the content of all the files to an output file
    public static void writeOutputFile(String pathOutFile) throws IOException {
        FileWriter fw = new FileWriter(pathOutFile);
        PrintWriter pw = new PrintWriter(fw);
        File fileName;

        for (File file : files){
            if (dependentFiles.containsKey(file)){
                fileName = geRequiredFileName(dependentFiles.get(file));
                pw.println(content.get(fileName));
                pw.println(content.get(file));
            }else{
                pw.println(content.get(file));
            }
        }
        pw.println("\n");
        pw.close();
    }

    // a method to get a required file name
    private static File geRequiredFileName(String fileName) {
        File requiredFile = null;
        for (File file : files){
            if (file.getName().equals(fileName)){
                requiredFile = file;
                break;
            }
        }
        return requiredFile;
    }

}
