package com.ltnc.be.port.service;

public interface JsonService {
  <T> String toJson(T obj);

  <T> T fromJSON(String json, Class<T> clazz);
}
