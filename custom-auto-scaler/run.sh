version=v3
docker build --pull --force-rm  --file=Dockerfile --tag=gcr.io/architect-certification-289902/23/custom-auto-scaler:v3 .
docker image push gcr.io/architect-certification-289902/23/custom-auto-scaler:v3
kubectl apply -f custom-auto-scaler.yaml
docker rmi gcr.io/architect-certification-289902/23/custom-auto-scaler:v3