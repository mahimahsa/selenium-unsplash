#!/bin/bash

echo "Waiting for Selenium to be ready at $SELENIUM_HOST..."

until curl -s "$SELENIUM_HOST/status" | grep '"ready": true' > /dev/null; do
  echo "Still waiting for Selenium..."
  sleep 2
done

echo "Selenium is ready."
