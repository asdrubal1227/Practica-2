/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica2;

import java.sql.*;

/**
 * Clase que permite conectar con la base de datos
 *
 *
 */
public class Conexion {
   static String bd = "contactos_bh";
   static String login = "asdrubal07";
   static String password = "mariobros1227";
   static String url = "jdbc:mysql://db4free.net/"+bd;

   Connection conn = null;

   /** Constructor de DbConnection */
   public Conexion() {
      try{
         //obtenemos el driver de para mysql
         Class.forName("com.mysql.jdbc.Driver");
         //obtenemos la conexiï¿½n
         conn = DriverManager.getConnection(url,login,password);

         if (conn!=null){
           // System.out.println("ConecciÃ³n a base de datos "+bd+" OK");
         }
      }
      catch(SQLException e){
         System.out.println(e);
      }catch(ClassNotFoundException e){
         System.out.println(e);
      }catch(Exception e){
         System.out.println(e);
      }
   }
   /**Permite retornar la conexiï¿½
     * @return n*/
   public Connection getConnection(){
      return conn;
   }

   public void desconectar(){
      conn = null;
   }

   public boolean reg_libro(Gestion g){
       
       Connection conect= getConnection();
       String sql="INSERT INTO fichas_libros (Nombre,Autor,Año_publicacion,Cantidad,Area) VALUES('"+g.getNombre()+"','"+g.getautor()+"','"+g.getaño()+"','"+g.getcantidad()+"','"+g.getarea()+"') ";
       try{
       Statement st = conect.createStatement();
       st.executeUpdate(sql);
                        System.out.println("Se a registrado exitosamente");
       //desconectar();
       return true;
       }catch(SQLException E){
           System.out.println("no se pudo guardar ese libro");
       return false;
       }
   }
    public boolean act_libro(Gestion g){
       
       Connection conect= getConnection();
       String sql="UPDATE fichas_libros SET Nombre='"+g.getNombre()+"',Autor='"+g.getautor()+"',Año_publicacion='"+g.getaño()+"',Cantidad='"+g.getcantidad()+"',Area='"+g.getarea()+"' WHERE Codigo='"+g.getcodigo()+"'";
       try{
       Statement st = conect.createStatement();
       st.executeUpdate(sql);
                        System.out.println("Se a actualizado exitosamente");
       //desconectar();
       return true;
       }catch(SQLException E){
           System.out.println("no existe ese libro");
       return false;
       }                         
   }
   public boolean sup_libro(Gestion g){
       
       Connection conect= getConnection();
       String sql="DELETE FROM fichas_libros where Codigo like '"+g.getcodigo()+"'";
       try{
       Statement st = conect.createStatement();
       st.executeUpdate(sql);
       System.out.println("Se a eliminado exitosamente"); 
       //desconectar();
       return true;
       }catch(SQLException E){
           System.out.println("no existe ese libro");
       return false;
       }
    }
   
   public boolean veri_act(int a){
       ResultSet rs=null;
       Connection conect= getConnection();
       try{
       Statement st = conect.createStatement();		
       rs=st.executeQuery("Select * from fichas_libros where Codigo like'%"+a+"%'");
          if(rs.next()==false){
               System.out.println("No existe un libro con codigo: "+a);
               return false;
          }else{
               return true;
          }
       }catch(SQLException E){
           System.out.println("Error al verificar libro");
       return false;
       }
   }
   
      public ResultSet bus_libro(Gestion g){
       ResultSet rs=null;
       Connection conect= getConnection();
       String sql="Select * from fichas_libros where Codigo like'%"+g.getcodigo()+"%'";
       try{
       Statement st = conect.createStatement();
       rs=st.executeQuery(sql);
       //desconectar();
       return rs;
       }catch(SQLException E){
           System.out.println("Error al eliminar libro");
       return rs;
       }
    }
      public boolean prestar_libro(Prestamo P){
       ResultSet rs=null;
       String [] datos = new String [6];
       Connection conect= getConnection();
       String sql="UPDATE fichas_libros SET Cantidad=Cantidad-1 WHERE Codigo='"+P.getLibro()+"'";
       String sql1="INSERT INTO fichas_prestamos (cod_libro,cedula)VALUES('"+P.getLibro()+"','"+P.getcedula()+"') ";
       try{
       Statement st = conect.createStatement();	
       //Statement st1 = conect.createStatement();		
       rs=st.executeQuery("Select * from fichas_libros where Codigo like'%"+P.getLibro()+"%'");
                            if(rs.next()==false){
                                System.out.println("No existe un libro con codigo: "+P.getLibro());
                            }else{
                                do{
                                         datos[0]=rs.getString(1);
                                         datos[1]=rs.getString(2);
                                         datos[2]=rs.getString(3);
                                         datos[3]=rs.getString(4);
                                         datos[4]=rs.getString(5);
                                         datos[5]=rs.getString(6);
                                     }while(rs.next());
                            if( datos[3].equals("0")){
                                System.out.println("No hay mas libros de '"+datos[0]+"' con Codigo '"+datos[4]+"'");
                            } else{
                                if(st.executeUpdate(sql1)==1){
                                    st.executeUpdate(sql);
                                    System.out.println("Se a prestado exitosamente el libro: '"+datos[0]+"' con Codigo: '"+datos[4]+"'");
                                }
                                else{
                                    System.out.println("Hubo un error al actualizar cantidad");
                                }
                                //desconectar();
                                return true;}
                            }
           return false;
       }catch(SQLException E){
           System.out.println("Error al prestar libro");
       return false;
       }
    }   
    public boolean devolver_libro(Prestamo P){
       
       Connection conect= getConnection();
       String sql="UPDATE fichas_libros SET Cantidad=Cantidad+1 WHERE Codigo=(SELECT cod_libro FROM fichas_prestamos WHERE Codigo='"+P.getLibro()+"')";
        String sql1="DELETE FROM fichas_prestamos WHERE Codigo LIKE '"+P.getLibro()+"'";
       try{
       Statement st = conect.createStatement();
       if(st.executeUpdate(sql)==1){
           st.executeUpdate(sql1);
       System.out.println("Se a devuelto con exito");
       }else{
           System.out.println("No existe ese codigo de prestamo, verifique en libros prestados");
       }
       //desconectar();
       return true;
       }catch(SQLException E){
           System.out.println("error al devolver libro");
       return false;
       }
    }   
    public ResultSet bus_prestamo(){
       ResultSet rs=null;
       Connection conect= getConnection();
       //Select fichas_prestamos.Codigo,cod_libro,Nombre,cedula from fichas_prestamos p join fichas_libros on fichas_libros.Codigo=cod_libro
       //Select p.Codigo,cod_libro,Nombre,cedula from fichas_prestamos p join fichas_libros l on l.Codigo=cod_libro
       String sql="Select p.Codigo,cod_libro,Nombre,cedula from fichas_prestamos p join fichas_libros l on l.Codigo=cod_libro";
       try{
       Statement st = conect.createStatement();
       rs=st.executeQuery(sql);
       //desconectar();
       return rs;
       }catch(SQLException E){
           System.out.println("No hay libros prestados");
       return rs;
       }
    }

}

