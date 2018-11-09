#include <Arduino.h>
#include "iotThermometer.h"

DeviceThermometer::DeviceThermometer(int _port) : Device(_port) {
}

char* DeviceThermometer::execute(char* command) {
    Serial.print("Thermomether comand: ");
    return Device::execute(command);
}
