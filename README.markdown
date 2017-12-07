A [Giter8][g8] template for showcasing branching of an input Kafka topic into multiple output topics based on certain
conditions.

Kafka KStream Branching
---

### Steps to install Zookeeper and Apache Kafka:

Step 1: Download Kafka

Download Kafka from [here](http://www-eu.apache.org/dist/kafka/1.0.0/kafka_2.11-1.0.0.tgz)

Step 2: Extract downloaded file

```bash
tar -xzvf kafka_2.11-1.0.0.tgz
cd kafka_2.11-1.0.0
```
### Steps to start Zookeeper and Kafka server :

Start Zookeeper:

```bash
bin/zookeeper-server-start.sh config/zookeeper.properties
```

Start Kafka server:

```bash
bin/kafka-server-start.sh config/server.properties
```


---
### Clone Project

```bash
sbt new knoldus/kafka-streams-branching.g8
cd kafka-streams-branching
sbt clean compile
```
---
### Producing tweets from your twitter account into Kafka topic

Step 1: Add your twitter authentication tokens in application.conf.

Step 2:
Execute the following command,

```bash
bin/activator "run-main com.knoldus.demo.TweetProducer"
```
This starts fetching tweets and push each of it into a Kafka topic queue.

---
### Processing the incoming tweets

Step 1:
Execute the following command,

```bash
bin/activator "run-main demo.KStreamDemo"
```

This begins stream processing on the input kafka topic and based on tha tags, it splits the incoming messages into
different output topics.


---
For any issue please raise a ticket @ [Github Issue](https://github.com/knoldus/kafka-streams-branching.g8/issues)

Template license
----------------
Written in 2017 by Himani Arora

To the extent possible under law, the author(s) have dedicated all copyright and related
and neighboring rights to this template to the public domain worldwide.
This template is distributed without any warranty. See <http://creativecommons.org/publicdomain/zero/1.0/>.

[g8]: http://www.foundweekends.org/giter8/
