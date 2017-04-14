/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prozeprojekt;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;
import java.util.Scanner;


/**
 * klasa odpowiedzialna za parsowanie plików, jej składowe prywatne odpowiadają odpowiednim polom w reszcie programu, czyli:
 * 1.Najpierw w konstruktorze dokonujemy sczytywania określonych danych z pliku
 * 2.Dane te zostają przypisane polom prywatnym w programie
 * 3.Wartości pól prywatnych zwracane są za pomocą publicznych metod klasy ustawienia,których to metod używamy w reszcie programu aby przypisać
 * wartości pól reszty klas
 */
public class Ustawienia 
{
   private int _mocSilnika;
   private int _przyspieszeniePlanety;
   private int _xpoczStatku;
   private int _ypoczStatku; 
   private int _czas;
   private String _obrazekStatku;
   private String _ikonaTła;
   private String _ikonaSerca;
   private String _ikonaPaliwa;
   private String _ikonaMeteoru;
   private int _rozmiarMenuX;
   private int _rozmiarMenuY;
   private String _Kolor;

  
   private int _liczbaZyc;
   private int _predkoscXpoczatkowa;
   private int _predkoscYpoczatkowa;
   private int _iloscPaliwa;
  

 /**
  * W konstruktorze przypisujemy polom prywatnym klasy Ustawienia wartości sczytanych z pliku danych, tworzony jest obiekt klasy FileReader,
  * służący do czytania z pliku. Stworzenie FileReeadera oraz przypisania dzieją się w bloku try(obsługa wyjątku nie znalezienia pliku).
  */
     public Ustawienia()
     {
      
   System.out.println("tu konstr. ustawienia");
   
     try (FileReader reader = new FileReader("konfiguracja.properties"))
     {
        Properties properties =  new Properties();
        properties.load(reader);
     //   this._mocSilnika=Double.parseDouble(properties.getProperty("mocSilnika"));
     //   this._przyspieszeniePlanety=Integer.parseInt(properties.getProperty("przyspieszeniePlanety")); 
        
        this._xpoczStatku=Integer.parseInt(properties.getProperty("xpoczStatku"));
        this._ypoczStatku=Integer.parseInt(properties.getProperty("ypoczStatku"));
        this._czas=Integer.parseInt(properties.getProperty("czas"));
        this._obrazekStatku=properties.getProperty("ikonaStatku");
        this._ikonaTła=properties.getProperty("ikonaTła");
        this._Kolor=properties.getProperty("kolor");
        this._rozmiarMenuX=Integer.parseInt(properties.getProperty("rozmiarMenuX"));
        this._rozmiarMenuY=Integer.parseInt(properties.getProperty("rozmiarMenuY"));
        this._ikonaSerca=properties.getProperty("ikonaSerca");
        this._ikonaPaliwa=properties.getProperty("ikonaPaliwa");
        this._ikonaMeteoru=properties.getProperty("ikonaMeteoru");
        this._liczbaZyc=Integer.parseInt(properties.getProperty("liczbaZyc"));
        this._predkoscXpoczatkowa=Integer.parseInt(properties.getProperty("predkoscXpoczatkowa"));
        this._predkoscYpoczatkowa=Integer.parseInt(properties.getProperty("predkoscYpoczatkowa"));
        this._iloscPaliwa=Integer.parseInt(properties.getProperty("iloscPaliwa"));

        
        System.out.println(_mocSilnika);
        
     }
     catch  (Exception e)
     {
         e.printStackTrace();
     }
     
           
 }
 
         public int zwrocMoc()
     {
       return _mocSilnika;          
     }        
     
         public int zwrocPrzyspieszenie()
     {
         return _przyspieszeniePlanety;
     }
     
         public int zwrocxPoczatkowe()
     {
         return _xpoczStatku;
     }
        
         public int zwrocyPoczatkowe()
     {
         return _ypoczStatku;
     }
           
         public int zwrocczas()
     {
         return _czas;
     }
         public String zwrocIkoneStatku()
         {
             return _obrazekStatku;
         }
         
         public String zwrocIkoneTla()
         {
             return _ikonaTła;
         }
         
         public String zwrocKolor()
         {
             return _Kolor;
         }
         
         public int zwrocRozmiarMenuX()
         {
             return _rozmiarMenuX;
         }
         
             public int zwrocRozmiarMenuY()
         {
             return _rozmiarMenuY;
         }
            public String zwrocIkoneSerca()
            {
                return _ikonaSerca;
            }
            
            public String zwrocIkonePaliwa()
                    {
                        return _ikonaPaliwa;
                    }
            
            public String zwrocIkoneMeteoru()
            {
                return _ikonaMeteoru;
            }
             
            public int zwrocLiczbeZyc()
            {
                return _liczbaZyc;
            }
            
            public int zwrocPredkoscX()
            {
                return _predkoscXpoczatkowa;
            }
            
              public int zwrocPredkoscY()
            {
                return _predkoscYpoczatkowa;
            }
              
              public int zwrocIloscPaliwa()
              {
                  return _iloscPaliwa;
              }
          
              

              public int[] zwrocTabliceX(String nazwa) throws FileNotFoundException
              {
                  int licznik=0;
                  File file = new File (nazwa);
                  try{
                  Scanner scan = new Scanner(file);
                   while(scan.hasNextLine())
                   {
                       scan.nextLine();
                       licznik++;
                   }
                  }
                  catch(FileNotFoundException e)
                  {
                   System.err.format("plik nie znaleziony\n");   
                  }
                   int tablica[]=new int [licznik];
                   Scanner scan1 = new Scanner(file);
                  for(int i=0; i<22; i++)
                    {
                        tablica[i]=Integer.parseInt(scan1.nextLine());
                    }
                    return tablica;                    
                  }            
                                      
      public int[] zwrocTabliceY(String nazwa) throws FileNotFoundException
              {
                 
                  int licznik=0;
                  File file = new File (nazwa);
                    

                  try{
                  Scanner scan = new Scanner(file);
                   while(scan.hasNextLine())
                   {
                       scan.nextLine();
                       licznik++;
                   }
                  }
                  catch(FileNotFoundException e)
                  {
                   System.err.format("plik nie znaleziony\n");   
                  }
                  
                   int tablica[]=new int [licznik];
           
                   System.out.println("licznik\n");
                   System.out.println(licznik);
                
                   Scanner scan1 = new Scanner(file);
                   
                
                
                    for(int i=0; i<22; i++)//dupa
                    {
                        tablica[i]=Integer.parseInt(scan1.nextLine());
                    }
                    return tablica;                    
                  }   
              



}