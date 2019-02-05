package com.example.fn;

import com.fnproject.fn.api.flow.Flow;
import com.fnproject.fn.api.flow.Flows;
import com.fnproject.fn.api.flow.FlowFuture;
import com.fnproject.fn.api.FnFeature;
import com.fnproject.fn.runtime.flow.FlowFeature;
import static com.fnproject.fn.api.flow.Flows.currentFlow;

import java.io.Serializable;
import com.example.fn.messages.*;
import com.fnproject.fn.api.FnConfiguration;
import com.fnproject.fn.api.RuntimeContext;

@FnFeature(FlowFeature.class)
public class FlowFunction implements Serializable {
    String getgifFuncID;
    String slackFuncID;

    @FnConfiguration
    public void configure(RuntimeContext ctx) {
        getgifFuncID = ctx.getConfigurationByKey("GETGIF_FUNC_ID")
                .orElseThrow(() -> new RuntimeException("Missing GetGif Func ID"));
        slackFuncID = ctx.getConfigurationByKey("SLACK_FUNC_ID")
                .orElseThrow(() -> new RuntimeException("Missing Slack Func ID"));
    }

    public void handleRequest(String input) {
        System.out.println("Starting Flow Function");

        Flow flow = Flows.currentFlow();

        GiphyRequest giphyRequest = new GiphyRequest();
        giphyRequest.q = input;

        FlowFuture<SlackResponse> f1 = flow.invokeFunction(getgifFuncID, giphyRequest, GiphyResponse.class)
                .thenCompose((giphyResponse) -> {
                    SlackRequest slackRequest = new SlackRequest();
                    slackRequest.image_url = giphyResponse.response;
                    slackRequest.msg = "Image from Request 1";
                    System.out.println(slackRequest.image_url);
                    return currentFlow().invokeFunction(slackFuncID, slackRequest, SlackResponse.class);
                });

        FlowFuture<SlackResponse> f2 = flow.invokeFunction(getgifFuncID, giphyRequest, GiphyResponse.class)
                .thenCompose((giphyResponse) -> {
                    SlackRequest slackRequest = new SlackRequest();
                    slackRequest.image_url = giphyResponse.response;
                    slackRequest.msg = "Image from Request 2";
                    System.out.println(slackRequest.image_url);
                    return currentFlow().invokeFunction(slackFuncID, slackRequest, SlackResponse.class);
                });

        flow.allOf(f1, f2).whenComplete((v, throwable) -> {
            if (throwable != null) {
                SlackRequest slackRequest = new SlackRequest();
                slackRequest.msg = "There was an error!";
                currentFlow().invokeFunction(slackFuncID, slackRequest, SlackResponse.class);

            } else {
                SlackRequest slackRequest = new SlackRequest();
                slackRequest.msg = "Completed Flow Successfully!";
                currentFlow().invokeFunction(slackFuncID, slackRequest, SlackResponse.class);
            }
        });
    }
}