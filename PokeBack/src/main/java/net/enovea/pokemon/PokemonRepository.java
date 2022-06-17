package net.enovea.pokemon;

import net.enovea.pokemon.Objects.FormPokemons;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class PokemonRepository {

    private static String url = "jdbc:mysql://localhost:3306/pokedb";
    private static Connection connection;
    static Statement command;

    public static void connectBDD() throws SQLException {

        connection = DriverManager.getConnection(url, "root", "");
        command = connection.createStatement();
    }

    public static void deleteTable() throws SQLException {

        connectBDD();
        command.executeUpdate("DELETE FROM pokemons");
    }

    public static void createTable() throws SQLException {

        connectBDD();
        command.executeUpdate("CREATE TABLE IF NOT EXISTS pokemons (id INT NOT NULL, name VARCHAR(255) NOT NULL, type1 VARCHAR(255) NOT NULL, type2 VARCHAR(255) NOT NULL, picture VARCHAR(255) NULL, nickname VARCHAR(255) NULL, PRIMARY KEY (id))");
    }

    public static void insertPokemon(FormPokemons formPokemon) throws SQLException {

        connectBDD();
        command.executeUpdate("INSERT INTO pokemons (id, name, type1, type2, picture, nickname) " +
                "VALUES (" + formPokemon.getId() + ", " +
                "'" + formPokemon.getName() + "', " +
                "'" + formPokemon.getTypes()[0].getType().getName() + "', " +
                "'" + (formPokemon.getTypes().length > 1 ? formPokemon.getTypes()[1].getType().getName() : "") + "', " +
                "'" + formPokemon.getSprites() + "', " +
                "'" + formPokemon.getName() + "')" +
                "");
    }
}
