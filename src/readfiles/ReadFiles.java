
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
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
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
                System.out.println(file.getName() + " unified file");
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
        File binFolder = new File(framesDir.getPath() + "//binFolder"); //the new folder where the files .bin will be storage
        if (!framesDir.exists()) {
                framesDir.mkdirs();
            }
        
        

        //variables
        String ffmpeg = null;
        String bmp2oac3 = null;
        String toFileBin = null;
        File videoFile = null;
        File fileBin = null;
        
        
        
//        System.out.println("Command-line arguments");
        for(String arg : args) {
            
            if (arg.contains("ffmpeg")) {
                ffmpeg = arg;
                int idxEnd = arg.indexOf(".mp4") + ".mp4".length()-1;
                //System.out.println(idxEnd);
                int idxBegin = 0;
                String line = "";
                //get the initial index of the name of the video
                for (int c = idxEnd; c > 0; c--) {
                    //System.out.println("Char: " + arg.charAt(c));
                    if ((arg.charAt(c)) == ' ') {
                        idxBegin = c;
                        idxBegin++;
                        break;
                    }
                }
                
                //creates the file video 
                String videoName = arg.substring(idxBegin, idxEnd+1);
                videoFile = new File(directory + "//" + videoName);
                System.out.println("Video name:" + videoFile.getName());
                System.out.println(videoFile.getName());
                if(!videoFile.isFile()) {
                    System.out.println("Video not found");
                    System.exit(1);
                }
                //System.out.println("Video name:  " + file.getName());
            }
                
            else if (arg.contains("bmp2oac3")) {
                bmp2oac3 = arg;
                File file = new File(directory + "//" + bmp2oac3);
                File file2 = new File(framesDir + "//" + bmp2oac3);
                System.out.println(file2.getPath());
                if (!file.isFile() && !file2.isFile()) {
                    System.out.println("File bmp2oac3.exe not found");
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
                System.out.println("Name of the file to be generated: " + bigBin);
                fileBin = new File(framesDir + "//" + bigBin);
                
            }
                
            //System.out.println(arg);
        }
        
        //check if the parameters are valid
        if((bmp2oac3 == null) && (ffmpeg == null) && (toFileBin == null)) {
            System.out.println("No valid command");
            System.exit(1);
        }
        
        
        
        //execute the command to convert a video to frames
        if (ffmpeg != null) {
            String output = executeCommand(directory, ffmpeg);
            System.out.println(output);
        }
        
        
        
        
        //execute command to convert a file.bmp to .bin, .data and .mif
        if (bmp2oac3 != null) {
            File oac = new File(directory + "//" + bmp2oac3);
            
            
            //check if the file bmp2oac doesnt exists in frames folder
            //if dont, move the file 
            if (!(new File(framesDir.getPath() + "//" + bmp2oac3).exists())) 
                oac.renameTo(new File(framesDir.getPath() + "//" + bmp2oac3)); //move the oac file to frames folder
            

            System.out.println("File bmp2oac3.exe moved to frames dir");
            //System.exit(0);
            
            //execute command to convert file to file 
            for (File file : framesDir.listFiles()) {
                
                if (file.getName().contains(".bmp")) {
                    String output = executeCommand(framesDir, bmp2oac3 + " " + file.getName());
                    System.out.println(output);
                }
                
            }
            
            //delete useless file genereted
            for (File file : framesDir.listFiles()) {
                
                if (file.getName().contains(".data")) {
                    executeCommand(framesDir, "del /f " + file.getName());
                    System.out.println("File" + file.getName() + " deleted");
                }
                
                if (file.getName().contains(".mif")) {
                    executeCommand(framesDir, "del /f " + file.getName());
                    System.out.println("File" + file.getName() + " deleted");
                }
                
            }
            
            //move all .bin to one folder to keep organized
            //File binFolder = new File(framesDir.getPath() + "//binFolder"); //the new folder where the file will be storage
            binFolder.mkdirs();
            for (File file : framesDir.listFiles()) {
                
                if(file.getName().contains(".bin")) {
                    file.renameTo(new File(binFolder.getPath() + "//" + file.getName()));
                    System.out.println(file.getName() + "moved to binFilder");
                }
            }
            
        }
        
        
        
        //execute command to gerenete one file .bin
        if (toFileBin != null) {
            toFileBin(binFolder, fileBin);
        }
        
        

    }
    
    
    
    
    
    
}
