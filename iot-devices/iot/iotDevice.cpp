#include <Arduino.h>
#include "iotDevice.h"

/*
Device::Device() {
    Serial.println("Device: default constructor");
}
*/

Device::Device(int _port) {
    Serial.println("Device: int constructor");
    port = _port;
}

char* Device::execute(char* command) {
    Serial.println(command);
    Serial.print("On port: ");
    Serial.println(port);
    return "";
}

