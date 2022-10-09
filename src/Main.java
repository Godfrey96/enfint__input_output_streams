import java.io.*;

public class Main {
    static String dir = System.getProperty("user.dir");

    // method for accepting the path and outfile
    public String readFileLoc(String filepath,String FileName) {

        String filepathName = filepath+FileName;
        BufferedReader br;
        String content="";
        String wholeContent="";

        try {
            br = new BufferedReader(new FileReader(filepathName));

            while ((content = br.readLine()) != null) {
                wholeContent += content + "\n";
            }
            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return wholeContent;
    }

    // method for outputting the concatenated files
    public void outPutFile(String outPutContent) throws IOException {

        FileWriter outFIle = new FileWriter(dir + "\\Output\\output.txt");
        BufferedWriter brWriter = new BufferedWriter(outFIle);

        brWriter.write(outPutContent);
        brWriter.close();
    }

    public static void main(String[] args) throws IOException {

        Main br = new Main();

        File directory=new File(dir + "\\Files\\Folder 1");
        int fileCount = directory.list().length;
        String require;
        String content ="";
        String outPutContent = "";
        String outPutContentA = "";

        for (int i = 1; i < fileCount; i++) {
            content = br.readFileLoc(dir + "\\Files\\Folder 1\\","file 1-" + i + ".txt");
            if(content != null) {
                outPutContent = outPutContent + content;
            }
        }

        require = "*require 'Folder 1/Folder 2/File 2-1'*";
        if (outPutContent.contains(require)){
            File directoryA = new File(dir + "\\Files\\Folder 1\\Folder 2");
            int fileCountA = directoryA.list().length;
            String contentA = "";

            for (int i = 1; i <= directoryA.list().length; i++) {
                contentA = br.readFileLoc(dir + "\\Files\\Folder 1\\Folder 2\\","file 2-" + i + ".txt");
                if (i > 1){
                    outPutContentA = "";
                }
                outPutContentA = outPutContentA + contentA;
            }
        }

        require = "*require 'Folder 1/File 1-1'*";
        if (outPutContentA.contains(require)){
            String contentA11 = "";
            contentA11 = br.readFileLoc(dir + "\\Files\\Folder 1\\","file 1-1.txt");
            outPutContentA = outPutContentA.replace(require, contentA11);
        }

        require = "*require 'Folder 1/Folder 2/File 2-1'*";
        if (outPutContentA.contains(require)){
            String contentA21 = "";
            contentA21 = br.readFileLoc(dir + "\\Files\\Folder 1\\Folder 2\\","file 2-1.txt");
            outPutContentA = outPutContentA.replace(require, contentA21);
        }

        // calling method for printing out the concatenated files
        br.outPutFile(outPutContentA);

    }

}