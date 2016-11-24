#!/bin/sh

java -cp target/lib/*:target/classes -Dhazelcast.config=/home/uranadh/hazelcast.xml com.hazelcast.samples.basics.Member
