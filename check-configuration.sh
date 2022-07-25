#!/bin/bash

echo -n "Dotnet " && { dotnet --info | grep Version; }
mvn --version | grep Apache
gradle --version | grep Gradle
python --version
{ java -version 2>&1 | grep version; } || { java --version 2>&1 | grep version; }
echo -n "Node " && node --version
docker --version
echo -n "tmux: " && tmux ls

