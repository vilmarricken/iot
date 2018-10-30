#ifndef __IOT_SERVER_H__
#define __IOT_SERVER_H__

#include <ESP8266WiFi.h>

void serverRun(String hostServer);

void iotRegistryDevice(char* host, char* id);

String read(WiFiClient client );

#endif
