package com.yahoo.searchdefinition.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * This class holds the extracted information after parsing a
 * "annotation" block, using simple data structures as far as
 * possible.  Do not put advanced logic here!
 * @author arnej27959
 **/
class ParsedAnnotation {

    private final String name;
    private ParsedStruct wrappedStruct = null;
    private final List<String> inherited = new ArrayList<>();

    ParsedAnnotation(String name) {
        this.name = name;
    }

    public String name() { return name; }
    public List<String> getInherited() { return List.copyOf(inherited); }
    public Optional<ParsedStruct> getStruct() { return Optional.ofNullable(wrappedStruct); }

    void setStruct(ParsedStruct struct) { this.wrappedStruct = struct; }
    void inherit(String other) { inherited.add(other); }
}