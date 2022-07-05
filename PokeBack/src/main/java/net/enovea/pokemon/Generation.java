package net.enovea.pokemon;

import lombok.Data;

import java.util.List;

@Data
public class Generation {
    private final int id;
    private final String name;
    private final String region;
    private final List<String> pokemonsName;

    public Generation(int id, String name, String region, List<String> pokemonsName){
        this.id = id;
        this.name = name;
        this.region = region;
        this.pokemonsName = pokemonsName;
    }
}
