#include <ESP8266WiFi.h>
#include <ESP8266WiFiMulti.h>
#include "iotServer.h"
#include "iotDevices.h"


IotServer::IotServer(char *hostServer){
    host = hostServer;
    server = new WiFiServer(800);
    Serial.println("-------------------------");
    Serial.println("IotServer::IotServer");
    Serial.print("host: ");Serial.println( + host);
    Serial.print("serverRun: ");Serial.println(server->status());
    Serial.println("-------------------------");
    devices = Devices();
}

void IotServer::serverRun() {
    Serial.print("serverRun: ");
    Serial.println(server->status());
    Serial.println("-------------------------");
    Serial.println("serverRun");
    Serial.print("host: ");Serial.println(host);
    Serial.println("-------------------------");
    if( server->status() == CLOSED ) {
        Serial.println("serverRun->begin");
        server->begin();
    }
    Serial.println("Server on");
    while( server->status() == 1 ) {
        WiFiClient client = server->available();
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
                Serial.println(command.substring(pos, subPos));
                pos = subPos + 1;
                subPos = command.indexOf(";", pos);
            }
            Serial.println(command.substring(pos));
        }
        client.stop();
    }
    server->stop();
}

String IotServer::read( WiFiClient client ){
    int strLen = client.read();
    char str[strLen+1];
    str[strLen] = 0;
    for( int j =0; j < strLen; j++ ) {
        str[j] = (char) client.read();
    }
    client.flush();
    return str;
}

void IotServer::iotRegistryDevice(char* id) {
    Serial.println("-------------------------");
    Serial.println("iotRegistryDevice");
    Serial.print("host: ");Serial.println(host);
    Serial.println("-------------------------");
    Serial.print("connecting to ");Serial.println(host);
    WiFiClient client;
    while (!client.connect(host, 800)) {
        //Serial.println("connection failed, waiting 1 sec...");
        delay(1000);
    }
    Serial.print("connected in ");Serial.println(host);
    delay(500);
    byte len = (byte)(strlen(id));
    client.write(len);
    client.print(id);
    client.stop();
    Serial.println("client stop");
    Serial.print("-> ");Serial.println(host);
}

