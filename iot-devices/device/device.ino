#include "iotConnect.h"

void setup(){
    Serial.begin(115200);
    Serial.println("Set up");
}

void loop(){
    iotConnectWiFi();
    iotRegistryDevice();
    //millis();
    Serial.print("Loop - ");
    Serial.println(millis());
    delay(5000);
}
