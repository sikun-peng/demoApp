# Mood-Sensing-App

## The webapp used an open source project as a template

   ```    
   https://github.com/PredixDev/predix-jpa-cf
   ``` 

### DataModel
   ``` 
Sample Picture Object Schema:

{	
	"id":1,
	"mood":"HAPPY",
	"latitude": 123.45,
	"longitude": 543.21,
  	"location":"office"
}
   ``` 
   
   ``` 
Sample Response calling MapService:

{
  "country":"USA",
  "state""CA",
  "city":"San Francisco",
  "location_type":"office"
}
   ``` 
   


### Authorization and Authentication
 -  Predix UAA
 
//This is really lame. 
//Checkout https://swagger.io/
### API: 
 ####  @GET
   ``` 
 http://mood-sensing-app.run.aws-usw02-pr.ice.predix.io/pictures)
 http://mood-sensing-app.run.aws-usw02-pr.ice.predix.io/pictures/{id}
 
 returns picture by id number 
 {"id":785001,"mood":"happy","latitude":123.45,"longitude":543.21, "locatoin_type":"school"}
   ``` 
   
  #### Search
  
   ``` 
 http://mood-sensing-app.run.aws-usw02-pr.ice.predix.io/search?q=J
 
 returns all picture containing letter "J" 
 [ {"id":785001,"mood":"happy","latitude":123.45,"longitude":543.21, "locatoin_type":"school"},
  {"id":785001,"mood":"happy","latitude":123.45,"longitude":543.21, "locatoin_type":"office"}]
   ```
 
# Mood-Sensing-App
