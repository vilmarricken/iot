#include <Arduino.h>
#include "iotDevices.h"
#include "iotDevice.h"
#include "iotRelay.h"

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
    String action = command;
    int p = action.indexOf(";");
    if( p == -1 ){
        //return "Invalid command: " + command;
    }
    
    devices[3]=DeviceRelay();
    return  "";
}

String Devices::registry(String command){
    int count = 0;    
    String *values = breakString(command, ";", &count);
    if(count == 2) {
        return "OK";
    }
    return "Devices: Invalid command: " + command;
}

String Devices::unregistry(String command){
    
}

