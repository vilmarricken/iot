/*
 *  This sketch sends a message to a TCP server
 *
 */

#include <ESP8266WiFi.h>
#include <ESP8266WiFiMulti.h>

ESP8266WiFiMulti WiFiMulti;

WiFiServer server(1000);

int deviceState[] = {0, 0, 0, 0, 0, 0, 0, 0};

int device[] = {D0, D1,  D2 , D3 ,D4 , D5, D6, D7};

int connected = 0;

int counter = 1;

void setup() {
  Serial.begin(115200);
  delay(10);
  WiFiMulti.addAP("Mazinho-GVT", "12345678");
  Serial.println();
  Serial.print("Wait for WiFi... ");
  while(WiFiMulti.run() != WL_CONNECTED) {
    Serial.print(".");
    delay(500);
  }
  Serial.println("");
  Serial.println("WiFi connected");
  Serial.println("IP address: ");
  Serial.println(WiFi.localIP());
  server.begin();
  Serial.println("Server started");
  delay(500);
}

void loop() {
  if( connected == 0 ) {
    const uint16_t port = 800;
    const char * host = "192.168.25.6"; // ip or dns
    Serial.print("connecting to ");
    Serial.println(host);
    WiFiClient client;
    while (!client.connect(host, port)) {
      Serial.println("connection failed");
      Serial.println("wait 5 sec...");
      delay(5000);
      return;
    }
    connected = 1;
    delay(500);
    client.stop();
  }

  WiFiClient conn = server.available();
  if (!conn) {
    Serial.println("##############################");
    Serial.println("Server Connection failed");
    Serial.println("wait 5 sec...");
    delay(5000);
    return;
  }
  Serial.prtinln("Connection received");
  String line = conn.readStringUntil('\r');
  Serial.println("Recebido:");
  Serial.println(line);
  conn.print("Send this data to server ");
  conn.println(counter++);
  Serial.println("closing connection");
  conn.stop();
}

