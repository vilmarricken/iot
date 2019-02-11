#include <Arduino.h>
#include "iotRelay.h"

DeviceRelay::DeviceRelay(int _index, int _port) :Device(_index, _port, OUTPUT) {
    Serial.println("Instanciado DeviceRelay");
}

String DeviceRelay::execute(String command) {
    Serial.println("DeviceRelay::execute command: " + command);
    if( command.equals("1") ) {
        digitalWrite(port, HIGH);
    } else {
        digitalWrite(port, LOW);
    }
    return Device::execute(command);
}
