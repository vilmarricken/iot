#include <Arduino.h>
#include "iotDevice.h"

Device::Device(int _index, int _port, int mode) {
    index = _index;
    port = _port;
    pinMode(port, mode);
}

char* Device::execute(char* command) {
    Serial.println(command);
    Serial.print("On port: ");
    Serial.println(port);
    return "";
}

