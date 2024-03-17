package fr.unk.domaine;

import java.util.List;

public interface Domain<T> {

    /**
     * Get all of the domain possibility
     * @return A list of all the possibility
     */
    List<T> getPossibility();

    /**
     * Duplicate the domain
     * @return A duplicate of the domain
     */
    Domain<T> duplicate();
}
