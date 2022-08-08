package net.enovea.pokemon;

import lombok.Data;
import net.enovea.pokemon.Objects.ListTypes;

import java.util.HexFormat;
import java.util.List;

@Data
public class Pokemon {
    private int id;
    private String name;
    private ListTypes[] types;
    private String picture;
    private String nickname;
    private Integer generationId;

    public Pokemon(int id, String name, ListTypes[] types, String picture, String nickname, Integer generationId) {
        this.id = id;
        this.name = name;
        this.types = types;
        this.picture = picture;
        this.nickname = nickname;
        this.generationId = generationId;
    }
}
