/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prozeprojekt;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author user
 */
public class Wybuch 
{
     Ustawienia ust = new Ustawienia(); 
  private final int czas = ust.zwrocczas();
     private Statek statek;
     private Image obrazek;
 private int x,y;
  private double stop;

     
    Wybuch()
    {
      
         ImageIcon ikona = new ImageIcon ("eksplozja.png");
             obrazek=ikona.getImage();
     
    
//        this.x=statek.wezX();
 //       this.y=statek.wezY();

    }
     
    public int zwrocX()
    {
        return x;
    }

    public int zwrocY()
    {
        return y;
    }
    
    public Image zwrocIkone()
    {
        return this.obrazek;
    }
    
    public void ustawPozycje()
    {
        this.x=statek.wezX()+100;
        this.y=statek.wezY()+200;
        
    }
    
      public void ustawWybuch()
    {
     
        double start = 0;
        stop+=czas;
            
       if ((stop-start<=700))
       {
           int k =statek.wezX();
           int b =statek.wezY();
           
           
       }
    }
    
     
     
     
     
}
