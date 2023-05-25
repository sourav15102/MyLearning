
- build/refer image
- run containers

sample:
```bash
version: "3.8"  
services:  
  gatekeeper:  
    build: ./Gatekeeper  
    image: gatekeeper_img
    container_name: gatekeeper_cont  
    ports:  
      - '6000:6000'  
    volumes:  
      - .:/myapp/Files/  
  sumcalculator:  
    build: ./SumCalculator  
    image: sumcalculator_img  
    container_name: sumcalculator_cont  
    volumes:  
      - .:/myapp/Files/
```
image: will give name to image, if build not specified then pick up existing image.
build: if name given by image already exists, it takes that, otherwise build., and give it that name specified.

Execute:
```bash
docker-compose up -d
docker compose up -d
```
`-d` for detach
