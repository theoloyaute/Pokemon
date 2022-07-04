package net.enovea.pokemon;

import lombok.Data;

@Data
public class Generation {
    private int id;
    private String name;
    private String region;

    public Generation(int id, String name, String region){
        this.id = id;
        this.name = name;
        this.region = region;
    }
}
