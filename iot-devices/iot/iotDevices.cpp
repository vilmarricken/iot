#include <Arduino.h>
#include "iotDevices.h"
#include "iotDevice.h"
#include "iotRelay.h"
#include "stringUtil.h"

Devices::Devices(){
    ports[0] = D0;
    ports[1] = D1;
    ports[2] = D2;
    ports[3] = D3;
    ports[4] = D4;
    ports[5] = D5;
    ports[6] = D6;
    ports[7] = D7;
}

String Devices::run(String command){
    //devices[3]=DeviceRelay();
    return  "";
}

String Devices::registry(String command){
    int count = 0;    
    String *values = breakString(command, ";", &count);
    if(count == 2) {
        int index = getDigit(values[0]);
        if( index != -1) {
            if(devices[index] != NULL) {
                Serial.print("Position ");Serial.print(index);Serial.println(" is NOT NULL");;
                delete devices[index];
            } else {
                Serial.print("Position ");Serial.print(index);Serial.println(" is NULL");
            }
            devices[index] = createDevice(values[1], ports[index]);
            if(devices[index] == NULL) {
                return "Devices: Invalid device type: " + values[1];
            }
            return "OK";
        }
    }
    return "Devices: Invalid command: " + command;
}

String Devices::unregistry(String command){
    
}

int Devices::getDigit(String command){
    if(command.length() == 1) {
        int c = command.charAt(0);
        if(c >= '0' && c <= '9'){
            return c - 48;
        }
    }
    return -1;
}

Device* Devices::createDevice(String type, int index){
    Serial.println("createDevice: " + type);
    if(type == "1") {
        return new DeviceRelay(index, ports[index]);
    }
    return NULL;
}

