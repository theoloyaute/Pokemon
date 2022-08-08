package net.enovea.pokemon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class PokemonRepository {

    private static String url = "jdbc:mysql://localhost:3306/pokedb";
    private static Connection connection;
    static Statement command;

    public void connectBDD() throws SQLException {
        connection = DriverManager.getConnection(url, "root", "");
        command = connection.createStatement();
    }

    public void deleteTable() throws SQLException {
        command.executeUpdate("DELETE FROM pokemons; ");
        command.executeUpdate("DELETE FROM generations; ");
    }

    public void insertPokemon(Pokemon pokemon) throws SQLException {
        command.executeUpdate("INSERT INTO pokemons (id, name, type1, type2, picture, nickname, generation_id) " +
                " VALUES ('" + pokemon.getId() + "', " +
                "'" + pokemon.getName() + "', " +
                "'" + pokemon.getTypes()[0].getType().getName() + "', " +
                "'" + (pokemon.getTypes().length > 1 ? pokemon.getTypes()[1].getType().getName() : "") + "', " +
                "'" + pokemon.getPicture() + "', " +
                "'" + pokemon.getNickname() + "', " +
                "'" + pokemon.getGenerationId() + "' " +
                "); ");
    }

    public void insertGeneration(Generation generation) throws SQLException {
        command.executeUpdate(" INSERT INTO generations (id, name, region) " +
                " VALUES ('" + generation.getId() + "', " +
                "'" + generation.getName() + "', " +
                "'" + generation.getRegion() + "' " +
                "); ");
    }
}
