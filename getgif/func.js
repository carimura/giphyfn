const fdk=require('@fnproject/fdk');
const request = require('request');
const key = process.env.GIPHY_KEY

/* 
  This function receives an input and returns a Giphy search for that input.

  input: string or json {q:"input"}
  output: json {response: "<image_url>"}
*/

fdk.handle(function(input) {
  console.log("---------------- GetGif Invoked ----------------")
  console.log("input is...") 
  console.log(input)
  inputType = typeof input

  console.log("input is of type " + inputType)

  if (inputType == "string") {
    var q = input || "cats"
  } else  {
    var q = input.q || "cats"
  }

  var offset = Math.floor(Math.random() * 50);
  var fmt = "string"
  
  var url = "http://api.giphy.com/v1/gifs/search?q="+q+"&api_key="+key+"&offset="+offset+"&fmt="+fmt+"&limit=1"

  return new Promise((resolve, reject) => {

    request(url, { json: true, headers: {'Access-Control-Allow-Origin' : '*'} }, (err, res, body) => {
      if (err) { return console.log(err); }
      //console.log(body)
      img = "https://i.giphy.com/"+body.data[0].id+".gif"

      resp = {'response':img}
      console.log("response is...")
      console.log(resp)
      resolve(resp)
    });

  });
  
});