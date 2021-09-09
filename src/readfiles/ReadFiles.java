
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
        
//        File dir = new File("C:\\Users\\mateu\\OneDrive\\Documentos\\OAC\\Lab 1\\Video Files\\frames");
//        for (File file : dir.listFiles()) {
//
//            if(file.getName().contains(".bmp")) {
//                String fileName = file.getName().replace(".bmp", "");
//                System.out.println(fileName);
//                String result = executeCommand("cd C:\\Users\\mateu\\OneDrive\\Documentos\\OAC\\Lab 1\\Video Files\\frames && bmp2oac3.exe "+ fileName + ".bmp");
//                executeCommand("cd C:\\Users\\mateu\\OneDrive\\Documentos\\OAC\\Lab 1\\Video Files\\frames && del /f " + fileName + ".mif");
//                executeCommand("cd C:\\Users\\mateu\\OneDrive\\Documentos\\OAC\\Lab 1\\Video Files\\frames && del /f " + fileName + ".data");
//                System.out.println(result);
//
//            }
//
//        }
    
        ProcessBuilder builder = new ProcessBuilder("cmd", "c");
        builder.directory(new File(System.getProperty("user.dir")));
        
        //get the current directory 
        System.out.println("Builder path: " + builder.directory());
        
        //Get the class path (used in jar file exection)
        //System.out.println("Path: " + ReadFiles.class.getProtectionDomain().getCodeSource().getLocation().getPath());
        
        
        //check if the dir frames exists, if dont, then creates
        File framesDir = new File(builder.directory() + "//frames");
        if (!framesDir.exists()) {
            framesDir.mkdirs();
        }
           
        String ffmpeg = null;
        String bmp2oac3 = null;
        
        System.out.println("Command-line arguments");
        for(String arg : args) {
            if (arg.contains("ffmpeg"))
                ffmpeg = arg;
            else if (arg.contains("bmp2oac3"))
                bmp2oac3 = arg;
            //System.out.println(arg);
        }
        
        //verifica se os parametros são validos
        if((bmp2oac3 == null) || (ffmpeg == null)) {
            System.out.println("Nenhum comando valido");
            System.exit(1);
        }
            
            
        
        System.out.println("FFMPEG: " + ffmpeg);
        System.out.println("BMP2OAC3: " + bmp2oac3);

    }
    
    
    
    
    
    
}
