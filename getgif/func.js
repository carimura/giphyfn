const fdk = require('@fnproject/fdk');
const request = require('request');
const key = process.env.GIPHY_KEY

/* 
  This function receives an input and returns a Giphy search for that input.

  input: [STRING] "dogs" or [JSON] {q:"dogs"}
  output: [JSON] {response: "<image_url>"}
*/

fdk.handle(function (input) {
  inputType = typeof input

  var q = (inputType == "string") ? input || "cats" : input.q || "cats"

  var offset = Math.floor(Math.random() * 50);
  var fmt = "string"

  var url = "http://api.giphy.com/v1/gifs/search?q=" + q + "&api_key=" + key + "&offset=" + offset + "&fmt=" + fmt + "&limit=1"

  return new Promise((resolve, reject) => {

    request(url, { json: true }, (err, res, body) => {
      if (err) { return console.log(err); }
      img = "https://i.giphy.com/" + body.data[0].id + ".gif"

      resp = { 'response': img }
      resolve(resp)
    });

  });

});