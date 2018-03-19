# Marco-Polo: A Spring Cloud Stream Demo

This repository contains two separate Spring Cloud Stream applications:

* Marco : A message producer (e.g., a Spring Cloud Stream Source)

* Polo : A message consumer (e.g., a Spring Cloud Stream Sink)

When running, Marco will produce a message every 1 second. Polo will consume the messages, but with a caveat. To simulate a slow consumer and to achieve a backed up queue, Polo will sleep for 1 minute after consuming the message. Since Marco is publishing more frequently than Polo is consuming, the queue could potentially back up and grow to a size unlimited by nothing by platform resources.

Polo, however, is configured to not let that happen. As the consumer of the messages, Polo is responsible for creating the queue. When it creates the queue, it sets a message time-to-live (TTL) to 120000 (e.g., 2 minutes). Therefore, any messages that are left unconsumed by Polo after 2 minutes will be automatically discarded by the broker.
