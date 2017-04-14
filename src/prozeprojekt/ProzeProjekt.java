/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package prozeprojekt;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;



/**
 * klasa w ktorej znajduje sie metoda main(), czyli wywolujemy w niej interfejs graficzny (obiekt klasy MojeMenu)
 */
public class ProzeProjekt 
{      

    public static void main(String[] args) throws IOException 
    {
               
        MojeMenu menu = new MojeMenu();
        
        Zapisywacz z = new Zapisywacz();
      //  z.otworzPlik("najlepszeWyniki.txt");
       // z.dodajRekord("15");
        //z.dodajRekord("\n");
        //z.dodajRekord("14");
        //z.zamknijPlik();
          
        
        
    }

}
