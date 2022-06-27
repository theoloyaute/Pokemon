package net.enovea.pokemon;

import ch.qos.logback.classic.html.UrlCssBuilder;
import net.enovea.pokemon.Objects.FormPokemons;
import net.enovea.pokemon.Objects.ListPokemon;
import net.enovea.pokemon.Objects.Results;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import static net.enovea.pokemon.BackPokemonApplication.MAPPER;

public class PokemonAPIExternal {

    public static void connectAPI() throws IOException {

        URL url = new URL("https://pokeapi.co/api/v2/pokemon-form?limit=898");

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();

        int responseCode = connection.getResponseCode();

        if (responseCode != 200) {
            throw new RuntimeException("HttpResponseCode " + responseCode);
        } else {
            StringBuilder result = new StringBuilder();
            Scanner scanner = new Scanner(url.openStream());

            while (scanner.hasNext()) {
                result.append(scanner.nextLine());
            }
            scanner.close();

            ListPokemon dataObject = MAPPER.readValue(result.toString(), ListPokemon.class);

            ArrayList<Results> resultsList = new ArrayList<>();
            for (Results result1 : dataObject.getResults()) {
                resultsList.add(result1);
            }
            APIForm(result1);
        }
    }

    public static void APIForm() throws IOException {

        URL urlForm = new URL(result1.getUrl());
        HttpURLConnection connectionForm = (HttpURLConnection) urlForm.openConnection();
        connectionForm.setRequestMethod("GET");
        connectionForm.connect();

        int responseCode1 = connectionForm.getResponseCode();

        if (responseCode1 != 200) {
            throw new RuntimeException("HttpResponseCode " + responseCode1);
        } else {
            StringBuilder formBuilder = new StringBuilder();
            Scanner scanner1 = new Scanner(urlForm.openStream());

            while (scanner1.hasNext()) {
                formBuilder.append(scanner1.nextLine());
            }
            scanner1.close();


            FormPokemons pokemons = MAPPER.readValue(formBuilder.toString(), FormPokemons.class);

            ArrayList<FormPokemons> formList = new ArrayList<>();
            formList.add(pokemons);

            System.out.println(pokemons.getName());
        }
    }
}
