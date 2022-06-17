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

import static net.enovea.pokemon.PokemonRepository.command;
import static net.enovea.pokemon.PokemonRepository.getData;


//@SpringBootApplication
public class BackPokemonApplication {

	private static final ObjectMapper MAPPER = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

	public static void main(String[] args) throws Exception {
//		SpringApplication.run(BackPokemonApplication.class, args);

		// Connect to database
		ge
	tData();

		// Connect to API
		try {
			URL url = new URL("https://pokeapi.co/api/v2/pokemon-form?limit=898");

			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.connect();

			int responseCode = connection.getResponseCode();

			if(responseCode != 200) {
				throw new RuntimeException("HttpResponseCode " + responseCode);
			} else {
				StringBuilder result = new StringBuilder();
				Scanner scanner = new Scanner(url.openStream());

				while(scanner.hasNext()) {
					result.append(scanner.nextLine());
				}
				scanner.close();

				ListPokemon dataObject = MAPPER.readValue(result.toString(), ListPokemon.class);

				ArrayList<Results> resultsList = new ArrayList<>();
				for(Results result1 : dataObject.getResults()) {
					resultsList.add(result1);

					// Connect to API formPokemon
					URL urlForm = new URL(result1.getUrl());
					HttpURLConnection connectionForm = (HttpURLConnection) urlForm.openConnection();
					connectionForm.setRequestMethod("GET");
					connectionForm.connect();

					int responseCode1 = connectionForm.getResponseCode();

					if (responseCode1 !=200) {
						throw new RuntimeException("HttpResponseCode " + responseCode1);
					} else {
						StringBuilder formBuilder = new StringBuilder();
						Scanner scanner1 = new Scanner(urlForm.openStream());

						while(scanner1.hasNext()) {
							formBuilder.append(scanner1.nextLine());
						}
						scanner1.close();


						FormPokemons pokemons = MAPPER.readValue(formBuilder.toString(), FormPokemons.class);

						ArrayList<FormPokemons> formList = new ArrayList<>();
						formList.add(pokemons);

						command.executeUpdate("INSERT INTO pokemons (id, name, type1, type2, picture, nickname) VALUES (" +
								"'" + pokemons.getId() + "', " +
								"'" + pokemons.getName() + "', " +
								"'" + pokemons.getTypes()[0].getType().getName() + "', " +
								"'" + (pokemons.getTypes().length > 1 ? pokemons.getTypes()[1].getType().getName() : "") + "', " +
								"'" + pokemons.getSprites().getFront_default() + "', " +
								"'" + pokemons.getName() + "')" +
								" FOR (id <= 151 INSERT INTO pokemons (generation_id) VALUES (' 1 ')");
					}
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
