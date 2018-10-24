#include <ESP8266WiFi.h>
#include <ESP8266WiFiMulti.h>
#include "iotConnect.h"

ESP8266WiFiMulti wiFiMulti;

WiFiServer server(1000);

short registred = 0;

const char    *id = "Mazinho-GVT";
const char    *ssid = "Mazinho-GVT";
const char    *pass = "12345678";
const char    *host = "192.168.25.20"; // ip or dns
const uint16_t port = 800;

void iotConnectWiFi() {
    if ( wiFiMulti.run() == WL_CONNECTED ) {
        return; 
    }
    //Serial.print("Wait for WiFi...");
    registred = 0;
    while ( wiFiMulti.run() != WL_CONNECTED ) {
        //Serial.print(" .");
        wiFiMulti.addAP(ssid, pass);
        delay(500);
    }
    //Serial.println("");
    //Serial.print("IP Address: ");
    //Serial.println(WiFi.localIP());
    //Serial.print(" gateway: ");
    //Serial.println(WiFi.gatewayIP());
    //Serial.print(" subnet: ");
    //Serial.println(WiFi.subnetMask());
}

void iotRegistryDevice() {
    if ( registred != 1 ) {
        //Serial.print("connecting to ");
        //Serial.println(host);
        WiFiClient client;
        while (!client.connect(host, port)) {
            //Serial.println("connection failed");
            //Serial.println("wait 1 sec...");
            delay(1000);
        }
        registred = 1;
        //Serial.println("connected");
        delay(500);
        byte len = (byte)(strlen(id));
        client.write(len);
        client.print(id);
    }
}
