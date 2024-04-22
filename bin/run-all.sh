#!/bin/bash

# Get the directory of the current script
SCRIPT_DIR="$(pwd)"

modules=("$SCRIPT_DIR/gateway/gateway-service" "$SCRIPT_DIR/auth/auth-service" "$SCRIPT_DIR/user/user-service" "$SCRIPT_DIR/wallet/wallet-service")
pids=()

# Function to stop services when script is terminated
stop_services() {
    for pid in "${pids[@]}"
    do
        if kill -0 $pid 2>/dev/null; then
            echo "Stopping service with PID $pid..."
            kill $pid
        fi
    done
    echo "Stopped"
    exit 0
}

# Trap various signals and call stop_services() when they occur
trap stop_services SIGINT SIGTERM SIGQUIT

for module in "${modules[@]}"
do
    echo "Starting $module..."
    if cd "$module"; then
        "$SCRIPT_DIR/mvnw" spring-boot:run &
        sleep 1  # Give the process a moment to start
        pids+=($!)
    else
        echo "Failed to navigate to $module directory. Skipping..."
    fi
done

echo "All services started! Press Ctrl+C to stop."

# Wait forever, until a signal is caught
while true; do
    sleep 1
done
