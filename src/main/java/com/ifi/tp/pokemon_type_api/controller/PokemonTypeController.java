package com.ifi.tp.pokemon_type_api.controller;

import com.ifi.tp.pokemon_type_api.bo.PokemonType;
import com.ifi.tp.pokemon_type_api.service.PokemonTypeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pokemon-types")
public class PokemonTypeController {

    PokemonTypeService pokemonTypeService;

    public PokemonTypeController(PokemonTypeService pokemonTypeService) {
        this.pokemonTypeService = pokemonTypeService;
    }


    @GetMapping("/{id}")
    PokemonType getPokemonTypeFromId(@PathVariable  int id){
        return pokemonTypeService.getPokemonType(id);
    }


    @GetMapping("/")
    public List<PokemonType> getAllPokemonTypes() {
        return pokemonTypeService.getAllPokemonTypes();
    }


    @RequestMapping(value = "/", params = "name")
    @ResponseBody
    PokemonType getPokemonTypeFromName(@RequestParam(value = "name")  String name){
        return pokemonTypeService.getPokemonType(name);
    }

    @RequestMapping(value = "/", params = "types")
    @ResponseBody
    public List<PokemonType> getPokemonTypeFromType(@RequestParam(value = "types")  String types){
        return pokemonTypeService.getPokemonTypeByTypes(types);
    }

}
