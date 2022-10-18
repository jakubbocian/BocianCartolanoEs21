/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package clientcaffe;

import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author davide.cartolano
 */
public class ClientCaffe {

    private Socket connection; // socket di connessione con il client
    private InputStream input; // stream di input del socket
    private OutputStreamWriter output; // stream di output del socket

    /**
     * @param connection
     */
    public ClientCaffe() throws IOException{

        this.connection = new Socket("localhost", 12345);
        input = this.connection.getInputStream();
        output = new OutputStreamWriter(this.connection.getOutputStream());

    }

    public void main(String[] args) {
       
        
        try {
            // TODO code application logic here
            ClientCaffe client = new ClientCaffe();
            
        } catch (IOException ex) {
            System.out.println("Errore creazione client");
        }
        
        String risposta_lista;
        risposta_lista = richiedi_lista();
        
        String[] lista_codici = risposta_lista.split(",");
        String[] lista_caffe = null;
        
        Scanner input = new Scanner(System.in); 
        int scelta = 0;
        String risposta;
        
        do{
        
            System.out.println("Menu:\n\n1 Vota caffe'\n2 Visualizza voto medio\n3 Esci\nScelta: ");
            scelta = input.nextInt();
            
            if(scelta == 1){
                
                int  scelta_voto;
                
                System.out.println("Quale caffe' vorresti votare?");
                
                int conta = 0;
                
                for(String caffe : lista_codici){
                
                    lista_caffe[conta] = "Caffe" + conta +1 ;
                    conta++;
                
                }
                
                try {
                    int voto;
                    System.out.println("Inserisci il voto: ");
                    
                    voto = input.nextInt();
                    risposta = "voto," + voto;
                    output.write(risposta);
                    
                    
                } catch (IOException ex) {
                    Logger.getLogger(ClientCaffe.class.getName()).log(Level.SEVERE, null, ex);
                } 
            }
            
            if(scelta == 2){
                
                try {
                    int voto;
                    System.out.println("Inserisci il voto: ");
                    
                    voto = input.nextInt();
                    risposta = "voto," + voto;
                    output.write(risposta);
                    
                    
                } catch (IOException ex) {
                    Logger.getLogger(ClientCaffe.class.getName()).log(Level.SEVERE, null, ex);
                } 
            }
            
        }while(scelta!=3);

    }
    
    public String richiedi_lista(){
    
        try {
            output.write("lista");
        } catch (IOException ex) {
            Logger.getLogger(ClientCaffe.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String risposta = null;
        
        while(risposta == null){
            risposta = input.toString();
        }
        
        return risposta;
    
    }

}
