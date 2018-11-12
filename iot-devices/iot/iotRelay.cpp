#include <Arduino.h>
#include "iotRelay.h"

DeviceRelay::DeviceRelay(int _index, int _port) :Device(_index, _port, OUTPUT) {
}

char* DeviceRelay::execute(char* command) {
    Serial.print("Relay comand: ");
    return Device::execute(command);
}
