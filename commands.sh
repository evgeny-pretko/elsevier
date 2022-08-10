#!/bin/bash

$CMD &
SERVICE_PID=$!

mvn test

wait "$SERVICE_PID"