#!/bin/bash
docker-compose down --volumes
docker rm -f freq-counter-app
docker-compose up --build --force-recreate --remove-orphans