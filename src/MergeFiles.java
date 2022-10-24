import java.io.*;

public class MergeFiles {

    private String pathRoot;
    private String outputFile;

    public MergeFiles(String pathRoot, String outputFile) {
        this.pathRoot = pathRoot;
        this.outputFile = outputFile;
    }

    public void findFilesAndConcatenate() throws IOException {
        File dir = new File(pathRoot);
        File fileOutputFileDir = new File(dir, outputFile);
        PrintWriter pw = new PrintWriter(new FileWriter(fileOutputFileDir));
        String[] files = dir.list();

        for (int i = 0; i < files.length; i++) {

            File f = new File(dir, files[i]);

            if (f.isDirectory()) {
                findFilesAndConcatenate();
            } else {
                BufferedReader br = new BufferedReader(new FileReader(f));
                String readLine = br.readLine();

                while (readLine != null) {
                    pw.println(readLine);
                    readLine = br.readLine();
                }
                br.close();
            }
            pw.close();
            pw.flush();
        }
    }

}
