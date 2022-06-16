package net.enovea.pokemon;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Objects;

class BackPokemonApplicationTests {

	@Test
	void contextLoads() {
		Padawan toto= null;

		if(Objects.equals(Padawan.MARTIN,toto))
			System.out.println("ce n'est qu'un handballeur");
	}

	public enum Padawan {
		DOUDOU, MARTIN
	}

}
