#include <Arduino.h>

String* breakString(String text, String delim, int *count) {
    int index = text.indexOf(delim);
    String *values;
    if( index < 0 ){
        values = new String[1];
        values[0] = text;
        *count = 1;
        return values;
    }
    *count = 2;
    values = new String[2];
    values[0] = text.substring(0, index);
    //Serial.println("Parte 1: " + values[0]);
    values[1] = text.substring(index + 1);
    //Serial.println("Parte 2: " + values[1]);
    return values;
}

