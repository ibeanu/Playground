**MORTGAGE APPLICATION**

To Run using docker

Ensure docker is installed and then run:

mvn package docker:build    ---- To create an docker image

docker run -t -p8080:8080 ikenna1/mortgage - to run the image

Tomcat will run on port 8080

MediaType - JSON

Endpoints:

POST/{your docker host-ip}//mortgage/loan - TO CREATE A NEW LOAN
Loan Payload example:
{
"firstName":"test",
"lastName":"test",
"amount":"10",
"startDate":"2015-10-11",
"endDate":"2016-01-01",
"rate":0.05
}

POST/{your docker host-ip}//mortgage/loan/{load_id}/invest - TO CREATE AN INVESTMENT
loan_id - loan id of loan to be invested (i.e 1)
Investment payload:
{
"firstName":"test",
"lastName":"test",
"amount":5,
"startDate":"2015-12-15"
}

GET/{your docker host-ip}//mortgage/loan/id=1&startDate=2015-12-01&endDate=2015-12-20
id - is the loan id and required
startDate and endDate  - represents the date range for investments associated with this loan (not required - defaults to all associated investment)
Example response:
 {
   "id": 1,
   "firstName": "test",
   "lastName": "test",
   "amount": 10,
   "startDate": "2015-10-11T00:00:00.000Z",
   "endDate": "2016-01-01T00:00:00.000Z",
   "rate": 0.05,
   "investments": [
     {
       "id": 4,
       "firstName": "test",
       "lastName": "test",
       "amount": 5,
       "startDate": "2015-12-10T00:00:00.000Z",
       "interestOwed": 0.55
     },
     {
       "id": 3,
       "firstName": "test",
       "lastName": "test",
       "amount": 5,
       "startDate": "2015-12-15T00:00:00.000Z",
       "interestOwed": 0.3
     }
   ]
 }
 
 You can run without docker:
 
  mvn package && java -jar target/mortgage-1.0.jar
  
 Then run endpoints on localhost.
 
 **Assumptionsor Ommitions:**
 
 Spring Active Profile was not used to run for production mode
 Relation between loan and investment is one to many and reverse many to one relationship.
 Investment does not need to be removed.
 
 Thanks.

