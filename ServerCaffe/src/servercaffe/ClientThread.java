package servercaffe;

import java.io.*;
import java.net.*;

public class ClientThread extends Thread{
    private Socket connection;
    private InputStream in;
    private OutputStreamWriter out;

    public ClientThread(Socket connection) throws IOException {
        this.connection = connection;
        in = this.connection.getInputStream();
        out = new OutputStreamWriter(this.connection.getOutputStream());
    }
    
    private String lista(){
        String res = "";
        for(int i=0; i<ServerCaffe.listaCaffe.size(); i++){
            res+=ServerCaffe.listaCaffe.get(i).codice+",";
        }
<<<<<<< Updated upstream
        System.out.println(res);
        if(res.equals(""))
            return res;
=======
        System.out.println(res.substring(0, res.length()-2));
>>>>>>> Stashed changes
        return res.substring(0, res.length()-2);
    }
    
    private String vota(String command){
        String res;
        String comps[];
        int scelta=-1;
        
        comps = command.split(",");
        for(int i=0;i<ServerCaffe.listaCaffe.size();i++){
            if(ServerCaffe.listaCaffe.get(i).codice == Integer.parseInt(comps[0])){
                scelta = i;
            }
        }
        System.out.println("voto " + Integer.parseInt(comps[1]));
        if(scelta==-1)
            return "ERRORE";
        System.out.println(Integer.parseInt(comps[1]));
        ServerCaffe.listaCaffe.get(scelta).aggiungiVoto(Integer.parseInt(comps[1]));
        return "OK";
    }
    
    private String media(String command){
        int scelta=-1;
        
        for(int i=0;i<ServerCaffe.listaCaffe.size();i++){
            if(ServerCaffe.listaCaffe.get(i).codice == Integer.parseInt(command)){
                scelta = i;
            }
        }
        
        if(scelta==-1)
            return "ERRORE";
        System.out.println(ServerCaffe.listaCaffe.get(scelta).media());
        return ServerCaffe.listaCaffe.get(scelta).media();
    }

    @Override
    public void run() {
        int n, i;
        String result, character;
        String comps[];
        byte buffer[] = new byte[1024];
        StringBuffer command = new StringBuffer();
        
        try{
            while((n=in.read(buffer))!=-1){
                if(n>0){
                    for(i=0;i<n;i++){
                        if(buffer[i]=='\r' || buffer[i]=='\n') {
                            comps = (command.toString()).split(",");
                            if(comps[0].equals("VOTA"))
                                result = vota(command.toString());
                            else if(comps[0].equals("MEDIA"))
                                result = media(command.toString());
                            else if(comps[0].equals("LISTA"))
                                result = lista();
                            else
                                result = "ERRORE";
                            out.write(result+"\r\n");
                            out.flush();
                            command = new StringBuffer();
                            break;
                        } else {
                            character = new String(buffer, i, 1, "ISO-8859-1");
                            command.append(character);
                        }
                    }
                }
            }
        } catch (IOException e){
            
        }
        
        try{
            in.close();
            out.close();
            connection.shutdownInput();
            connection.shutdownOutput();
            connection.close();
        } catch (IOException e){
            
        }
    }
    
    
}
