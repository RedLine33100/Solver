package fr.redline.domaine;

import java.util.List;

public interface Domain<T> {
    List<T> getPossibility();

    boolean inDomain(T value);

    void removeFromDomain(T value);

    void addToDomain(T value);
}
