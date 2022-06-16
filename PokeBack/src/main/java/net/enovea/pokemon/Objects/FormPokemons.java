package net.enovea.pokemon.Objects;

import lombok.Data;

@Data
public class FormPokemons {
    private Number id;
    private String name;
    private Number order;
    private Sprites sprites;
    private ListTypes[] types;
}
