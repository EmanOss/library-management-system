package com.example.library.mapper;

public interface Mapper<Entity, Dto> {

    Dto mapToDto(Entity entity);

    Entity mapFromDto(Dto dto);
}
