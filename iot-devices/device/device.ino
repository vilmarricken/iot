#include <EEPROM.h>
#include <ESP8266WiFi.h>
#include <ESP8266WiFiMulti.h>

ESP8266WiFiMulti WiFiMulti;

WiFiServer server(1000);

short connectioWasAlive = -1;

void setup()
{
  //Serial.begin(9600);
  Serial.begin(115200);
  EEPROM.begin(64);
}

void loop()
{
  while( !connectWiFi() ){
    return;
  }
  //Serial.println("System connected");
  delay(1000);
  /*
  return;
  // read a byte from the current address of the EEPROM
  value = EEPROM.read(address);

  Serial.print(address);
  Serial.print("\t");
  Serial.print(value, DEC);
  Serial.println();

  // advance to the next address of the EEPROM
  address = address + 1;

  // there are only 512 bytes of EEPROM, from 0 to 511, so if we're
  // on address 512, wrap around to address 0
  if (address == 512)
    address = 0;

  delay(500);
  */
}

boolean connectWiFi() {
  while(WiFiMulti.run() == WL_CONNECTED) {
    if( connectioWasAlive == 0 ) {
      connectioWasAlive = 1;
      Serial.print("IP Address: ");
      Serial.print(WiFi.localIP());
      Serial.print(" gateway: ");
      Serial.print(WiFi.gatewayIP());
      Serial.print(" subnet: ");
      Serial.print(WiFi.subnetMask());
      Serial.println("");
    }
    return true; 
  }
  if( connectioWasAlive == -1 ) {
    WiFiMulti.addAP("Mazinho-GVT", "12345678");
    connectioWasAlive = 0;
  }
  Serial.println("Wait for WiFi... ");
  delay(500);
  return false;
}

