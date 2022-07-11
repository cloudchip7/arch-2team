# architect-project


docker-compose -f docker-compose-producer.yml up

docker-compose -f docker-compose-producer-api.yml up


docker-compose up --scale redis-master=3 -d


docker-compose -f docker-compose-producer-api-repl.yml up --scale producer=5