package servercaffe;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class ServerCaffe extends Thread {

    private ServerSocket server;
    public static ArrayList<Caffe> listaCaffe = new ArrayList();
    
    public ServerCaffe(int port) throws IOException {
        server = new ServerSocket(port);
        server.setSoTimeout(port);
    }

    @Override
    public void run() {
        Socket connection = null;

        while (!Thread.interrupted()) {
            try {
                connection = server.accept();
                System.out.println("Connessione da: " + connection.getInetAddress().toString() + ":" + connection.getPort());
                Thread client = new ClientThread(connection);
                client.start();
            } catch (SocketTimeoutException e) {

            } catch (IOException e) {

            }

            try {
                server.close();
            } catch (IOException e) {

            }
        }
    }

    public static void main(String[] args) {
        int c;
        listaCaffe.add(new Caffe(15215));
        listaCaffe.add(new Caffe(27812));
        
        try {
            ServerCaffe server = new ServerCaffe(12345);
            server.start();
            c = System.in.read();
            server.interrupt();
            server.join();
        } catch (IOException e) {
            System.out.println("Errore");
        } catch (InterruptedException e){
            System.out.println("Fine");
        }
    }
}
