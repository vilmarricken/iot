#ifndef __IOT_DEVICES_H__
#define __IOT_DEVICES_H__

#include <Arduino.h>
#include "iotDevice.h"

class Devices {

    private:
        Device devices[8];
        int ports[8];
        String* commands(String command);

    public:
        Devices();
        String run(String command);
        String registry(String command);
        String unregistry(String command);

};
#endif
