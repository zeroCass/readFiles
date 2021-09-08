
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
    public static void main(String[] args) throws IOException, InterruptedException {
        
       //Recebe o diretorio dos arquivos
        //File dir = new File("C:\\Users\\mayll\\OneDrive\\Documentos\\Lab1_OAC\\videoFiles\\frames\\S");
        //Cria o novo file que ira receber os dados
        //File newFile = new File("C:\\Users\\mayll\\OneDrive\\Documentos\\Lab1_OAC\\videoFiles\\frames\\S\\bigS.s");
        //toFileS toS = new toFileS(dir,newFile);
        
    ProcessBuilder processBuilder = new ProcessBuilder("C:/Windows/System32/cmd.exe");

    // -- Linux --

    // Run a shell command
    //processBuilder.command("bash", "-c", "ls /home/mkyong/");

    // Run a shell script
    //processBuilder.command("path/to/hello.sh");

    // -- Windows --

    // Run a command
    
    Process process = processBuilder.start();
    BufferedWriter p_stdin = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
    File dir = new File("C:\\Users\\mayll\\OneDrive\\Documentos\\Lab1_OAC\\videoFiles\\frames\\teste");
    for (File file : dir.listFiles()) {
        
        if(file.getName().contains(".bmp")) {
            String fileName = file.getName().replace(".bmp", "");
            System.out.println(fileName);
        
            try {
                //single execution
                p_stdin.write("cd C:\\Users\\mayll\\OneDrive\\Documentos\\Lab1_OAC\\videoFiles\\frames\\teste");
                p_stdin.newLine();
                p_stdin.write("bmp2oac3.exe " + fileName + ".bmp");
                p_stdin.newLine();
                p_stdin.write("del /f " + fileName + ".mif");
                p_stdin.newLine();
                p_stdin.flush();
            }
            catch (IOException e) {
                System.out.println(e);
            }
        
        }
        
        
        
        //processBuilder.command("cmd.exe", "/c", "cd C:\\Users\\mayll\\OneDrive\\Documentos\\Lab1_OAC\\videoFiles\\frames\\imagens && bmp2oac3.exe " + fileName);
    }
    Scanner s = new Scanner( process.getInputStream() );
    while (s.hasNext())
    {
        System.out.println( s.next() );
    }
       s.close();
    p_stdin.write("exit");
    p_stdin.newLine();
    p_stdin.flush();
    System.exit(0);
    // Run a bat file
    //processBuilder.command("C:\\Users\\mkyong\\hello.bat");

  /*  try {

        Process process = processBuilder.start();

        StringBuilder output = new StringBuilder();

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getInputStream()));

        String line;
        while ((line = reader.readLine()) != null) {
            output.append(line + "\n");
        }

        int exitVal = process.waitFor();
        if (exitVal == 0) {
            System.out.println("Success!");
            System.out.println(output);
            //System.exit(0);
        } else {
            //abnormal...
        }

    } catch (IOException e) {
        e.printStackTrace();
    } catch (InterruptedException e) {
        e.printStackTrace();
    }*/
    
    

        //ProcessBuilder builder = new ProcessBuilder();
        //builder.command("cmd.exe", "/c", "dir");
        //builder.directory(new File("C:\\Users\\mayll\\OneDrive\\Documentos\\Lab1_OAC\\videoFiles\\frames\\imagens"));
        //builder.start();
        

        
        
    }
    
}
