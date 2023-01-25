# SupakiAssignment

A marketplace to purchase gaming items.

## Instructions to run the application

Run any desired function inside the makefile. (preferably 'run')

## Technology stack

**Spring-Boot** <br>
**PostgreSQL** <br>

## APIS

### Create User

#### /user [POST]
To create a user.
<br>
Payload:
<br>
{
    "emailAddress":"anishksaha1997@gmail.com",
    "name": "Anish",
    "initialBalanceAmount": 500.00
}

<br>
<br>

#### /purchase  [POST]
To make a purchase of an item from the marketplace.
<br>
Payload:
<br>
{
    "itemId": "f1a6483e-14c7-425a-be86-646b9d01aa4b",
    "userId": "665c63f7-493c-49a2-8f5d-5ff86bb17e96"
}

<br>
<br>

#### /listings GET]
To view all the items available for sale in the marketplace.
<br>
URL format:  /listings?page=0&size=10

<br>
<br>

#### /orders [GET]
To view all the orders made by a particular user.
<br>
URL format: /orders?userId=53b2465b-daf1-44ae-86a4-1c4af0d7f647&page=0&size=10

<br>
<br>

#### /wallet [GET]
To view wallet details including the balance of a particular user.
<br>
URL format: /wallet?userId=53b2465b-daf1-44ae-86a4-1c4af0d7f647

<br>
<br>


## Assumptions and considerations
**1.** Here 'order' is separate which shows the list of items purchased by the user. <br>
**2.** Here 'wallet' is used to show the balance of a particular user.
**3.** The same item (with the same external id) cannot be loaded again in the database.







