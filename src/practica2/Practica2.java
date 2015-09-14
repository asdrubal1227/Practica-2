package practica2;
//

import java.util.Scanner;
import java.sql.*;

public class Practica2 {
        static  Prestamo prestamo= new Prestamo();
        static  Gestion gestion= new Gestion(); 
        
        static Scanner teclado= new Scanner (System.in);
        static boolean veri=false;
        static ResultSet rs;
        static Conexion conectar= new Conexion();
         
    public static void main(String[] args) throws SQLException {
        boolean band;
        int elige=0;
        int selec=0; 
        String val;
        while (true){
                
       do{
        System.out.println("/////////////////");
            System.out.println("Menu");
            System.out.println("/////////////////");
            System.out.println("1. Gestion");
            System.out.println("2. Prestamos");
            System.out.println("/////////////////");
            System.out.println("Seleccione una opción del menu");
            val=teclado.next();
            if(gestion.ValidaNumero(val)==true){
            band=true;
             elige= Integer.parseInt(val);
            }
            else{
                band=false;
            }
           
            }while(band==false);   
                    
                 
            switch(elige){
            case 1:
                do{
                System.out.println("/////////////////");
                System.out.println("Menu");
                System.out.println("/////////////////");
                System.out.println("1. Ingresar libro");
                System.out.println("2. Actualizar libro");
                System.out.println("3. Eliminar libro");
                System.out.println("4. Buscar libro");
                System.out.println("/////////////////");
                System.out.println("Seleccione una opción del menu");
                val=teclado.next();
                    if(gestion.ValidaNumero(val)){
                    band=true;
                     selec= Integer.parseInt(val);
                    }
                    else{
                        band=false;
                    }                   
                 }while(band==false);
                switch(selec){
                    case 1:
                        gestion.datos();
                        conectar.reg_libro(gestion);
                    break;//
                    case 2:                    
                        codigo("Actualizar");
                        if(conectar.veri_act(gestion.getcodigo())){
                        gestion.datos();       
                        conectar.act_libro(gestion); 
                        }              
                    break;
                    case 3:
                        codigo("Eliminar");
                        conectar.sup_libro(gestion);
                    break;
                    case 4:
                        codigo("Buscar");
                        try{ 
                            rs =conectar.bus_libro(gestion);
                            String [] datos = new String [6];
                            if(rs.next()==false){
                                System.out.println("No hay libros con este codigo");
                            }else{
                                    do {
                                         datos[0]=rs.getString(1);
                                         datos[1]=rs.getString(2);
                                         datos[2]=rs.getString(3);
                                         datos[3]=rs.getString(4);
                                         datos[4]=rs.getString(5);
                                         datos[5]=rs.getString(6);
                            System.out.print("Codigo\t: "+datos[4]+"|\t|");
                            System.out.print("Nombre\t: "+datos[0]+"|\t|");
                            System.out.print("Autor\t: "+datos[1]+"|\t|");
                            System.out.print("Año\t: "+datos[2]+"|\t|");
                            System.out.print("Cant.\t: "+datos[3]+"|\t|");
                            System.out.println("Area\t: "+datos[5]+"|\t");
                                     }while(rs.next());
                            }
                             }catch (SQLException e) {
                                 System.out.println(e.getMessage());
                             }
                            
                           

                    break;
                    default:
                        System.out.println("ingreso una opción incorrecta, intente de nuevo");
                    break;
                }
            break;
            case 2:
        do{
            System.out.println("/////////////////");
            System.out.println("Menu");
            System.out.println("/////////////////");
            System.out.println("1. Prestar libro");
            System.out.println("2. Devolver libro");
            System.out.println("3. Libros prestados");
            System.out.println("/////////////////");
            System.out.println("Seleccione una opción del menu");
            
            val=teclado.next();
                    if(gestion.ValidaNumero(val)){
                    band=true;
                     selec= Integer.parseInt(val);
                    }
                    else{
                        band=false;
                    }                   
                 }while(band==false);
            
            switch(selec){
                case 1:
                  
                   prestamo.prestar();
                   conectar.prestar_libro(prestamo);
                        
                break;
                case 2:
                    
                    
                    prestamo.devolver();
                   conectar.devolver_libro(prestamo);
                    break;
                         
                     case 3:
                         try{ 
                            rs =conectar.bus_prestamo();
                            String [] datos = new String [4];
                            if(rs.next()==false){
                                System.out.println("No hay libros prestados");
                            }else{
                            System.out.print("Codigo Prestamo\t|");
                            System.out.print("Codigo Libro\t|");
                            System.out.print("Cedula\t\t|");
                            System.out.println("Libro\t|");
                                    do{
                                         datos[0]=rs.getString(1);
                                         datos[1]=rs.getString(2);
                                         datos[2]=rs.getString(3);
                                         datos[3]=rs.getString(4);
                                         System.out.print(datos[0]+"\t\t|");
                                         System.out.print(datos[1]+"\t\t|");
                                         System.out.print(datos[3]+"\t\t|");
                                         System.out.println(datos[2]+"\t\t|");
                                     } while(rs.next());
                            
                            }
                             }catch (SQLException e) {
                                 System.out.println(e.getMessage());
                             }
                         break;
                default:
                    System.out.println("ingreso una opción incorrecta, intente de nuevo");
                    break;
            }
            break;
            default:
                System.out.println("ingreso una opción incorrecta, intente de nuevo");
            break;
        }
                       
        }
       
    }
    public static void codigo(String a){
    String val;
    boolean band;   
    int cod;
    do{
        System.out.println("Ingrese Codigo de Libro a "+a);
        val=teclado.next();
        if(gestion.ValidaNumero(val)){
        cod=Integer.parseInt(val);
        gestion.setcodigo(cod);
        band=true;
        }
        else{
        band=false;
        }
        }while(band==false);
    }
}

