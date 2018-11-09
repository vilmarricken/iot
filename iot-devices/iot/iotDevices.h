#ifndef __IOT_DEVICES_H__
#define __IOT_DEVICES_H__

#include <Arduino.h>
#include "iotDevice.h"

class Devices {

    private:
        Device *devices[8] = {NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL};
        int ports[8];
        int getDigit(String command);
        Device* createDevice(String command, int port);

    public:
        Devices();
        String run(String command);
        String registry(String command);
        String unregistry(String command);

};
#endif
