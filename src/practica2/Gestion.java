package practica2;
//
import java.util.Scanner;


public class Gestion extends Practica2 {  
    public Scanner teclado= new Scanner (System.in);

    private String Nombre;
    private String autor;
    private int año;
    private int selec;
    private  int codigo;
    private int cantidad;
    private String area;
    private int selctarea;
    private int veri=0;
   private String val;
            
   public void datos(){
     Scanner teclado = new Scanner(System.in);
     System.out.println("Ingrese Nombre");
     this.Nombre=teclado.nextLine();
     System.out.println("Ingrese Autor");
     this.autor=teclado.nextLine();
     boolean band;
     do{
     System.out.println("Ingrese Año de publicaciòn");
     this.val=teclado.nextLine();
       if(ValidaNumero(val)){
       this.año=Integer.parseInt(val);
       band=true;
       }
       else{
           
           band=false;
       }
     }while(band==false);
     
     do{
       System.out.println("Ingrese Cantidad");
       this.val=teclado.nextLine();
       if(ValidaNumero(val)){
       this.cantidad=Integer.parseInt(val);
       band=true;
       }
       else{
           
           band=false;
       }
     }while(band==false);
     
             veri=0;
     while (veri==0){
     System.out.println("Escoja Area de publicación");
                System.out.println("/////////////////");
                System.out.println("1. Química");
                System.out.println("2. Física");
                System.out.println("3. Tecnología");
                System.out.println("4. Cálculo");
                System.out.println("5. Programación");
                System.out.println("/////////////////");
     
     this.selctarea=teclado.nextInt();
         if(selctarea>=1 && selctarea<=5){
            switch (selctarea){
                case 1:
                   this.area="Química";
                   break;
                case 2:
                   this.area="Física";
                   break;
                case 3:
                   this.area="Tecnología";
                   break;
                case 4:
                   this.area="Cálculo";
                   break;
                case 5:
                   this.area="Programación";
                   break;
                }
             veri=1;
         }else{
             veri=0;
            System.out.println("No es una opcion valida, intente de nuevo");
             
            }         
        }    
    }
 
 public int getaño() {
        return año;
    }

    public void setaño(int año) {
        this.año = año;
    }
 
    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public int getcodigo() {
        return codigo;
    }

    public void setcodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getautor() {
        return autor;
    }

    public void setautor(String autor) {
        this.autor = autor;
    }
    public String getarea() {
        return area;
    }

    public void setarea(String area) {
        this.area = area;
    }
    public int getcantidad() {
        return cantidad;
    }

    public void setcantidad(int cantidad) {
        this.cantidad = cantidad;
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
}
