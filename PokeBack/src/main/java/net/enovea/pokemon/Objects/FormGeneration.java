package net.enovea.pokemon.Objects;

import lombok.Data;

import java.util.List;

@Data
public class FormGeneration {
    private int id;
    private Results main_region;
    private String name;
    private List<Results> pokemon_species;
}
