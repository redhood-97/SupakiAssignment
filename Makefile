build:
	mvn clean install
run:
	mvn spring-boot:run
docker:
	docker build -t xyz:latest .
docker-run:
	docker run -p 8083:8083 xyz:latest
compose:
	docker compose up