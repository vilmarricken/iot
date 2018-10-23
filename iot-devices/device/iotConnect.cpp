#include <ESP8266WiFi.h>
#include <ESP8266WiFiMulti.h>
#include "iotConnect.h"

ESP8266WiFiMulti wiFiMulti;

WiFiServer server(1000);

short registred = 0;

char ssid[] = "Mazinho-GVT";
char pass[] = "12345678";
//String server = "192.168.25.20";

void connectWiFi() {
    if ( wiFiMulti.run() == WL_CONNECTED ) {
        return; 
    }
    Serial.println("Wait for WiFi...");
    registred = 0;
    while ( wiFiMulti.run() != WL_CONNECTED ) {
        wiFiMulti.addAP(ssid, pass);
        delay(500);
    }
    Serial.print("IP Address: ");
    Serial.print(WiFi.localIP());
    Serial.print(" gateway: ");
    Serial.print(WiFi.gatewayIP());
    Serial.print(" subnet: ");
    Serial.print(WiFi.subnetMask());
    Serial.println("");
}

void registryDevice() {
    while( registred != 1 ) {
        const uint16_t port = 800;
        const char *host = "iot-server"; // ip or dns
        Serial.print("connecting to ");
        Serial.println(host);
        WiFiClient client;
        if (!client.connect(host, port)) {
            Serial.println("connection failed");
            Serial.println("wait 1 sec...");
            delay(1000);
        }
        registred = 1;
        Serial.println("connected");
        delay(500);
    }
}
