#include <Arduino.h>
#include "iotDevice.h"

Device::Device() {
}

char* Device::execute(char command) {
    Serial.println("Device comand: " + command);
    return "";
}

void Device::setPort(int _port){
    port = _port;
}

int Device::getPort(){
    return port;
}
