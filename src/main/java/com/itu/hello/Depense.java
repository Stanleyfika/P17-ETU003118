package com.itu.hello; 
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Depense  {
    private int id;
    private int prevision;
    private double somme;
    private String libelle;
    private double depense;
    private double reste;

   
    public Depense() {
  
    }

    // Constructeur avec paramètres
    public Depense(int id,int prevision, double somme) {
       
        this.id=id;
        this.prevision = prevision;
        this.somme=somme;
    }

     public Depense(int prevision, double somme) {
       
        
        this.prevision = prevision;
        this.somme=somme;
    }

    public Depense(int prevision, String libelle,double depense,double reste) {
        this.prevision = prevision;
        this.libelle=libelle;
        this.depense=depense;
        this.reste=reste;
    }

     
     
    // Getters et setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getprevision() {
        return prevision;
    }

    public void setprevision(int prevision) {
        this.prevision = prevision;
    }

     public double getsomme() {
        return somme;
    }

    public void setsomme(double somme) {
        this.somme = somme;
    }

      public String getlibelle() {
        return libelle;
    }

    public void setlibelle(String libelle) {
        this.libelle = libelle;
    }

    public double getdepense() {
        return depense;
    }

    public void setdepense(double depense) {
        this.depense = depense;
    }

      public double getreste() {
        return reste;
    }

    public void setreste(double reste) {
        this.reste = reste;
    }
  
   @Override
    public String toString() {
        return "Depense{" +
                "id=" + id +
                ", prevision='" + prevision + '\'' +
                '}';
    }

 

    public void save() {
        String sql = "insert into Depense(idPrevision,somme) values(?,?)";
        String sqlUpdate="update Prevision set montant=montant-(?) where id=?";

        Connexion co=new Connexion();
        
        try (Connection conn = co.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             PreparedStatement stmt1=conn.prepareStatement(sqlUpdate)) {
            
            stmt.setInt(1,this.getprevision());
            stmt.setDouble(2, this.getsomme());

             
            stmt1.setDouble(1,this.getsomme());
            stmt1.setInt(2,this.getprevision());
      
        
        
            
            stmt.executeUpdate();
            stmt1.executeUpdate();

            
      
            
        } catch (SQLException e) {
            e.printStackTrace();
          
        }
    }
  
   public static List<Depense> findAll() {
        String sql = "select * from Depense";
        List<Depense> DepenseList = new ArrayList<>();
        Connexion co = new Connexion();

        try (Connection connection = co.getConnection();
                Statement stmt1 = connection.createStatement();) {

            try (ResultSet resultSet1 = stmt1.executeQuery(sql)) {

                while (resultSet1.next()) {
                    int id = resultSet1.getInt("id");
                    int prevision=resultSet1.getInt("idPrevision");
                    double somme = resultSet1.getDouble("somme");

                    Depense Depense = new Depense(id,prevision,somme);
                    DepenseList.add(Depense);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return DepenseList;
    }

       public static List<Depense> Etat() {
        String sql = "select Prevision.id,libelle,sum(somme) as depense,(montant-sum(somme)) as reste from Prevision join Depense on Prevision.id=Depense.Idprevision group by Prevision.id";
        List<Depense> EtatList = new ArrayList<>();
        Connexion co = new Connexion();

        try (Connection connection = co.getConnection();
                Statement stmt1 = connection.createStatement();) {

            try (ResultSet resultSet1 = stmt1.executeQuery(sql)) {

                while (resultSet1.next()) {
                    int id = resultSet1.getInt("id");
                    String prevision=resultSet1.getString("libelle");
                    double depense = resultSet1.getDouble("depense");
                    double reste = resultSet1.getDouble("reste");
                    Depense Depense = new Depense(id,prevision,depense,reste);
                    EtatList.add(Depense);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return EtatList;
    }



}