package net.enovea.pokemon;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.sql.SQLException;

//@SpringBootApplication
public class BackPokemonApplication {

    public static final ObjectMapper MAPPER = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    public static void main(String[] args) throws Exception {
//		SpringApplication.run(BackPokemonApplication.class, args);

        PokemonAPIExternal pokemonAPIExternal = new PokemonAPIExternal();
        PokemonRepository pokemonRepository = new PokemonRepository();

        pokemonRepository.connectBDD();
        pokemonRepository.deleteTable();

        try {
            var generations = pokemonAPIExternal.getGenerations();
            var pokemons = pokemonAPIExternal.getPokemons();

            generations.forEach( generation -> {
                try {
                    pokemonRepository.insertGeneration(generation);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
            pokemons.forEach( pokemon -> {
                try {
                    pokemonRepository.insertPokemon(pokemon);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
