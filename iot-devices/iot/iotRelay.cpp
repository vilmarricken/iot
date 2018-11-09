#include <Arduino.h>
#include "iotRelay.h"

DeviceRelay::DeviceRelay(int _port) :Device(_port) {
}

char* DeviceRelay::execute(char* command) {
    Serial.print("Relay comand: ");
    return Device::execute(command);
}
