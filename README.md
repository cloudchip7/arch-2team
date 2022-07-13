# architect-project


docker-compose -f docker-compose-producer.yml up

docker-compose -f docker-compose-producer-api.yml up


docker-compose up --scale redis-master=3 -d


docker-compose -f docker-compose-producer-api-repl.yml up --scale producer=5



rabbitmq-producer-schedule 와 rabbitmq-producer-schedule2는 같은 소스코드이다. 
