#include <ESP8266WiFi.h>
#include <ESP8266WiFiMulti.h>
#include "iotWiFi.h"

ESP8266WiFiMulti wiFiMulti;

boolean iotConnectWiFi(char* ssid, char* pass) {
    if ( wiFiMulti.run() == WL_CONNECTED ) {
        return false; 
    }
    //Serial.print("Wait for WiFi...");
    while ( wiFiMulti.run() != WL_CONNECTED ) {
        //Serial.print(" .");
        wiFiMulti.addAP(ssid, pass);
        delay(1000);
    }
    return true;
    //Serial.println(""); Serial.print("IP Address: " + WiFi.localIP()); Serial.print(" gateway: " + WiFi.gatewayIP()); Serial.print(" subnet: " + WiFi.subnetMask());
}
