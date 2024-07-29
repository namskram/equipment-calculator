package namskram.classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Amplifier extends CharacterType {
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

    public Amplifier(String name) {
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
            put("Ruan Mei", "Break");
            put("Robin", "Atk");
            put("Sparkle", "Crit");
            put("HMC", "Break");
            put("Tingyun", "Atk");
            put("Bronya", "Crit");
            put("Pela", "Debuff");
            put("Silver Wolf", "Debuff");
            put("Asta", "Generic");
            put("Hanya", "Generic");
            put("Yukong", "Generic");
        }};
    }

    private void setType(String charName) {
        this.type = types.get(charName);
    }

    private void setChars() {
        List<String> list = Arrays.asList("Ruan Mei", "Robin", "Sparkle", "HMC", 
                                                "Tingyun", "Bronya", "Pela", "Silver Wolf",
                                                "Asta", "Hanya", "Yukong");
        this.characters.addAll(list);
    }

    private void setWeights() {
        if (this.type == "Break") {

        }
        if (this.type == "Atk") {

        }

        if (this.type == "Crit") {

        }

        if (this.type == "Debuff") {

        }

        if (this.type == "Generic") {

        }
    }

}
