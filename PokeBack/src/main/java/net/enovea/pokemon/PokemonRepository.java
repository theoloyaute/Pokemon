package net.enovea.pokemon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class PokemonRepository {

    private static String url = "jdbc:mysql://localhost:3306/pokedb";
    private static Connection connection;
    static Statement command;

    public static void getData() {
        try {
            connection = DriverManager.getConnection(url , "root", "");
            command = connection.createStatement();
            command.executeQuery("SELECT * FROM pokemons");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
