#ifndef __IOT_CONNECT_H__
#define __IOT_CONNECT_H__

#include <ESP8266WiFi.h>

void iotConnectWiFi();

void iotRegistryDevice();

void serverBegin();

String read(WiFiClient client );

#endif
