#!/bin/bash

arg1="$1"

if [ "$arg1" = "up" ]; then
    echo "Starting EMS !............."
    echo "Running docker compose up --build"
    docker-compose up --build -d
    sleep 5
    echo "EMS db started successfully and running in port 9999"
    echo "phpMyAdmin @ http://localhost:8001"
elif [ "$arg1" = "down" ]; then
    echo "Exporting the database !............."
    docker exec -i db mysqldump -uadmin -ppassword --events --routines EMSDB > ../db/EMSDB.sql
    # Wait for a few seconds to allow the export to complete
    sleep 10

    echo "Successfully exported !"
    echo "Ending EMSDB server ....... running docker-compose down"
    docker-compose down
    echo "EMSDB ended successfully!"

else
    echo "Invalid Argument. Use 'up' or 'down'"
fi
