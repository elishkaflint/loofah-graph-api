#!/usr/bin/env bash

echo $(curl -s -X POST \
  localhost:8080/skills \
  -H 'Content-Type: application/json' \
  -H 'Accept: application/json' \
  -d '{"query":"{ allSkills { title } }"}'
) | json_pp 
