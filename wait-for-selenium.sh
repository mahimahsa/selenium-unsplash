#!/bin/bash

if [ -z "$SELENIUM_HOST" ]; then
  echo "❌ SELENIUM_HOST is not set"
  exit 1
fi

echo "Waiting for Selenium to be ready at $SELENIUM_HOST..."

attempts=0

until curl -s "$SELENIUM_HOST/status" | grep '"ready": true' > /dev/null; do
  attempts=$((attempts+1))
  echo "Attempt $attempts: Selenium not ready yet..."
  sleep 2

  if [ $attempts -gt 15 ]; then
    echo "❌ Timeout waiting for Selenium."
    exit 1
  fi
done

echo "✅ Selenium is ready."
