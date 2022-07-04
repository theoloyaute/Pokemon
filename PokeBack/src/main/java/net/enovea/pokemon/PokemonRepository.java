package net.enovea.pokemon;

import net.enovea.pokemon.Objects.FormGenerations;
import net.enovea.pokemon.Objects.FormPokemons;
import net.enovea.pokemon.Objects.Results;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class PokemonRepository {

    private static String url = "jdbc:mysql://localhost:3306/pokedb";
    private static Connection connection;
    static Statement command;

    public static void connectBDD() throws SQLException {

        connection = DriverManager.getConnection(url, "root", "");
        command = connection.createStatement();
    }

    public void deleteTable() throws SQLException {

        connectBDD();
        command.executeUpdate("DROP TABLE pokemons, generations");
    }

    public void createTable() throws SQLException {

        connectBDD();
        command.executeUpdate("CREATE TABLE IF NOT EXISTS pokemons (id INT NOT NULL, name VARCHAR(255) NOT NULL, type1 VARCHAR(255) NOT NULL, type2 VARCHAR(255) NULL, picture VARCHAR(255) NULL, nickname VARCHAR(255) NULL, PRIMARY KEY (id))");
        command.executeUpdate("CREATE TABLE IF NOT EXISTS generations (id INT PRIMARY KEY NULL ,name VARCHAR(255) NULL)");
    }

    public void insertPokemon(List<Pokemon> pokemon) throws SQLException {

        connectBDD();
//        command.executeUpdate(" INSERT INTO pokemons (id, name, type1, type2, picture, nickname) " +
//                " VALUES ('" + pokemon.getId() + "', " +
//                "'" + pokemon.getName() + "', " +
//                "'" + pokemon.getTypes()[0].getType().getName() + "', " +
//                "'" + (pokemon.getTypes().length > 1 ? pokemon.getTypes()[1].getType().getName() : "") + "', " +
//                "'" + pokemon.getSprites().getFront_default() + "', " +
//                "'" + pokemon.getName() + "')" +
//                "");
    }

    public void insertGeneration(List<Generation> generations) {

    }

//    public void insertGeneration(Results results, FormGenerations formGenerations) throws SQLException {
//
//        connectBDD();
//        command.executeUpdate("INSERT INTO generations (id, name) VALUES ('" + formGenerations.getId() + "', '" + results.getName() + "')");
//    }
}
