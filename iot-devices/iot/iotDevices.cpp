#include <Arduino.h>
#include "iotDevices.h"
#include "iotDevice.h"
#include "iotRelay.h"

Devices::Devices(){
}

String Devices::run(char* command){
    String action = command;
    int p = action.indexOf(";");
    if( p == -1 ){
        return "Invalid command: " + String(command);
    }
    devices[3]=DeviceRelay();
    return  "";
}
