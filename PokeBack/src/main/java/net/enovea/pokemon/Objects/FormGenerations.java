package net.enovea.pokemon.Objects;

import lombok.Data;

@Data
public class FormGenerations {
    private Number id;
    private Results main_regions;
    private String name;
    private Results[] pokemon_species;
}
