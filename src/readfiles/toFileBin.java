package readfiles;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class toFileBin {
    private OutputStream binNewFile;
    private File dir;
    private String fileName;
    
    public toFileBin(File dir, File newFile) throws java.io.FileNotFoundException{
        //recebe como argumento a localização do arquivo que gostariamos de ler escrever
        this.dir = dir;
        //this.binDir = new FileInputStream(dir);
        this.binNewFile = new FileOutputStream(newFile);
        this.fileName = newFile.getName();
    }
    
    public void writeBin() throws FileNotFoundException, IOException{
        for(File file : this.dir.listFiles()){
            System.out.println("loop for");
            if(file.getName().contains(".bin") && !file.getName().equals(this.fileName)){
                InputStream is = new FileInputStream(file);
                int lerbyte = is.read();
                while(lerbyte != -1){
                    lerbyte = is.read();

                    this.binNewFile.write(lerbyte);
                    //System.out.println(file.getName());
                } 
                System.out.println("Arquivo " + file.getName() + " gerado" );
                is.close();
            }              
        }
        this.binNewFile.close();
    }   
}


