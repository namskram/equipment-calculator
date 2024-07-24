package namskram.classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Specialist extends CharacterType {

    private String name;
    private Map<String, Double> weights;
    private Map<String, Double> avgRolls;
    private List<String> characters;
    private String element;
    private String path;

    public Specialist(String name) {
        this.name = name;
        this.weights = new HashMap<String, Double>();
        this.avgRolls = new HashMap<String, Double>();
        this.characters = new ArrayList<String>();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Map<String, Double> getWeights() {
        return this.weights;
    }

    @Override
    public Map<String, Double> getAvgRolls() {
        return this.avgRolls;
    }

    @Override
    public String getElement() {
        return this.element;
    }

    @Override
    public String getPath() {
        return this.path;
    }

    @Override
    public List<String> getCharacters() {
        List<String> list = Arrays.asList("Firefly", "Black Swan", "Boothill", "Kafka", 
                                                "Topaz", "Luka", "Sampo", "Xueyi", "Guinafen",
                                                "Jade", "Welt", "Sushang", "PMC");
        this.characters.addAll(list);
        return this.characters;
    }
}
