#ifndef __IOT_RELAY_H__
#define __IOT_RELAY_H__
#include "iotDevice.h"

class DeviceRelay : public Device {

    private:
    
    public:
        DeviceRelay(int _index, int _port);
        //DeviceRelay();
        char* execute(char* command);

};

#endif