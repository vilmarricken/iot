#ifndef __IOT_DEVICES_H__
#define __IOT_DEVICES_H__

#include <Arduino.h>
#include "iotDevice.h"

class Devices {

    private:
        Device devices[8];
        String* commands(String command);

    public:
        Devices();
        char* run(char* command);
        char* registry(char* command);
        char* unregistry(char* command);

};
#endif
