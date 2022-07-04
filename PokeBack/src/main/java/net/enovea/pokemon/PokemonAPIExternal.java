package net.enovea.pokemon;

import net.enovea.pokemon.Objects.*;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import static net.enovea.pokemon.BackPokemonApplication.MAPPER;

public class PokemonAPIExternal implements PokemonAPI {

    List<Results> resultsPokemons = new ArrayList<>();
    List<FormPokemons> formPokemons = new ArrayList<>();
    List<Results> resultsGenerations = new ArrayList<>();
    List<FormGenerations> formGenerations = new ArrayList<>();


    public URL connectAPItoPokemon() throws IOException {
        URL url1 = new URL("https://pokeapi.co/api/v2/pokemon-form?limit=15");
        HttpURLConnection connection = (HttpURLConnection) url1.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();

        int responseCode = connection.getResponseCode();

        if (responseCode != 200) {
            throw new RuntimeException("HttpResponseCode " + responseCode);
        } else {
            return url1;
        }
    }

    public List<Results> getResultsPokemons(URL url1) throws IOException {

        StringBuilder builder = new StringBuilder();
        Scanner scanner = new Scanner(url1.openStream());

        while (scanner.hasNext()) {
            builder.append(scanner.nextLine());
        }
        scanner.close();

        ListPokemons pokemons = MAPPER.readValue(builder.toString(), ListPokemons.class);

        resultsPokemons.addAll(pokemons.getResults());
        return resultsPokemons;
    }

    public List<FormPokemons> getFormPokemons() throws IOException {

        resultsPokemons.parallelStream().map(results -> {
            try {
                return MAPPER.readValue(new URL(results.getUrl()).openStream(), FormPokemons.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return resultsPokemons;
        }).forEach(formPokemons::add);

        return formPokemons;
    }

    public URL connectAPItoGeneration() throws IOException {
        URL url2 = new URL("https://pokeapi.co/api/v2/generation");
        HttpURLConnection connection = (HttpURLConnection) url2.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();

        int responseCode = connection.getResponseCode();

        if (responseCode != 200) {
            throw new RuntimeException("HttpResponseCode " + responseCode);
        } else {
            return url2;
        }
    }

    public List<Results> getResultsGenerations(URL url2) throws IOException {

        StringBuilder builder = new StringBuilder();
        Scanner scanner = new Scanner(url2.openStream());

        while (scanner.hasNext()) {
            builder.append(scanner.nextLine());
        }
        scanner.close();

        ListGenerations generations = MAPPER.readValue(builder.toString(), ListGenerations.class);

        resultsGenerations.addAll(generations.getResults());
        return resultsGenerations;
    }

    public List<FormGenerations> getFormGenerations() throws IOException {

        resultsGenerations.parallelStream().map(results -> {
            try {
                return MAPPER.readValue(new URL(results.getUrl()).openStream(), FormGenerations.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return resultsGenerations;
        }).forEach(System.out::println);

        return formGenerations;
    }

//    public List<Pokemon> setPokemon(List<FormPokemons> formPokemons, List<FormGenerations> formGenerations) {
//        List<Pokemon> pokemons = new ArrayList<>();
//        formPokemons.forEach(form -> {
//            pokemons.add(new Pokemon(form.getId(),
//                    form.getName(),
//                    form.getSprites().getFront_default(),
//                    form.getTypes()[0].getType().getName(),
//                    (form.getTypes().length > 1 ? form.getTypes()[1].getType().getName() : ""),));
//        });
//        return pokemons;
//    }

    @Override
    public List<Pokemon> getPokemons() {
        return null;
    }

    @Override
    public List<Generation> getGenerations() {
        return null;
    }


    public Pokemon findPokemon(FormPokemons formPokemons, List<FormGenerations> formGenerations) {
        for (FormGenerations generations : formGenerations){
            for (Results pokemon_species : generations.getPokemon_species()) {
                if (pokemon_species.getName().equals(formPokemons.getName())) {
                    formPokemons.setGenerationId(generations.getId());
                }
            }
        }

        return new Pokemon(formPokemons.getId(),
                formPokemons.getName(),
                formPokemons.getSprites().getFront_default(),
                formPokemons.getTypes()[0].getType().getName(),
                (formPokemons.getTypes().length > 1 ? formPokemons.getTypes()[1].getType().getName() : ""),
                formPokemons.getGenerationId());
    }
}
