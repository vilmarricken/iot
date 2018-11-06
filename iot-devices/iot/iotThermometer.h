#ifndef __IOT_THERMOMETER_H__
#define __IOT_THERMOMETER_H__
#include "iotDevice.h"

class DeviceThermometer : public Device {

    private:
    
    public:
        DeviceThermometer();
        char* execute(char* command);
};

#endif
