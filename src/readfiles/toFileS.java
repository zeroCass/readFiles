package readfiles;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class toFileS {
    private File dir;
    private File newFile;
    private FileWriter both;
    
    public toFileS (File dir, File newFile){
        this.dir = dir;
        this.newFile = newFile;
    }
    
    public void writeFile() throws IOException{
        //BufferedReader br = new BufferedReader(new FileReader());       
        this.both = new FileWriter(this.newFile);
           
        int count = 0; //contador de arquivos dentro do diretorio
        //for que percorre todos os arquivos dentro do dir
        for(File file : this.dir.listFiles()) {
            BufferedReader bf = new BufferedReader(new FileReader(file)); //buffer de leitura de arquivos
           
            String str = ""; //String vazia que ira receber os dados do arquivo lido linha a linha
            String fileName = file.getName().replaceAll(".s", ""); //pega o nome do arquivo e remove a extensao
            
            //se arquivo listado for o arquivo que será gerado, ignora o proximo codigo
            if (file.getName().equals(this.newFile.getName()))
                continue;
            //this.both.write(file.getName() + "\n");
            
            System.out.println(file.getName());
            
            //ler os dados linha por linha do arquivo
            while((str = bf.readLine()) != null) {
                //caso seja o primeiro arquivo, mantém as informações de dimensoes e .byte
                if (count == 0) {
                    this.both.write(str + "\n");
                    continue;
                }
                //se linha tiver as informalçoes de dimensoes, não grava e vai para a proxima linha
                if(str.contains(fileName + ": .word 320, 240") && !str.contains(".byte"))  
                    continue;
                
                //caso contenha byte, remove ele 
                if (str.contains(".byte"))
                    str = str.replace(".byte ", "");
                           
                this.both.write(str + "\n"); //salva a linha no arquivo pretendido com quebra de linha
            }
            count++;
        }
        this.both.close();
    }
}
