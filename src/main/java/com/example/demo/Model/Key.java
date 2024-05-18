package com.example.demo.Model;

import java.util.Objects;

public class Key {
    private int key;
    private String value;

    public Key(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Key{" +
                "key=" + key +
                ", value='" + value + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Key key1 = (Key) o;
        return key == key1.key && Objects.equals(value, key1.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, value);
    }
}
