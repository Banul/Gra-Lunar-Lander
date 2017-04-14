/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prozeprojekt;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import static javax.swing.SpringLayout.HEIGHT;
import static javax.swing.SpringLayout.WIDTH;

/**
 *
 *Klasa której reprezentacją jest ikonka serca wyświetlana na planszy, służy pokazaniu graczowi ile zostało mu żyć, pola klasy to:
 * 1. obiekt typu ustawienia do sczytywania ikonki dla obiektów tej klasy
 * 2. obiekt typu image - do niego ładowana jest ikonka, która jest potem wyświetlana
 * 3. dwa obiekty typu int - do określenia położenia obiektów klas na planszy
 *
 */
public class Życie 
{
 Ustawienia ust = new Ustawienia();
 private Image obrazek;
 private Image obrazek2;
 private int x,y;
 
/**
 * 1.Określamy położenie
 * 2.Ładujemy pole obrazek odpowiednią ikoną
 * 
 * @param x położenie x na planszy
 * @param y położenie y na planszy
 */
public Życie(int x, int y)
{
 this.x= x;
 this.y=y;
 ImageIcon ikona = new ImageIcon (ust.zwrocIkoneSerca());
 
 obrazek=ikona.getImage();
 
 //Image img = myIcon2.getImage(); 

 
}
/**
 * zwraca ikonę serca, potrzebne do narysowania na planszy
 * 
     * @return 
 */


  public Image wezIkone()
  {
      return obrazek;
  }
  public Image wezIkonePrzeskalowanaZycia(int x,int y)
  {
    Image newimg = this.wezIkone().getScaledInstance(x, y,  java.awt.Image.SCALE_SMOOTH);  
 ImageIcon nowaIkona = new ImageIcon(newimg);
 obrazek2 = nowaIkona.getImage(); 
 return obrazek2;
  }
  
  //img=obrazek

/*
  Image img = myIcon2.getImage();
  Image newimg = img.getScaledInstance(230, 310,  java.awt.Image.SCALE_SMOOTH);  
newIcon = new ImageIcon(newimg); 
  */
  
 
  public int zwrocX()
  {
      return x;
  }
  
    public int zwrocY()
  {
      return y;
  }
    
    /**
     * ustawiamy położenie serca
     * @param ustawiacz gdzie chcemy umieścić ikonę serca
     */
    public void ustawX(int ustawiacz)
    {
        this.x = ustawiacz;
    }
 
}

