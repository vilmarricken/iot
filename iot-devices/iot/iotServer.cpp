#include <ESP8266WiFi.h>
#include <ESP8266WiFiMulti.h>
#include "iotServer.h"
#include "iotDevices.h"
#include "stringUtil.h"


IotServer::IotServer(char *hostServer){
    host = hostServer;
    server = new WiFiServer(800);
    Serial.println("-------------------------");
    Serial.println("IotServer::IotServer");
    Serial.print("host: ");Serial.println( + host);
    Serial.print("serverRun: ");Serial.println(server->status());
    Serial.println("-------------------------");
    devices = new Devices();
}

void IotServer::serverRun() {
    Serial.print("serverRun: ");
    Serial.println(server->status());
    Serial.println("-------------------------");
    Serial.println("serverRun");
    Serial.print("host: ");Serial.println(host);
    Serial.println("-------------------------");
    if( server->status() != 1 ) {
        Serial.println("serverRun->begin");
        server->begin();
    }
    Serial.println("Server listening on port 800");
    while( server->status() == 1 ) {
        WiFiClient client = server->available();
        if ( !client ) {    
            delay(1000);
            continue;  
        }
        Serial.println("Lendo");
        delay(100);
        while(client.available()) {
            String command = read(client);
            Serial.println("command: " + command);
            int count = 0;
            String *commands = breakString(command, ";", &count);
            Serial.println(count);
            Serial.println(commands[0]);
            if(count == 2) {
                Serial.println(commands[1]);
                if(commands[0] == "1") {
                    write(devices->registry(commands[1]), &client);
                } else {
                    Serial.println("Server: Invalid command " + command);
                    write("Server: Invalid command: " + command, &client);
                }
            } else {
                Serial.println("Server: Invalid command " + command);
                write("Server: Invalid message: " + command, &client);
            }
        }
        //client.stop();
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
    return str;
}

void IotServer::write(String text, WiFiClient *client){
    byte len = (byte)(text.length());
    client->write(len);
    client->print(text);
    client->flush();
    delay(1000);
    Serial.println("Resposta enviada");
}

void IotServer::iotRegistryDevice(char* id) {
    Serial.print("connecting to ");Serial.println(host);
    WiFiClient client;
    while (!client.connect(host, 800)) {
        delay(1000);
    }
    Serial.print("connected in ");Serial.println(host);
    delay(500);
    write(id, &client);
    client.stop();
}
