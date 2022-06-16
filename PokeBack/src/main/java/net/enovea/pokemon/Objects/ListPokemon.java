package net.enovea.pokemon.Objects;

import lombok.Data;

@Data
public class ListPokemon {
    private Number count;
    private String next;
    private String previous;
    private Results[] results;
}
