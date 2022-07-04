package net.enovea.pokemon;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URL;

//@SpringBootApplication
public class BackPokemonApplication {

    public static final ObjectMapper MAPPER = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    public static void main(String[] args) throws Exception {
//		SpringApplication.run(BackPokemonApplication.class, args);

        PokemonAPIExternal pokemonAPIExternal = new PokemonAPIExternal();
        PokemonRepository pokemonRepository = new PokemonRepository();

//        pokemonRepository.deleteTable();
//        pokemonRepository.createTable();

//        // Connect to API
        try {
//            var pokemons = pokemonAPIExternal.getPokemons();
//            var generations = pokemonAPIExternal.getGenerations();
//
//            pokemonRepository.insertPokemon(pokemons);
//            pokemonRepository.insertGeneration(generations);
            URL url1 = pokemonAPIExternal.connectAPItoPokemon();
            URL url2 = pokemonAPIExternal.connectAPItoGeneration();
            pokemonAPIExternal.getResultsPokemons(url1);
//            pokemonAPIExternal.getFormPokemons();
            pokemonAPIExternal.getResultsGenerations(url2);
            pokemonAPIExternal.getFormGenerations();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
