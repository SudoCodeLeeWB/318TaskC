Task C for CSCI318 

* WonbeenLee (8243657)
* Mayowa Adeniyi (7684861)


___


# Running Instructions
* To run this project, we need Kafka and Zookeeper. Normally, zookeeper is included when you download Kafka.


## 0. Install Dependencies (kafka) - ( Macos / Linux )
* Using wget :
```shell
wget https://downloads.apache.org/kafka/3.0.0/kafka_2.13-3.0.0.tgz
tar -xzf kafka_2.13-3.0.0.tgz
```

Or

* Go to Kafka Download page :
https://kafka.apache.org/downloads
![KafkaInstallation](./ReadmeImageFiles/KafkaInstallation.png)

* Download 3.8.0 binary file ( recommend to install Scala 2.13 )


___
## 1. Kafka , Zookeeper Starting

Move to Kafka binary Directory
```shell
cd kafka_2.13-3.8.0  
```

To run kafka broker, you need to run zookeeper first. 
* Run zookeeper
```shell
bin/zookeeper-server-start.sh config/zookeeper.properties
```

* Open a new Terminal and Run Kafka Message Broker
```shell
bin/kafka-server-start.sh config/server.properties
```


Before going to next step, lets try adding a new topic and check it can produce - consume properly  
Open Another terminal , go to the same kafka path  

* Create sample topic :
```shell
bin/kafka-topics.sh --create --topic test-topic --bootstrap-server localhost:9092 --partitions 1 --replication-factor 1
```
* List all topics to check topic generation
```shell
bin/kafka-topics.sh --list --bootstrap-server localhost:9092
```

* Produce a test message ( producer )
```shell
bin/kafka-console-producer.sh --topic test-topic --bootstrap-server localhost:9092
```


Open Another terminal , go to the same kafka path
* Consume a test message ( consumer )
```shell
bin/kafka-console-consumer.sh --topic test-topic --from-beginning --bootstrap-server localhost:9092
```

##### Conclusion: 
We have 4 different terminals opened  
In the Producer Terminal, type a message and press return.  
if the message is appearing at the consumer terminal, then it is ready to execute project  
You can now terminate producer / consumer terminal instance  


___
## 2. Run multiple Projects ( follow command )
In this project, there are 5 different microservices included. ( This will be explained more in the below Project Structure section. )  
We will use maven to do the build / run process  

Let's consider you are in the root path : 318TaskC  
Open Terminal for each microservice  

* Before executing microservices, we need to first build shared-events. This is a shared event which will be used for entire kafka broker. 
* This is because, system tries to deserialize the event as a same event.
* Go to shared-events directory and execute below command
```shell
mvn clean install -DskipTests
```

Now, we can start running projects
* We Strongly Recommend to run this project in windows / Linux systems. 
* The Api gateway might not properly not work in MacOS environments, especially Apple silicon devices. 
* This is due to **"netty-resolver-dns-native-macos"** dependency.



* ApiGateWay
```shell
cd ApiGateWay
mvn clean install
mvn spring-boot:run
```

* OrderService 
```shell
cd OrderService
mvn clean install
mvn spring-boot:run
```

* ProductService
```shell
cd ProductService
mvn clean install
mvn spring-boot:run
```

* ShippingMicroService
```shell
cd ShippingMicroService
mvn clean install
mvn spring-boot:run
```

* UserService
```shell
cd UserService
mvn clean install
mvn spring-boot:run
```


___
## Project Structure
![ProjectStructure](./ReadmeImageFiles/projectStructureImage.png)

Using the Domain Driven Design Principles and Event Driven Principles,
we have .. 
* Core domain : Order
* Supporting Domain : Product , User , Shipping 
* Generic : Payment ( 3rd party, which will not be implemented, but still Order Microservice has a Rest Client for this)

And we Also have other microservices that are not a part of a problem domain, such as..
* Real Time analysis ( Analysis )
* Api GateWay ( ApiGW ) to route user's Rest calls. 

___

## Sample CURL Api Calls :

### Use case 1 : **Add new Product to Order**
  
Windows Powershell
```shell
Invoke-RestMethod -Uri 'http://localhost:8080/order/addNew/1/1/1' -Method Patch
```
Windows Cmd
```shell
curl --location --request PATCH "http://localhost:8080/order/addNew/1/1/1"
```
MacOs / Linux
```shell
curl --location --request PATCH 'http://localhost:8080/order/addNew/1/1/1'
```

### Use case 2 : **Increase quantity of an existing product**

Windows Powershell
```shell
Invoke-RestMethod -Uri 'http://localhost:8080/order/add/1/1/1' -Method Patch
```
Windows Cmd
```shell
curl --location --request PATCH "http://localhost:8080/order/add/1/1/1"
```
MacOs / Linux
```shell
curl --location --request PATCH 'http://localhost:8080/order/add/1/1/1'
```


### Use case 3 : **Deduct quantity of an existing product **

Windows Powershell
```shell
Invoke-RestMethod -Uri 'http://localhost:8080/order/remove/1/1/1' -Method Patch
```
Windows Cmd
```shell
curl --location --request PATCH "http://localhost:8080/order/remove/1/1/1"
```
MacOs / Linux
```shell
curl --location --request PATCH 'http://localhost:8080/order/remove/1/1/1'
```

### Use case 4 : **Place Order**

Windows Powershell
```shell
Invoke-RestMethod -Uri 'http://localhost:8080/order/placeOrder/1' -Method Patch
```
Windows Cmd
```shell
curl --location --request PATCH "http://localhost:8080/order/placeOrder/1"
```
MacOs / Linux
```shell
curl --location --request PATCH 'http://localhost:8080/order/placeOrder/1'
```

### Use case 5 : **Cancel Order**

Windows Powershell
```shell
Invoke-RestMethod -Uri 'http://localhost:8080/order/cancelOrder/1' -Method Patch
```
Windows Cmd
```shell
curl --location --request PATCH "http://localhost:8080/order/cancelOrder/1"
```
MacOs / Linux
```shell
curl --location --request PATCH 'http://localhost:8080/order/cancelOrder/1'
```

### Use case 7 : **Add new Product into Product Service**

Windows Powershell
```shell
Invoke-RestMethod -Uri 'http://localhost:8080/product/add' -Method Post -ContentType 'application/json' -Body '{
    "name": "Smartphone",
    "description": "Latest smartphone model",
    "price": 999.99,
    "stock": 50,
    "category": "ELECTRONICS"
}'
```
Windows Cmd
```shell
curl --location "http://localhost:8080/product/add" ^
--header "Content-Type: application/json" ^
--data "{
    \"name\": \"Smartphone\",
    \"description\": \"Latest smartphone model\",
    \"price\": 999.99,
    \"stock\": 50,
    \"category\": \"ELECTRONICS\"
}"
```
MacOs / Linux
```shell
curl --location 'http://localhost:8080/product/add' \
--header 'Content-Type: application/json' \
--data '{
    "name": "Smartphone",
    "description": "Latest smartphone model",
    "price": 999.99,
    "stock": 50,
    "category": "ELECTRONICS"
}'
```

### Use case 8 : **Update Product Stock**

Windows Powershell
```shell
Invoke-RestMethod -Uri 'http://localhost:8080/product/updateStock/1/20' -Method Patch -ContentType 'application/json'
```
Windows Cmd
```shell
curl --location --request PATCH "http://localhost:8080/product/updateStock/1/20" ^
--header "Content-Type: application/json"
```
MacOs / Linux
```shell
curl --location --request PATCH 'http://localhost:8080/product/updateStock/1/20' \
--header 'Content-Type: application/json'
```


### Use case 9 : **Delete Product From Product Service**

Windows Powershell
```shell
Invoke-RestMethod -Uri 'http://localhost:8080/product/delete/1' -Method Delete -ContentType 'application/json'
```
Windows Cmd
```shell
curl --location --request DELETE "http://localhost:8080/product/delete/1" ^
--header "Content-Type: application/json"
```
MacOs / Linux
```shell
curl --location --request DELETE 'http://localhost:8080/product/delete/1' \
--header 'Content-Type: application/json'
```


### Usecase 10 : **Update Order Shipment Status**

Windows Powershell
```shell

```
Windows cmd (NOT THIS, TODO)
```cmd
curl -X PATCH -H "Content-Type:application/json" -d "{}" http://localhost:8085/shipment?orderID=23
```

MacOS / Linux
```shell

```




























#### References : 
https://kafka.apache.org/documentation/

