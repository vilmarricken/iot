#ifndef __IOT_SERVER_H__
#define __IOT_SERVER_H__
#include <ESP8266WiFi.h>
#include <Arduino.h>
#include "iotDevices.h"

class IotServer {

    private:
        WiFiServer *server;
        Devices devices;
        char *host;

    public:
        IotServer(char *hostServer);
        void serverRun();
        void iotRegistryDevice(char* id);
        String read(WiFiClient client );

};

#endif
