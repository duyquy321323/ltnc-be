package com.ltnc.be.adapter.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ltnc.be.port.service.JsonService;
import org.springframework.stereotype.Service;

@Service
public class JsonServiceImpl implements JsonService {
  private static final Gson GSON = new GsonBuilder().create();

  @Override
  public <T> String toJson(T obj) {
    return GSON.toJson(obj);
  }

  @Override
  public <T> T fromJSON(String json, Class<T> clazz) {
    return GSON.fromJson(json, clazz);
  }
}
