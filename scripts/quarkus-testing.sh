#!/bin/bash
http :8080/api/species
http :8080/api/species name=Hyena habitat=Savana
http :8080/api/species
http :8080/api/species/1/facts fact="Hyenas are not canine"
http :8080/api/species/1/facts fact="Hyenas have a bite force of 1000 PSI"
http :8080/api/species/1/facts
