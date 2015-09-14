package practica2;
//
import java.util.Scanner;


public class Prestamo {
    
    private String cedula;
    private String Nombre1;
    private int Libro;
    private String val;
     
    
    public void prestar(){
         boolean band;
     Scanner teclado = new Scanner(System.in);
     do{
       
        System.out.println("Ingrese Codigo del Libro");
        val=teclado.next();
         if(ValidaNumero(val)){
             this.Libro=Integer.parseInt(val);
             band=true;
         }
         else{
             band=false;
         }        
     }while (band==false);
     
        System.out.println("Ingrese Numero de cedula");
        this.cedula=teclado.next();


 }
     public void devolver(){
     boolean band;
     Scanner teclado = new Scanner(System.in);
     do{
        System.out.println("Ingrese Codigo del Prestamo");
        val=teclado.next();
         if(ValidaNumero(val)){
             this.Libro=Integer.parseInt(val);
             band=true;
         }
         else{
             band=false;
         }        
     }while (band==false);
     }
     
      public void  mostrar(){
     System.out.println("Nombre: "+Nombre1);
     System.out.println("Cedula: "+cedula);
      }
     public String getNombre1() {
        return Nombre1;
    }

    public void setNombre1(String Nombre1) {
        this.Nombre1 = Nombre1;
    }

    public String getcedula() {
        return cedula;
    }

    public void setcedula(String cedula) {
        this.cedula = cedula;
    }

    public int getLibro() {
        return Libro;
    }

    public void setLibro(int Libro) {
        this.Libro = Libro;
    }
     public boolean ValidaNumero(String val) {
        int i;
        try{
        i= Integer.parseInt(val);
        return true;
        }catch(Exception e)
        {
            System.out.println("error, ingrese solo numeros");
        return false;
        }
    }

    String getCantidad() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
