package net.enovea.pokemon;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.enovea.pokemon.Objects.FormPokemons;
import net.enovea.pokemon.Objects.ListPokemon;
import net.enovea.pokemon.Objects.Results;

import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

import static net.enovea.pokemon.PokemonRepository.*;


//@SpringBootApplication
public class BackPokemonApplication {

    public static final ObjectMapper MAPPER = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    public static void main(String[] args) throws Exception {
        PokemonAPIExternal pokemonAPIExternal = new PokemonAPIExternal();
        PokemonRepository pokemonRepository = new PokemonRepository();
//		SpringApplication.run(BackPokemonApplication.class, args);

        deleteTable();
        createTable();

        // Connect to API
        try {
            PokemonAPIExternal.connectAPI();
            insertPokemon(pokemons);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
