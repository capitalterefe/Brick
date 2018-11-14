Feature: Manage Pet Owners Information From The Dashboard
@dryRun
Scenario: Register A new Pet Owner
Given Admin Is on Pet Clinic Home Page 
When Admin Register A New Owner 
|firstName|lastName|address			 |city		|telephone		|
|John	  |Doe	   |125 Smooth Dr	 |citi		|2022300010		|
Then Admin Verify Newly Registered User Exist in the Database 
|firstName|
|John 	  |
Then Admin Should Able To Search A Registered Owner with FirstName 
|firstName |
|John	 	 |
Then Admin Should Able To Search A Registered Owner with LastName 
|lastName		  |
|Doe			  |
Then Admin Should Able To Search A Registered Owner with Address
|address	  	  |
|125 Smooth Dr	  |
Then Admin Should Able To Search A Registered Owner with City
|city	  |
|citi	  |
Then Admin Should Able To Search A Registered Owner with Telephone
|telephone		|
|2022300010	    |


