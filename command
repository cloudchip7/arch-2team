

kubectl exec -it custom-auto-scaler-d678cccdb-cgph5  /bin/sh


kubectl describe deployment


version=v3
docker build --pull --force-rm  --file=Dockerfile --tag=gcr.io/architect-certification-289902/23/custom-auto-scaler:v6 .

docker build --pull --file=Dockerfile --tag=gcr.io/architect-certification-289902/23/custom-auto-scaler:v6 .
docker image push gcr.io/architect-certification-289902/23/custom-auto-scaler:v6
kubectl apply -f custom-auto-scaler.yaml
docker rmi gcr.io/architect-certification-289902/23/custom-auto-scaler:v6


./gradle jibDockerBuild

