# Poc-Multitenancy

This proof of concept created to validate the behaviour of the multitenancy architecure.

The approach selected is routing by share db and different scheme.

## Test Service By Postman

* Get all user for tenant1 

     Curl Example local : 
  
    ```bash
  curl --location --request GET 'http://localhost:8080/poc-multitenant-api/v1/user/' \
  --header 'X-TenantID: tenant1'
     ```

* Create user for tenant2 

     Curl Example local : 
  
    ```bash
  curl --location --request POST 'http://localhost:8080/poc-multitenant-api/v1/user/' \
  --header 'Content-Type: application/json' \
  --header 'X-TenantID: tenant2' \
  --data-raw '{
      "name": "Mumbai"
  }'
    ```
* Get user by id for tenant1

     Curl Example local : 
  
    ```bash
  curl --location --request GET 'http://localhost:8080/poc-multitenant-api/v1/user/1' \
  --header 'X-TenantID: tenant1'
     ```

* Delete user by id for tenant2

     Curl Example local : 
  
    ```bash
  curl --location --request DELETE 'http://localhost:8080/poc-multitenant-api/v1/user/1' \
  --header 'X-TenantID: tenant2'
     ```

### Exception Messages

* Tenant field null : `code 412 : Precondition failed`

    ```json
  
  {
      "status": 412,
      "errors": [
          {
              "internalMessage": "Tenant is null",
              "developerMessage": "Error during execution tenant validation."
          }
      ]
  }
     ```
  
* Tenant doesn't exist : `code 412 : Precondition failed`
  
     ```json
  {
      "status": 412,
      "errors": [
          {
              "internalMessage": "Tenant doesn't exist",
              "developerMessage": "Error during execution tenant validation."
          }
      ]
  }
    ```
