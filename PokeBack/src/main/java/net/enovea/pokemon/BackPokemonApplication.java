package net.enovea.pokemon;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URL;
import java.sql.SQLException;

//@SpringBootApplication
public class BackPokemonApplication {

    public static final ObjectMapper MAPPER = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    public static void main(String[] args) throws Exception {
//		SpringApplication.run(BackPokemonApplication.class, args);

        PokemonAPIExternal pokemonAPIExternal = new PokemonAPIExternal();
        PokemonRepository pokemonRepository = new PokemonRepository();

        pokemonRepository.deleteTable();
        pokemonRepository.createTable();

        try {
            var pokemons = pokemonAPIExternal.getPokemons();
            var generations = pokemonAPIExternal.getGenerations();
            System.out.println(pokemonAPIExternal.getPokemons());
            pokemons.forEach( pokemon -> {
                try {
                    pokemonRepository.insertPokemon(pokemon);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });

            generations.forEach( generation -> {
                try {
                    pokemonRepository.insertGeneration(generation);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });



        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
