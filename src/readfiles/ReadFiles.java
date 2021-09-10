
package readfiles;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class ReadFiles {
    
    
    public static String executeCommand (File dir, String command) throws InterruptedException {
        String line;
        String resultat = "";
        try {
            ProcessBuilder builder;

            builder = new ProcessBuilder("cmd.exe", "/c", command);
            builder.directory(dir);
            
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
            p.waitFor();
        } catch (IOException e) {
            System.out.println("Exception = " + e.getMessage());
        }
        return resultat;
    
    }
    
    
    
    public static void toFileBin (File dir, File newFile) throws FileNotFoundException, IOException {
        
        OutputStream binFile = new FileOutputStream(newFile);
        
        for(File file : dir.listFiles()){
            if(file.getName().contains(".bin") && !file.getName().equals(newFile.getName())){
                InputStream is = new FileInputStream(file);
                int lerbyte = is.read();
                while(lerbyte != -1){
                    lerbyte = is.read();

                    binFile.write(lerbyte);
                    //System.out.println(file.getName());
                } 
                System.out.println("Arquivo " + file.getName() + " unificado");
                is.close();
            }              
        }
        binFile.close(); 
}
    
    
    
    
    
    
    public static void main(String[] args) throws IOException, InterruptedException {
        
        File directory = new File(System.getProperty("user.dir"));
        //get the current directory 
        //System.out.println("Builder path: " + directory);
       
        //Get the class path (used in jar file exection)
        //System.out.println("Path: " + ReadFiles.class.getProtectionDomain().getCodeSource().getLocation().getPath());
        //System.out.println("Path dir: " + System.getProperty("user.dir"));
        
      //check if the dir frames exists, if dont, then creates
        File framesDir = new File(directory + "//frames");
        if (!framesDir.exists()) {
                framesDir.mkdirs();
            }
           
        String ffmpeg = null;
        String bmp2oac3 = null;
        String toFileBin = null;
        File videoFile = null;
        File fileBin = null;
        
        
        System.out.println("Command-line arguments");
        for(String arg : args) {
            if (arg.contains("ffmpeg")) {
                ffmpeg = arg;
                int idxEnd = arg.indexOf(".mp4") + ".mp4".length()-1;
                //System.out.println(idxEnd);
                int idxBegin = 0;
                String line = "";
                for (int c = idxEnd; c > 0; c--) {
                    //System.out.println("Char: " + arg.charAt(c));
                    if ((arg.charAt(c)) == ' ') {
                        idxBegin = c;
                        idxBegin++;
                        break;
                    }
                }
                
                String videoName = arg.substring(idxBegin, idxEnd+1);
                videoFile = new File(directory + "//" + videoName);
                System.out.println("Video name:" + videoFile.getName());
                System.out.println(videoFile.getName());
                if(!videoFile.isFile()) {
                    System.out.println("Video nao encontrado");
                    System.exit(1);
                }
                //System.out.println("Video name:  " + file.getName());
            }
                
            else if (arg.contains("bmp2oac3")) {
                bmp2oac3 = arg;
                File file = new File(directory + "//" + bmp2oac3);
                if (!file.isFile()) {
                    System.out.println("Arquivo bmp2oac3.exe Nao existe");
                    System.exit(1);
                }
            }
            
            else if (arg.contains("toFileBin")) {
                toFileBin = arg;
                int idxEnd = arg.indexOf(".bin") + ".bin".length()-1;
                //System.out.println(idxEnd);
                int idxBegin = 0;
                String line = "";
                for (int c = idxEnd; c > 0; c--) {
                    //System.out.println("Char: " + arg.charAt(c));
                    if ((arg.charAt(c)) == ' ') {
                        idxBegin = c;
                        idxBegin++;
                        break;
                    }
                }
                String bigBin = arg.substring(idxBegin, idxEnd+1);
                System.out.println("Nome do arquivo a ser gerado: " + bigBin);
                fileBin = new File(framesDir + "//" + bigBin);
                
            }
                
            //System.out.println(arg);
        }
        
        //verifica se os parametros são validos
        if((bmp2oac3 == null) && (ffmpeg == null) && (toFileBin == null)) {
            System.out.println("Nenhum comando valido");
            System.exit(1);
        }
        
        
        
        if (ffmpeg != null) {
            String output = executeCommand(directory, ffmpeg);
            System.out.println(output);
        }
        
        
        
        
        
        if (bmp2oac3 != null) {
            
            for (File file : framesDir.listFiles()) {
                
                if (file.getName().contains(".bmp")) {
                    String output = executeCommand(framesDir, bmp2oac3 + " " + file.getName());
                    System.out.println(output);
                }
                
            }
            
            for (File file : framesDir.listFiles()) {
                
                if (file.getName().contains(".data")) {
                    executeCommand(framesDir, "del /f " + file.getName());
                    System.out.println("Arquivo" + file.getName() + " Excluido");
                }
                
                if (file.getName().contains(".mif")) {
                    executeCommand(framesDir, "del /f " + file.getName());
                    System.out.println("Arquivo" + file.getName() + " Excluido");
                }
                
            }
            
        }
        
        
        
        
        if (toFileBin != null) {
            toFileBin(framesDir, fileBin);
        }
        
        

    }
    
    
    
    
    
    
}
