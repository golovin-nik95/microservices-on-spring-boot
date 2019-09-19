# microservices-on-spring-boot
Capstone project of the course "Java Engineer to Scalable Backend Developer" at Grid Dynamics

## How to run?

### Build all modules:

`./gradlew clean build`

### Start infrastructure modules in docker:

`docker-compose up -d zipkin-server config-server service-registry hystrix-dashboard`

### Start each service either in local or in docker:

**Local:** `./gradlew <service>:bootRun`

**Docker:** `docker-compose up -d <service> --build --force-recreate`

## Services

* Zipkin-server:
    * hostname: zipkin-server
    * ports: 9411:9411
    * admin UI: http://localhost:9411/zipkin

* Config-server: 
    * hostname: config-server
    * ports: 8888:8888

* Service-registry: 
    * hostname: service-registry
    * ports: 8761:8761

* Hystrix-dashboard: 
    * hostname: hystrix-dashboard
    * ports: 8788:8788
    * admin UI: http://localhost:8788/hystrix

* Catalog-service: 
    * hostname: catalog-service
    * ports: 8181:8181

* Inventory-service: 
    * hostname: inventory-service
    * ports: 8282:8282

* Product-service: 
    * hostname: product-service
    * ports: 8383:8383

## How to test project scenarios

### Check project API

There is a Postman collection "ngolovin-mosb.postman_collection.json" in the project root directory, 
which you can import to test the API, or use the commands below

* Catalog-service:
    * `curl -X GET http://localhost:8181/v1/api/catalogs/b6c0b6bea69c722939585baeac73c13d`
    * `curl -X GET http://localhost:8181/v1/api/catalogs?sku=pp5006380337`

* Inventory-service:
    * `curl -X POST http://localhost:8282/v1/api/inventories \
         -H 'Content-Type: application/json' \
         -d '["b6c0b6bea69c722939585baeac73c13d", "93e5272c51d8cce02597e3ce67b7ad0a", "013e320f2f2ec0cf5b3ff5418d688528"]'`

* Product-service:
    * `curl -X GET http://localhost:8383/v1/api/products/b6c0b6bea69c722939585baeac73c13d`
    * `curl -X GET http://localhost:8383/v1/api/products?sku=pp5006380337`

### Check Netflix Eureka work

Visit http://localhost:8761/ to see instances currently registered with Eureka

### Check Netflix Hystrix work

1. Open Hystrix dashboard http://localhost:8788/hystrix
2. Enter Netflix Turbine stream URL http://localhost:8788/turbine.stream or 
product-service hystrix stream http://localhost:8383/actuator/hystrix.stream and click button to run monitoring
3. Fire product-service API calls. 
Every third request to the catalog-service occurs with a 3 seconds delay.
Every fifth request to the inventory-service occurs with a 5 seconds delay. 
After 5 failed calls, the circuit breaker opens for 30 seconds, 
during which error '503 service unavailable' is returned on each request.

### Check Zipkin work

1. Fire product-service API calls
2. Open Zipkin dashboard http://localhost:9411/zipkin and see distributed traces
