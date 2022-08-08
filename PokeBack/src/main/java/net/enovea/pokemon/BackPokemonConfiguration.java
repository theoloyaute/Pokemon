package net.enovea.pokemon;

import org.springframework.context.annotation.Bean;

public class BackPokemonConfiguration {
    @Bean
    public PokemonControler pokemonController(PokemonAPIExternal pokemonAPIExternal) {
        return new PokemonControler();
    }

    public PokemonAPIExternal pokemonAPI() {
        return new PokemonAPIExternal();
    }

    public PokemonRepository pokemonRepository() {
        return new PokemonRepository();
    }
}
