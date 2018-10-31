#ifndef __IOT_DEVICES_H__
#define __IOT_DEVICES_H__

#include "iotDevice.h"

class Devices {

    private:
        Device devices[8];
    
    public:
        Devices();
        char* run(int device, char* command);

};

#endif
