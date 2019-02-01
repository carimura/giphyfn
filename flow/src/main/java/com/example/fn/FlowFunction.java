package com.example.fn;

import com.fnproject.fn.api.flow.Flow;
import com.fnproject.fn.api.flow.Flows;
import com.fnproject.fn.api.flow.FlowFuture;
import com.fnproject.fn.api.FnFeature;
import com.fnproject.fn.runtime.flow.FlowFeature;
import static com.fnproject.fn.api.flow.Flows.currentFlow;

import com.example.fn.messages.*;
// import com.fnproject.fn.api.FnConfiguration;
// import com.fnproject.fn.api.RuntimeContext;

@FnFeature(FlowFeature.class)

public class FlowFunction {

    // String giphyKey;

    // @FnConfiguration
    // public void configure(RuntimeContext ctx) {
    //     giphyKey = ctx.getConfigurationByKey("GIPHY_KEY").orElseThrow(() -> new RuntimeException("Missing Giphy Key"));
    // }

    public void handleRequest(String input) {
        System.out.println("---------------------- Flow Invoked ----------------------");
        System.out.println("input --> " + input);
        
        Flow flow = Flows.currentFlow();

        GiphyRequest giphyRequest = new GiphyRequest();
        giphyRequest.q = input;

        // Return the image URL in GiphyResponse
        FlowFuture<SlackResponse> f1 = flow.invokeFunction("01D2GRXN83NG8G00GZJ000006N", giphyRequest, GiphyResponse.class)
            .thenCompose((giphyResponse) -> {
                SlackRequest slackRequest = new SlackRequest();
                slackRequest.image_url = giphyResponse.response;
                slackRequest.msg = "Image from Request 1";
                return currentFlow().invokeFunction("01D2N6WGASNG8G00GZJ00000KR", slackRequest, SlackResponse.class);
            });

        FlowFuture<SlackResponse> f2 = flow.invokeFunction("01D2GRXN83NG8G00GZJ000006N", giphyRequest, GiphyResponse.class)
            .thenCompose((giphyResponse) -> {
                SlackRequest slackRequest = new SlackRequest();
                slackRequest.image_url = giphyResponse.response;
                slackRequest.msg = "Image from Request 2";
                return currentFlow().invokeFunction("01D2N6WGASNG8G00GZJ00000KR", slackRequest, SlackResponse.class);
            });

        flow.allOf(f1, f2);
            // .whenComplete((v, throwable) -> {
            //     System.out.println("INSIDE WHEN COMPLETE");
            // });

        // String resp = "{\"payload\": {\"google\": {\"expectUserResponse\": \"false\",\"richResponse\": {\"items\": [{\"simpleResponse\": {\"textToSpeech\": \"OK I have invoked the Flow function for you.\"}}]}}}}";
        // System.out.println("Intended response back to google --> " + resp);
        // return resp;
    }
}