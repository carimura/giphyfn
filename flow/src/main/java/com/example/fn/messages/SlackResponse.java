package com.example.fn.messages;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SlackResponse implements Serializable {
    public String ok;
}