
package readfiles;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Scanner;

public class ReadFiles {
    
    
    public static String executeCommand (String command) {
        String line;
        String resultat = "";
        try {
            ProcessBuilder builder;

            builder = new ProcessBuilder("cmd.exe", "/c", command);
    
            
            builder.redirectErrorStream(true);
            Process p = builder.start();
            BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
            while (true) {
                line = r.readLine();
                if (line == null) {
                    break;
                }
                resultat += line + "\n";
            }
        } catch (IOException e) {
            System.out.println("Exception = " + e.getMessage());
        }
        return resultat;
    
    }
    
    
    
    
    
    
    
    
    public static void main(String[] args) throws IOException, InterruptedException {
        
       //Recebe o diretorio dos arquivos
        //File dir = new File("C:\\Users\\mayll\\OneDrive\\Documentos\\Lab1_OAC\\videoFiles\\frames\\S");
        //Cria o novo file que ira receber os dados
        //File newFile = new File("C:\\Users\\mayll\\OneDrive\\Documentos\\Lab1_OAC\\videoFiles\\frames\\S\\bigS.s");
        //toFileS toS = new toFileS(dir,newFile);
        
        
        
//    ProcessBuilder processBuilder = new ProcessBuilder("C:/Windows/System32/cmd.exe");
//    processBuilder.directory(new File("C:\\Users\\mateu\\OneDrive\\Documentos\\OAC\\Lab 1\\Video Files\\frames"));

    // -- Linux --

    // Run a shell command
    //processBuilder.command("bash", "-c", "ls /home/mkyong/");

    // Run a shell script
    //processBuilder.command("path/to/hello.sh");

    // -- Windows --

    // Run a command
    
//    Process process = processBuilder.start();
//    BufferedWriter p_stdin = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
    File dir = new File("C:\\Users\\mateu\\OneDrive\\Documentos\\OAC\\Lab 1\\Video Files\\frames");
    for (File file : dir.listFiles()) {

        if(file.getName().contains(".bmp")) {
            String fileName = file.getName().replace(".bmp", "");
            System.out.println(fileName);
            String result = executeCommand("cd C:\\Users\\mateu\\OneDrive\\Documentos\\OAC\\Lab 1\\Video Files\\frames && bmp2oac3.exe "+ fileName + ".bmp");
            executeCommand("cd C:\\Users\\mateu\\OneDrive\\Documentos\\OAC\\Lab 1\\Video Files\\frames && del /f " + fileName + ".mif");
            executeCommand("cd C:\\Users\\mateu\\OneDrive\\Documentos\\OAC\\Lab 1\\Video Files\\frames && del /f " + fileName + ".data");
            System.out.println(result);
            
            
//            try {
//                //single execution
//                p_stdin.write("cd C:\\Users\\mayll\\OneDrive\\Documentos\\Lab1_OAC\\videoFiles\\frames\\teste");
//                p_stdin.newLine();
//                p_stdin.write("bmp2oac3.exe " + fileName + ".bmp");
//                p_stdin.newLine();
//                p_stdin.write("del /f " + fileName + ".mif");
//                p_stdin.newLine();
//                p_stdin.flush();
//            }
//            catch (IOException e) {
//                System.out.println(e);
//            }
        
        }
        
        
        
        //processBuilder.command("cmd.exe", "/c", "cd C:\\Users\\mayll\\OneDrive\\Documentos\\Lab1_OAC\\videoFiles\\frames\\imagens && bmp2oac3.exe " + fileName);
    }
//    Scanner s = new Scanner( process.getInputStream() );
//    while (s.hasNext())
//    {
//        System.out.println( s.next() );
//    }
//       s.close();
//    p_stdin.write("exit");
//    p_stdin.newLine();
//    p_stdin.flush();
//    int exit = process.waitFor();
//    System.out.println("Exit status: " + exit);

        

        
        
    }
    
}
