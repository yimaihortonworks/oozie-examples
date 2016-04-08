#!/bin/bash

mvn package -DskipTests
rm -fr target/examples-oozie
tar -xvzf target/oozie-examples-0.0.1-SNAPSHOT-bundle.tar.gz -C target
hadoop fs -rmr /workflows/oozie-examples
hadoop fs -put target/oozie-examples /workflows/oozie-examples
export OOZIE_URL=http://sandbox.hortonworks.com:11000/oozie
oozie job -config target/oozie-examples/job.properties -D jump.to=java -run
