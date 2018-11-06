#include <Arduino.h>
#include "iotRelay.h"

DeviceRelay::DeviceRelay() {
}

char* DeviceRelay::execute(char* command) {
    Serial.print("Relay comand: ");
    return Device::execute(command);
}
