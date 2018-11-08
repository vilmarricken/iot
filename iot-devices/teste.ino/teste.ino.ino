/*
 *  This sketch sends a message to a TCP server
 *
 */

#include <ESP8266WiFi.h>
#include <ESP8266WiFiMulti.h>

ESP8266WiFiMulti WiFiMulti;

WiFiServer server(1000);

int counter = 1;

int counterServer = 1;

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
  delay(3000);
  Serial.println(WiFi.localIP());
  server.begin();
  Serial.println("Server started");
}

void loop() {
  Serial.println("Aguardando");
  WiFiClient conn = server.available();
  if (!conn) {
    Serial.println("##############################");
    Serial.print("Server Connection failed ");
    Serial.println(counterServer++);
    Serial.println("wait 5 sec...");
    delay(5000);
    return;
  }
  Serial.println("Connection received");
  delay(100);
  Serial.println("Recebendo:");
  String line = conn.readStringUntil('\r');
  while(line.length() == 0){
    delay(200);
    Serial.println("Tentando ler novamente");
    line = conn.readStringUntil('\r');
  }
  Serial.println("Recebido:");
  Serial.println(line);
  conn.print("Send this data to server ");
  conn.println(counter++);
  Serial.println("closing connection");
  conn.stop();
  Serial.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
  Serial.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
  delay(30000);
}

