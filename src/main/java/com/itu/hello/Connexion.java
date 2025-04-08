package com.itu.hello; 

import java.sql.*;

public class Connexion {


    private static final String URL = "jdbc:mysql://172.80.237.53:3306/db_s2_ETU003118";
    private static final String UTILISATEUR = "ETU003118";
    private static final String MOT_DE_PASSE = "b6dQfPPb";

    private static Connection connection = null;

    // Méthode pour obtenir la connexion
    public static Connection getConnection() {
        try {
          
            if (connection == null || connection.isClosed()) {
                
                Class.forName("com.mysql.cj.jdbc.Driver");

            
                connection = DriverManager.getConnection(URL, UTILISATEUR, MOT_DE_PASSE);
                System.out.println("Connexion réussie à la base de données MySQL.");
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Le driver MySQL est introuvable.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Erreur lors de la connexion à la base de données.");
            e.printStackTrace();
        }
        return connection;
    }

    // fermer la connexion
    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Connexion fermée.");
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la fermeture de la connexion.");
            e.printStackTrace();
        }
    }

  
}
