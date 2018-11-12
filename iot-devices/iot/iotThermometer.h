#ifndef __IOT_THERMOMETER_H__
#define __IOT_THERMOMETER_H__
#include "iotDevice.h"
#include <OneWire.h>

class DeviceThermometer : public Device {

    private:
        OneWire  *sensor;
    
    public:
        DeviceThermometer(int _index, int _port);
        char* execute(char* command);
};

#endif
