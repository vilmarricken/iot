#ifndef __IOT_RELAY_H__
#define __IOT_RELAY_H__
#include "iotDevice.h"

class DeviceRelay : public Device {

    private:
        int port;
    
    public:
        DeviceRelay(int _port);
        //DeviceRelay();
        char* execute(char* command);

};

#endif
