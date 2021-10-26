#!/usr/bin/env bash
docker-compose -f postgresql.yml up -d 
./levanta-jregistry.sh &
./levanta-api.sh &
./levanta-gateway.sh &


