package sit.int202.classicmodels.entities.cart;

import lombok.ToString;
import sit.int202.classicmodels.entities.Product;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@ToString
public class Cart<K, V extends CartItem> {
    private Map<K, V> map;

    public Cart() {
        this.map = new HashMap<>();
    }

    public void addItem(K key, V value) {
        this.map.putIfAbsent(key, value);
    }

    public V removeItem(K key) {
        return map.remove(key);
    }

    public void clear() {
        map.clear();
    }

    public int getNoOfItem() {
        return map.size();
    }

    public int getQuantity() {
        return map.values()
                .stream()
                .mapToInt(CartItem::getQuantity)
                .sum();
    }

    public double getTotalPrice() {
        return map.values()
                .stream()
                .mapToDouble(CartItem::getTotal)
                .sum();
    }

    public Collection<V> getAllItem() {
        return Collections.unmodifiableCollection(map.values());
    }
}
