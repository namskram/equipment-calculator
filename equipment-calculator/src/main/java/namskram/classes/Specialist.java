package namskram.classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Specialist extends CharacterType {
    private final String name;
    private final Map<String, Double> avgRolls = new HashMap<String, Double>() {{
        put("SPD", 2.3);
        put("HP", 38.0);
        put("ATK", 19.0);
        put("DEF", 19.0);
        put("HP%", 3.9);
        put("ATK%", 3.9);
        put("DEF%", 4.9);
        put("Break Effect%", 5.8);
        put("Effect Hit Rate%", 3.9);
        put("Effect RES%", 3.9);
        put("CRIT Rate%" , 2.9);
        put("CRIT DMG%" , 5.8);
    }};
    private Map<String, Double> weights;
    private Map<String, String> types;
    private List<String> characters;
    private String element;
    private String path;
    private String type;

    public Specialist(String name) {
        this.name = name;
        setChars();
        setTypes();
        setType(name);
        this.weights = new HashMap<String, Double>() {{
            put("SPD", 0.0);
            put("HP", 0.0);
            put("ATK", 0.0);
            put("DEF", 0.0);
            put("HP%", 0.0);
            put("ATK%", 0.0);
            put("DEF%", 0.0);
            put("Break Effect%", 0.0);
            put("Effect Hit Rate%", 0.0);
            put("Effect RES%", 0.0);
            put("CRIT Rate%" , 0.0);
            put("CRIT DMG%" , 0.0);
        }};
        setWeights();
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

    public String getType() {
        return this.type;
    }

    public Map<String, String> getTypes() {
        return this.types;
    }

    @Override
    public List<String> getCharacters() {
        return this.characters;
    }

    private void setTypes() {
        this.types = new HashMap<String, String>() {{
            put("Firefly", "Break");
            put("Black Swan", "DOT");
            put("Boothill", "Break");
            put("Kafka", "DOT");
            put("Topaz", "Generic");
            put("Luka", "DOT");
            put("Sampo", "DOT");
            put("Xueyi", "Break");
            put("Guinafen", "DOT");
            put("Jade", "Generic");
            put("Welt", "Generic");
            put("Sushang", "Break");
            put("PMC", "Break");
        }};
    }

    private void setType(String charName) {
        this.type = types.get(charName);
    }

    private void setChars() {
        List<String> list = Arrays.asList("Firefly", "Black Swan", "Boothill", "Kafka", 
                                                "Topaz", "Luka", "Sampo", "Xueyi", "Guinafen",
                                                "Jade", "Welt", "Sushang", "PMC");
        this.characters.addAll(list);
    }

    private void setWeights() {
        if (this.type == "Break") {

        }
        if (this.type == "DOT") {

        }

        if (this.type == "Generic") {

        }
    }

}
