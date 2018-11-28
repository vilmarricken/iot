#include <Arduino.h>
#include "iotRelay.h"

DeviceRelay::DeviceRelay(int _index, int _port) :Device(_index, _port, OUTPUT) {
    Serial.println("Instanciado DeviceRelay");
}

String DeviceRelay::execute(String command) {
    Serial.println("DeviceRelay::execute command: " + command);
    digitalWrite(port, HIGH);
    return Device::execute(command);
}
