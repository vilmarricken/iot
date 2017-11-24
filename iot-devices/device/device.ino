#include <EEPROM.h>
#include <ESP8266WiFi.h>
#include <ESP8266WiFiMulti.h>

ESP8266WiFiMulti WiFiMulti;

WiFiServer server(1000);

short connectionWasAlive = -1;
short connectionWasRegistred = 0;

struct IOT {
  char ssid[11];
  char pass[11];
  char id[4];
};

byte version = byte(1);

IOT iot;

void setup()
{
  //Serial.begin(9600);
  Serial.begin(115200);
  EEPROM.begin(sizeof(IOT));
  readConfigurations();
}

void loop()
{
  if( !connectWiFi() ){
    return;
  }
  if ( !registryDevice() ) {
	  return;
  }
  
}

void readConfigurations() {
  if (EEPROM.length() == 0) {
	Serial.println("EEPROM vazio");
    iot = new IOT();
	iot.ssid = "iotManager"
	iot.pass = "iot987321"
  } else {
    Serial.print("Lendo EEPROM: ");
	Serial.println(EEPROM.length());
	EEPROM.get(0, iot);
	Serial.print("SSID: ");
	Serial.println(iot.ssid);
	Serial.print("PASS: ");
	Serial.println(iot.pass);
	Serial.print("  ID: ");
	Serial.println(iot.id);
  }
}

void writeConfigurations() {
  Serial.println("Gravando EEPROM");
  EEPROM.put(0, iot);
}

void clearConfigurations() {
  Serial.println("Limpando EEPROM");
  for (int i = 0 ; i < EEPROM.length() ; i++) {
    EEPROM.write(i, 0);
  }
  readConfigurations();
}

bool connectWiFi() {
  while(WiFiMulti.run() == WL_CONNECTED) {
    if( connectionWasAlive != 1 ) {
      connectionWasAlive = 1;
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
  if( connectionWasAlive == -1 ) {
    WiFiMulti.addAP("Mazinho-GVT", "12345678");
  }
  connectionWasAlive = 0;
  Serial.println("Wait for WiFi... ");
  delay(500);
  return false;
}

bool registryDevice(WiFiClient client) {
  if( connectionWasRegistred != 1 ) {
    const uint16_t port = 800;
    const char *host = "iot-server"; // ip or dns
    Serial.print("connecting to ");
    Serial.println(host);
    if (!client.connect(host, port)) {
        Serial.println("connection failed");
        Serial.println("wait 1 sec...");
        delay(1000);
        return false;
    }
	connectionWasRegistred = 1;
    Serial.println("connected");
	delay(500);
	client.close();
    return true;
  }
}
