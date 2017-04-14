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
 * Po zderzeniu się z obiektem klasy meteor statkiem tracimy życie. Meteory spadają z losowych punktów, mają stałą określoną 
 * prędkość w pionie oraz prędkość poziomą zerową.
 */


public class Meteor 
{
 Ustawienia ust = new Ustawienia();   
 private Image obrazek;
 private int x,y;

/**
 * ustawiamy obrazek, położenie początkowe,
 * 
 */ 
 public Meteor (double skalax, double skalay)
 {
     this.x = (int)(250*skalax);
     this.y = (int)(200*skalay);
     
  ImageIcon ikona = new ImageIcon (ust.zwrocIkoneMeteoru());
  obrazek=ikona.getImage();
 }
 /**
  * zwracamy ikonę statku 
     * @return 
  */
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
     * funkcja która powoduje się zwiększanie współrzędnej y meteoru (czyli wywołuje jego ruch), współrzędna x na której
     * poruszać się będzie meteor jest losowana generatorem liczb pseudolosowych
     */
    public void ruszMeteor()
    {
        if (this.y<= 900)
        {            
            this.y+=10;  
        }
        else 
        {
            Random generator = new Random();
            this.y=0;
            this.x=generator.nextInt(1000);
         }
     
    }
    
}

