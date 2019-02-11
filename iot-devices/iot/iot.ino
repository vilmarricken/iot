#include "iotWiFi.h"
#include "iotServer.h"

char* ssid = "Mazinho-Home";
char* pass = "mazinho123";

char* serverHost = "192.168.25.15";
IotServer* server = NULL;

char* id = "Regrador";

void setup(){
    Serial.begin(115200);
    delay(100);
    Serial.println("");
    Serial.println("");
    Serial.println("Set up");
}

void loop(){
    if(iotConnectWiFi(ssid, pass)) {
        if( server == NULL ) {
            server = new IotServer(serverHost);
        }
        server->iotRegistryDevice(id);
    }
    server->serverRun();
    Serial.print("Loop - ");
    Serial.println(millis());
    delay(5000);
}
