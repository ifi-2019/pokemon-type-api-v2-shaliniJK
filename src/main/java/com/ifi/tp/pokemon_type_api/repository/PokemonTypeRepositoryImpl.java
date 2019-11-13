package com.ifi.tp.pokemon_type_api.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ifi.tp.pokemon_type_api.bo.PokemonType;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class PokemonTypeRepositoryImpl implements PokemonTypeRepository {

    private List<PokemonType> pokemons;

    public PokemonTypeRepositoryImpl() {
        try {
            var pokemonsStream = new ClassPathResource("pokemons.json").getInputStream();

            var objectMapper = new ObjectMapper();
            var pokemonsArray = objectMapper.readValue(pokemonsStream, PokemonType[].class);
            this.pokemons = Arrays.asList(pokemonsArray);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public PokemonType findPokemonTypeById(int id) {
        System.out.println("Loading Pokemon information for Pokemon id " + id);

        int i = 0;
        boolean found = false;
        PokemonType pokemonType = null;

        while (! found && i < this.pokemons.size()) {
            pokemonType = this.pokemons.get(i);

            if (pokemonType.getId() == id) {
                found = true;
            }
            i++;
        }

        return pokemonType;
    }

    @Override
    public PokemonType findPokemonTypeByName(String name) {
        System.out.println("Loading Pokemon information for Pokemon name " + name);

        int i = 0;
        boolean found = false;
        PokemonType pokemonType = null;

        while (! found && i < this.pokemons.size()) {
            pokemonType = this.pokemons.get(i);

            if (name.equals(pokemonType.getName())) {
                found = true;
            }
            i++;
        }

        return pokemonType;
    }

    @Override
    public List<PokemonType> findAllPokemonType() {

        return this.pokemons;
    }

    @Override
    public List<PokemonType> findPokemonTypeByTypes(String types) {
        List<PokemonType> result = new ArrayList<>();

        for (PokemonType p : this.pokemons) {
            List<String> pokemonTypes = p.getTypes();

            if (pokemonTypes.containsAll(Arrays.asList(types.split(",")))) {
                result.add(p);
            }
        }

        return result;
    }

}
