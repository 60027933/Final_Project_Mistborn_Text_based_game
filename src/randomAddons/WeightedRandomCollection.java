package src.randomAddons;

import java.util.NavigableMap;
import java.util.Random;
import java.util.TreeMap;

public class WeightedRandomCollection<T> {
    private final NavigableMap<Double, T> map = new TreeMap<>();
    private final Random random = new Random();
    private double totalWeight = 0;

    public void add(double weight, T result) {
        if (weight <= 0) return;
        totalWeight += weight;
        // Map the accumulated weight boundary to the item
        map.put(totalWeight, result);
    }

    public T next() {
        // Prevent crash if next() is called on an empty collection
        if (map.isEmpty()) {
            return null; // Or throw an IllegalStateException
        }
        
        // Generate a random cursor between 0.0 (inclusive) and totalWeight (exclusive)
        double value = random.nextDouble() * totalWeight;
        
        // FIX: Use ceilingEntry to get the entry greater than OR equal to 'value'
        return map.ceilingEntry(value).getValue();
    }


    public boolean isEmpty(){
        if(map.isEmpty()){return true;}else{return false;}
    }
}
