#include <Arduino.h>
#include "iotDevices.h"
#include "iotDevice.h"
#include "iotRelay.h"
#include "iotThermometer.h"
#include "stringUtil.h"

Devices::Devices(){
    ports[0] = D0;
    Serial.print("Init port 0: ");
    Serial.println(D0);
    ports[1] = D1;
    Serial.print("Init port 1: ");
    Serial.println(D1);
    ports[2] = D2;
    Serial.print("Init port 2: ");
    Serial.println(D2);
    ports[3] = D3;
    Serial.print("Init port 3: ");
    Serial.println(D3);
    ports[4] = D4;
    Serial.print("Init port 4: ");
    Serial.println(D4);
    ports[5] = D5;
    Serial.print("Init port 5: ");
    Serial.println(D5);
    ports[6] = D6;
    Serial.print("Init port 6: ");
    Serial.println(D6);
    ports[7] = D7;
    Serial.print("Init port 7: ");
    Serial.println(D7);
}

String Devices::execute(String command){
    int count = 0;
    Serial.println("Devices::execute: " + command);
    String *values = breakString(command, ";", &count);
    int index = getDigit(values[0]);
    Serial.print("XXX: ");
    Serial.print(index);
    Serial.print(" - ");
    Serial.println(count);
    if(count == 1) {
        return devices[index]->execute("");
    } else {
        return devices[index]->execute(values[1]);
    }
    return  "ERROR: 0";
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
            devices[index] = createDevice(values[1], index);
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
    if(type == "2") {
        //DeviceSolidRelay
        return new DeviceRelay(index, ports[index]);
    }
    if(type == "3") {
        return new DeviceThermometer(index, ports[index]);
    }
    return NULL;
}

