#include "iotConnect.h"

int deviceState[] = {0, 0, 0, 0, 0, 0, 0, 0};

int deviceType[] = {0, 0, 0, 0, 0, 0, 0, 0};

//int device[] = {D0, D1,  D2 , D3 ,D4 , D5, D6, D7};

int addr = 0;

String id = "device1";

byte version = byte(1);

void setup()
{
    Serial.begin(115200);
    Serial.println();
}

void loop()
{
    connectWiFi();
    registryDevice();
    millis();
}
