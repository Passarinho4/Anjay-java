#!/bin/bash
java -Djava.library.path=library/build/cmake -jar demo/build/libs/demo.jar --lifetime 90 -e $DEVICEID -u coap://$SERVER_ADDRESS:5683
