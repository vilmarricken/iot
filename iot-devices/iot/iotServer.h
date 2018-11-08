#ifndef __IOT_SERVER_H__
#define __IOT_SERVER_H__
#include <ESP8266WiFi.h>
#include <Arduino.h>
#include "iotDevices.h"

class IotServer {

    private:
        WiFiServer *server;
        Devices *devices;
        char *host;
        String read(WiFiClient client);
        void write(String text, WiFiClient *client);

    public:
        IotServer(char *hostServer);
        void serverRun();
        void iotRegistryDevice(char* id);

};

#endif
