package fr.unk.variable;

import java.util.Map;

public interface Variable<T> {

    T getValue(Map<String, Object> maps);

}
