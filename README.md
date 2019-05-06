# 1. Contact List API

This project was generated with:
```
[Java] version 1.8
[jboss hibernate] version 5.2.17
[Spring boot] version 2.0.4
[Postgree] version 9.6

```

## API URL's

```
##Contacts
GET - getAllContacts
http://localhost:8080/api/contacts

[
	{
		"id": 1,
		"name": "Bruno",
		"lastname": "Brito",
		"details":[
			{
			 	"id":1,
				"phone": "98900-2474",
				"mail": "brunobrito.contato@gmail.com"
			},
			{
				"id" : 1,
				"phone": "98240-4666",
				"mail": "brunobritto.android@gmail.com"
			}
		]
	}
]

POST 
http://localhost:8080/api/contacts
{
	"name": "Bruno",
	"lastname": "Brito"
}

UPDATE
http://localhost:8080/api/contacts/1
{
	"name": "Bruno",
	"lastname": "Correia"
}

DELETE
http://localhost:8080/api/contacts/1

GET - byId
http://localhost:8080/api/contacts/1

##Detail
GET - getAllDetailsbyContactId
http://localhost:8080/api/contacts/1/details
[
{	
	"id" : 1,
	"phone": "98900-2474",
	"mail": "brunobrito.contato@gmail.com"

},
{	
	"id" : 2,
	"phone": "98240-4666",
	"mail": "brunobritto.android@gmail.com"

}
]

POST 
http://localhost:8080/api/contacts/1/details
{	
	"phone": "98900-2474",
	"mail": "brunobrito.contato@gmail.com"
}

UPDATE
http://localhost:8080/api/contacts/1/details/2
{	
	"phone": "98240-4666",
	"mail": "oldwave.studio@gmail.com"
}

DELETE
http://localhost:8080/api/contacts/1/details/2

```

## Run app in IDE
Import Project as MAVEN mode.
