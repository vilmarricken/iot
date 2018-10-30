#include <ESP8266WiFi.h>
#include <ESP8266WiFiMulti.h>
#include "iotServer.h"

const uint16_t port = 800;
WiFiServer server(port);

void serverRun(String hostServer) {
    if( server.status() == CLOSED ) {
        server.begin();
    }
    Serial.println("Server on");
    while( server.status() == 1 ) {
        WiFiClient client = server.available();
        if ( !client ) {    
            Serial.println("Aguardando na porta 800");
            delay(1000);
            continue;  
        }
        Serial.println("Lendo");
        delay(100);
        while(client.available()) {
            String command = read(client);
            Serial.println("command: " + command);
            int pos = 0;
            int subPos = command.indexOf(";", pos);
            while( subPos > 0 ) {
                Serial.print("subPos: ");Serial.println(subPos);
                Serial.println(command.substring(pos, subPos));
                pos = subPos + 1;
                Serial.print("subPos: ");Serial.println(subPos);
                Serial.print("pos: ");Serial.println(pos);
                subPos = command.indexOf(";", pos);
            }
            Serial.println(command.substring(pos));
        }
        client.stop();
    }
    server.stop();
}

String read( WiFiClient client ){
    int strLen = client.read();
    char str[strLen+1];
    str[strLen] = 0;
    for( int j =0; j < strLen; j++ ) {
        str[j] = (char) client.read();
    }
    client.flush();
    return str;
}

void iotRegistryDevice(char* host, char* id) {
    //Serial.print("connecting to ");
    //Serial.println(host);
    WiFiClient client;
    while (!client.connect(host, port)) {
        Serial.println("connection failed, waiting 1 sec...");
        delay(1000);
    }
    //Serial.println("connected");
    delay(500);
    byte len = (byte)(strlen(id));
    client.write(len);
    client.print(id);
    client.stop();
}
