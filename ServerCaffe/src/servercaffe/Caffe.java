/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servercaffe;

import java.util.ArrayList;

/**
 *
 * @author JAKUBBOCIAN
 */
public class Caffe {
    public int codice;
    private ArrayList voti;

    public Caffe(int codice) {
        this.codice = codice;
        voti = new ArrayList();
    }
    
    public synchronized void aggiungiVoto(int voto){
        voti.add(voto);
    }
    
    public synchronized String media(){
        int tot=0;
        for(int i=0; i<voti.size();i++){
            tot+=(int)voti.get(i);
        }
        return Double.toString(tot/voti.size());
    }
}
