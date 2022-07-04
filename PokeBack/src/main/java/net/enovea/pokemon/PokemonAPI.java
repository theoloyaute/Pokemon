package net.enovea.pokemon;

import java.io.IOException;
import java.util.List;

public interface PokemonAPI {
    List<Pokemon> getPokemons() throws IOException;
    List<Generation> getGenerations();
}
