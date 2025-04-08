package com.itu.hello; 
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Prevision  {
    
    
    private int id;
    private String libelle;
    private double montant;

   
    public Prevision() {
  
    }

    // Constructeur avec paramètres
    public Prevision(int id,String libelle, double montant) {
       
        this.id=id;
        this.libelle = libelle;
        this.montant=montant;
    }

     public Prevision(String libelle, double montant) {
       
        
        this.libelle = libelle;
        this.montant=montant;
    }
     
    // Getters et setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

     public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    
    @Override
    public String toString() {
        return "Prevision{" +
                "id=" + id +
                ", libelle='" + libelle + '\'' +
                '}';
    }

 

    public void save() {
        String sql = "insert into Prevision(libelle,montant) values(?,?)";

        Connexion co=new Connexion();
        
        try (Connection conn = co.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1,this.getLibelle());
            stmt.setDouble(2, this.getMontant());
        
            
            stmt.executeUpdate();
      
            
        } catch (SQLException e) {
            e.printStackTrace();
          
        }
    }
  
   public static List<Prevision> findAll() {
        String sql = "select * from Prevision";
        List<Prevision> PrevisionList = new ArrayList<>();
        Connexion co = new Connexion();

        try (Connection connection = co.getConnection();
                Statement stmt1 = connection.createStatement();) {

            try (ResultSet resultSet1 = stmt1.executeQuery(sql)) {

                while (resultSet1.next()) {
                    int id = resultSet1.getInt("id");
                    String libelle=resultSet1.getString("libelle");
                    double montant = resultSet1.getDouble("montant");

                    Prevision Prevision = new Prevision(id,libelle,montant);
                    PrevisionList.add(Prevision);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return PrevisionList;
    }

    public static boolean check(double somme,int idprevision){
   String sql = "select montant from Prevision where id='"+idprevision+"'";
       
        Connexion co = new Connexion();

        try (Connection connection = co.getConnection();
                Statement stmt1 = connection.createStatement();) {

            try (ResultSet resultSet1 = stmt1.executeQuery(sql)) {

                while (resultSet1.next()) {
                    double check = resultSet1.getDouble("montant");
                
                if(check-somme<0){
                    return false;
                }else{
                    return true;
                }
            
                  
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;

    }


}