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
        File dir = new File("C:\\Users\\mayll\\OneDrive\\Documentos\\Lab1_OAC\\videoFiles\\frames\\teste\\");
        
        //Cria o novo file que ira receber os dados
        File newFile = new File("C:\\Users\\mayll\\OneDrive\\Documentos\\Lab1_OAC\\videoFiles\\frames\\teste\\teste1.bin");
        /*toFileS toS = new toFileS(dir,newFile);
        toS.writeFile();*/
        toFileBin toB = new toFileBin(dir ,newFile);
        toB.writeBin();          
    }    
}
