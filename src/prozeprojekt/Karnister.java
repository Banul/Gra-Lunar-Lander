/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prozeprojekt;

import java.awt.Image;
import java.util.Random;
import javax.swing.ImageIcon;

/**
 * Po wleceniu statkiem na obiekt klasy karnister, zwiększa się wartość pola paliwo statku. 
 */
public class Karnister 
{
 Ustawienia ust = new Ustawienia(); 
  private final int czas = ust.zwrocczas();
 private Image obrazek;
 private int x,y;
 private double stop;
 
 /**
  * ustawia położenie i ikonę obiektu klasy 
  */
 public Karnister (double skalax, double skalay)
 {
     
     this.x = (int)(50*skalax);
     this.y = (int)(200*skalay);
     
  ImageIcon ikona = new ImageIcon (ust.zwrocIkonePaliwa());
  obrazek=ikona.getImage();
 }
 
 /**
  * funkcja w której ustawiamy w losowy sposób współrzędne obiektu klasy karnister(zwiększającego poziom paliwa)
  */
 public void UstawLosoweWspolrzedne(double skalax, double skalay)
 {
      Random generator = new Random();
      this.x=(int)(generator.nextInt(1400)*skalax);
      this.y=(int)(generator.nextInt(300)*skalay);
 }
 
 public Image wezIkone()
  {
      return obrazek;
  }
 
 public int zwrocX()
  {
      return x;
  }
  
    public int zwrocY()
  {
      return y;
  }
    /**
     * funckja w której ustalamy co jaki czas karnister ma zmieniać położenie oraz wywoływana jest tutaj funkcja 
     * UstawLosoweWspolrzedne która losuje nam miejsce gdzie pojawi sie obiekt klasy karnister
     */
    
    public void ruszKarnister(double skalax, double skalay)
    {
     
        double start = 0;
        stop+=czas;
            
       if ((stop-start>=5000))
       {
           this.UstawLosoweWspolrzedne(skalax, skalay);
           stop = start;
       }
    }
    
 
 
    
}
