# Spring Boot + TDD example

## Run Spring Boot application
```
mvn spring-boot:run
```

Docker:
-------
docker build -t cloudmahesh/sb-jpa-tdd-bdd-h2:tag1 .
docker push cloudmahesh/sb-jpa-tdd-bdd-h2:tag1

Minikube:
---------
minikube start
minikube tunnel
minikube stop

minikube image load cloudmahesh/sb-jpa-tdd-bdd-h2:tag1
minikube image ls
minikube image rm cloudmahesh/sb-jpa-tdd-bdd-h2:tag1

K8S:
----
kubectl get nodes
kubectl create deployment sb-jpa-tdd-bdd-h2 --image=cloudmahesh/sb-jpa-tdd-bdd-h2:tag1
kubectl get deployments
kubectl expose deployment sb-jpa-tdd-bdd-h2 --type LoadBalancer --port 8080 --target-port 8080
kubectl get pods
kubectl get service sb-jpa-tdd-bdd-h2
kubectl get services

K8S-Manifest:
-------------
kubectl apply -f sb-jpa-tdd-bdd-h2.yml
kubectl delete -f sb-jpa-tdd-bdd-h2.yml

PostMan collection: <br>
-------------------- <br>
Refer sb-jpa-tdd-bdd-h2.postman_collection.json