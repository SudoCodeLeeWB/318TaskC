# 318TaskC
Task C for CSCI318 



___
### Structure of a Project

Add image here == Architecture image



___

### Run Instructions :


#### Installing Dependency ( Zookeeper , Kafka ) 
* To run this project, we need Kafka and Zookeeper. Normally, zookeeper is included when you download Kafka.


#### 0. Install kafka ( Macos / Linux )
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



#### 1. Kafka , Zookeeper Starting

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
if the message is appearing at the consumer terminal, then it is ready to execute Project!
You can now terminate producer / consumer terminal instance



#### 2. Run multiple Projects ( follow command )
In this project, there are 5 different microservices included. ( This will be explained more in the below Project Structure section. )
we will use maven to do the build / run process

Let's consider you are in the root path : 318TaskC
Open Terminal for each microservice!


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
### Project Structure
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
### Sample CURL Api Calls : 
* Since We have an API Gateway, evey calls will go to this gateway first ( 8080 )


* Use case : 
* Os : 
  * Windows Powershell
  * Windows Cmd
  * MacOs / Linux 





#### References : 
https://kafka.apache.org/documentation/
