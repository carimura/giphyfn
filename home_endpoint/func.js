const fdk = require('@fnproject/fdk');
const request = require('request');

/* 
  This function takes a webhook from Google Home and triggers
  the flow function.

  input: some long json that Google Home sends
  action: triggers the Flow function
  output: formatted Json response that Google Home expects
*/

fdk.handle(function (input) {
  return callFlow(input);
});

async function callFlow(input) {
  var searchPhrase = "cats"

  if (input.queryResult != undefined) {
    if (input.queryResult.queryText != undefined) {
      searchPhrase = input.queryResult.queryText
    }
  }
  console.log("searchPhrase --> " + searchPhrase)

  var url = "https://carimura.ngrok.io/t/giphyfn/flow"

  console.log("Requesting " + url)
  return new Promise((resolve, reject) => {
    request.post(url, {
      headers: { 'content-type': 'application/x-www-form-urlencoded' },
      body: searchPhrase
    },
      function (err, resp, body) {
        if (err) { return console.log(err) }
        r = { 'fulfillmentText': `OK I've triggered the flow function with search term ${searchPhrase}` }
        resolve(r)
      }
    );
  });

}


