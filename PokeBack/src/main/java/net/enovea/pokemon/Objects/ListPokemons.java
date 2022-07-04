package net.enovea.pokemon.Objects;

import lombok.Data;

import java.util.List;

@Data
public class ListPokemons {
    private int count;
    private List<Results> results;
}
