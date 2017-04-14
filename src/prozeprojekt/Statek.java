/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.

dx->prędkość chwilowa w kierunku x
dy->prędkość chwilowa w kierunku y
przyspieszenie w kier x = 0
przyspieszenie w kierunku y == przyspieszenie planety
v=vo+a*t
 */
package prozeprojekt;

import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

/**
 * 
 * klasa która reprezentuje statek powietrzny
 * 
 * zmienne:
 * 1.Moc silnika- jak szybko statek przyspiesza (stała)
 * 2.przyspieszeniePlanety(stała)
 * 3.Obiekt klasy Ustawienia (pozwala sczytywać z pliku)
 * 4.Zmienne określające współrzędne i prędkość statku
 * 5.liczbaŻyć - określa ile szans zostało graczowi
 * 6.obrazek - ikona, która reprezentuje obiekt klasy Statek
 * 7.iloscPaliwa - określa ile zostało paliwa
 * 
 */
public class Statek 
{
    /**
     *  tworzymy obiekt ustawienia po to, aby móc sczytywac z pliku konfiguracyjnego, obiekt ust ma metody które zwracaja nam poszczegolne 
     *  wartosci
     */
    boolean pauza = false;
    Ustawienia ust= new Ustawienia();
    /**
     * jak szybko bedzie poruszal sie statek
     */
     private double mocSilnika=0.6;
//    final private int przyspieszeniePlanety =ust.zwrocPrzyspieszenie();
    
    //* współrzędne naszego statku */
    private int x;
    private int y;
   /**
    * predkosc x statku w danym momencie
    */
    private double dx;
    /**
     * predkosc y statku w danym momencie
     */
    private double dy;
    /**
     * zmienna która przechowuje ilosc pozostalych zyc
     */
    private int liczbaZyc;
    
  /**
   * ikona naszego statku
   */
    private Image obrazek;
    /**
     * ilosc pozostalego paliwa
     */
    private int iloscPaliwa;
    
    private int wysokosc;
    private int szerokosc;
 
 
 /**
  * konstruktor, ustawiamy w nim liczbę początkowych żyć, ustawiamy początkową prędkość i położenie, oraz określamy początkowe położenie statku,
  * wszystkie te parametry sczytywane są z pliku konfiguracyjnego
  */
 public Statek()
 {
     /**
      * ustawiamy w konstruktorze ilosc pozostalych zyc
      */
     this.liczbaZyc=ust.zwrocLiczbeZyc();
     
     
     this.x=ust.zwrocxPoczatkowe();
     this.y=ust.zwrocyPoczatkowe();
   
     this.dx=ust.zwrocPredkoscX();
     this.dy=ust.zwrocPredkoscY();
     ImageIcon ikona = new ImageIcon (ust.zwrocIkoneStatku());
     
     obrazek=ikona.getImage();
     this.iloscPaliwa =ust.zwrocIloscPaliwa();
     this.wysokosc=ikona.getIconHeight();
     this.szerokosc=ikona.getIconWidth();
     
 }

 /**
  * funkcja ktora odpowiada za ruch statku, czyli zwiekszamy badz zmniejszamy wspolrzedna x o wartosc mocy silnika,
  * wspolrzedna y jest poza tym zmniejszana cały czas co symuluje istnienie grawitacji. Funkcja ruch jest wywoływana
  * w metodzie ActionPerformed klasy Plansza, czyli wywoływana jest co określony czas, w pliku czas ten został
  * ustalony na 60 ms
  */
 
 public void ruch()
 {
     this.dy+=0.1;
     this.x  += dx; 
     this.y  += dy;
       if (this.iloscPaliwa==0)
     {
         this.mocSilnika=0;
     }       
       else
       {
           this.mocSilnika=0.6;
       }
   
       
       
 }
 
 public void ustawLiczbeZyc(int zycia)
{
 this.liczbaZyc=zycia;  
}
 
 public boolean czyPauza()
 {
     return pauza;
 }
 
 public void ustawPolozenie(int x, int y)
 {
   this.x=x;
   this.y=y;
 }
 
 public int zwrocWysokosc()
 {
     return this.wysokosc;
 }
 
 public int zwrocSzerokosc()
 {
     return this.szerokosc;
 }
 
 public int wezX()
 {
     return x;
 }
 
  public int wezY()
 {
     return y;
 }
  
  public int zwrocLiczbeZyc()
 {
     return liczbaZyc;
 }
  
  public Image wezIkone()
  {
      return obrazek;
  }
  
  public void DodajPaliwo(int paliwo)
  {
      this.iloscPaliwa+=paliwo;
  }
  
  public void UstawPaliwo (int paliwo)
  {
      this.iloscPaliwa=paliwo;
  }
  
  public String IlePaliwa()
  {
      return (Integer.toString(this.iloscPaliwa));
  }
  
  public int IloscPaliwaInt()
  {
      return iloscPaliwa;
  }
  
  public void ZmniejszLiczbeZyc()
  {   
      if (this.liczbaZyc>0)   
      --this.liczbaZyc;
     
  }
  
  public void ZmienObrazekNaLecacy()
  {
     ImageIcon ikona = new ImageIcon ("spaceshipsOgien.gif");
     obrazek=ikona.getImage();
  }
  
  public void ZmienObrazekNaNormalny()
  {
     ImageIcon ikona = new ImageIcon (ust.zwrocIkoneStatku());
     obrazek=ikona.getImage();
  }
  
  public void ZmienObrazekNaZniszczony()
  {
       ImageIcon ikona = new ImageIcon ("eksplozja.png");
     obrazek=ikona.getImage();
  }
   
  public String JakaPredkoscPozioma()
  {
       return (Double.toString(this.dx));
  }
  
    public String JakaPredkoscPionowa()
  {
      return (Double.toString(this.dy));
  }
    public String JakaWspX()
    {
        return Integer.toString(this.x);
    }
    
    public String JakaWspY()
    {
        return Integer.toString(this.y);
    }
    
    
    public Double JakaPredkoscPoziomaInt()
    {
        return this.dx;
    }
    
    public double JakaPredkoscPionowaInt()
    {
        return this.dy;
    }
    
    public void UstawPredkosc (double dx, double dy)
    {
        this.dx=dx;
        this.dy=dy;
    }
 
  
  /**
   * metoda keyPressed w zaleznosci od nacisnietego przycisku modelujemy zachowanie statku, zwiekszajac badz zmniejszajac jego predkosc
   * 
   * @param e zawiera informację o tym jaki klawisz został naciśnięty, dzięki czemu nasz statek może odpowiednio zareagować
   */
  public void keyPressed (KeyEvent e)
  {
     
      int klawisz = e.getKeyCode();
      
      if (klawisz==KeyEvent.VK_LEFT&&this.czyPauza()==false)
      {
          
          this.dx += -mocSilnika;
            if(this.iloscPaliwa<=0)
           this.iloscPaliwa=0;
          else
              this.iloscPaliwa-=5;
           this.ZmienObrazekNaLecacy();
          
      }
      if (klawisz==KeyEvent.VK_RIGHT&&this.czyPauza()==false)
      {
          this.dx += mocSilnika;
          if(this.iloscPaliwa<=0)
           this.iloscPaliwa=0;
          else
              this.iloscPaliwa-=5;
             this.ZmienObrazekNaLecacy();
      
      }
      
      if (klawisz==KeyEvent.VK_P)
      {
          this.pauza =! this.pauza;
      }
      
      if (klawisz==KeyEvent.VK_DOWN&&this.czyPauza()==false)
      {
          this.dy += mocSilnika;
        if(this.iloscPaliwa<=0)
           this.iloscPaliwa=0;
          else
              this.iloscPaliwa-=5;
             this.ZmienObrazekNaLecacy();
      }
      if (klawisz==KeyEvent.VK_UP&&this.czyPauza()==false)
      {
          this.dy += -mocSilnika;
           if(this.iloscPaliwa<=0)
           this.iloscPaliwa=0;
          else
              this.iloscPaliwa-=5;
             this.ZmienObrazekNaLecacy();
      }
      
  }
      /**
       * 
       * modelujemy zachowanie statku, gdy przycisk zostal puszczony 
     * @param e
       */
      public void keyReleased (KeyEvent e) 
      {
         
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) 
        { 
             this.ZmienObrazekNaNormalny();
        }

        if (key == KeyEvent.VK_RIGHT)
        {
          this.ZmienObrazekNaNormalny();
          
        }

        if (key == KeyEvent.VK_UP) 
        {
               this.ZmienObrazekNaNormalny();
          
        }

        if (key == KeyEvent.VK_DOWN) 
        {
                this.ZmienObrazekNaNormalny();
        }
    }
      }

  
  

 
 
 
 
 
   
