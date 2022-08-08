package net.enovea.pokemon;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@RestController
public class PokemonControler {
    PokemonAPIExternal pokemonAPIExternal = new PokemonAPIExternal();
    PokemonRepository pokemonRepository = new PokemonRepository();

    @GetMapping("/init")
    PokemonRepository connectToBdd() throws SQLException {
        pokemonRepository.connectBDD();
        return null;
    }

    @GetMapping("/pokemons")
    List<Pokemon> getPokemons() throws IOException {
        return pokemonAPIExternal.getPokemons();
    }

    @GetMapping("/generations")
    List<Generation> getGenerations() throws IOException {
        return pokemonAPIExternal.getGenerations();
    }


}
