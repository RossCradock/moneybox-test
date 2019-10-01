package com.example.minimoneybox.Network;

import com.example.minimoneybox.Model.Session;
import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class SessionDeserializer implements JsonDeserializer<Session> {
    @Override
    public Session deserialize(JsonElement je, Type type, JsonDeserializationContext jdc)
            throws JsonParseException  {
        // Get the "Session" element from the parsed JSON
        JsonElement session = je.getAsJsonObject().get("Session");

        return new Gson().fromJson(session, Session.class);
    }
}
