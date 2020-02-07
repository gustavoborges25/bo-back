package com.gusgo.bo.entity;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class IbgeCity {

    private int id;
    private String nome;

    @JsonProperty("microrregiao")
    private int stateIbgeId;

    @JsonSetter
    public void setStateIbgeId(Map<String,Object> microrregiao) {
        Map<String,Object> mesorregiao = (Map<String,Object>) microrregiao.get("mesorregiao");
        Map<String,Object> uf = (Map<String,Object>) mesorregiao.get("UF");
        this.stateIbgeId = (int) uf.get("id");
    }
}
