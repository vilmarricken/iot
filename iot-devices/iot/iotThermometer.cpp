#include <Arduino.h>
#include "iotThermometer.h"

DeviceThermometer::DeviceThermometer() {
}

char* DeviceThermometer::execute(char* command) {
    Serial.print("Thermomether comand: ");
    return Device::execute(command);
}
