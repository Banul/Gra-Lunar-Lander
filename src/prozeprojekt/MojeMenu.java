/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prozeprojekt;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.WindowStateListener;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.KeyStroke;


/**
 * 
 * klasa Moje menu, która opisuje caly interfejs graficzny ktory mamy w programie,
 * klasa implementuje interfejs ActionListener, poniewaz chcemy okreslac zdarzenia 
 * nastepujace po nacisnieciu danego przycisku. Po naciśnięciu opcji nowa gra otwiera się obiekt 
 * typu JFrame i możemy rozpocząć rozgrywkę.Naciśnięcie innych przycisków otwiera okno z inną informacją.
 * Nie jest obsługiwana jeszcze lista najlepszych wyników.
 * 
 * w klasie występują :
 * 1. JMenuBar pasekMenu - do niego dodajemy inne komponenty, nasza baza
 * 2. JMenu graj, informacje - obiekty które dodajemy do pasekMenu
 * 3. JMenuITem - nowaGra, najlepszeWyniki, ustawienia, zakoncz, autorzy, wersjaGry - obiekty dodawane do graj lub informacje
 * 4. JRadioButton oraz ButtonGroup - służą do utworzenia grupy przycisków typu RadioButton (potrzebne do ustawienia poziomu w oknie Ustawienia)
 * 5. Stałe określające rozmiar menu
 */
public class MojeMenu extends JFrame implements ActionListener
        
{
  // Ustawienia ust = new Ustawienia();
public final int szerokosc = 320;
     public final int wysokosc = szerokosc/12*9;
     public final int skala = 2;
     
     
   private int poziomTrudnosci;
   
   private JMenuBar pasekMenu;

   
   private JMenu graj, informacje;

   private JMenuItem nowaGra, najlepszeWyniki, ustawienia, zakoncz, autorzy, wersjaGry;

   private ButtonGroup przyciskiPoziomuTrudnosci;
   private JRadioButton łatwy,średni,trudny; 
   private final int RozmiarMenuX=800;
   private final int RozmiarMenuY=800;
   private int h, w;
 
   /**
    * W konstrukotorze powstaje pasek menu, do którego dodajemy komponenty, ustawiamy skróty klawiszowe oraz mnemonikę(otwieranie za 
    * pomocą 1 przycisku), ustawiamy także ikonę tła , która pobierana jest z pliku konfiguracyjnego.
    */
    public MojeMenu()

    { 

        this.setVisible(true);
        this.setTitle("Lunar Lander");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   
        this.setSize(RozmiarMenuX, RozmiarMenuY); 
        
       
       JLabel background;
        background = new JLabel(new ImageIcon("Lunar.png"));
	add(background);
        
        
        pasekMenu = new JMenuBar();
        graj = new JMenu("graj");
        informacje = new JMenu ("informacje");
        
        this.setJMenuBar(pasekMenu);
        pasekMenu.add(graj);
        pasekMenu.add(informacje);
        nowaGra = new JMenuItem ("nowa gra",'N');
      
        najlepszeWyniki = new JMenuItem ("najlepsze wyniki");
   
        ustawienia = new JMenuItem ("ustawienia");
    
        zakoncz = new JMenuItem ("zakoncz");
      
        autorzy = new JMenuItem("autorzy");
       
        wersjaGry = new JMenuItem ("wersja gry");
        
      
        graj.add(nowaGra);
        graj.add(najlepszeWyniki);
        graj.add(ustawienia);
        graj.add(zakoncz);
        informacje.add(autorzy);
        informacje.add(wersjaGry);
       
    
        zakoncz.addActionListener(this);
        
        
        zakoncz.setAccelerator(KeyStroke.getKeyStroke("ctrl X"));
        
        autorzy.addActionListener(this);
        autorzy.setAccelerator(KeyStroke.getKeyStroke("ctrl A"));
        
        wersjaGry.addActionListener(this);
        wersjaGry.setAccelerator(KeyStroke.getKeyStroke("ctrl S"));
        
        ustawienia.addActionListener(this);
        ustawienia.setAccelerator(KeyStroke.getKeyStroke("ctrl F"));
        
        nowaGra.addActionListener(this);
        
        
               
    }
    
    
/**
 * Funkcja która obsługuje kliknięcia na odpowiednie przyciski w programie, w zależności od tego co jest źródłem zdarzenia, powstają różne okna
 * 1. zakoncz - wywołuje metodę dispose(), która konczy program
 * 2. autorzy,wersja gry - informacje o autorach, wersji gry
 * 3. ustawienia - okno pozwala na wybór jednego z trzech poziomów trudności
 * 4. NowaGra - tworzymy nowy obiekt typu JFrame, do którego dodajemy obiekt Plansza(klasa plansza obsługuje rozgrywkę),menu zostaje zamknięte
 * 
 * @param e zawiera informację o zdarzeniu (korzystamy z tego że zna źródło zdarzenia, czyli jaki przycisk został wciśnięty)
 */
    @Override
  
    public void actionPerformed(ActionEvent e) {
        Object źródło = e.getSource();
        if (źródło==zakoncz)
        {
            this.dispose();
        }
        
        else if (źródło==autorzy)
        {
            
            JOptionPane oknoDialogowe = new JOptionPane();
            JOptionPane.showMessageDialog(null, "Banul&Drachal");
            oknoDialogowe.setVisible(true);
        }
        else if (źródło==wersjaGry)
        {
            JOptionPane oknoDialogowe = new JOptionPane();
            JOptionPane.showMessageDialog(null, "wersja 1.0");
            oknoDialogowe.setVisible(true);
        }
        else if (źródło==ustawienia)
        {
            JOptionPane oknoDialogowe = new JOptionPane();
           
            łatwy = new JRadioButton("łatwy",true);
            średni = new JRadioButton("średni",false);
            trudny = new JRadioButton("trudny",false);
            
            łatwy.addActionListener(this);
            średni.addActionListener(this);
            trudny.addActionListener(this);
            
            przyciskiPoziomuTrudnosci = new ButtonGroup();
            
            przyciskiPoziomuTrudnosci.add(łatwy);
            przyciskiPoziomuTrudnosci.add(średni);
            przyciskiPoziomuTrudnosci.add(trudny);
            
            oknoDialogowe.add(łatwy);
            oknoDialogowe.add(średni);
            oknoDialogowe.add(trudny);
            
            JPanel panel = new JPanel();
           
            JLabel jlabel = new JLabel("Ustaw poziom trudności: ");
            
            
            panel.add(jlabel);
            panel.add(łatwy);
            panel.add(średni);
            panel.add(trudny);
            
            
            
            JOptionPane.showMessageDialog(null, panel);
       
                    
            oknoDialogowe.setVisible(true);
            
        }
        
          else if (źródło == łatwy)
      {
          this.poziomTrudnosci=1;
      }
      
        else if (źródło == średni)
      {
          this.poziomTrudnosci=2;
      }
        
        
          else if (źródło == trudny)
      {
          this.poziomTrudnosci=3;
      }
        
        
      
      else if(źródło==nowaGra)
    {
      
        System.out.println(this.ZwrocPoziomTrudnosci());
         JFrame f = new JFrame(); 
           
         setLocationRelativeTo(this);
        
        Plansza p=new Plansza(f); 
      //  p.setSize(100,100);
        f.pack();
          f.add(p);
          f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          f.setResizable(true);
          f.setLocationRelativeTo(null);
          f.setVisible(true);
              
      
          
          f.setBackground(Color.BLACK);

          
    
   
          this.dispose();
          
     }
  
    
    
   }

    public int ZwrocPoziomTrudnosci()
            {
                return this.poziomTrudnosci;
                        
            }
         
          

            
        }
                
       
        

     
        
    

