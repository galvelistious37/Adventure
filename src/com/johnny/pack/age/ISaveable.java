package com.johnny.pack.age;

import java.util.List;

// DId this work?
public interface ISaveable {
    List<String> write();
    void read(List<String> savedValues);
}
