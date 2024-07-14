void setup() {
  pinMode(9,INPUT);
  pinMode(8,INPUT);
  pinMode(3, OUTPUT);
  pinMode(4, OUTPUT);
  Serial.begin(9600);
}

int previousAvailability1 = 0; // Initialize with any value (e.g., 0)
int previousAvailability2 = 0; // Initialize with any value (e.g., 0)

void loop() {
  int slot1 = 1; // Replace with actual slot number reading
  int slot2 = 2;
  bool available1 = digitalRead(9); // Assuming HIGH indicates occupied
  bool available2 = digitalRead(8);
  if (available1 != previousAvailability1) { // Send update only if availability changes
      String data1 = String(slot1) + "," + String(available1);
      Serial.println(data1);
      if(available1 == true){
        digitalWrite(3, HIGH);   // turn the LED on (HIGH is the voltage level)
        delay(1000); 
      }
      else{
        digitalWrite(3, LOW);    // turn the LED off by making the voltage LOW
        delay(1000);
      }
  }
  previousAvailability1 = available1; // Store previous availability for comparison 

  if (available2 != previousAvailability2) { // Send update only if availability changes
      String data2 = String(slot2) + "," + String(available2);
      Serial.println(data2);
      if(available2 == true){
        digitalWrite(4, HIGH);   // turn the LED on (HIGH is the voltage level)
        delay(1000); 
      }
      else{
        digitalWrite(4, LOW);    // turn the LED off by making the voltage LOW
        delay(1000);
      }
  }
  previousAvailability2 = available2; // Store previous availability for comparison 

  delay(2000);
}
