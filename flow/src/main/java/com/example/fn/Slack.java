// package com.example.fn;

// import com.example.fn.messages.*;

// import com.fnproject.fn.api.flow.FlowFuture;
// import com.fnproject.fn.api.flow.Flows;
// import com.fnproject.fn.api.flow.HttpResponse;

// /*
// REFACTOR INTO THIS CLASS AFTER DEMO.
// */
// public class Slack {


//     public static FlowFuture<HttpResponse> postMessageToSlack(String slackFuncID, String channel, String message, String url) {
//         SlackRequest slackRequest = new SlackRequest();
//         slackRequest.image_url = url;
//         slackRequest.msg = "Image from Request 2";
//         return Flows.currentFlow().invokeFunction(slackFuncID, req);
//     }


//     // public static FlowFuture<HttpResponse> postImageToSlack(String slackFuncID, String channel, String url, String filename, String title, String initial_comment) {
//     //     SlackRequest req = new SlackRequest();
//     //     SlackUpload upload = new SlackUpload();
//     //     upload.filename = filename;
//     //     upload.title = title;
//     //     upload.initial_comment = initial_comment;
//     //     upload.url = url;
//     //     req.upload = upload;
//     //     req.channel = channel;
//     //     return Flows.currentFlow().invokeFunction(slackFuncID, req);
//     // }
// }
