#include <Arduino.h>
#include "iotDevice.h"

Device::Device() {
}

char* Device::execute(char* command) {
    Serial.println(command);
    Serial.print("On port: ");
    Serial.println(port);
    return "";
}

void Device::setPort(int _port){
    port = _port;
}

int Device::getPort(){
    return port;
}
