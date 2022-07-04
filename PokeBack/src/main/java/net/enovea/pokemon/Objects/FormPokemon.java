package net.enovea.pokemon.Objects;

import lombok.Data;

@Data
public class FormPokemon {
    private int id;
    private String name;
    private Sprites sprites;
    private ListTypes[] types;
}
