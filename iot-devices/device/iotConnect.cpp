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
    //Serial.println(""); Serial.print("IP Address: " + WiFi.localIP()); Serial.print(" gateway: " + WiFi.gatewayIP()); Serial.print(" subnet: " + WiFi.subnetMask());
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
        client.stop();
    }
    server.stop();
}

String* read( WiFiClient client ){
    int arrays = client.read();
    String strs[arrays];
    for( int i = 0; i < arrays; i++ ){            
        int strLen = client.read();
        char str[strLen];
        strs[i] = str;
        Serial.print("LENGTH: ");
        Serial.println(sizeof(strLen));
        for( int j =0; j < strLen; j++ ) {
            str[j] = (char) client.read();
        }
    }
    client.flush();
    return strs;
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
