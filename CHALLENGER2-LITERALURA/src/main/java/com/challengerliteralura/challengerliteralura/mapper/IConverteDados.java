package com.challengerliteralura.challengerliteralura.mapper;

public interface IConverteDados {

    <T> T obterDados(String json, Class<T> classe);

}