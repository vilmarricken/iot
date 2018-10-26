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
        delay(1000);
    }
    iotRegistryDevice();
    serverBegin();
    //Serial.println("");
    //Serial.print("IP Address: ");
    //Serial.println(WiFi.localIP());
    //Serial.print(" gateway: ");
    //Serial.println(WiFi.gatewayIP());
    //Serial.print(" subnet: ");
    //Serial.println(WiFi.subnetMask());
}

void serverBegin(){
    if( server.status() == CLOSED ) {
        server.begin();
    }
    Serial.println("Server on");
    while( server.status() == 1 ) {
        WiFiClient client = server.available();
        if ( !client ) {    
            Serial.println("Aguardando");
            delay(1000);
            continue;  
        }
        Serial.println("Lendo");
        while(!client.available()) {
            delay(10);
        }
        int ss = client.read();
        Serial.println("SS: " + ss);
        int s = client.read();
        Serial.println("S: " + s);
        char t[s];
        t[s] = (char) 0;
        Serial.print("LENGTH: ");
        Serial.println(sizeof(t));
        for( int i =0; i < s; i++ ) {
            t[i] = (char) client.read();
        }
        client.flush();
        client.stop();
        Serial.println(t);
    }
    server.stop();
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
        client.stop();
    }
}
