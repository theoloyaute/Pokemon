package net.enovea.pokemon;

import lombok.Data;

@Data
public class Pokemon {
    private Number id;
    private String name;
    private String type1;
    private String type2;
    private String picture;
    private String nickname;
    private Number generationId;

    public Pokemon(Number id, String name, String front_default, String name1, String s, Number generationId) {
        this.id = id;
        this.name = name;
        this.picture = front_default;
        this.type1 = name1;
        this.type2 = s;
        this.generationId = generationId;
    }
}
