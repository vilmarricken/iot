#include <Arduino.h>
#include "iotDevice.h"
#include "error.h"

Device::Device(int _index, int _port, int mode) {
    Serial.print("Device::Device: ");
    Serial.print(_index);
    Serial.print(" - ");
    Serial.print(_port);
    Serial.print(" - ");
    if( mode == OUTPUT ) {
        Serial.println("OUTPUT");
    } else {
        Serial.println("INPUT");
    }
    index = _index;
    port = _port;
    pinMode(port, mode);
}

String Device::execute(String command) {
    Serial.println("Device::execute " + command);
    Serial.print("Device::execute On port: ");
    Serial.println(port);
    return "OK";
}
