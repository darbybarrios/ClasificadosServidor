/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Principal.java
 *
 * Created on 11/06/2016, 03:15:27 PM
 */
package clasificados;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

/**
 *
 * @author Familia
 */
public class Principal extends javax.swing.JFrame {
    private Twitter twitterLara;
    private Twitter twitterCaracas;
    private Twitter twitterMiranda;
    private Twitter twitterMerida;
    private Twitter twitterCarabobo;
    private Twitter twitterZulia;
    private Twitter twitterPanama;
    private Twitter twitterChile;
    private RequestToken requestToken;
    private Status statusLara;
    private Status statusCaracas;
    private Status statusMiranda;
    private Status statusMerida;
    private Status statusCarabobo;
    private Status statusZulia;
    private Status statusPanama;
    private Status statusChile;
    private DefaultListModel modeloLara;
    private DefaultListModel modeloCaracas;
    private DefaultListModel modeloMiranda;
    private DefaultListModel modeloMerida;
    private DefaultListModel modeloCarabobo;
    private DefaultListModel modeloZulia;
    private DefaultListModel modeloPanama;
    private DefaultListModel modeloChile;
    private String driver = "org.postgresql.Driver"; 
    private String connectString = "jdbc:postgresql://localhost:5432/clasificados/"; // ACA PONE DONDE ESTA TU BASE DE DATOS 
    private String user = "postgres"; 
    private String password = "postgres";

    /** Creates new form Principal */
    public Principal() {
        initComponents();
    }
    
    private class HiloLara implements Runnable{
        @Override
        public void run() {
            try {
                int hora,minuto,segundo;
                
                long tiempo;
                int posicion = 0;
                Statement stmt = null;
                Connection conn = null;
                ResultSet rs = null;
                ResultSet rs1 = null;
                Class.forName(driver);
                conn = DriverManager.getConnection(connectString, user, password);
                stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                rs1 = stmt.executeQuery("select numero from posicion where id = 1");                  
                if (rs1.next()) {
                                rs1.beforeFirst();
                                while(rs1.next()){
                                    posicion = rs1.getInt(1);
                                    modeloLara.addElement("Posición: "+rs1.getString(1));
                                    System.out.println(rs1.getString(1));

                                }
                            
                }
                tiempo = 20*60000;
                
                while(true){    
                        
                        for(int i = posicion;i<=36;i++){
                            Calendar calendario = new GregorianCalendar();            
                            hora =calendario.get(Calendar.HOUR_OF_DAY);
                            minuto=calendario.get(Calendar.MINUTE);
                            segundo=calendario.get(Calendar.SECOND);
                            System.out.println(hora+":"+minuto+":"+segundo);
                            rs = stmt.executeQuery("select a.anuncio from anuncios a, contactos b where a.numero = "+i+" and a.contacto = b.id and b.lara = true and b.estatus = 'AC'");
                            if (rs.next()) {
                                rs.beforeFirst();
                                while(rs.next()){
                                try {
                                    statusLara = twitterLara.updateStatus(rs.getString(1));
                                    System.out.println(hora+":"+minuto+":"+segundo + " - " + rs.getString(1));
                                    modeloLara.addElement(hora+":"+minuto+":"+segundo + " - " + rs.getString(1));
                                } catch (TwitterException ex) {
                                    modeloLara.addElement(ex.getMessage());
                                    Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                    Thread.sleep(2000);
                                   
                                }
                            
                            }
                            
                            stmt.executeUpdate("update posicion set numero = "+i+" where id = 1");
                            
                            Thread.sleep(tiempo);
                        }
                        posicion = 1;
                        
                    
                }
            } catch (InterruptedException ex) {
                modeloLara.addElement(ex.getMessage());
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                modeloLara.addElement(ex.getMessage());
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                modeloLara.addElement(ex.getMessage());
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
                
	    
             
        }
        
    }
    
    private class HiloCaracas implements Runnable{
        @Override
        public void run() {
            try {
                int hora,minuto,segundo;
                
                long tiempo;
                String where = "";
                int posicion = 0;
                Statement stmt = null;
                Connection conn = null;
                ResultSet rs = null;
                ResultSet rs1 = null;
                Class.forName(driver);
                conn = DriverManager.getConnection(connectString, user, password);
                stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                rs1 = stmt.executeQuery("select numero from posicion where id = 2");                  
                if (rs1.next()) {
                                rs1.beforeFirst();
                                while(rs1.next()){
                                    posicion = rs1.getInt(1);
                                    modeloCaracas.addElement("Posición: "+rs1.getString(1));
                                    System.out.println(rs1.getString(1));

                                }
                            
                }
                tiempo = 20*60000;
                
                while(true){    
                        
                        for(int i = posicion;i<=36;i++){
                            Calendar calendario = new GregorianCalendar();            
                            hora =calendario.get(Calendar.HOUR_OF_DAY);
                            minuto=calendario.get(Calendar.MINUTE);
                            segundo=calendario.get(Calendar.SECOND);
                            System.out.println(hora+":"+minuto+":"+segundo);
                            rs = stmt.executeQuery("select a.anuncio from anuncios a, contactos b where a.numero = "+i+" and a.contacto = b.id and b.caracas = true and b.estatus = 'AC'");
                            if (rs.next()) {
                                rs.beforeFirst();
                                while(rs.next()){
                                try {
                                    statusCaracas = twitterCaracas.updateStatus(rs.getString(1));                                    
                                    System.out.println(hora+":"+minuto+":"+segundo + " - " + rs.getString(1));
                                    modeloCaracas.addElement(hora+":"+minuto+":"+segundo + " - " + rs.getString(1));
                                } catch (TwitterException ex) {
                                    modeloCaracas.addElement(ex.getMessage());
                                    Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                    Thread.sleep(2000);
                                    
                                    

                                }
                            
                            }
                            
                            stmt.executeUpdate("update posicion set numero = "+i+" where id = 2");
                            
                            Thread.sleep(tiempo);
                        }
                        
                    posicion = 1;
                }
            } catch (InterruptedException ex) {
                modeloCaracas.addElement(ex.getMessage());
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                modeloCaracas.addElement(ex.getMessage());
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                modeloCaracas.addElement(ex.getMessage());
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
                
	    
             
        }
        
    }
   private class HiloMiranda implements Runnable{
        @Override
        public void run() {
            try {
                int hora,minuto,segundo;
                
                long tiempo;
                String where = "";
                int posicion = 0;
                Statement stmt = null;
                Connection conn = null;
                ResultSet rs = null;
                ResultSet rs1 = null;
                Class.forName(driver);
                conn = DriverManager.getConnection(connectString, user, password);
                stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                rs1 = stmt.executeQuery("select numero from posicion where id = 3");                  
                if (rs1.next()) {
                                rs1.beforeFirst();
                                while(rs1.next()){
                                    posicion = rs1.getInt(1);
                                    modeloMiranda.addElement("Posición: "+rs1.getString(1));
                                    System.out.println(rs1.getString(1));

                                }
                            
                }
                tiempo = 20*60000;
                
                while(true){    
                        
                        for(int i = posicion;i<=36;i++){
                            Calendar calendario = new GregorianCalendar();            
                            hora =calendario.get(Calendar.HOUR_OF_DAY);
                            minuto=calendario.get(Calendar.MINUTE);
                            segundo=calendario.get(Calendar.SECOND);
                            System.out.println(hora+":"+minuto+":"+segundo);
                            rs = stmt.executeQuery("select a.anuncio from anuncios a, contactos b where a.numero = "+i+" and a.contacto = b.id and b.miranda = true and b.estatus = 'AC'");
                            if (rs.next()) {
                                rs.beforeFirst();
                                while(rs.next()){
                                try {
                                    statusMiranda = twitterMiranda.updateStatus(rs.getString(1));                                    
                                    System.out.println(hora+":"+minuto+":"+segundo + " - " + rs.getString(1));
                                    modeloMiranda.addElement(hora+":"+minuto+":"+segundo + " - " + rs.getString(1));
                                } catch (TwitterException ex) {
                                    modeloMiranda.addElement(ex.getMessage());
                                    Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                    Thread.sleep(2000);
                                    
                                    

                                }
                            
                            }
                            
                            stmt.executeUpdate("update posicion set numero = "+i+" where id = 3");
                            
                            Thread.sleep(tiempo);
                        }
                        
                    posicion = 1;
                }
            } catch (InterruptedException ex) {
                modeloMiranda.addElement(ex.getMessage());
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                modeloMiranda.addElement(ex.getMessage());
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                modeloMiranda.addElement(ex.getMessage());
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
                
	    
              
        }
        
    }
   
   private class HiloMerida implements Runnable{
        @Override
        public void run() {
            try {
                int hora,minuto,segundo;
                
                long tiempo;
                String where = "";
                int posicion = 0;
                Statement stmt = null;
                Connection conn = null;
                ResultSet rs = null;
                ResultSet rs1 = null;
                Class.forName(driver);
                conn = DriverManager.getConnection(connectString, user, password);
                stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                rs1 = stmt.executeQuery("select numero from posicion where id = 4");                  
                if (rs1.next()) {
                                rs1.beforeFirst();
                                while(rs1.next()){
                                    posicion = rs1.getInt(1);
                                    modeloMerida.addElement("Posición: "+rs1.getString(1));
                                    System.out.println(rs1.getString(1));

                                }
                            
                }
                tiempo = 20*60000;
                
                while(true){    
                        
                        for(int i = posicion;i<=36;i++){
                            Calendar calendario = new GregorianCalendar();            
                            hora =calendario.get(Calendar.HOUR_OF_DAY);
                            minuto=calendario.get(Calendar.MINUTE);
                            segundo=calendario.get(Calendar.SECOND);
                            System.out.println(hora+":"+minuto+":"+segundo);
                            rs = stmt.executeQuery("select a.anuncio from anuncios a, contactos b where a.numero = "+i+" and a.contacto = b.id and b.merida = true and b.estatus = 'AC'");
                            if (rs.next()) {
                                rs.beforeFirst();
                                while(rs.next()){
                                try {
                                    statusMerida = twitterMerida.updateStatus(rs.getString(1));                                    
                                    System.out.println(hora+":"+minuto+":"+segundo + " - " + rs.getString(1));
                                    modeloMerida.addElement(hora+":"+minuto+":"+segundo + " - " + rs.getString(1));
                                } catch (TwitterException ex) {
                                    modeloMerida.addElement(ex.getMessage());
                                    Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                    Thread.sleep(2000);
                                    
                                    

                                }
                            
                            }
                            
                            stmt.executeUpdate("update posicion set numero = "+i+" where id=4");
                            
                            Thread.sleep(tiempo);
                        }
                        
                    posicion = 1;
                }
            } catch (InterruptedException ex) {
                modeloMerida.addElement(ex.getMessage());
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                modeloMerida.addElement(ex.getMessage());
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                modeloMerida.addElement(ex.getMessage());
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
                
	    
               
        }
        
    }
   
   private class HiloCarabobo implements Runnable{
        @Override
        public void run() {
            try {
                int hora,minuto,segundo;
                
                long tiempo;
                String where = "";
                int posicion = 0;
                Statement stmt = null;
                Connection conn = null;
                ResultSet rs = null;
                ResultSet rs1 = null;
                Class.forName(driver);
                conn = DriverManager.getConnection(connectString, user, password);
                stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                rs1 = stmt.executeQuery("select numero from posicion where id = 5");                  
                if (rs1.next()) {
                                rs1.beforeFirst();
                                while(rs1.next()){
                                    posicion = rs1.getInt(1);
                                    modeloCarabobo.addElement("Posición: "+rs1.getString(1));
                                    System.out.println(rs1.getString(1));

                                }
                            
                }
                tiempo = 20*60000;
                
                while(true){    
                        
                        for(int i = posicion;i<=36;i++){
                            Calendar calendario = new GregorianCalendar();            
                            hora =calendario.get(Calendar.HOUR_OF_DAY);
                            minuto=calendario.get(Calendar.MINUTE);
                            segundo=calendario.get(Calendar.SECOND);
                            System.out.println(hora+":"+minuto+":"+segundo);
                            rs = stmt.executeQuery("select a.anuncio from anuncios a, contactos b where a.numero = "+i+" and a.contacto = b.id and b.carabobo = true and b.estatus = 'AC'");
                            if (rs.next()) {
                                rs.beforeFirst();
                                while(rs.next()){
                                try {
                                    statusCarabobo = twitterCarabobo.updateStatus(rs.getString(1));                                    
                                    System.out.println(hora+":"+minuto+":"+segundo + " - " + rs.getString(1));
                                    modeloCarabobo.addElement(hora+":"+minuto+":"+segundo + " - " + rs.getString(1));
                                } catch (TwitterException ex) {
                                    modeloCarabobo.addElement(ex.getMessage());
                                    Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                    Thread.sleep(2000);
                                    
                                    

                                }
                            
                            }
                            
                            stmt.executeUpdate("update posicion set numero = "+i+" where id = 5");
                            
                            Thread.sleep(tiempo);
                        }
                        
                    posicion = 1;
                }
            } catch (InterruptedException ex) {
                modeloCarabobo.addElement(ex.getMessage());
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                modeloCarabobo.addElement(ex.getMessage());
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                modeloCarabobo.addElement(ex.getMessage());
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
                
	    
                
        }
        
    }
   
   private class HiloZulia implements Runnable{
        @Override
        public void run() {
            try {
                int hora,minuto,segundo;
                
                long tiempo;
                String where = "";
                int posicion = 0;
                Statement stmt = null;
                Connection conn = null;
                ResultSet rs = null;
                ResultSet rs1 = null;
                Class.forName(driver);
                conn = DriverManager.getConnection(connectString, user, password);
                stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                rs1 = stmt.executeQuery("select numero from posicion where id = 6");                  
                if (rs1.next()) {
                                rs1.beforeFirst();
                                while(rs1.next()){
                                    posicion = rs1.getInt(1);
                                    modeloZulia.addElement("Posición: "+rs1.getString(1));
                                    System.out.println(rs1.getString(1));

                                }
                            
                }
                tiempo = 20*60000;
                
                while(true){    
                        
                        for(int i = posicion;i<=36;i++){
                            Calendar calendario = new GregorianCalendar();            
                            hora =calendario.get(Calendar.HOUR_OF_DAY);
                            minuto=calendario.get(Calendar.MINUTE);
                            segundo=calendario.get(Calendar.SECOND);
                            System.out.println(hora+":"+minuto+":"+segundo);
                            rs = stmt.executeQuery("select a.anuncio from anuncios a, contactos b where a.numero = "+i+" and a.contacto = b.id and b.zulia = true and b.estatus = 'AC'");
                            if (rs.next()) {
                                rs.beforeFirst();
                                while(rs.next()){                                    
                                try {
                                    statusZulia = twitterZulia.updateStatus(rs.getString(1));
                                    System.out.println(hora+":"+minuto+":"+segundo + " - " + rs.getString(1));
                                    modeloZulia.addElement(hora+":"+minuto+":"+segundo + " - " + rs.getString(1));
                                } catch (TwitterException ex) {
                                    modeloZulia.addElement(ex.getMessage());
                                    Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                    Thread.sleep(2000);
                                    
                                    

                                }
                            
                            }
                            
                            stmt.executeUpdate("update posicion set numero = "+i +" where id = 6" );
                            
                            Thread.sleep(tiempo);
                        }
                        
                    posicion = 1;
                }
            } catch (InterruptedException ex) {
                modeloZulia.addElement(ex.getMessage());
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                modeloZulia.addElement(ex.getMessage());
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                modeloZulia.addElement(ex.getMessage());
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
                
	    
              
        }
        
    }
   
   private class HiloPanama implements Runnable{
        @Override
        public void run() {
            try {
                int hora,minuto,segundo;
                
                long tiempo;
                String where = "";
                int posicion = 0;
                Statement stmt = null;
                Connection conn = null;
                ResultSet rs = null;
                ResultSet rs1 = null;
                Class.forName(driver);
                conn = DriverManager.getConnection(connectString, user, password);
                stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                rs1 = stmt.executeQuery("select numero from posicion where id = 7");                  
                if (rs1.next()) {
                                rs1.beforeFirst();
                                while(rs1.next()){
                                    posicion = rs1.getInt(1);
                                    modeloPanama.addElement("Posición: "+rs1.getString(1));
                                    System.out.println(rs1.getString(1));

                                }
                            
                }
                tiempo = 20*60000;
                
                while(true){    
                        
                        for(int i = posicion;i<=36;i++){
                            Calendar calendario = new GregorianCalendar();            
                            hora =calendario.get(Calendar.HOUR_OF_DAY);
                            minuto=calendario.get(Calendar.MINUTE);
                            segundo=calendario.get(Calendar.SECOND);
                            System.out.println(hora+":"+minuto+":"+segundo);
                            rs = stmt.executeQuery("select a.anuncio from anuncios a, contactos b where a.numero = "+i+" and a.contacto = b.id and b.panama = true and b.estatus = 'AC'");
                            if (rs.next()) {
                                rs.beforeFirst();
                                while(rs.next()){
                                try {
                                    statusPanama = twitterPanama.updateStatus(rs.getString(1));                                    
                                    System.out.println(hora+":"+minuto+":"+segundo + " - " + rs.getString(1));
                                    modeloPanama.addElement(hora+":"+minuto+":"+segundo + " - " + rs.getString(1));
                                } catch (TwitterException ex) {
                                     modeloPanama.addElement(ex.getMessage());
                                    Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                                }                                   
                                    Thread.sleep(2000);
                                    
                                    

                                }
                            
                            }
                            
                            stmt.executeUpdate("update posicion set numero = "+i+" where id = 7");
                            
                            Thread.sleep(tiempo);
                        }
                        
                    posicion = 1;
                }
            } catch (InterruptedException ex) {
                modeloPanama.addElement(ex.getMessage());
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                modeloPanama.addElement(ex.getMessage());
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                modeloPanama.addElement(ex.getMessage());
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
                
	    
                
        }
        
    }
   
   private class HiloChile implements Runnable{
        @Override
        public void run() {
            try {
                int hora,minuto,segundo;
                
                long tiempo;
                String where = "";
                int posicion = 0;
                Statement stmt = null;
                Connection conn = null;
                ResultSet rs = null;
                ResultSet rs1 = null;
                Class.forName(driver);
                conn = DriverManager.getConnection(connectString, user, password);
                stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                rs1 = stmt.executeQuery("select numero from posicion where id = 8");                  
                if (rs1.next()) {
                                rs1.beforeFirst();
                                while(rs1.next()){
                                    posicion = rs1.getInt(1);
                                    modeloChile.addElement("Posición: "+rs1.getString(1));
                                    System.out.println(rs1.getString(1));

                                }
                            
                }
                tiempo = 20*60000;
                
                while(true){    
                        
                        for(int i = posicion;i<=36;i++){
                            Calendar calendario = new GregorianCalendar();            
                            hora =calendario.get(Calendar.HOUR_OF_DAY);
                            minuto=calendario.get(Calendar.MINUTE);
                            segundo=calendario.get(Calendar.SECOND);
                            System.out.println(hora+":"+minuto+":"+segundo);
                            rs = stmt.executeQuery("select a.anuncio from anuncios a, contactos b where a.numero = "+i+" and a.contacto = b.id and b.chile = true and b.estatus = 'AC'");
                            if (rs.next()) {
                                rs.beforeFirst();
                                while(rs.next()){
                                try {
                                    statusChile = twitterChile.updateStatus(rs.getString(1));                                    
                                    System.out.println(hora+":"+minuto+":"+segundo + " - " + rs.getString(1));
                                    modeloChile.addElement(hora+":"+minuto+":"+segundo + " - " + rs.getString(1));
                                } catch (TwitterException ex) {
                                     modeloChile.addElement(ex.getMessage());
                                    Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                                }                                   
                                    Thread.sleep(2000);
                                    
                                    

                                }
                            
                            }
                            
                            stmt.executeUpdate("update posicion set numero = "+i+" where id = 8");
                            
                            Thread.sleep(tiempo);
                        }
                        
                    posicion = 1;
                }
            } catch (InterruptedException ex) {
                modeloChile.addElement(ex.getMessage());
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                modeloChile.addElement(ex.getMessage());
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                modeloChile.addElement(ex.getMessage());
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
                
	    
                
        }
        
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        btnIniciarSesionLara = new javax.swing.JButton();
        btnAceptarLara = new javax.swing.JButton();
        txtCodigoLara = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listaLara = new javax.swing.JList();
        btnIniciarLara = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        btnIniciarSesionCaracas = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtCodigoCaracas = new javax.swing.JTextField();
        btnAceptarCaracas = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        listaCaracas = new javax.swing.JList();
        btnIniciarCaracas = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        btnIniciarSesionMiranda = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtCodigoMiranda = new javax.swing.JTextField();
        btnAceptarMiranda = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        listaMiranda = new javax.swing.JList();
        btnIniciarMiranda = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        btnIniciarSesionMerida = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txtCodigoMerida = new javax.swing.JTextField();
        btnAceptarMerida = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        listaMerida = new javax.swing.JList();
        btnIniciarMerida = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        btnIniciarSesionCarabobo = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        txtCodigoCarabobo = new javax.swing.JTextField();
        btnAceptarCarabobo = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        listaCarabobo = new javax.swing.JList();
        btnIniciarCarabobo = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        btnIniciarSesionZulia = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        txtCodigoZulia = new javax.swing.JTextField();
        btnAceptarZulia = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        listaZulia = new javax.swing.JList();
        btnIniciarZulia = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        btnIniciarSesionPanama = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        txtCodigoPanama = new javax.swing.JTextField();
        btnAceptarPanama = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        listaPanama = new javax.swing.JList();
        btnIniciarPanama = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        btnIniciarSesionChile = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        txtCodigoChile = new javax.swing.JTextField();
        btnAceptarChile = new javax.swing.JButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        listaChile = new javax.swing.JList();
        btnIniciarChile = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Clasificados");
        setBounds(new java.awt.Rectangle(200, 100, 0, 0));
        setResizable(false);

        btnIniciarSesionLara.setText("Iniciar Sesión");
        btnIniciarSesionLara.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarSesionLaraActionPerformed(evt);
            }
        });

        btnAceptarLara.setText("Aceptar");
        btnAceptarLara.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarLaraActionPerformed(evt);
            }
        });

        jLabel1.setText("Código");

        jScrollPane1.setViewportView(listaLara);

        btnIniciarLara.setText("Iniciar");
        btnIniciarLara.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarLaraActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 855, Short.MAX_VALUE)
                    .addComponent(btnIniciarSesionLara)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCodigoLara, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAceptarLara)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 527, Short.MAX_VALUE)
                        .addComponent(btnIniciarLara)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnIniciarSesionLara)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtCodigoLara, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAceptarLara)
                    .addComponent(btnIniciarLara))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Lara", jPanel1);

        btnIniciarSesionCaracas.setText("Iniciar Sesión");
        btnIniciarSesionCaracas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarSesionCaracasActionPerformed(evt);
            }
        });

        jLabel3.setText("Código");

        btnAceptarCaracas.setText("Aceptar");
        btnAceptarCaracas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarCaracasActionPerformed(evt);
            }
        });

        jScrollPane2.setViewportView(listaCaracas);

        btnIniciarCaracas.setText("Iniciar");
        btnIniciarCaracas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarCaracasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 855, Short.MAX_VALUE)
                    .addComponent(btnIniciarSesionCaracas)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCodigoCaracas, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAceptarCaracas)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 527, Short.MAX_VALUE)
                        .addComponent(btnIniciarCaracas)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnIniciarSesionCaracas)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtCodigoCaracas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAceptarCaracas)
                    .addComponent(btnIniciarCaracas))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Caracas", jPanel2);

        btnIniciarSesionMiranda.setText("Iniciar Sesión");
        btnIniciarSesionMiranda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarSesionMirandaActionPerformed(evt);
            }
        });

        jLabel5.setText("Código");

        btnAceptarMiranda.setText("Aceptar");
        btnAceptarMiranda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarMirandaActionPerformed(evt);
            }
        });

        jScrollPane3.setViewportView(listaMiranda);

        btnIniciarMiranda.setText("Iniciar");
        btnIniciarMiranda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarMirandaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 855, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCodigoMiranda, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAceptarMiranda)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 523, Short.MAX_VALUE)
                        .addComponent(btnIniciarMiranda))
                    .addComponent(btnIniciarSesionMiranda))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnIniciarSesionMiranda)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtCodigoMiranda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnIniciarMiranda)
                    .addComponent(btnAceptarMiranda))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Miranda", jPanel3);

        btnIniciarSesionMerida.setText("Iniciar Sesión");
        btnIniciarSesionMerida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarSesionMeridaActionPerformed(evt);
            }
        });

        jLabel7.setText("Código");

        btnAceptarMerida.setText("Aceptar");
        btnAceptarMerida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarMeridaActionPerformed(evt);
            }
        });

        jScrollPane4.setViewportView(listaMerida);

        btnIniciarMerida.setText("Iniciar");
        btnIniciarMerida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarMeridaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 855, Short.MAX_VALUE)
                    .addComponent(btnIniciarSesionMerida)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCodigoMerida, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAceptarMerida)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 555, Short.MAX_VALUE)
                        .addComponent(btnIniciarMerida)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnIniciarSesionMerida)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtCodigoMerida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAceptarMerida)
                    .addComponent(btnIniciarMerida))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Merida", jPanel4);

        btnIniciarSesionCarabobo.setText("Iniciar Sesión");
        btnIniciarSesionCarabobo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarSesionCaraboboActionPerformed(evt);
            }
        });

        jLabel9.setText("Código");

        btnAceptarCarabobo.setText("Aceptar");
        btnAceptarCarabobo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarCaraboboActionPerformed(evt);
            }
        });

        jScrollPane5.setViewportView(listaCarabobo);

        btnIniciarCarabobo.setText("Iniciar");
        btnIniciarCarabobo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarCaraboboActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 855, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnIniciarSesionCarabobo)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCodigoCarabobo, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAceptarCarabobo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 611, Short.MAX_VALUE)
                        .addComponent(btnIniciarCarabobo)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 444, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnIniciarSesionCarabobo)
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtCodigoCarabobo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAceptarCarabobo)
                    .addComponent(btnIniciarCarabobo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Carabobo", jPanel5);

        btnIniciarSesionZulia.setText("Iniciar Sesión");
        btnIniciarSesionZulia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarSesionZuliaActionPerformed(evt);
            }
        });

        jLabel11.setText("Código");

        btnAceptarZulia.setText("Aceptar");
        btnAceptarZulia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarZuliaActionPerformed(evt);
            }
        });

        jScrollPane6.setViewportView(listaZulia);

        btnIniciarZulia.setText("Iniciar");
        btnIniciarZulia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarZuliaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 855, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCodigoZulia, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnIniciarSesionZulia, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAceptarZulia)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 612, Short.MAX_VALUE)
                        .addComponent(btnIniciarZulia)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 444, Short.MAX_VALUE)
            .addGap(0, 444, Short.MAX_VALUE)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnIniciarSesionZulia)
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtCodigoZulia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAceptarZulia)
                    .addComponent(btnIniciarZulia))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Zulia", jPanel6);

        btnIniciarSesionPanama.setText("Iniciar Sesión");
        btnIniciarSesionPanama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarSesionPanamaActionPerformed(evt);
            }
        });

        jLabel13.setText("Código");

        btnAceptarPanama.setText("Aceptar");
        btnAceptarPanama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarPanamaActionPerformed(evt);
            }
        });

        jScrollPane7.setViewportView(listaPanama);

        btnIniciarPanama.setText("Iniciar");
        btnIniciarPanama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarPanamaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 855, Short.MAX_VALUE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCodigoPanama, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnIniciarSesionPanama, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAceptarPanama)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 618, Short.MAX_VALUE)
                        .addComponent(btnIniciarPanama)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 444, Short.MAX_VALUE)
            .addGap(0, 444, Short.MAX_VALUE)
            .addGap(0, 444, Short.MAX_VALUE)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnIniciarSesionPanama)
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtCodigoPanama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAceptarPanama)
                    .addComponent(btnIniciarPanama))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Panama", jPanel7);

        btnIniciarSesionChile.setText("Iniciar Sesión");
        btnIniciarSesionChile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarSesionChileActionPerformed(evt);
            }
        });

        jLabel14.setText("Código");

        btnAceptarChile.setText("Aceptar");
        btnAceptarChile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarChileActionPerformed(evt);
            }
        });

        jScrollPane8.setViewportView(listaChile);

        btnIniciarChile.setText("Iniciar");
        btnIniciarChile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarChileActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 865, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnIniciarChile)))
                .addContainerGap())
            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel8Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel8Layout.createSequentialGroup()
                            .addComponent(jLabel14)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtCodigoChile, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(btnIniciarSesionChile, javax.swing.GroupLayout.Alignment.LEADING))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(btnAceptarChile)
                    .addContainerGap(689, Short.MAX_VALUE)))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap(52, Short.MAX_VALUE)
                .addComponent(btnIniciarChile)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel8Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(btnIniciarSesionChile)
                    .addGap(18, 18, 18)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel14)
                        .addComponent(txtCodigoChile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnAceptarChile))
                    .addContainerGap(375, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("Chile", jPanel8);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void btnAceptarCaracasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarCaracasActionPerformed
try {
                            // get request token.
                            // this will throw IllegalStateException if access token is already available
                            modeloCaracas.addElement("Token de solicitud conseguido");
                            modeloCaracas.addElement("Request token: " + requestToken.getToken());
                            modeloCaracas.addElement("Request token secret: " + requestToken.getTokenSecret());
                            
                            
                            System.out.println("Token de solicitud conseguido");
                            System.out.println("Request token: " + requestToken.getToken());
                            System.out.println("Request token secret: " + requestToken.getTokenSecret());
                            AccessToken accessToken = null;

                            
                            while (null == accessToken) {
                                modeloCaracas.addElement("Abra la siguiente URL y permitir el acceso a su cuenta :");
                                modeloCaracas.addElement(requestToken.getAuthorizationURL());
                                System.out.println("Abra la siguiente URL y permitir el acceso a su cuenta :");
                                System.out.println(requestToken.getAuthorizationURL());
                                
                                modeloCaracas.addElement("Introduzca el Codigo ( si está disponible ) y pulsa aceptar después de que le concede el acceso [ PIN ] .");
                                System.out.print("Introduzca el Codigo ( si está disponible ) y pulsa aceptar después de que le concede el acceso [ PIN ] .");
                                String pin = txtCodigoCaracas.getText();
                                modeloCaracas.addElement(pin);
                                
                                    if (pin.length() > 0) {
                                        accessToken = twitterCaracas.getOAuthAccessToken(requestToken, pin);
                                    } else {
                                        accessToken = twitterCaracas.getOAuthAccessToken(requestToken);
                                    }
                            }
                            modeloCaracas.addElement("Tienes token de acceso .");
                            modeloCaracas.addElement("Access token: " + accessToken.getToken());
                            modeloCaracas.addElement("Access token secret: " + accessToken.getTokenSecret());
                            System.out.println("Tienes token de acceso .");
                            System.out.println("Access token: " + accessToken.getToken());
                            System.out.println("Access token secret: " + accessToken.getTokenSecret());
                } catch (TwitterException te) {
                      if (401 == te.getStatusCode()) {
                            modeloCaracas.addElement("No se puede obtener el token de acceso .");
                            System.out.println("No se puede obtener el token de acceso .");
                     } else {
                          modeloCaracas.addElement("ERROR "+te.getMessage());
                          System.out.println("ERROR "+te.getMessage());
                     }          
                } catch (IllegalStateException ie) {
              if (!twitterCaracas.getAuthorization().isEnabled()) {
                   modeloCaracas.addElement("OAuth consumer key/secret no esta establico.");
                   System.out.println("OAuth consumer key/secret no esta establico.");                               
             }
           }
}//GEN-LAST:event_btnAceptarCaracasActionPerformed

private void btnAceptarMeridaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarMeridaActionPerformed
try {
                            // get request token.
                            // this will throw IllegalStateException if access token is already available
                            modeloMerida.addElement("Token de solicitud conseguido");
                            modeloMerida.addElement("Request token: " + requestToken.getToken());
                            modeloMerida.addElement("Request token secret: " + requestToken.getTokenSecret());
                            
                            
                            System.out.println("Token de solicitud conseguido");
                            System.out.println("Request token: " + requestToken.getToken());
                            System.out.println("Request token secret: " + requestToken.getTokenSecret());
                            AccessToken accessToken = null;

                            
                            while (null == accessToken) {
                                modeloMerida.addElement("Abra la siguiente URL y permitir el acceso a su cuenta :");
                                modeloMerida.addElement(requestToken.getAuthorizationURL());
                                System.out.println("Abra la siguiente URL y permitir el acceso a su cuenta :");
                                System.out.println(requestToken.getAuthorizationURL());
                                
                                modeloMerida.addElement("Introduzca el Codigo ( si está disponible ) y pulsa aceptar después de que le concede el acceso [ PIN ] .");
                                System.out.print("Introduzca el Codigo ( si está disponible ) y pulsa aceptar después de que le concede el acceso [ PIN ] .");
                                String pin = txtCodigoMerida.getText();
                                modeloMerida.addElement(pin);
                                
                                    if (pin.length() > 0) {
                                        accessToken = twitterMerida.getOAuthAccessToken(requestToken, pin);
                                    } else {
                                        accessToken = twitterMerida.getOAuthAccessToken(requestToken);
                                    }
                            }
                            modeloMerida.addElement("Tienes token de acceso .");
                            modeloMerida.addElement("Access token: " + accessToken.getToken());
                            modeloMerida.addElement("Access token secret: " + accessToken.getTokenSecret());
                            System.out.println("Tienes token de acceso .");
                            System.out.println("Access token: " + accessToken.getToken());
                            System.out.println("Access token secret: " + accessToken.getTokenSecret());
                } catch (TwitterException te) {
                      if (401 == te.getStatusCode()) {
                            modeloMerida.addElement("No se puede obtener el token de acceso .");
                            System.out.println("No se puede obtener el token de acceso .");
                     } else {
                          modeloMerida.addElement("ERROR "+te.getMessage());
                          System.out.println("ERROR "+te.getMessage());
                     }          
                } catch (IllegalStateException ie) {
              if (!twitterMerida.getAuthorization().isEnabled()) {
                   modeloMerida.addElement("OAuth consumer key/secret no esta establico.");
                   System.out.println("OAuth consumer key/secret no esta establico.");                               
             }
           }
}//GEN-LAST:event_btnAceptarMeridaActionPerformed

private void btnIniciarSesionLaraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarSesionLaraActionPerformed
    File file = new File("/app/sistemas/config.properties");
        Properties prop = new Properties();
        InputStream is = null;
        OutputStream os = null;
        try{
            if (file.exists()) {
                is = new FileInputStream(file);
                prop.load(is);

            }
        
            modeloLara = new DefaultListModel();
            listaLara.setModel(modeloLara);
            
            twitterLara = new TwitterFactory().getInstance();
            modeloLara.addElement("oauth.consumerKey: "+prop.getProperty("oauth.consumerKey"));
            modeloLara.addElement("oauth.consumerSecret: "+prop.getProperty("oauth.consumerSecret"));
            System.out.println("oauth.consumerKey: "+prop.getProperty("oauth.consumerKey"));
            System.out.println("oauth.consumerSecret: "+prop.getProperty("oauth.consumerSecret"));
            twitterLara.setOAuthConsumer(prop.getProperty("oauth.consumerKey"), prop.getProperty("oauth.consumerSecret"));
            requestToken = twitterLara.getOAuthRequestToken();	
            Desktop.getDesktop().browse(new URI(requestToken.getAuthorizationURL()));
	   
            
        } catch (Exception te) {
            System.out.println("ERROR: " + te.getMessage());
            modeloLara.addElement("ERROR: " + te.getMessage());
            
        } 
}//GEN-LAST:event_btnIniciarSesionLaraActionPerformed

private void btnAceptarLaraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarLaraActionPerformed
try {
                            // get request token.
                            // this will throw IllegalStateException if access token is already available
                            modeloLara.addElement("Token de solicitud conseguido");
                            modeloLara.addElement("Request token: " + requestToken.getToken());
                            modeloLara.addElement("Request token secret: " + requestToken.getTokenSecret());
                            
                            
                            System.out.println("Token de solicitud conseguido");
                            System.out.println("Request token: " + requestToken.getToken());
                            System.out.println("Request token secret: " + requestToken.getTokenSecret());
                            AccessToken accessToken = null;

                            
                            while (null == accessToken) {
                                modeloLara.addElement("Abra la siguiente URL y permitir el acceso a su cuenta :");
                                modeloLara.addElement(requestToken.getAuthorizationURL());
                                System.out.println("Abra la siguiente URL y permitir el acceso a su cuenta :");
                                System.out.println(requestToken.getAuthorizationURL());
                                
                                modeloLara.addElement("Introduzca el Codigo ( si está disponible ) y pulsa aceptar después de que le concede el acceso [ PIN ] .");
                                System.out.print("Introduzca el Codigo ( si está disponible ) y pulsa aceptar después de que le concede el acceso [ PIN ] .");
                                String pin = txtCodigoLara.getText();
                                modeloLara.addElement(pin);
                                
                                    if (pin.length() > 0) {
                                        accessToken = twitterLara.getOAuthAccessToken(requestToken, pin);
                                    } else {
                                        accessToken = twitterLara.getOAuthAccessToken(requestToken);
                                    }
                            }
                            modeloLara.addElement("Tienes token de acceso .");
                            modeloLara.addElement("Access token: " + accessToken.getToken());
                            modeloLara.addElement("Access token secret: " + accessToken.getTokenSecret());
                            System.out.println("Tienes token de acceso .");
                            System.out.println("Access token: " + accessToken.getToken());
                            System.out.println("Access token secret: " + accessToken.getTokenSecret());
                } catch (TwitterException te) {
                      if (401 == te.getStatusCode()) {
                            modeloLara.addElement("No se puede obtener el token de acceso .");
                            System.out.println("No se puede obtener el token de acceso .");
                     } else {
                          modeloLara.addElement("ERROR "+te.getMessage());
                          System.out.println("ERROR "+te.getMessage());
                     }          
                } catch (IllegalStateException ie) {
              if (!twitterLara.getAuthorization().isEnabled()) {
                   modeloLara.addElement("OAuth consumer key/secret no esta establico.");
                   System.out.println("OAuth consumer key/secret no esta establico.");                               
             }
           }
}//GEN-LAST:event_btnAceptarLaraActionPerformed

private void btnIniciarLaraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarLaraActionPerformed
    new Thread (new HiloLara()).start(); 
}//GEN-LAST:event_btnIniciarLaraActionPerformed

private void btnIniciarSesionCaracasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarSesionCaracasActionPerformed
File file = new File("/app/sistemas/config.properties");
        Properties prop = new Properties();
        InputStream is = null;
        OutputStream os = null;
        try{
            if (file.exists()) {
                is = new FileInputStream(file);
                prop.load(is);

            }
        
            modeloCaracas = new DefaultListModel();
            listaCaracas.setModel(modeloCaracas);
            
            twitterCaracas = new TwitterFactory().getInstance();
            modeloCaracas.addElement("oauth.consumerKey: "+prop.getProperty("oauth.consumerKey"));
            modeloCaracas.addElement("oauth.consumerSecret: "+prop.getProperty("oauth.consumerSecret"));
            System.out.println("oauth.consumerKey: "+prop.getProperty("oauth.consumerKey"));
            System.out.println("oauth.consumerSecret: "+prop.getProperty("oauth.consumerSecret"));
            twitterCaracas.setOAuthConsumer(prop.getProperty("oauth.consumerKey"), prop.getProperty("oauth.consumerSecret"));
            requestToken = twitterCaracas.getOAuthRequestToken();	
            Desktop.getDesktop().browse(new URI(requestToken.getAuthorizationURL()));
	   
            
        } catch (Exception te) {
            System.out.println("ERROR: " + te.getMessage());
            modeloCaracas.addElement("ERROR: " + te.getMessage());
            
        }
}//GEN-LAST:event_btnIniciarSesionCaracasActionPerformed

private void btnIniciarCaracasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarCaracasActionPerformed
    new Thread (new HiloCaracas()).start(); 
}//GEN-LAST:event_btnIniciarCaracasActionPerformed

private void btnIniciarSesionMirandaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarSesionMirandaActionPerformed
File file = new File("/app/sistemas/config.properties");
        Properties prop = new Properties();
        InputStream is = null;
        OutputStream os = null;
        try{
            if (file.exists()) {
                is = new FileInputStream(file);
                prop.load(is);

            }
        
            modeloMiranda = new DefaultListModel();
            listaMiranda.setModel(modeloMiranda);
            
            twitterMiranda = new TwitterFactory().getInstance();
            modeloMiranda.addElement("oauth.consumerKey: "+prop.getProperty("oauth.consumerKey"));
            modeloMiranda.addElement("oauth.consumerSecret: "+prop.getProperty("oauth.consumerSecret"));
            System.out.println("oauth.consumerKey: "+prop.getProperty("oauth.consumerKey"));
            System.out.println("oauth.consumerSecret: "+prop.getProperty("oauth.consumerSecret"));
            twitterMiranda.setOAuthConsumer(prop.getProperty("oauth.consumerKey"), prop.getProperty("oauth.consumerSecret"));
            requestToken = twitterMiranda.getOAuthRequestToken();	
            Desktop.getDesktop().browse(new URI(requestToken.getAuthorizationURL()));
	   
            
        } catch (Exception te) {
            System.out.println("ERROR: " + te.getMessage());
            modeloMiranda.addElement("ERROR: " + te.getMessage());
            
        }
}//GEN-LAST:event_btnIniciarSesionMirandaActionPerformed

private void btnIniciarSesionMeridaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarSesionMeridaActionPerformed
File file = new File("/app/sistemas/config.properties");
        Properties prop = new Properties();
        InputStream is = null;
        OutputStream os = null;
        try{
            if (file.exists()) {
                is = new FileInputStream(file);
                prop.load(is);

            }
        
            modeloMerida = new DefaultListModel();
            listaMerida.setModel(modeloMerida);
            
            twitterMerida = new TwitterFactory().getInstance();
            modeloMerida.addElement("oauth.consumerKey: "+prop.getProperty("oauth.consumerKey"));
            modeloMerida.addElement("oauth.consumerSecret: "+prop.getProperty("oauth.consumerSecret"));
            System.out.println("oauth.consumerKey: "+prop.getProperty("oauth.consumerKey"));
            System.out.println("oauth.consumerSecret: "+prop.getProperty("oauth.consumerSecret"));
            twitterMerida.setOAuthConsumer(prop.getProperty("oauth.consumerKey"), prop.getProperty("oauth.consumerSecret"));
            requestToken = twitterMerida.getOAuthRequestToken();	
            Desktop.getDesktop().browse(new URI(requestToken.getAuthorizationURL()));
	   
            
        } catch (Exception te) {
            System.out.println("ERROR: " + te.getMessage());
            modeloMerida.addElement("ERROR: " + te.getMessage());
            
        }
}//GEN-LAST:event_btnIniciarSesionMeridaActionPerformed

private void btnIniciarSesionCaraboboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarSesionCaraboboActionPerformed
File file = new File("/app/sistemas/config.properties");
        Properties prop = new Properties();
        InputStream is = null;
        OutputStream os = null;
        try{
            if (file.exists()) {
                is = new FileInputStream(file);
                prop.load(is);

            }
        
            modeloCarabobo = new DefaultListModel();
            listaCarabobo.setModel(modeloCarabobo);
            
            twitterCarabobo = new TwitterFactory().getInstance();
            modeloCarabobo.addElement("oauth.consumerKey: "+prop.getProperty("oauth.consumerKey"));
            modeloCarabobo.addElement("oauth.consumerSecret: "+prop.getProperty("oauth.consumerSecret"));
            System.out.println("oauth.consumerKey: "+prop.getProperty("oauth.consumerKey"));
            System.out.println("oauth.consumerSecret: "+prop.getProperty("oauth.consumerSecret"));
            twitterCarabobo.setOAuthConsumer(prop.getProperty("oauth.consumerKey"), prop.getProperty("oauth.consumerSecret"));
            requestToken = twitterCarabobo.getOAuthRequestToken();	
            Desktop.getDesktop().browse(new URI(requestToken.getAuthorizationURL()));
	   
            
        } catch (Exception te) {
            System.out.println("ERROR: " + te.getMessage());
            modeloCarabobo.addElement("ERROR: " + te.getMessage());
            
        }
}//GEN-LAST:event_btnIniciarSesionCaraboboActionPerformed

private void btnIniciarSesionZuliaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarSesionZuliaActionPerformed
File file = new File("/app/sistemas/config.properties");
        Properties prop = new Properties();
        InputStream is = null;
        OutputStream os = null;
        try{
            if (file.exists()) {
                is = new FileInputStream(file);
                prop.load(is);

            }
        
            modeloZulia = new DefaultListModel();
            listaZulia.setModel(modeloZulia);
            
            twitterZulia = new TwitterFactory().getInstance();
            modeloZulia.addElement("oauth.consumerKey: "+prop.getProperty("oauth.consumerKey"));
            modeloZulia.addElement("oauth.consumerSecret: "+prop.getProperty("oauth.consumerSecret"));
            System.out.println("oauth.consumerKey: "+prop.getProperty("oauth.consumerKey"));
            System.out.println("oauth.consumerSecret: "+prop.getProperty("oauth.consumerSecret"));
            twitterZulia.setOAuthConsumer(prop.getProperty("oauth.consumerKey"), prop.getProperty("oauth.consumerSecret"));
            requestToken = twitterZulia.getOAuthRequestToken();	
            Desktop.getDesktop().browse(new URI(requestToken.getAuthorizationURL()));
	   
            
        } catch (Exception te) {
            System.out.println("ERROR: " + te.getMessage());
            modeloZulia.addElement("ERROR: " + te.getMessage());
            
        }
}//GEN-LAST:event_btnIniciarSesionZuliaActionPerformed

private void btnIniciarSesionPanamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarSesionPanamaActionPerformed
File file = new File("/app/sistemas/config.properties");
        Properties prop = new Properties();
        InputStream is = null;
        OutputStream os = null;
        try{
            if (file.exists()) {
                is = new FileInputStream(file);
                prop.load(is);

            }
        
            modeloPanama = new DefaultListModel();
            listaPanama.setModel(modeloPanama);
            
            twitterPanama = new TwitterFactory().getInstance();
            modeloPanama.addElement("oauth.consumerKey: "+prop.getProperty("oauth.consumerKey"));
            modeloPanama.addElement("oauth.consumerSecret: "+prop.getProperty("oauth.consumerSecret"));
            System.out.println("oauth.consumerKey: "+prop.getProperty("oauth.consumerKey"));
            System.out.println("oauth.consumerSecret: "+prop.getProperty("oauth.consumerSecret"));
            twitterPanama.setOAuthConsumer(prop.getProperty("oauth.consumerKey"), prop.getProperty("oauth.consumerSecret"));
            requestToken = twitterPanama.getOAuthRequestToken();	
            Desktop.getDesktop().browse(new URI(requestToken.getAuthorizationURL()));
	   
            
        } catch (Exception te) {
            System.out.println("ERROR: " + te.getMessage());
            modeloPanama.addElement("ERROR: " + te.getMessage());
            
        }
}//GEN-LAST:event_btnIniciarSesionPanamaActionPerformed

private void btnAceptarMirandaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarMirandaActionPerformed
try {
                            // get request token.
                            // this will throw IllegalStateException if access token is already available
                            modeloMiranda.addElement("Token de solicitud conseguido");
                            modeloMiranda.addElement("Request token: " + requestToken.getToken());
                            modeloMiranda.addElement("Request token secret: " + requestToken.getTokenSecret());
                            
                            
                            System.out.println("Token de solicitud conseguido");
                            System.out.println("Request token: " + requestToken.getToken());
                            System.out.println("Request token secret: " + requestToken.getTokenSecret());
                            AccessToken accessToken = null;

                            
                            while (null == accessToken) {
                                modeloMiranda.addElement("Abra la siguiente URL y permitir el acceso a su cuenta :");
                                modeloMiranda.addElement(requestToken.getAuthorizationURL());
                                System.out.println("Abra la siguiente URL y permitir el acceso a su cuenta :");
                                System.out.println(requestToken.getAuthorizationURL());
                                
                                modeloMiranda.addElement("Introduzca el Codigo ( si está disponible ) y pulsa aceptar después de que le concede el acceso [ PIN ] .");
                                System.out.print("Introduzca el Codigo ( si está disponible ) y pulsa aceptar después de que le concede el acceso [ PIN ] .");
                                String pin = txtCodigoMiranda.getText();
                                modeloMiranda.addElement(pin);
                                
                                    if (pin.length() > 0) {
                                        accessToken = twitterMiranda.getOAuthAccessToken(requestToken, pin);
                                    } else {
                                        accessToken = twitterMiranda.getOAuthAccessToken(requestToken);
                                    }
                            }
                            modeloMiranda.addElement("Tienes token de acceso .");
                            modeloMiranda.addElement("Access token: " + accessToken.getToken());
                            modeloMiranda.addElement("Access token secret: " + accessToken.getTokenSecret());
                            System.out.println("Tienes token de acceso .");
                            System.out.println("Access token: " + accessToken.getToken());
                            System.out.println("Access token secret: " + accessToken.getTokenSecret());
                } catch (TwitterException te) {
                      if (401 == te.getStatusCode()) {
                            modeloMiranda.addElement("No se puede obtener el token de acceso .");
                            System.out.println("No se puede obtener el token de acceso .");
                     } else {
                          modeloMiranda.addElement("ERROR "+te.getMessage());
                          System.out.println("ERROR "+te.getMessage());
                     }          
                } catch (IllegalStateException ie) {
              if (!twitterMiranda.getAuthorization().isEnabled()) {
                   modeloMiranda.addElement("OAuth consumer key/secret no esta establico.");
                   System.out.println("OAuth consumer key/secret no esta establico.");                               
             }
           }
}//GEN-LAST:event_btnAceptarMirandaActionPerformed

private void btnAceptarCaraboboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarCaraboboActionPerformed
try {
                            // get request token.
                            // this will throw IllegalStateException if access token is already available
                            modeloCarabobo.addElement("Token de solicitud conseguido");
                            modeloCarabobo.addElement("Request token: " + requestToken.getToken());
                            modeloCarabobo.addElement("Request token secret: " + requestToken.getTokenSecret());
                            
                            
                            System.out.println("Token de solicitud conseguido");
                            System.out.println("Request token: " + requestToken.getToken());
                            System.out.println("Request token secret: " + requestToken.getTokenSecret());
                            AccessToken accessToken = null;

                            
                            while (null == accessToken) {
                                modeloCarabobo.addElement("Abra la siguiente URL y permitir el acceso a su cuenta :");
                                modeloCarabobo.addElement(requestToken.getAuthorizationURL());
                                System.out.println("Abra la siguiente URL y permitir el acceso a su cuenta :");
                                System.out.println(requestToken.getAuthorizationURL());
                                
                                modeloCarabobo.addElement("Introduzca el Codigo ( si está disponible ) y pulsa aceptar después de que le concede el acceso [ PIN ] .");
                                System.out.print("Introduzca el Codigo ( si está disponible ) y pulsa aceptar después de que le concede el acceso [ PIN ] .");
                                String pin = txtCodigoCarabobo.getText();
                                modeloCarabobo.addElement(pin);
                                
                                    if (pin.length() > 0) {
                                        accessToken = twitterCarabobo.getOAuthAccessToken(requestToken, pin);
                                    } else {
                                        accessToken = twitterCarabobo.getOAuthAccessToken(requestToken);
                                    }
                            }
                            modeloCarabobo.addElement("Tienes token de acceso .");
                            modeloCarabobo.addElement("Access token: " + accessToken.getToken());
                            modeloCarabobo.addElement("Access token secret: " + accessToken.getTokenSecret());
                            System.out.println("Tienes token de acceso .");
                            System.out.println("Access token: " + accessToken.getToken());
                            System.out.println("Access token secret: " + accessToken.getTokenSecret());
                } catch (TwitterException te) {
                      if (401 == te.getStatusCode()) {
                            modeloCarabobo.addElement("No se puede obtener el token de acceso .");
                            System.out.println("No se puede obtener el token de acceso .");
                     } else {
                          modeloCarabobo.addElement("ERROR "+te.getMessage());
                          System.out.println("ERROR "+te.getMessage());
                     }          
                } catch (IllegalStateException ie) {
              if (!twitterCarabobo.getAuthorization().isEnabled()) {
                   modeloCarabobo.addElement("OAuth consumer key/secret no esta establico.");
                   System.out.println("OAuth consumer key/secret no esta establico.");                               
             }
           }
}//GEN-LAST:event_btnAceptarCaraboboActionPerformed

private void btnAceptarZuliaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarZuliaActionPerformed
try {
                            // get request token.
                            // this will throw IllegalStateException if access token is already available
                            modeloZulia.addElement("Token de solicitud conseguido");
                            modeloZulia.addElement("Request token: " + requestToken.getToken());
                            modeloZulia.addElement("Request token secret: " + requestToken.getTokenSecret());
                            
                            
                            System.out.println("Token de solicitud conseguido");
                            System.out.println("Request token: " + requestToken.getToken());
                            System.out.println("Request token secret: " + requestToken.getTokenSecret());
                            AccessToken accessToken = null;

                            
                            while (null == accessToken) {
                                modeloZulia.addElement("Abra la siguiente URL y permitir el acceso a su cuenta :");
                                modeloZulia.addElement(requestToken.getAuthorizationURL());
                                System.out.println("Abra la siguiente URL y permitir el acceso a su cuenta :");
                                System.out.println(requestToken.getAuthorizationURL());
                                
                                modeloZulia.addElement("Introduzca el Codigo ( si está disponible ) y pulsa aceptar después de que le concede el acceso [ PIN ] .");
                                System.out.print("Introduzca el Codigo ( si está disponible ) y pulsa aceptar después de que le concede el acceso [ PIN ] .");
                                String pin = txtCodigoZulia.getText();
                                modeloZulia.addElement(pin);
                                
                                    if (pin.length() > 0) {
                                        accessToken = twitterZulia.getOAuthAccessToken(requestToken, pin);
                                    } else {
                                        accessToken = twitterZulia.getOAuthAccessToken(requestToken);
                                    }
                            }
                            modeloZulia.addElement("Tienes token de acceso .");
                            modeloZulia.addElement("Access token: " + accessToken.getToken());
                            modeloZulia.addElement("Access token secret: " + accessToken.getTokenSecret());
                            System.out.println("Tienes token de acceso .");
                            System.out.println("Access token: " + accessToken.getToken());
                            System.out.println("Access token secret: " + accessToken.getTokenSecret());
                } catch (TwitterException te) {
                      if (401 == te.getStatusCode()) {
                            modeloZulia.addElement("No se puede obtener el token de acceso .");
                            System.out.println("No se puede obtener el token de acceso .");
                     } else {
                          modeloZulia.addElement("ERROR "+te.getMessage());
                          System.out.println("ERROR "+te.getMessage());
                     }          
                } catch (IllegalStateException ie) {
              if (!twitterZulia.getAuthorization().isEnabled()) {
                   modeloZulia.addElement("OAuth consumer key/secret no esta establico.");
                   System.out.println("OAuth consumer key/secret no esta establico.");                               
             }
           }
}//GEN-LAST:event_btnAceptarZuliaActionPerformed

private void btnAceptarPanamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarPanamaActionPerformed
try {
                            // get request token.
                            // this will throw IllegalStateException if access token is already available
                            modeloPanama.addElement("Token de solicitud conseguido");
                            modeloPanama.addElement("Request token: " + requestToken.getToken());
                            modeloPanama.addElement("Request token secret: " + requestToken.getTokenSecret());
                            
                            
                            System.out.println("Token de solicitud conseguido");
                            System.out.println("Request token: " + requestToken.getToken());
                            System.out.println("Request token secret: " + requestToken.getTokenSecret());
                            AccessToken accessToken = null;

                            
                            while (null == accessToken) {
                                modeloPanama.addElement("Abra la siguiente URL y permitir el acceso a su cuenta :");
                                modeloPanama.addElement(requestToken.getAuthorizationURL());
                                System.out.println("Abra la siguiente URL y permitir el acceso a su cuenta :");
                                System.out.println(requestToken.getAuthorizationURL());
                                
                                modeloPanama.addElement("Introduzca el Codigo ( si está disponible ) y pulsa aceptar después de que le concede el acceso [ PIN ] .");
                                System.out.print("Introduzca el Codigo ( si está disponible ) y pulsa aceptar después de que le concede el acceso [ PIN ] .");
                                String pin = txtCodigoPanama.getText();
                                modeloPanama.addElement(pin);
                                
                                    if (pin.length() > 0) {
                                        accessToken = twitterPanama.getOAuthAccessToken(requestToken, pin);
                                    } else {
                                        accessToken = twitterPanama.getOAuthAccessToken(requestToken);
                                    }
                            }
                            modeloPanama.addElement("Tienes token de acceso .");
                            modeloPanama.addElement("Access token: " + accessToken.getToken());
                            modeloPanama.addElement("Access token secret: " + accessToken.getTokenSecret());
                            System.out.println("Tienes token de acceso .");
                            System.out.println("Access token: " + accessToken.getToken());
                            System.out.println("Access token secret: " + accessToken.getTokenSecret());
                } catch (TwitterException te) {
                      if (401 == te.getStatusCode()) {
                            modeloPanama.addElement("No se puede obtener el token de acceso .");
                            System.out.println("No se puede obtener el token de acceso .");
                     } else {
                          modeloPanama.addElement("ERROR "+te.getMessage());
                          System.out.println("ERROR "+te.getMessage());
                     }          
                } catch (IllegalStateException ie) {
              if (!twitterPanama.getAuthorization().isEnabled()) {
                   modeloPanama.addElement("OAuth consumer key/secret no esta establico.");
                   System.out.println("OAuth consumer key/secret no esta establico.");                               
             }
           }
}//GEN-LAST:event_btnAceptarPanamaActionPerformed

private void btnIniciarMirandaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarMirandaActionPerformed
new Thread (new HiloMiranda()).start();
}//GEN-LAST:event_btnIniciarMirandaActionPerformed

private void btnIniciarMeridaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarMeridaActionPerformed
new Thread (new HiloMerida()).start();
}//GEN-LAST:event_btnIniciarMeridaActionPerformed

private void btnIniciarCaraboboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarCaraboboActionPerformed
new Thread (new HiloCarabobo()).start();
}//GEN-LAST:event_btnIniciarCaraboboActionPerformed

private void btnIniciarZuliaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarZuliaActionPerformed
new Thread (new HiloZulia()).start();
}//GEN-LAST:event_btnIniciarZuliaActionPerformed

private void btnIniciarPanamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarPanamaActionPerformed
new Thread (new HiloPanama()).start();
}//GEN-LAST:event_btnIniciarPanamaActionPerformed

    private void btnIniciarSesionChileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarSesionChileActionPerformed
        File file = new File("/app/sistemas/config.properties");
        Properties prop = new Properties();
        InputStream is = null;
        OutputStream os = null;
        try{
            if (file.exists()) {
                is = new FileInputStream(file);
                prop.load(is);

            }
        
            modeloChile = new DefaultListModel();
            listaChile.setModel(modeloChile);
            
            twitterChile = new TwitterFactory().getInstance();
            modeloChile.addElement("oauth.consumerKey: "+prop.getProperty("oauth.consumerKey"));
            modeloChile.addElement("oauth.consumerSecret: "+prop.getProperty("oauth.consumerSecret"));
            System.out.println("oauth.consumerKey: "+prop.getProperty("oauth.consumerKey"));
            System.out.println("oauth.consumerSecret: "+prop.getProperty("oauth.consumerSecret"));
            twitterChile.setOAuthConsumer(prop.getProperty("oauth.consumerKey"), prop.getProperty("oauth.consumerSecret"));
            requestToken = twitterChile.getOAuthRequestToken();	
            Desktop.getDesktop().browse(new URI(requestToken.getAuthorizationURL()));
	   
            
        } catch (Exception te) {
            System.out.println("ERROR: " + te.getMessage());
            modeloChile.addElement("ERROR: " + te.getMessage());
            
        }
    }//GEN-LAST:event_btnIniciarSesionChileActionPerformed

    private void btnAceptarChileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarChileActionPerformed
        // TODO add your handling code here:
        try {
                            // get request token.
                            // this will throw IllegalStateException if access token is already available
                            modeloChile.addElement("Token de solicitud conseguido");
                            modeloChile.addElement("Request token: " + requestToken.getToken());
                            modeloChile.addElement("Request token secret: " + requestToken.getTokenSecret());
                            
                            
                            System.out.println("Token de solicitud conseguido");
                            System.out.println("Request token: " + requestToken.getToken());
                            System.out.println("Request token secret: " + requestToken.getTokenSecret());
                            AccessToken accessToken = null;

                            
                            while (null == accessToken) {
                                modeloChile.addElement("Abra la siguiente URL y permitir el acceso a su cuenta :");
                                modeloChile.addElement(requestToken.getAuthorizationURL());
                                System.out.println("Abra la siguiente URL y permitir el acceso a su cuenta :");
                                System.out.println(requestToken.getAuthorizationURL());
                                
                                modeloChile.addElement("Introduzca el Codigo ( si está disponible ) y pulsa aceptar después de que le concede el acceso [ PIN ] .");
                                System.out.print("Introduzca el Codigo ( si está disponible ) y pulsa aceptar después de que le concede el acceso [ PIN ] .");
                                String pin = txtCodigoChile.getText();
                                modeloChile.addElement(pin);
                                
                                    if (pin.length() > 0) {
                                        accessToken = twitterChile.getOAuthAccessToken(requestToken, pin);
                                    } else {
                                        accessToken = twitterChile.getOAuthAccessToken(requestToken);
                                    }
                            }
                            modeloChile.addElement("Tienes token de acceso .");
                            modeloChile.addElement("Access token: " + accessToken.getToken());
                            modeloChile.addElement("Access token secret: " + accessToken.getTokenSecret());
                            System.out.println("Tienes token de acceso .");
                            System.out.println("Access token: " + accessToken.getToken());
                            System.out.println("Access token secret: " + accessToken.getTokenSecret());
                } catch (TwitterException te) {
                      if (401 == te.getStatusCode()) {
                            modeloChile.addElement("No se puede obtener el token de acceso .");
                            System.out.println("No se puede obtener el token de acceso .");
                     } else {
                          modeloChile.addElement("ERROR "+te.getMessage());
                          System.out.println("ERROR "+te.getMessage());
                     }          
                } catch (IllegalStateException ie) {
              if (!twitterChile.getAuthorization().isEnabled()) {
                   modeloChile.addElement("OAuth consumer key/secret no esta establico.");
                   System.out.println("OAuth consumer key/secret no esta establico.");                               
             }
           }
    }//GEN-LAST:event_btnAceptarChileActionPerformed

    private void btnIniciarChileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarChileActionPerformed
        new Thread (new HiloChile()).start();
    }//GEN-LAST:event_btnIniciarChileActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new Principal().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptarCarabobo;
    private javax.swing.JButton btnAceptarCaracas;
    private javax.swing.JButton btnAceptarChile;
    private javax.swing.JButton btnAceptarLara;
    private javax.swing.JButton btnAceptarMerida;
    private javax.swing.JButton btnAceptarMiranda;
    private javax.swing.JButton btnAceptarPanama;
    private javax.swing.JButton btnAceptarZulia;
    private javax.swing.JButton btnIniciarCarabobo;
    private javax.swing.JButton btnIniciarCaracas;
    private javax.swing.JButton btnIniciarChile;
    private javax.swing.JButton btnIniciarLara;
    private javax.swing.JButton btnIniciarMerida;
    private javax.swing.JButton btnIniciarMiranda;
    private javax.swing.JButton btnIniciarPanama;
    private javax.swing.JButton btnIniciarSesionCarabobo;
    private javax.swing.JButton btnIniciarSesionCaracas;
    private javax.swing.JButton btnIniciarSesionChile;
    private javax.swing.JButton btnIniciarSesionLara;
    private javax.swing.JButton btnIniciarSesionMerida;
    private javax.swing.JButton btnIniciarSesionMiranda;
    private javax.swing.JButton btnIniciarSesionPanama;
    private javax.swing.JButton btnIniciarSesionZulia;
    private javax.swing.JButton btnIniciarZulia;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JList listaCarabobo;
    private javax.swing.JList listaCaracas;
    private javax.swing.JList listaChile;
    private javax.swing.JList listaLara;
    private javax.swing.JList listaMerida;
    private javax.swing.JList listaMiranda;
    private javax.swing.JList listaPanama;
    private javax.swing.JList listaZulia;
    private javax.swing.JTextField txtCodigoCarabobo;
    private javax.swing.JTextField txtCodigoCaracas;
    private javax.swing.JTextField txtCodigoChile;
    private javax.swing.JTextField txtCodigoLara;
    private javax.swing.JTextField txtCodigoMerida;
    private javax.swing.JTextField txtCodigoMiranda;
    private javax.swing.JTextField txtCodigoPanama;
    private javax.swing.JTextField txtCodigoZulia;
    // End of variables declaration//GEN-END:variables
}
