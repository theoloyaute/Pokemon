package net.enovea.pokemon.Objects;

import lombok.Data;

import java.util.ArrayList;

@Data
public class FormPokemons {
    private Number id;
    private String name;
    private Sprites sprites;
    private ListTypes[] types;
    private Number generationId;
}
