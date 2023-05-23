#!/bin/bash

# Check if the process name is provided as an argument
if [ $# -eq 0 ]; then
  echo "Please provide the process name as an argument."
  exit 1
fi

# Get the process name from the argument
process_name=$1

# Get the PIDs of the Java processes matching the name
pids=$(pgrep -fl "$process_name" | awk '{print $1}')

# Iterate through each PID and display the PID, RSS in MB, and command
for pid in $pids; do
    rss_kb=$(ps -o rss -p "$pid" | awk 'NR==2')
    rss_mb=$(bc <<< "scale=2; $rss_kb / 1024")
    command=$(ps -o command -p "$pid" | awk 'NR==2')
    echo "PID: $pid | RSS: $rss_mb MB | Command: $command"
done
