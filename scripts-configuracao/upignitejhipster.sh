#!/usr/bin/env bash
docker container run --name ignitejhipster -v ~/desenvolvimento/myproject/.m2:/home/jhipster/.m2 -v ~/desenvolvimento/myproject/EmDiasMicroServicoApp:/home/jhipster/app -v ~/desenvolvimento/myproject/EmDiasMobile:/home/jhipster/gateway -p 8080:8080 -p 9001:9000 -p 3001:3001 -d -t ignitejhipster
