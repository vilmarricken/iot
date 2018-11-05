#include <Arduino.h>
#include "iotRelay.h"

DeviceRelay::DeviceRelay() {
}

char* DeviceRelay::execute(char command) {
    Serial.println("Relay comand: " + command);
    return "";
}
