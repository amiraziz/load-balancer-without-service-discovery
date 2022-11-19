# load-balancer-without-service-discovery

This project is providing two scenario for load balancing microservices without any service registery.

1. In first scenario I used @LoadBalancerClients for registering configs and building `ServiceInstanceListSupplier` bean for every services.
and after that in MyController class I injected `LoadBalancerClient` for finding service with round robin pattern. this part will activate in `direct` profile

2. And in second case I used feign client and it is runnable with `feign` profile. 
