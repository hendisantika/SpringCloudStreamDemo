# Marco-Polo: A Spring Cloud Stream Demo

This repository contains two separate Spring Cloud Stream applications:

* Marco : A message producer (e.g., a Spring Cloud Stream Source)

* Polo : A message consumer (e.g., a Spring Cloud Stream Sink)

When running, Marco will produce a message every 1 second. Polo will consume the messages, but with a caveat. To simulate a slow consumer and to achieve a backed up queue, Polo will sleep for 1 minute after consuming the message. Since Marco is publishing more frequently than Polo is consuming, the queue could potentially back up and grow to a size unlimited by nothing by platform resources.

Polo, however, is configured to not let that happen. As the consumer of the messages, Polo is responsible for creating the queue. When it creates the queue, it sets a message time-to-live (TTL) to 120000 (e.g., 2 minutes). Therefore, any messages that are left unconsumed by Polo after 2 minutes will be automatically discarded by the broker.

### Running the applications

First, you’ll need to start a RabbitMQ broker. (See https://www.rabbitmq.com/download.html for details.)

With the broker started, you can run Marco and Polo just as you would any Spring Boot application. This includes importing them into Spring Tool Suite and using the Boot Dashboard or building them and running the executable JAR files produced by the build.

For simplicity’s sake, you can run them using the spring-boot Maven plugin. Open two console windows, one for Marco and one for Polo, and change into the corresponding project directory for each window. Then perform the following command in each window:

`mvnw spring-boot:run`

It does not matter whether you start Marco first or Polo first. After both apps are running, you should see Polo logging the message it received from Marco, once every minute.

#Examining the queue

While Marco and Polo are running, you can examine the queue by pointing a web browser to the RabbitMQ console (http://localhost:15672/ if running locally). Click on the "Queues" tab at the top and you should see the "marcoPolo" queue listed. The actual queue name will vary, but will always begin with `marcoPolo.anonymous..`

![Overview](img/overview.png "Overview Message")



Notice the items under the "Features" column. These tell use three important things about the queue:

 * `Excl` - This is an exclusive queue. Polo created the queue and Polo is the only client that can consume messages from the queue. When Polo dies, the queue will be destroyed.

 * `AD` - The queue is set to auto-delete. The queue will be deleted when the last subscriber unsubscribes. This feature is all but meaningless in light of the Excl feature.

 * `TTL` - This queue has a time-to-live set for its messages.

You’ll also notice that the total number of messages in the queue is capped out at roughly 120. It might be slightly higher or lower depending on the timing of when the data is captured, but it will never exceed greatly beyond 120 as long as there is only one producer that is producing messages at a rate of one per second.

If you click on the queue name, you’ll see more details about the queue. This include a graph that shows how many messages are in the queue.

![Messages](img/messages.png "Overview Message")

Again, this shows that the queue has maxed out at roughly 120 messages.

It’s important to understand that the queue size isn’t capped on a specific number of messages, but rather each message is given a maximum TTL. If there are more than one producer and/or if the producer is producing messages at a rate faster than 1 per second, then the queue size will increase. But none of the messages will survive in the queue without either being consumed or being automatically removed by the broker after their TTL expires.


