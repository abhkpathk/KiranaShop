Kirana Shop API Documentation

Introduction:
The Kirana Shop API is designed to facilitate transaction management for Kirana stores. It allows users to record transactions, retrieve transaction records by date, and calculate profit/loss for specific dates. The API adheres to the OpenAPI 3.0.1 specifications.
Base URL: http://localhost:5051

Endpoints
1. Record Transaction
Endpoint: /transactions/record
HTTP Method: POST
Description: Records a transaction with details of type, amount, and currency.
Request Body:
{
    "type": "string",
    "amount": 0,
    "currency": "string"
}
 

Response:
200 OK: Transaction recorded successfully.

Attaching screenshot of some scenario/Exception:
1. If all input is correct and transaction is saved successfully.
 
2. If user enter input for type other than Debit or Credit .
 

3. If user enter input for currency other than INR or USD .
 

2. Get Transactions by Date
Endpoint: /transactions/getrecord
HTTP Method: GET
Description: Retrieves transactions for a specific date.
Parameters:
date (query parameter): Date in DD-MM-YYYY format (required).
Response:
200 OK: Returns an array of transaction objects.

 

3.Get Profit/Loss by Date
Endpoint: /transactions/getprofitloss
HTTP Method: GET
Description: Calculates profit/loss for a specific date .
Parameters:
date (query parameter): Date in DD-MM-YYYY format (required).
Response:
200 OK: Returns profit/loss as a string.
 




Data Schemas
TransactionRequest Schema
{
    "type": "string",
    "amount": 0,
    "currency": "string"
}
Transaction Schema
{
    "id": 0,
    "type": "string",
    "currency": "string",
    "amount": 0,
    "transactionDate": "string"
}
Usage:
To interact with the API, send HTTP requests to the specified endpoints using appropriate methods (POST/GET) along with required parameters or request bodies.

