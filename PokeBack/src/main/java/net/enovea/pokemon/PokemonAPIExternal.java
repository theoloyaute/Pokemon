package net.enovea.pokemon;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class PokemonAPIExternal {

    public static void connectAPI() throws IOException {
        URL url = new URL("https://pokeapi.co/api/v2/pokemon-form?limit=898");

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();

        int responseCode = connection.getResponseCode();
    }
}
