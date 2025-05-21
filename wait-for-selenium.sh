#!/bin/bash

echo "Waiting for Selenium to be ready at $SELENIUM_HOST..."

until curl -s "/status" | grep '"ready": true' > /dev/null; do
  attempts=$((attempts+1))
  echo "Attempt $attempts: Selenium not ready yet..."
  sleep 2

  if [ $attempts -gt 15 ]; then
    echo "Timeout waiting for Selenium."
    exit 1
  fi
done

echo "Selenium is ready."
