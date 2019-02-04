const fdk = require('@fnproject/fdk');
const request = require('request');
const FN_SERVER_URL = process.env.FN_SERVER_URL

/* 
  This function takes a webhook from Google Home and triggers
  the flow function.

  input: [json] some long json that Google Home sends
  action: triggers the Flow function
  output: [json] response that Google Home expects
*/

fdk.handle(function (input) {
  return callFlow(input);
});

async function callFlow(input) {
  var searchPhrase = "cats"

  if (input.queryResult && input.queryResult.parameters && input.queryResult.parameters.searchPhrase) {
    searchPhrase = input.queryResult.parameters.searchPhrase
  }
  console.log("searchPhrase --> " + searchPhrase)

  var url = FN_SERVER_URL + "/t/giphyfn/flow"

  return new Promise((resolve, reject) => {
    request.post(url, {
      headers: { 'content-type': 'application/x-www-form-urlencoded' },
      body: searchPhrase
    },
      async function (err, resp, body) {
        if (err) { return console.log(err) }
        r = { 'fulfillmentText': `OK I've triggered the flow function with search term ${searchPhrase}` }
        resolve(r)
      }
    );
  });

}


