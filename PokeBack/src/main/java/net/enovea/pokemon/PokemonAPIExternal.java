package net.enovea.pokemon;

import net.enovea.pokemon.Objects.*;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

import static net.enovea.pokemon.BackPokemonApplication.MAPPER;

public class PokemonAPIExternal implements PokemonAPI {

    private <T> T retrieveFromURl(URL url, Class<T> returnType) throws IOException {
        StringBuilder builder = new StringBuilder();
        Scanner scanner = new Scanner(url.openStream());

        while (scanner.hasNext()) {
            builder.append(scanner.nextLine());
        }
        scanner.close();

        return MAPPER.readValue(builder.toString(), returnType);
    }

    private URL connectAPItoPokemon() throws IOException {
        URL url = new URL("https://pokeapi.co/api/v2/pokemon-form?limit=28");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();

        int responseCode = connection.getResponseCode();

        if (responseCode != 200) {
            throw new RuntimeException("HttpResponseCode " + responseCode);
        } else {
            return url;
        }
    }

    private List<Results> getPokemonsUrl(URL url) throws IOException {
        return retrieveFromURl(url, ListPokemons.class).getResults();
    }

    private FormPokemon getFormPokemon(URL url) throws IOException {
        return retrieveFromURl(url, FormPokemon.class);
    }

    @Override
    public List<Pokemon> getPokemons() throws IOException {
        var url = connectAPItoPokemon();
        var pokemonsGeneration = getPokemonsGeneration(Collections.emptyList());
        return getPokemonsUrl(url)
                .parallelStream()
                .map(pokemonURL -> {
                    try {
                        var formPokemon = getFormPokemon(new URL(pokemonURL.getUrl()));
                        return new Pokemon(
                                formPokemon.getId(),
                                formPokemon.getName(),
                                formPokemon.getTypes(),
                                formPokemon.getSprites().getFront_default(),
                                formPokemon.getName(),
                                pokemonsGeneration.get(formPokemon.getName())
                                );
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                })
                .toList();
    }

    private URL connectAPItoGeneration() throws IOException {
        URL url = new URL("https://pokeapi.co/api/v2/generation");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();

        int responseCode = connection.getResponseCode();

        if (responseCode != 200) {
            throw new RuntimeException("HttpResponseCode " + responseCode);
        } else {
            return url;
        }
    }

    private List<Results> getGenerationsUrl(URL url) throws IOException {
        return retrieveFromURl(url, ListGenerations.class).getResults();
    }

    private FormGeneration getFormGeneration(URL url) throws IOException {
        return retrieveFromURl(url, FormGeneration.class);
    }

    @Override
    public List<Generation> getGenerations() throws IOException {
        var url = connectAPItoGeneration();
        return getGenerationsUrl(url)
                .parallelStream()
                .map(generationURL -> {
                    try {
                        var formGeneration = getFormGeneration(new URL(generationURL.getUrl()));
                        return new Generation(
                                formGeneration.getId(),
                                formGeneration.getName(),
                                formGeneration.getMain_regions().getName()
                        );
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }).toList();
    }

    private Map<String, Integer> getPokemonsGeneration(List<FormGeneration> formGenerations) {
        var pokemonsGeneration = new HashMap<String, Integer>();

//        for (FormGeneration generation : formGenerations){
//            for (Results pokemon_species : generation.getPokemon_species()) {
//               pokemonsGeneration.put(pokemon_species.getName(), generation.getId());
//            }
//        }

        formGenerations.forEach(formGeneration ->
                        formGeneration.getPokemon_species()
                                .forEach(pokemon -> pokemonsGeneration.put(pokemon.getName(), formGeneration.getId()))
                        );

        return pokemonsGeneration;
    }
}
