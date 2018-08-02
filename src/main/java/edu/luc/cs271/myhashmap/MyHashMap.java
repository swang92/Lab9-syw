package edu.luc.cs271.myhashmap;

import java.util.*;

/**
 * A generic HashMap custom implementation using chaining. Concretely, the table is an ArrayList of
 * chains represented as LinkedLists.
 *
 * @param <K> the key type
 * @param <V> the value type
 */
public class MyHashMap<K, V> implements Map<K, V> {

  private static final int DEFAULT_TABLE_SIZE = 11; // a prime

  private List<List<Entry<K, V>>> table;

  public MyHashMap() {
    this(DEFAULT_TABLE_SIZE);
  }

  public MyHashMap(final int tableSize) {
    // allocate a table of the given size
    table = new ArrayList<>(tableSize);
    // then create an empty chain at each position
    for (int i = 0; i < tableSize; i += 1) {
      table.add(new LinkedList<>());
    }
  }

  @Override
  public int size() {
    int result = 0;
    for (int i = 0; i < table.size(); i++) {
      table.get(i).size();
    }
    return result;
  }

  @Override
  public boolean isEmpty() {
    return size() == 0;
  }

  @Override
  public boolean containsKey(final Object key) {
    final int index = calculateIndex(key);
    final Iterator<Entry<K, V>> iter = table.get(index).iterator();
    while (iter.hasNext()) {
      final Entry<K, V> entry = iter.next();
      if (entry.getKey().equals(key)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean containsValue(final Object value) {
    final int index = calculateIndex(value);
    final Iterator<Entry<K, V>> iter = table.get(index).iterator();
    while (iter.hasNext()) {
      final Entry<K, V> entry = iter.next();
      if (entry.getValue().equals(value)) {
        return true;
      }
    }
    return false;
  }


  @Override
  public V get(final Object key) {
    final int index = calculateIndex(key);
    final Iterator<Entry<K, V>> iter = table.get(index).iterator();
    while (iter.hasNext()) {
      final Entry<K, V> entry = iter.next();
      if (entry.getKey().equals(key)) {
        return entry.getValue();
      }
    }
    return null;
  }

  @Override
  public V put(final K key, final V value) {
    final int index = calculateIndex(key);
    final Iterator<Entry<K, V>> iter = table.get(index).iterator();
    while (iter.hasNext()) {
      final Entry<K, V> entry = iter.next();
      if (entry.getKey().equals(key)) {
        final V oldValue = entry.getValue();
        iter.remove();
        return oldValue;
      }
    }
    return null;
  }

  @Override
  public V remove(final Object key) {
    final int index = calculateIndex(key);
    final Iterator<Entry<K, V>> iter = table.get(index).iterator();
    while (iter.hasNext()) {
      final Entry<K, V> entry = iter.next();
      if (entry.getKey().equals(key)) {
        final V oldValue = entry.getValue();
        iter.remove();
        return oldValue;
      }
    }
    return null;
  }

  @Override
  public void putAll(final Map<? extends K, ? extends V> m) {
    for (Map.Entry<? extends K, ? extends V> entry : m.entrySet()) {
      this.put(entry.getKey(), entry.getValue());
    }
  }

  @Override
  public void clear() {
    for (int i = 0; i < table.size(); i++) {
      table.get(i).clear();
    }
  }

    /** The resulting keySet is not "backed" by the Map, so we keep it unmodifiable. */
    @Override
    public Set<K> keySet () {
      final Set<K> result = new HashSet<>();
      int index = 0;
      final Iterator<Entry<K, V>> currChain = table.get(index).iterator();
      for(Entry<K,V> e : table.get(index))
        while (currChain.hasNext()) {
          final Entry<K, V> entry = currChain.next();
          result.add(entry.getKey());
        }
      return Collections.unmodifiableSet(result);
    }

    /** The resulting values collection is not "backed" by the Map, so we keep it unmodifiable. */
    @Override
    public Collection<V> values () {
      final List<V> result = new LinkedList<>();
      int index = 0;
      final Iterator<Entry<K, V>> currChain = table.get(index).iterator();
      for(Entry<K,V> e : table.get(index))
        while (currChain.hasNext()) {
          final Entry<K, V> entry = currChain.next();
          result.add(entry.getValue());
        }
      return Collections.unmodifiableCollection(result);
    }

    /** The resulting entrySet is not "backed" by the Map, so we keep it unmodifiable. */
    @Override
    public Set<Entry<K, V>> entrySet () {
      int index = 0;
      final Iterator<Entry<K, V>> currChain = table.get(index).iterator();
      final Set<Entry<K, V>> result = new HashSet<>();

      for(Entry<K,V> e : table.get(index))
      while (currChain.hasNext()) {
        final Entry<K, V> entry = currChain.next();
        result.add(entry);
      }
      return Collections.unmodifiableSet(result);
    }

    @Override
    public String toString () {
      return table.toString();
    }

    public boolean equals ( final Object that){
      if (this == that) {
        return true;
      } else if (!(that instanceof Map)) {
        return false;
      } else if (this.entrySet() == ((Map) that).entrySet()){
        return true;
      } else
      return false;
    }

    private int calculateIndex ( final Object key){
      // positive remainder (as opposed to %)
      // required in case hashCode is negative!
      return Math.floorMod(key.hashCode(), table.size());
    }
  }



