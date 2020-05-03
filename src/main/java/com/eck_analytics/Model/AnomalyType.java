package com.eck_analytics.Model;

public enum AnomalyType {
    NOO(0,""),
    NOT_IDENTIFIED(1,"+"),
    NORMAL(2,"N"),
    ARRHYTHMIA(3,"A"),
    VIBRATION(4,"~"),
    STOP(5,"|"),
    V(6,"V");


    private final int typeId;
    private final String typeString;

    AnomalyType(int typeId, String typeString) {
        this.typeId = typeId;
        this.typeString = typeString;
    }

    public static int getTypeId(String typeString) {
        switch (typeString){
            case("+"): return 1;
            case("N"): return 2;
            case("A"): return 3;
            case("~"): return 4;
            case("|"): return 5;
            case("V"): return 6;
        }
        return 0;
    }
}
