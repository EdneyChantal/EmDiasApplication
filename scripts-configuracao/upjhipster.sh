#!/usr/bin/env bash
docker container run --name jhipster -v ~/desenvolvimento/myproject/EmDiasApi:/home/jhipster/app -v ~/desenvolvimento/.m2:/home/jhipster/.m2 -v ~/desenvolvimento/myproject/EmDiasGateway:/home/jhipster/gateway -p 8080:8080 -p 9001:9000 -p 3001:3001 -d -t jhipster/jhipster
