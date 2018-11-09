#include <Arduino.h>
#include "iotDevice.h"

Device::Device(int _port) {
    port = _port;
}

char* Device::execute(char* command) {
    Serial.println(command);
    Serial.print("On port: ");
    Serial.println(port);
    return "";
}

