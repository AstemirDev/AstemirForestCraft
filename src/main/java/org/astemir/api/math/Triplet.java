package org.astemir.api.math;


import java.util.Objects;

public class Triplet<K,V,T> {

    private final K key;

    private final V value;

    private final T transcription;


    public Triplet(K key, V value, T transcription) {
        this.key = key;
        this.value = value;
        this.transcription = transcription;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public T getTranscription() {
        return transcription;
    }

    @Override
    public String toString() {
        return key + "=" + value +"="+transcription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o instanceof Triplet) {
            Triplet pair = (Triplet) o;
            if (!Objects.equals(key, pair.key)) return false;
            if (!Objects.equals(transcription, pair.transcription)) return false;
            return Objects.equals(value, pair.value);
        }
        return false;
    }
}

