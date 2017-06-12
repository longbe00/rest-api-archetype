package ${package}.core.mybatis.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import ${package}.core.annotation.DbValue;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum YesNoType {
    Y("Y"), N("N");

    private @Getter(onMethod = @__({@DbValue, @JsonValue}) ) String code;
}
