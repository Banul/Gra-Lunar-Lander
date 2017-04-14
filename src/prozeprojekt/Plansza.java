/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prozeprojekt;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * Klasa ta dziedziczy po klasie JPanel(abyśmy mogli wykonywać na niej rysowanie) oraz implementuje ActionListenera, dzięki czemu co pewien określony
 * czas (sczytywany z pliku) wywoływana jest metoda ActionPerformed() 
 * pola klasy:
 * 1.String paliwo - wypisywane na ekranie, obok pozostałego poziomu paliwa
 * 2.Obiekt klasy ustawienia - do parsowania pliku
 * 3.Timer time - okresla co jaki czas wywoływana jest metoda ActionPerformed, zmienna czas określa co ile
 * 4.Statek, życie, meteor, karnister - Statkiem sterujemy, życie jest reprezentowane ikoną serca (ile serc tyle mamy żyć), meteory będą niszczyć statek
 * (zabierać życie w następnych etapach),karnistry(po wleceniu na nie) będą w przyszłości zwiększać poziom paliwa rysujemy ikony tych trzech 
 * elementów na planszy
 * 
 * 
 * 
 * 
 * 
 */

public class Plansza extends JPanel implements ActionListener
        
       
{      
         // GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
//int width = gd.getDisplayMode().getWidth();
//int height = gd.getDisplayMode().getHeight();
    
    private double wspX;
    private double wspY;
private double skalaX;
private double skalaY;
private int LiczbaPunktow;
private int level = 1;
private int ileDobrychLadowan=0;
private int ilePerfekcyjnychLadowan=0;

    Image img = Toolkit.getDefaultToolkit().createImage("kosmos3.jpg");
     Random generator1 = new Random(); //komentuje to randomowe
  
    private String paliwo = "poziom paliwa: ";
    private String predkoscPozioma="predkosc pozioma: ";
    private String predkoscPionowa="predkosc pionowa: ";
    private String pozycjaX = "pozycja x:";
    private String pozycjaY = "pozycja y:";
         private Karnister karnister;
     Ustawienia ust = new Ustawienia();
     private String kolor = ust.zwrocKolor();
     private final int czas = ust.zwrocczas();
     private Timer time;
     private Statek statek;
     private Życie życie;
     private Meteor meteor;
     private Wybuch wybuch;
     private boolean CzyKoniecGry;
     private long start, stop1; //mierzenie czasu
     
     int wspolrzednaXpoZderzeniu = (int) (500*skalaX);
     int wspolrzednaYpoZderzeniu = 200;
    
     private int x[];
     private int y[];
     private int x1[];
     private int y1[];
     private int x2[];
     private int y2[];
     
     ArrayList<Integer> listaX = new ArrayList<Integer>(10);
     ArrayList<Integer> listaY = new ArrayList<Integer>(10);
     
      private boolean CzyKolizja=false;
      private double stop;    
      
   /**
    * W konstruktorze pobieramy z plików wygląd planszy, tworzymy obiekty: 
    * 1.Karnister- pojawia się losowo na mapie, wlecenie nań powoduje zwiększenie poziomu paliwa
    * 2.Życie- ikonka w kształcie serca informująca ile szans jeszcze pozostało
    * 3.Statek- obiekt którym się poruszmy po mapie i którym mamy wylądować
    * 4.Meteor- spada w losowym miejscu, kolizja z nim powoduje utratę jednego życia
    * 
    * Ponadto tworzymy obiekt klasy KAdapter, która jest słuchaczem zdarzeń zachodzących na planszy i z tej funkcji
    * wywołujemy metody statku odpowiedzialne za jego ruch
    */
     public Plansza(JFrame f)
     {

         
        // this.setSize(new Dimension(szerokosc*skala, wysokosc*skala));
      
        
        this.WczytajPlansze();
                             
    
          
          int z1 = (int) (1000*skalaX);
          int z2 = (int) (10*skalaY);
          
        karnister = new Karnister(this.zwrocSkaleX(),this.zwrocSkaleY()); 
        życie  = new Życie(z1,z2);
        statek = new Statek();
        meteor = new Meteor(this.zwrocSkaleX(),this.zwrocSkaleY());
        wybuch = new Wybuch();
        
         x1 = new int[x.length];
        y1 = new int[y.length];
        x2 = new int[x.length];
        y2 = new int[y.length];
  
        
        //tworzymy listy z tych tablic
        for (int i : x) listaX.add(i);// pkt. X dupa
        
        for (int i : y) listaY.add(i);// pkt Y

      
        this.setBackground(Color.BLACK);
        
       
         this. addKeyListener(new KAdapter () );
         
        setFocusable(true);
        this.setFocusTraversalKeysEnabled(false);
//*wykonujemy metodę ActionPerformed co 60 ms (czas==60 ms)       
        time=new Timer(60,this);
        time.start();
        
   
                      
     }
     

     
     /**
      * jest to funkcja służąca do rysowania na naszej planszy, rysujemy na niej:
      * 1.string "poziom paliwa", będzie tam w przyszłości liczba, która będzie maleć podczas naciskania klawiszy,
      * 2.Rysujemy ikony statku, karnistra, życia oraz meteoru, rysowana jest też mapa
      * 3.Wyświetlane są komunikaty opisujące prędkość statku, jego położenie, oraz poziom paliwa 
      * 
      * @param g jest to obiekt który rysuje
      */
     @Override
    public void paintComponent(Graphics g)
    {                
          for (int i=0; i<x.length; i++)
        {
            x1[i]=x[i];
        }
        
         for (int i=0; i<y.length; i++)
        {
            y1[i]=y[i];
        }
        
        
      super.paintComponent(g);
      this.WczytajPlansze();
        
           wspX=this.getWidth();
           wspY=this.getHeight();
           
           skalaX = wspX/1280;
           skalaY = wspY/748;
           

           
                 for (int i=0; i<x.length; i++)
              x2[i]=(int) (x1[i]*skalaX);
          
           for (int i=0; i<y.length; i++)
              y2[i]=(int) (y1[i]*skalaY);
           
          
      // this.setSize(wysokoscPanel, szerokoscPanel); 
      g.drawImage(img, 0, 0, null);
      g.setFont(new Font("TimesRoman",Font.PLAIN,(int)(12*skalaX)));
      g.setColor(Color.RED);
      g.drawString(paliwo ,((int)(50*skalaX)), (int) (50*skalaY));
      g.drawString(statek.IlePaliwa(),((int)(130*skalaX)),(int) (50*skalaY));
      g.drawString(predkoscPozioma, ((int)(900*skalaX)),(int) (50*skalaY));
      g.drawString(statek.JakaPredkoscPozioma(),((int) (1030*skalaX)),(int) (50*skalaY));
      g.drawString(predkoscPionowa,((int) (900*skalaX)),(int) (70*skalaY));
      g.drawString(statek.JakaPredkoscPionowa(),((int) (1030*skalaX)),(int) (70*skalaY));
      g.drawString(pozycjaX, ((int) (900*skalaX)),(int) (90*skalaY));
      g.drawString(statek.JakaWspX(),((int) (1030*skalaX)),(int) (90*skalaY));
      g.drawString(pozycjaY, ((int) (900*skalaX)),(int) (110*skalaY));
      g.drawString(statek.JakaWspY(),((int) (1030*skalaX)),(int) (110*skalaY));
      /*
      int m= this.getWidth();
      int k=this.getHeight();
         System.out.println("tu");
         System.out.print(m);
         System.out.print(k);
      */
      g.setColor(Color.WHITE);
      Graphics2D g2d = (Graphics2D) g;
       
    
       
      
          
      g2d.drawImage(statek.wezIkone(), statek.wezX(), statek.wezY(), this);
      g2d.drawImage(karnister.wezIkone(), karnister.zwrocX(),karnister.zwrocY(), this);
      g2d.drawImage(meteor.wezIkone(), meteor.zwrocX(),meteor.zwrocY(), this);
    
      g2d.setColor(Color.WHITE);
    
      
      
      if(statek.IloscPaliwaInt()<=150)
      {
          g.setFont(new Font("Arial",Font.BOLD,(int)(28*skalaX)));
          g.drawString("Mało Paliwa!",(int)(400*skalaX),(int)(50*skalaX));
      }
          
      
      for (int i = 0; i < statek.zwrocLiczbeZyc(); i++) 
        {  
           int tempx = (int)(życie.wezIkone().getWidth(this)*skalaX);
           int tempy = (int)(życie.wezIkone().getHeight(this)*skalaY);
           g2d.drawImage(życie.wezIkonePrzeskalowanaZycia(tempx, tempy), (int)((życie.zwrocX()+50*i)*skalaX) ,(int)(życie.zwrocY()*skalaY), this); 
                                        
        }
     

      Toolkit.getDefaultToolkit().sync();
      
     
      
      
      g2d.setColor(Color.getHSBColor(20, 1, (float) 0.1));
      Polygon p = new Polygon(x2,y2,22);
      g2d.drawPolygon(p); 
      g2d.fillPolygon(p);
   }
    

   
     @Override
     /**
      * Metoda wywoływana jest przez czas określony w obiekcie timer, powoduje:
      * 1.wywołanie metody ruch statku (czyli jeśli się rusza to zmieniają się jego współrzędne x oraz y
      * 2. wywołanie metody repaint(), co powoduje wywołanie metody paintComponent(), cała plansza rysowana jest raz jeszcze
      */
    public void actionPerformed(ActionEvent e) 
    {
        if (statek.czyPauza()==false)
        {
      // this.SprawdzKolizjeStatkuZPodlozem();
      // this.SprawdzKolizjeMeteoruzPodlozem(); 
       this.SprawdzKolizjeStatkuzMeteorem();
       this.SprawdzKolizjeStatkuzKarnistrem();
       this.SprawdzKolizjeStatkuZPodlozem();
       this.SprawdzCzyWylecial();
       meteor.ruszMeteor();
       statek.ruch();
       karnister.ruszKarnister(this.zwrocSkaleX(),this.zwrocSkaleY());
        if(statek.zwrocLiczbeZyc()==0||this.ZwrocLevel()==4)
          this.zeroZycLubKoniecLeveli();
       
       
       
       
       
       this.repaint();
        }
        else
        {
            
        }
        
       
    }
    
    public int ZwrocLevel()
    {
        return this.level;
    }

    public void ZmienLevel()
    {
        ++this.level;
    }
    
    public int zwrocLiczbePunktow()
    {
        return this.LiczbaPunktow;
    }
    
    public void ustawLiczbePunktowIladowan(int punkty,int ladowaniaDobre, int ladowaniaPerfekcyjne)
    {
        this.LiczbaPunktow=punkty;
        this.ileDobrychLadowan=ladowaniaDobre;
        this.ilePerfekcyjnychLadowan=ladowaniaPerfekcyjne;
    }
    
       public void ustawLevel(int level)
       {
         this.level=level;   
       }
    public void zeroZycLubKoniecLeveli()
    {
        
        this.PoliczLiczbePunktow();
        String wiadomosc;
        wiadomosc = "Koniec gry, Twoja liczba punktow to "+this.zwrocLiczbePunktow()+".Czy chcesz zagrac jeszcze raz?";
        int odp = JOptionPane.showConfirmDialog(null,wiadomosc,"pytanie", JOptionPane.YES_NO_OPTION);
        if (odp == JOptionPane.YES_OPTION)
                {
                    statek.ustawLiczbeZyc(3);
                    statek.UstawPaliwo(ust.zwrocIloscPaliwa());
                    this.ustawLiczbePunktowIladowan(0,0,0);
                    this.ustawLevel(1);
                }
        else if(odp == JOptionPane.NO_OPTION)
                {
                    System.exit(0);
                }
        
    }
/**
 * Sprawdzamy czy statek wleciał na karnister, jeśli tak, to wówczas zwiększamy poziom paliwa statku metodą
 * DodajPaliwo i możemy latać dzięki temu dłużej
 */
    
    public void SprawdzKolizjeStatkuzKarnistrem()
    {
        
        if((Math.abs(statek.wezX()-karnister.zwrocX())<=10)&&(Math.abs(statek.wezY()-karnister.zwrocY())<=10))
        {
            karnister.UstawLosoweWspolrzedne(this.zwrocSkaleX(),this.zwrocSkaleY()); 
            statek.DodajPaliwo(50);
            
        }
    }
    
    public double zwrocSkaleX()
    {
        return this.skalaX;
    }
    
    public double zwrocSkaleY()
    {
        return this.skalaY;
    }
    
    public int ZwrocLiczbePerfekcyjnychLadowan()
    {
        return this.ilePerfekcyjnychLadowan;
    }
    
    public int ZwrocLiczbeDobrychLadowan()
    {
        return this.ileDobrychLadowan;
    }
 
    /**
     * Metoda która sprawdza czy nie zaszła kolizcja statku z meteorem, oba elementy poruszają się na planszy.
     * Jeśli kolizja zaszła, to wówczas pojaiwa się stosowny komunikat o tym,
     * tracimy życie i statek zostaje umieszczony na górze ekranu z prędkością zerową. Po zderzeniu przywracana jest 
     * początkowa wartość paliwa.
     */
    public void SprawdzKolizjeStatkuzMeteorem()
    {
      double start=0;  
      stop+=czas;
      if(((Math.abs(statek.wezX()-meteor.zwrocX())<=20)&&(Math.abs(statek.wezY()-meteor.zwrocY())<=20))&&(stop-start>=100))
      {
          
          statek.ZmienObrazekNaZniszczony();
          statek.ZmniejszLiczbeZyc();
          String wiadomosc="Twój statek został zniszczony przez meteor. Pozostała liczba żyć :"+statek.zwrocLiczbeZyc();
          JOptionPane oknoDialogowe = new JOptionPane();
 
          JOptionPane.showMessageDialog(null, wiadomosc);
          oknoDialogowe.setVisible(true);
          statek.ZmienObrazekNaNormalny();
          stop=start;
          statek.ustawPolozenie(wspolrzednaXpoZderzeniu, wspolrzednaYpoZderzeniu);
          statek.UstawPaliwo(ust.zwrocIloscPaliwa());
          
      }
        
    }
    
    public void SprawdzCzyWylecial()
    {
        if (((statek.wezX()*skalaX)>(1300*skalaX)) || ((statek.wezX()*skalaX)<(-20*skalaX)||(statek.wezY()<-30*skalaY)))
        {
            
            statek.ZmniejszLiczbeZyc();
            String wiadomosc="Wyleciałeś.Pozostała liczba żyć : "+statek.zwrocLiczbeZyc();
          JOptionPane oknoDialogowe = new JOptionPane();
 
          JOptionPane.showMessageDialog(null, wiadomosc);
          oknoDialogowe.setVisible(true);
          statek.ustawPolozenie((int)(500*skalaX), (int)(200*skalaY)); 
        }
            
    }
    
    private void PoliczLiczbePunktow()
    {        
        this.LiczbaPunktow +=500+ statek.IloscPaliwaInt()*statek.zwrocLiczbeZyc()+1000*this.ZwrocLiczbeDobrychLadowan()+5000*this.ZwrocLiczbePerfekcyjnychLadowan();               
    }
    
    
    
    public void SprawdzKolizjeStatkuZPodlozem()
{

    
Rectangle r = new Rectangle(statek.wezX(), statek.wezY(), statek.zwrocWysokosc(), statek.zwrocSzerokosc());
  

        for (int i=0; i<21; i++)
        {
            if (r.intersectsLine(x2[i],y2[i],x2[i+1],y2[i+1]))
            {
                if((i==4||i==14)&&((statek.JakaPredkoscPoziomaInt()<=5)&&(statek.JakaPredkoscPionowaInt()<=5)))
                {
                    if((statek.JakaPredkoscPoziomaInt()<=3)&&(statek.JakaPredkoscPionowaInt()<=3))
                            this.ileDobrychLadowan++;
                    if((statek.JakaPredkoscPoziomaInt()<=1)&&(statek.JakaPredkoscPionowaInt()<=1))
                            this.ilePerfekcyjnychLadowan++;
                    statek.ustawPolozenie(wspolrzednaXpoZderzeniu, wspolrzednaYpoZderzeniu);
                    String wiadomosc="Gratulacje, lądowanie udane!";
                    JOptionPane oknoDialogowe = new JOptionPane();
 
          JOptionPane.showMessageDialog(null, wiadomosc);
          oknoDialogowe.setVisible(true);
          statek.UstawPaliwo(ust.zwrocIloscPaliwa());
          statek.UstawPredkosc(0, 0);
          if(this.ZwrocLevel()!=4)
              this.ZmienLevel();
          System.out.println("levele");
          System.out.println(this.level);
              
          break;
                }
                else 
                        { 
                            
                             CzyKolizja=true;
                             statek.ZmniejszLiczbeZyc();
                 statek.UstawPaliwo(ust.zwrocIloscPaliwa());
               statek.ustawPolozenie(wspolrzednaXpoZderzeniu, wspolrzednaYpoZderzeniu);
                String wiadomosc="Twój statek został zniszczony, zderzyłeś się z podłożem. Pozostała liczba żyć :"+statek.zwrocLiczbeZyc();
                   
           
           JOptionPane oknoDialogowe = new JOptionPane();
          JOptionPane.showMessageDialog(null, wiadomosc);
          oknoDialogowe.setVisible(true);
          statek.UstawPredkosc(0, 0);
            break;
               }
            }
        }
}
    
   //  if (((statek.JakaPredkoscPionowa()>= 1) || (statek.JakaPredkoscPozioma()>=1)) && (((statek.wezX()<=300&&statek.wezX()>=240)&&statek.wezY()>=739)||((statek.wezX()<=1600&&statek.wezX()>=700)&&statek.wezY()>=739)));
     //   {
   
       // }
    
    /**
     * Sprawdzamy czy nie zaszła kolizja statku z podłożem, jeśli zaszła, to pojawia się informacja o tym zdarzeniu,
     * 
     */
   
    
    
     /**
      * Meteory w naszej grze oddziałują na podłoże, deformując je, czyli wprowadzamy zmiany w liście z której rysowane
      * jest podłoże.
      */
    
  
    
    /**
     * jest to klasa która nasłuchuje zdarzeń, zawiera 2 metody->keyReleased w której to metodzie wywołujemy
     * metody statku które opisują co się dzieje gdy puszczamy przycisk oraz metodę keyPressed w której to 
     * metodzie wywołujemy metody statku które opisują co się dzieje gdy wciśniemy klawisz
     */
   
    
    
    private class KAdapter extends KeyAdapter
            
    {
        /**
         * W przypadku puszczenia klawisza wywołuje się metoda puszczenia klawisza dla statku
         * @param e niesie informację o źródle zdarzenia (czyli co zostało puszczone)
         */
    
        public void keyReleased (KeyEvent e)
        {
            statek.keyReleased(e);
            
        }
         /**
          * w przypadku wciśnięcia klawisza wywołuje metodę naciśnięcia klawisza dla statku
          * @param e niesie informację o źródle zdarzenia (czyli co zostało wciśnięte)
          */
    
        public void keyPressed (KeyEvent e)
        {
            statek.keyPressed(e);
                     
        }
    }
    
    public void WczytajPlansze()
{
     try
        {
         if (this.ZwrocLevel() == 1) 
        this.x=ust.zwrocTabliceX("TablicaX.txt");
         else if (this.ZwrocLevel()==2)
             this.x=ust.zwrocTabliceX("TablicaX2.txt");
         else if (this.ZwrocLevel()==3)
             this.x=ust.zwrocTabliceX("TablicaX3.txt");
        }
         catch(FileNotFoundException e)
                  {
                   System.err.format("plik nie znaleziony\n");   
                  }
        
          try
        {
             if (this.ZwrocLevel() == 1) 
        this.y=ust.zwrocTabliceY("TablicaY.txt");
             else if (this.ZwrocLevel()==2)
                 this.y=ust.zwrocTabliceY("TablicaY2.txt");
             else if (this.ZwrocLevel()==3)
                 this.y=ust.zwrocTabliceY("TablicaY3.txt");
        }
         catch(FileNotFoundException e)
                  {
                   System.err.format("plik nie znaleziony\n");   
                  }
                             

}
    
    
    
        
}




/*
  g2d.drawLine(0, 240, 240, 740);
      g2d.drawLine(240, 740, 250, 740);
       g2d.drawLine(300, 740, 300, 740);
        g2d.drawLine(300, 600, 500, 600);


*/
 /**
     * służy do określenia gdzie będą punkty X podłoża
     * @return zwraca tablicę punktów X podłoża
     */
    /*
    public int [] generujX()
    {
        int [] tablicaX=new int [n];
        
        tablicaX[0]=0;
        tablicaX[n-1]=1300;
        
        for (int w = 2; w <= n-1; w++) 
        {
         tablicaX[w-1]=generator1.nextInt(1300);    
        }
           Arrays.sort(tablicaX, 0, n-1);
        
        for (int c=0; c < tablicaX.length; c++)
        {
            System.out.println("num:" + tablicaX[c]);
        }
        
        
        return tablicaX;
        
    }
    
  
    * służy do określenia gdzie są punkty Y podłoża
    * @return zwraca tablicę punktów Y podłoża
    
    public int [] generujY()
    {
      int [] tablicaY=new int [n];  
        
      for (int i = 1; i <= n; i++) 
        { 
         tablicaY[i-1]=generator1.nextInt(120)+600;            
        }
      
        for (int i = k; i < k+5; i++) 
        {
            tablicaY[i]=z;
            
        }
        
        for (int i=k-5;i<k;i++ )
        {
            tablicaY[i]=z;
        }
        
       
        
         for (int c=0; c < tablicaY.length; c++)
        {
            System.out.println("num:" + tablicaY[c]);
        }
        
         return tablicaY;   
    }
    */
   /* to potem dorobić->losowość 
    public void ZapiszDoPliku (String tablica[]){
       
      try{
          FileWriter fr = new FileWriter("TablicaXrand.txt");
          BufferedWriter br = new BufferedWriter(fr);
          PrintWriter out = new PrintWriter(br);
          for(int i=0; i<tablica.length; i++){
          if(tablica[i] != null)
                   
            out.write(tablica[i]);
                out.write("\n");       
          }
          out.close();
           
           
      }
       
      catch(IOException e){
       System.out.println(e);   
      }
  }
    */





















 /*
         int i = listaX.size();
      int k=0;
      if (k<=(i-1))
     {                  
              g2d.drawLine(listaX.get(k),listaY.get(k), listaX.get(k+1), listaY.get(k+1)); 
              ++k;
         
     }
      */
      //  for (int i = 0; i <x.length-1; i++) 
        //{
    //  g2d.drawLine(listaX.get(i),listaY.get(i), listaX.get(++i), listaY.get(i));   
      
          
// g2d.drawLine(0, 240, 137, 525);
          // g2d.drawLine(157, 567, 240, 740);
          // g2d.drawLine(240, 740, 300, 740);
        //}
        
       // g2d.drawLine(30, 40, 50, 60);
        
      //  g2d.drawLine(70, 60, 160, 200);
        
       /* 
         g2d.drawLine(listaX.get(0),listaY.get(0), listaX.get(1), listaY.get(1));
      g2d.drawLine(listaX.get(2),listaY.get(2), listaX.get(3), listaY.get(3));//lądowisko
      g2d.drawLine(listaX.get(4),listaY.get(4), listaX.get(5), listaY.get(5));
      g2d.drawLine(listaX.get(6),listaY.get(6), listaX.get(7), listaY.get(7));
      g2d.drawLine(listaX.get(8),listaY.get(8), listaX.get(9), listaY.get(9));
    */










 /*
    public void SprawdzKolizjeStatkuZPodlozem()
    {
        if (  (((statek.wezY()>=735)&&(statek.wezX()<=1600&&statek.wezX()>=700))||(((statek.wezX()<=300)&&(statek.wezX()>=240))&&(statek.wezY()>=735)))&& (statek.JakaPredkoscPionowaInt()>=5||statek.JakaPredkoscPoziomaInt()>=5))
        {
           // System.out.println("oNie");
          statek.UstawPredkosc(0, 0);
          statek.ZmienObrazekNaZniszczony();
          statek.ZmniejszLiczbeZyc();
          String wiadomosc="Twój statek został zniszczony, zderzyłeś się z podłożem. Pozostała liczba żyć :"+statek.zwrocLiczbeZyc();
          JOptionPane oknoDialogowe = new JOptionPane();
 
          JOptionPane.showMessageDialog(null, wiadomosc);
          oknoDialogowe.setVisible(true);
          statek.ZmienObrazekNaNormalny();
          statek.ustawPolozenie(wspolrzednaXpoZderzeniu, wspolrzednaYpoZderzeniu);
          statek.UstawPaliwo(ust.zwrocIloscPaliwa());
        }
        
       else if (  ((statek.wezX()>=0&&statek.wezX()<=240)&&(statek.wezY()>=2.5*statek.wezX()+235 ))||((statek.wezX()<=500&&statek.wezX()>=300)&&(statek.wezY()>=-0.7*statek.wezX()+920))||(( statek.wezX()<=700 && statek.wezX()>=500)&&(statek.wezY()>=0.7*statek.wezX()+220)))
        {
           // System.out.println("oNie");
            statek.UstawPredkosc(0, 0);
            statek.ZmienObrazekNaZniszczony();
          statek.ZmniejszLiczbeZyc();
          String wiadomosc="Twój statek został zniszczony, zderzyłeś się z podłożem. Pozostała liczba żyć :"+statek.zwrocLiczbeZyc();
          JOptionPane oknoDialogowe = new JOptionPane();
 
          JOptionPane.showMessageDialog(null, wiadomosc);
          oknoDialogowe.setVisible(true);
          statek.ZmienObrazekNaNormalny();
          statek.ustawPolozenie(wspolrzednaXpoZderzeniu, wspolrzednaYpoZderzeniu);
          statek.UstawPaliwo(ust.zwrocIloscPaliwa());
        }
        
       else if ((  (((statek.wezY()>=700)&&(statek.wezX()<=1600&&statek.wezX()>=700))||(((statek.wezX()<=300)&&(statek.wezX()>=240))&&(statek.wezY()>=700)))&& (statek.JakaPredkoscPionowaInt()<=5&&statek.JakaPredkoscPoziomaInt()<5)))
       {
           // System.out.println("oNie");
            statek.UstawPredkosc(0, 0);
          String wiadomosc="Gratulacje, lądowanie udane!";
          JOptionPane oknoDialogowe = new JOptionPane();
 
          JOptionPane.showMessageDialog(null, wiadomosc);
          oknoDialogowe.setVisible(true);
          statek.ustawPolozenie(wspolrzednaXpoZderzeniu, wspolrzednaYpoZderzeniu);
          statek.UstawPaliwo(ust.zwrocIloscPaliwa());
           
       }
        
        
        
        
    }
*/










  /*
    public void SprawdzKolizjeMeteoruzPodlozem()
    {
      if ((meteor.zwrocX()>=0 && meteor.zwrocX()<=240) && (meteor.zwrocY()>=2.083*meteor.zwrocX() && meteor.zwrocY()<=2.083*meteor.zwrocX()+40))
      {
        
          int k = meteor.zwrocX()+20;
          int l = meteor.zwrocX()-20;
          listaX.add(k);
          listaX.add(l);
          
          Collections.sort(listaX);
         
          int IndeksWiekszego=listaX.indexOf(k);
          int IndeksMniejszego=listaX.indexOf(l);                
          int YwiekszegoIksa = (int) (k*2.2+240);                                   
          int YmniejszegoIksa = (int) (l*2.2+240);     
       
          listaY.add(IndeksMniejszego, YmniejszegoIksa);
          listaY.add(IndeksWiekszego, YwiekszegoIksa);
          
          
      }    
      
      else if((meteor.zwrocX()>=240&&meteor.zwrocX()<=300)&&(meteor.zwrocY()<=740 && meteor.zwrocY()>=725))
          {
              int k = meteor.zwrocX()+2;
          int l = meteor.zwrocX()-2;
          listaX.add(k);
          listaX.add(l);
          
          Collections.sort(listaX);
          int IndeksWiekszego=listaX.indexOf(k);
          int IndeksMniejszego=listaX.indexOf(l);                
          int YwiekszegoIksa = 740;                                   
          int YmniejszegoIksa = 740;                     
          listaY.add(IndeksMniejszego, YmniejszegoIksa);
          listaY.add(IndeksWiekszego, YwiekszegoIksa);
                   
          }
        
        else if((meteor.zwrocX()>=300&&meteor.zwrocX()<=500)&&(meteor.zwrocY()<=-0.7*meteor.zwrocX()+950 && meteor.zwrocY()>=-0.7*meteor.zwrocX()+935))
          {
              int k = meteor.zwrocX()+20;
          int l = meteor.zwrocX()-20;
          listaX.add(k);
          listaX.add(l);
          
          Collections.sort(listaX);
          
          int IndeksWiekszego=listaX.indexOf(k);
          int IndeksMniejszego=listaX.indexOf(l);                
          int YwiekszegoIksa = (int)(-0.7*k+950);                                   
          int YmniejszegoIksa = (int)(-0.7*k+950);                     
          listaY.add(IndeksMniejszego, YmniejszegoIksa);
          listaY.add(IndeksWiekszego, YwiekszegoIksa);
                   
          }
      
          
    }

 */