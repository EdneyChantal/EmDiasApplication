#!/usr/bin/env bash

JAVA_OPTS="-Xms128m -Xmx256m"
java -jar /home/eschantal/desenvolvimento/myproject/arquitetura/jhipster-registry-5.0.2.jar --spring.profiles.active=dev,swagger --spring.security.user.password=admin --jhipster.registry.password=admin --spring.cloud.config.server.composite.0.type=native --spring.cloud.config.server.composite.0.search-locations=/home/eschantal/desenvolvimento/scripts-sefaz-al/meus-projetos/central-server-config/localhost-config --jhipster.security.authentication.jwt.base64-secret=Y2UxODk5MzY1ZDU4ZmI0NmM2ZWI5OWVhNzdiMzY2ODE2ZTg1ZmNhMGE2Y2M3OTAxYmJhYjA4MWVmNTYwNzdhN2Q2NDU2ZWYwNTI0MDE4Zjk3N2JhYjIxYTMwMWM1NTlkMjQ1M2I0OWI4ZDZjNmM0ZTc5YTJiMmNhMzg2NjMzZTQ=

