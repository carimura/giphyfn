import fdk
import json
import sys
import requests

"""
  input: 
  output: 
"""
def handler(ctx, data=None, loop=None):
    sys.stderr.write("------- RECEIVING GOOGLE HOME REQUEST! -------\n")
    if data and len(data) > 0:
        body = json.loads(data)
        #sys.stderr.write(json.dumps(body))

    searchPhrase = body["queryResult"]["queryText"]
    sys.stderr.write("\nsearch phrase from Google Home --> " + searchPhrase + "\n")

    process_url = "https://carimura.ngrok.io/t/giphyfn/flow"
    headers = {'Content-Type': 'text/plain'}

    # this kills my response back to Google.. timing out??
    r = requests.post(process_url, headers=headers, data=searchPhrase)
    sys.stderr.write(str(r.status_code))
    
    resp = {
            "payload": {
                "google": {
                "expectUserResponse": "false",
                "richResponse": {
                    "items": [
                    {
                        "simpleResponse": {
                        "textToSpeech": "OK I've invoked the Flow function for you."
                        }
                    }
                    ]
                }
                }
            }
            }

    sys.stderr.write("-------// ENDING HOME_ENDPOINT -------")
    return resp

if __name__ == "__main__":
    fdk.handle(handler)
