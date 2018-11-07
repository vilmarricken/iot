#include <Arduino.h>
#include "iotDevices.h"
#include "iotDevice.h"
#include "iotRelay.h"

Devices::Devices(){
}

char* Devices::run(char* command){
    String action = command;
    int p = action.indexOf(";");
    if( p == -1 ){
        //return "Invalid command: " + command;
    }
    
    devices[3]=DeviceRelay();
    return  "";
}

char* Devices::registry(char* command){
    int p = ((String)command).toInt();
    return "";
}

char* Devices::unregistry(char* command){
    
}

