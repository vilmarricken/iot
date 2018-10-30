#include "iotWiFi.h"
#include "iotServer.h"

char* ssid = "Mazinho-GVT";
char* pass = "12345678";

char* serverHost = "192.168.25.20";

char* id = "Regrador";

void setup(){
    Serial.begin(115200);
    Serial.println("");
    Serial.println("");
    Serial.println("Set up");
}

void loop(){
    if(iotConnectWiFi(ssid, pass)) {
        iotRegistryDevice(serverHost, id);
    }
    serverRun(serverHost);
    Serial.print("Loop - ");
    Serial.println(millis());
    delay(5000);
}
