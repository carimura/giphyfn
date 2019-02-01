package com.example.fn.messages;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FlowResponse implements Serializable {
    
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Payload implements Serializable {
        
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Google implements Serializable {
            public Boolean expectUserResponse;
            
            @JsonIgnoreProperties(ignoreUnknown = true)
            public static class RichResponse implements Serializable {
                
                @JsonIgnoreProperties(ignoreUnknown = true)
                public static class Items implements Serializable {
                    public String textToSpeech;
                }

                public List<Items> result = new ArrayList<>();

            }
        }
    }
}

 // resp = {
//     "payload": {
//         "google": {
//         "expectUserResponse": "false",
//         "richResponse": {
//             "items": [
//             {
//                 "simpleResponse": {
//                 "textToSpeech": "OK I've invoked the Flow function for you."
//                 }
//             }
//             ]
//         }
//         }
//     }
//     }