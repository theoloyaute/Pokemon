package net.enovea.pokemon.Objects;

import lombok.Data;

import java.util.List;

@Data
public class ListGenerations {
    private Number count;
    private List<Results> results;
}
