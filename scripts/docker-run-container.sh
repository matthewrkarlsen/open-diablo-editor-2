#!/bin/sh

cd ..
docker run --publish 127.0.0.1:4666:4666 --detach --name open-diablo-editor open-diablo-editor:2.0.0b1